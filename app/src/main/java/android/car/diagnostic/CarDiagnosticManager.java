package android.car.diagnostic;

import android.car.Car;
import android.car.CarApiUtil;
import android.car.CarLibLog;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.diagnostic.ICarDiagnostic;
import android.car.diagnostic.ICarDiagnosticEventListener;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.internal.CarPermission;
import com.android.car.internal.CarRatedListeners;
import com.android.car.internal.SingleMessageHandler;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public final class CarDiagnosticManager implements CarManagerBase {
    public static final int[] FRAME_TYPES = {0, 1};
    public static final int FRAME_TYPE_FREEZE = 1;
    public static final int FRAME_TYPE_LIVE = 0;
    private static final int MSG_DIAGNOSTIC_EVENTS = 0;
    private final SparseArray<CarDiagnosticListeners> mActiveListeners = new SparseArray<>();
    private final SingleMessageHandler<CarDiagnosticEvent> mHandlerCallback;
    private CarDiagnosticEventListenerToService mListenerToService;
    private final ICarDiagnostic mService;
    private final CarPermission mVendorExtensionPermission;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FrameType {
    }

    /* loaded from: classes2.dex */
    public interface OnDiagnosticEventListener {
        void onDiagnosticEvent(CarDiagnosticEvent carDiagnosticEvent);
    }

    public CarDiagnosticManager(IBinder service, Context context, Handler handler) {
        this.mService = ICarDiagnostic.Stub.asInterface(service);
        this.mHandlerCallback = new SingleMessageHandler<CarDiagnosticEvent>(handler.getLooper(), 0) { // from class: android.car.diagnostic.CarDiagnosticManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.android.car.internal.SingleMessageHandler
            public void handleEvent(CarDiagnosticEvent event) {
                CarDiagnosticListeners listeners;
                synchronized (CarDiagnosticManager.this.mActiveListeners) {
                    listeners = (CarDiagnosticListeners) CarDiagnosticManager.this.mActiveListeners.get(event.frameType);
                }
                if (listeners != null) {
                    listeners.onDiagnosticEvent(event);
                }
            }
        };
        this.mVendorExtensionPermission = new CarPermission(context, Car.PERMISSION_VENDOR_EXTENSION);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this.mActiveListeners) {
            this.mActiveListeners.clear();
            this.mListenerToService = null;
        }
    }

    private void assertFrameType(int frameType) {
        switch (frameType) {
            case 0:
            case 1:
                return;
            default:
                throw new IllegalArgumentException(String.format("%d is not a valid diagnostic frame type", Integer.valueOf(frameType)));
        }
    }

    public boolean registerListener(OnDiagnosticEventListener listener, int frameType, int rate) throws CarNotConnectedException, IllegalArgumentException {
        assertFrameType(frameType);
        synchronized (this.mActiveListeners) {
            if (this.mListenerToService == null) {
                this.mListenerToService = new CarDiagnosticEventListenerToService(this);
            }
            boolean needsServerUpdate = false;
            CarDiagnosticListeners listeners = this.mActiveListeners.get(frameType);
            if (listeners == null) {
                listeners = new CarDiagnosticListeners(rate);
                this.mActiveListeners.put(frameType, listeners);
                needsServerUpdate = true;
            }
            if (listeners.addAndUpdateRate(listener, rate)) {
                needsServerUpdate = true;
            }
            if (needsServerUpdate && !registerOrUpdateDiagnosticListener(frameType, rate)) {
                return false;
            }
            return true;
        }
    }

    public void unregisterListener(OnDiagnosticEventListener listener) {
        int[] iArr;
        synchronized (this.mActiveListeners) {
            for (int frameType : FRAME_TYPES) {
                doUnregisterListenerLocked(listener, frameType);
            }
        }
    }

    private void doUnregisterListenerLocked(OnDiagnosticEventListener listener, int frameType) {
        CarDiagnosticListeners listeners = this.mActiveListeners.get(frameType);
        if (listeners != null) {
            boolean needsServerUpdate = false;
            if (listeners.contains(listener)) {
                needsServerUpdate = listeners.remove(listener);
            }
            if (listeners.isEmpty()) {
                try {
                    this.mService.unregisterDiagnosticListener(frameType, this.mListenerToService);
                } catch (RemoteException e) {
                }
                this.mActiveListeners.remove(frameType);
            } else if (needsServerUpdate) {
                try {
                    registerOrUpdateDiagnosticListener(frameType, listeners.getRate());
                } catch (CarNotConnectedException e2) {
                }
            }
        }
    }

    private boolean registerOrUpdateDiagnosticListener(int frameType, int rate) throws CarNotConnectedException {
        try {
            return this.mService.registerOrUpdateDiagnosticListener(frameType, rate, this.mListenerToService);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public CarDiagnosticEvent getLatestLiveFrame() throws CarNotConnectedException {
        try {
            return this.mService.getLatestLiveFrame();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return null;
        }
    }

    public long[] getFreezeFrameTimestamps() throws CarNotConnectedException {
        try {
            return this.mService.getFreezeFrameTimestamps();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return new long[0];
        }
    }

    public CarDiagnosticEvent getFreezeFrame(long timestamp) throws CarNotConnectedException {
        try {
            return this.mService.getFreezeFrame(timestamp);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return null;
        }
    }

    public boolean clearFreezeFrames(long... timestamps) throws CarNotConnectedException {
        try {
            return this.mService.clearFreezeFrames(timestamps);
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public boolean isLiveFrameSupported() throws CarNotConnectedException {
        try {
            return this.mService.isLiveFrameSupported();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public boolean isFreezeFrameNotificationSupported() throws CarNotConnectedException {
        try {
            return this.mService.isFreezeFrameNotificationSupported();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public boolean isGetFreezeFrameSupported() throws CarNotConnectedException {
        try {
            return this.mService.isGetFreezeFrameSupported();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public boolean isClearFreezeFramesSupported() throws CarNotConnectedException {
        try {
            return this.mService.isClearFreezeFramesSupported();
        } catch (RemoteException e) {
            throw new CarNotConnectedException();
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CarDiagnosticEventListenerToService extends ICarDiagnosticEventListener.Stub {
        private final WeakReference<CarDiagnosticManager> mManager;

        public CarDiagnosticEventListenerToService(CarDiagnosticManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        private void handleOnDiagnosticEvents(CarDiagnosticManager manager, List<CarDiagnosticEvent> events) {
            manager.mHandlerCallback.sendEvents(events);
        }

        @Override // android.car.diagnostic.ICarDiagnosticEventListener
        public void onDiagnosticEvents(List<CarDiagnosticEvent> events) {
            CarDiagnosticManager manager = this.mManager.get();
            if (manager != null) {
                handleOnDiagnosticEvents(manager, events);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CarDiagnosticListeners extends CarRatedListeners<OnDiagnosticEventListener> {
        CarDiagnosticListeners(int rate) {
            super(rate);
        }

        void onDiagnosticEvent(CarDiagnosticEvent event) {
            List<OnDiagnosticEventListener> listeners;
            long updateTime = event.timestamp;
            if (updateTime < this.mLastUpdateTime) {
                Log.w(CarLibLog.TAG_DIAGNOSTIC, "dropping old data");
                return;
            }
            this.mLastUpdateTime = updateTime;
            boolean hasVendorExtensionPermission = CarDiagnosticManager.this.mVendorExtensionPermission.checkGranted();
            final CarDiagnosticEvent eventToDispatch = hasVendorExtensionPermission ? event : event.withVendorSensorsRemoved();
            synchronized (CarDiagnosticManager.this.mActiveListeners) {
                listeners = new ArrayList<>(getListeners());
            }
            listeners.forEach(new Consumer<OnDiagnosticEventListener>() { // from class: android.car.diagnostic.CarDiagnosticManager.CarDiagnosticListeners.1
                @Override // java.util.function.Consumer
                public void accept(OnDiagnosticEventListener listener) {
                    listener.onDiagnosticEvent(eventToDispatch);
                }
            });
        }
    }
}
