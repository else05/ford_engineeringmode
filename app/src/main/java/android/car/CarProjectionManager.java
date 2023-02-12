package android.car;

import android.car.ICarProjection;
import android.car.ICarProjectionCallback;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class CarProjectionManager implements CarManagerBase {
    public static final int PROJECTION_LONG_PRESS_VOICE_SEARCH = 2;
    public static final int PROJECTION_VOICE_SEARCH = 1;
    private final ICarProjectionCallbackImpl mBinderListener = new ICarProjectionCallbackImpl();
    private final Handler mHandler;
    private CarProjectionListener mListener;
    private final ICarProjection mService;
    private int mVoiceSearchFilter;

    /* loaded from: classes2.dex */
    public interface CarProjectionListener {
        void onVoiceAssistantRequest(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarProjectionManager(IBinder service, Handler handler) {
        this.mService = ICarProjection.Stub.asInterface(service);
        this.mHandler = handler;
    }

    public void regsiterProjectionListener(CarProjectionListener listener, int voiceSearchFilter) throws CarNotConnectedException {
        registerProjectionListener(listener, voiceSearchFilter);
    }

    public void registerProjectionListener(CarProjectionListener listener, int voiceSearchFilter) throws CarNotConnectedException {
        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            if (this.mListener == null || this.mVoiceSearchFilter != voiceSearchFilter) {
                try {
                    this.mService.registerProjectionListener(this.mBinderListener, voiceSearchFilter);
                } catch (RemoteException e) {
                    throw new CarNotConnectedException(e);
                }
            }
            this.mListener = listener;
            this.mVoiceSearchFilter = voiceSearchFilter;
        }
    }

    public void unregsiterProjectionListener() {
        unregisterProjectionListener();
    }

    public void unregisterProjectionListener() {
        synchronized (this) {
            try {
                this.mService.unregisterProjectionListener(this.mBinderListener);
            } catch (RemoteException e) {
            }
            this.mListener = null;
            this.mVoiceSearchFilter = 0;
        }
    }

    public void registerProjectionRunner(Intent serviceIntent) throws CarNotConnectedException {
        if (serviceIntent == null) {
            throw new IllegalArgumentException("null serviceIntent");
        }
        synchronized (this) {
            try {
                try {
                    this.mService.registerProjectionRunner(serviceIntent);
                } catch (RemoteException e) {
                    throw new CarNotConnectedException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterProjectionRunner(Intent serviceIntent) {
        if (serviceIntent == null) {
            throw new IllegalArgumentException("null serviceIntent");
        }
        synchronized (this) {
            try {
                this.mService.unregisterProjectionRunner(serviceIntent);
            } catch (RemoteException e) {
            }
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVoiceAssistantRequest(boolean fromLongPress) {
        synchronized (this) {
            if (this.mListener == null) {
                return;
            }
            CarProjectionListener listener = this.mListener;
            listener.onVoiceAssistantRequest(fromLongPress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ICarProjectionCallbackImpl extends ICarProjectionCallback.Stub {
        private final WeakReference<CarProjectionManager> mManager;

        private ICarProjectionCallbackImpl(CarProjectionManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.ICarProjectionCallback
        public void onVoiceAssistantRequest(final boolean fromLongPress) {
            final CarProjectionManager manager = this.mManager.get();
            if (manager != null) {
                manager.mHandler.post(new Runnable() { // from class: android.car.CarProjectionManager.ICarProjectionCallbackImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        manager.handleVoiceAssistantRequest(fromLongPress);
                    }
                });
            }
        }
    }
}
