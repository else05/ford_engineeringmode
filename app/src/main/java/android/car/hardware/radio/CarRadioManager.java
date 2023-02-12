package android.car.hardware.radio;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.radio.ICarRadio;
import android.car.hardware.radio.ICarRadioEventListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class CarRadioManager implements CarManagerBase {
    private static final boolean DBG = false;
    private static final int MSG_RADIO_EVENT = 0;
    private static final String TAG = "CarRadioManager";
    private int mCount;
    private final Handler mHandler;
    @GuardedBy("this")
    private CarRadioEventListener mListener = null;
    @GuardedBy("this")
    private CarRadioEventListenerToService mListenerToService = null;
    private final ICarRadio mService;

    /* loaded from: classes2.dex */
    public interface CarRadioEventListener {
        void onEvent(CarRadioEvent carRadioEvent);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        WeakReference<CarRadioManager> mMgr;

        EventCallbackHandler(CarRadioManager mgr, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                CarRadioManager mgr = this.mMgr.get();
                if (mgr != null) {
                    mgr.dispatchEventToClient((CarRadioEvent) msg.obj);
                    return;
                }
                return;
            }
            Log.e(CarRadioManager.TAG, "Event type not handled?" + msg);
        }
    }

    /* loaded from: classes2.dex */
    private static class CarRadioEventListenerToService extends ICarRadioEventListener.Stub {
        private final WeakReference<CarRadioManager> mManager;

        public CarRadioEventListenerToService(CarRadioManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.radio.ICarRadioEventListener
        public void onEvent(CarRadioEvent event) {
            CarRadioManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleEvent(event);
            }
        }
    }

    public CarRadioManager(IBinder service, Handler handler) throws CarNotConnectedException {
        this.mCount = 0;
        this.mService = ICarRadio.Stub.asInterface(service);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
        try {
            this.mCount = this.mService.getPresetCount();
        } catch (RemoteException ex) {
            Log.e(TAG, "Could not connect: " + ex.toString());
            throw new CarNotConnectedException(ex);
        }
    }

    public synchronized void registerListener(CarRadioEventListener listener) throws CarNotConnectedException {
        if (this.mListener != null) {
            throw new IllegalStateException("Listener already registered. Did you call registerListener() twice?");
        }
        this.mListener = listener;
        try {
            this.mListenerToService = new CarRadioEventListenerToService(this);
            this.mService.registerListener(this.mListenerToService);
        } catch (RemoteException ex) {
            Log.e(TAG, "Could not connect: " + ex.toString());
            throw new CarNotConnectedException(ex);
        } catch (IllegalStateException ex2) {
            Car.checkCarNotConnectedExceptionFromCarService(ex2);
        }
    }

    public synchronized void unregisterListener() {
        try {
            this.mService.unregisterListener(this.mListenerToService);
        } catch (RemoteException ex) {
            Log.e(TAG, "Could not connect: " + ex.toString());
        }
        this.mListenerToService = null;
        this.mListener = null;
    }

    public int getPresetCount() throws CarNotConnectedException {
        return this.mCount;
    }

    public CarRadioPreset getPreset(int presetNumber) throws CarNotConnectedException {
        try {
            CarRadioPreset preset = this.mService.getPreset(presetNumber);
            return preset;
        } catch (RemoteException ex) {
            Log.e(TAG, "getPreset failed with " + ex.toString());
            throw new CarNotConnectedException(ex);
        }
    }

    public boolean setPreset(CarRadioPreset preset) throws IllegalArgumentException, CarNotConnectedException {
        try {
            return this.mService.setPreset(preset);
        } catch (RemoteException ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEventToClient(CarRadioEvent event) {
        CarRadioEventListener listener;
        synchronized (this) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onEvent(event);
        } else {
            Log.e(TAG, "Listener died, not dispatching event.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(CarRadioEvent event) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(0, event));
    }

    @Override // android.car.CarManagerBase
    public synchronized void onCarDisconnected() {
        this.mListener = null;
        this.mListenerToService = null;
    }
}
