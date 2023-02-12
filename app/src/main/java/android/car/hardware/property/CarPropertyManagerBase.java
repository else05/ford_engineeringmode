package android.car.hardware.property;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.ICarProperty;
import android.car.hardware.property.ICarPropertyEventListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes2.dex */
public class CarPropertyManagerBase {
    @GuardedBy("mLock")
    private CarPropertyEventCallback mCallback;
    private final boolean mDbg;
    private final Handler mHandler;
    @GuardedBy("mLock")
    private ICarPropertyEventListener mListenerToService;
    private final Object mLock = new Object();
    private final ICarProperty mService;
    private final String mTag;

    /* loaded from: classes2.dex */
    public interface CarPropertyEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    /* loaded from: classes2.dex */
    private static final class EventCallbackHandler extends Handler {
        private static final int MSG_GENERIC_EVENT = 0;
        private final WeakReference<CarPropertyManagerBase> mMgr;

        EventCallbackHandler(CarPropertyManagerBase mgr, Looper looper) {
            super(looper);
            this.mMgr = new WeakReference<>(mgr);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                CarPropertyManagerBase mgr = this.mMgr.get();
                if (mgr != null) {
                    mgr.dispatchEventToClient((CarPropertyEvent) msg.obj);
                    return;
                }
                return;
            }
            Log.e("EventtCallbackHandler", "Event type not handled:  " + msg);
        }
    }

    public CarPropertyManagerBase(IBinder service, Handler handler, boolean dbg, String tag) {
        this.mDbg = dbg;
        this.mTag = tag;
        this.mService = ICarProperty.Stub.asInterface(service);
        this.mHandler = new EventCallbackHandler(this, handler.getLooper());
    }

    public void registerCallback(CarPropertyEventCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallback != null) {
                throw new IllegalStateException("Callback is already registered.");
            }
            this.mCallback = callback;
            this.mListenerToService = new ICarPropertyEventListener.Stub() { // from class: android.car.hardware.property.CarPropertyManagerBase.1
                @Override // android.car.hardware.property.ICarPropertyEventListener
                public void onEvent(CarPropertyEvent event) throws RemoteException {
                    CarPropertyManagerBase.this.handleEvent(event);
                }
            };
        }
        try {
            this.mService.registerListener(this.mListenerToService);
        } catch (RemoteException ex) {
            Log.e(this.mTag, "Could not connect: ", ex);
            throw new CarNotConnectedException(ex);
        } catch (IllegalStateException ex2) {
            Car.checkCarNotConnectedExceptionFromCarService(ex2);
        }
    }

    public void unregisterCallback() {
        ICarPropertyEventListener listenerToService;
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
            this.mCallback = null;
            this.mListenerToService = null;
        }
        if (listenerToService == null) {
            Log.w(this.mTag, "unregisterListener: listener was not registered");
            return;
        }
        try {
            this.mService.unregisterListener(listenerToService);
        } catch (RemoteException ex) {
            Log.e(this.mTag, "Failed to unregister listener", ex);
        } catch (IllegalStateException ex2) {
            Car.hideCarNotConnectedExceptionFromCarService(ex2);
        }
    }

    public List<CarPropertyConfig> getPropertyList() throws CarNotConnectedException {
        try {
            return this.mService.getPropertyList();
        } catch (RemoteException e) {
            Log.e(this.mTag, "Exception in getPropertyList", e);
            throw new CarNotConnectedException(e);
        }
    }

    public boolean getBooleanProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Boolean> carProp = getProperty(Boolean.class, prop, area);
        if (carProp != null) {
            return carProp.getValue().booleanValue();
        }
        return false;
    }

    public float getFloatProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Float> carProp = getProperty(Float.class, prop, area);
        if (carProp != null) {
            return carProp.getValue().floatValue();
        }
        return 0.0f;
    }

    public int getIntProperty(int prop, int area) throws CarNotConnectedException {
        CarPropertyValue<Integer> carProp = getProperty(Integer.class, prop, area);
        if (carProp != null) {
            return carProp.getValue().intValue();
        }
        return 0;
    }

    public <E> CarPropertyValue<E> getProperty(Class<E> clazz, int propId, int area) throws CarNotConnectedException {
        Class<?> actualClass;
        try {
            CarPropertyValue<E> propVal = this.mService.getProperty(propId, area);
            if (propVal != null && propVal.getValue() != null && (actualClass = propVal.getValue().getClass()) != clazz) {
                throw new IllegalArgumentException("Invalid property type. Expected: " + clazz + ", but was: " + actualClass);
            }
            return propVal;
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "getProperty failed with " + e.toString() + ", propId: 0x" + Integer.toHexString(propId) + ", area: 0x" + Integer.toHexString(area), e);
            throw new CarNotConnectedException(e);
        }
    }

    public <E> void setProperty(Class<E> clazz, int propId, int area, E val) throws CarNotConnectedException {
        try {
            this.mService.setProperty(new CarPropertyValue(propId, area, val));
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "setProperty failed with " + e.toString(), e);
            throw new CarNotConnectedException(e);
        }
    }

    public void setBooleanProperty(int prop, int area, boolean val) throws CarNotConnectedException {
        setProperty(Boolean.class, prop, area, Boolean.valueOf(val));
    }

    public void setFloatProperty(int prop, int area, float val) throws CarNotConnectedException {
        setProperty(Float.class, prop, area, Float.valueOf(val));
    }

    public void setIntProperty(int prop, int area, int val) throws CarNotConnectedException {
        setProperty(Integer.class, prop, area, Integer.valueOf(val));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEventToClient(CarPropertyEvent event) {
        CarPropertyEventCallback listener;
        synchronized (this.mLock) {
            listener = this.mCallback;
        }
        if (listener == null) {
            Log.e(this.mTag + "CarPropertyManagerBase", "Listener died, not dispatching event.");
            return;
        }
        CarPropertyValue propVal = event.getCarPropertyValue();
        switch (event.getEventType()) {
            case 0:
                listener.onChangeEvent(propVal);
                return;
            case 1:
                listener.onErrorEvent(propVal.getPropertyId(), propVal.getAreaId());
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(CarPropertyEvent event) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(0, event));
    }

    public void onCarDisconnected() {
        ICarPropertyEventListener listenerToService;
        synchronized (this.mLock) {
            listenerToService = this.mListenerToService;
        }
        if (listenerToService != null) {
            unregisterCallback();
        }
    }
}
