package android.car.cluster.renderer;

import android.app.ActivityOptions;
import android.app.Service;
import android.car.CarNotConnectedException;
import android.car.cluster.renderer.IInstrumentCluster;
import android.car.cluster.renderer.IInstrumentClusterCallback;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.car.navigation.CarNavigationInstrumentCluster;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import com.android.internal.annotations.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public abstract class InstrumentClusterRenderingService extends Service {
    public static final String EXTRA_KEY_CALLBACK_SERVICE = "android.car.cluster.IInstrumentClusterCallback";
    private static final String TAG = "CAR.L.CLUSTER";
    @GuardedBy("mLock")
    private IInstrumentClusterCallback mCallback;
    private final Object mLock = new Object();
    private RendererBinder mRendererBinder;

    protected abstract NavigationRenderer getNavigationRenderer();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (Log.isLoggable("CAR.L.CLUSTER", 3)) {
            Log.d("CAR.L.CLUSTER", "onBind, intent: " + intent);
        }
        if (intent.getExtras().containsKey(EXTRA_KEY_CALLBACK_SERVICE)) {
            IBinder callbackBinder = intent.getExtras().getBinder(EXTRA_KEY_CALLBACK_SERVICE);
            synchronized (this.mLock) {
                this.mCallback = IInstrumentClusterCallback.Stub.asInterface(callbackBinder);
            }
        } else {
            Log.w("CAR.L.CLUSTER", "onBind, no callback in extra!");
        }
        if (this.mRendererBinder == null) {
            this.mRendererBinder = new RendererBinder(getNavigationRenderer());
        }
        return this.mRendererBinder;
    }

    protected void onKeyEvent(KeyEvent keyEvent) {
    }

    public void setClusterActivityLaunchOptions(String category, ActivityOptions activityOptions) throws CarNotConnectedException {
        IInstrumentClusterCallback cb;
        synchronized (this.mLock) {
            cb = this.mCallback;
        }
        if (cb == null) {
            throw new CarNotConnectedException();
        }
        try {
            cb.setClusterActivityLaunchOptions(category, activityOptions.toBundle());
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        }
    }

    public void setClusterActivityState(String category, Bundle state) throws CarNotConnectedException {
        IInstrumentClusterCallback cb;
        synchronized (this.mLock) {
            cb = this.mCallback;
        }
        if (cb == null) {
            throw new CarNotConnectedException();
        }
        try {
            cb.setClusterActivityState(category, state);
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        }
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        IInstrumentClusterCallback cb;
        writer.println("**" + getClass().getSimpleName() + "**");
        StringBuilder sb = new StringBuilder();
        sb.append("renderer binder: ");
        sb.append(this.mRendererBinder);
        writer.println(sb.toString());
        if (this.mRendererBinder != null) {
            writer.println("navigation renderer: " + this.mRendererBinder.mNavigationRenderer);
            String owner = "none";
            synchronized (this.mLock) {
                if (this.mRendererBinder.mNavContextOwner != null) {
                    owner = "[uid: " + this.mRendererBinder.mNavContextOwner.first + ", pid: " + this.mRendererBinder.mNavContextOwner.second + "]";
                }
            }
            writer.println("navigation focus owner: " + owner);
        }
        synchronized (this.mLock) {
            cb = this.mCallback;
        }
        writer.println("callback: " + cb);
    }

    /* loaded from: classes2.dex */
    private class RendererBinder extends IInstrumentCluster.Stub {
        @GuardedBy("mLock")
        private Pair<Integer, Integer> mNavContextOwner;
        @GuardedBy("mLock")
        private NavigationBinder mNavigationBinder;
        private final NavigationRenderer mNavigationRenderer;
        private final UiHandler mUiHandler;

        RendererBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = navigationRenderer;
            this.mUiHandler = new UiHandler(InstrumentClusterRenderingService.this);
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public IInstrumentClusterNavigation getNavigationService() throws RemoteException {
            NavigationBinder navigationBinder;
            synchronized (InstrumentClusterRenderingService.this.mLock) {
                if (this.mNavigationBinder == null) {
                    this.mNavigationBinder = new NavigationBinder(this.mNavigationRenderer);
                    if (this.mNavContextOwner != null) {
                        this.mNavigationBinder.setNavigationContextOwner(((Integer) this.mNavContextOwner.first).intValue(), ((Integer) this.mNavContextOwner.second).intValue());
                    }
                }
                navigationBinder = this.mNavigationBinder;
            }
            return navigationBinder;
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void setNavigationContextOwner(int uid, int pid) throws RemoteException {
            synchronized (InstrumentClusterRenderingService.this.mLock) {
                this.mNavContextOwner = new Pair<>(Integer.valueOf(uid), Integer.valueOf(pid));
                if (this.mNavigationBinder != null) {
                    this.mNavigationBinder.setNavigationContextOwner(uid, pid);
                }
            }
        }

        @Override // android.car.cluster.renderer.IInstrumentCluster
        public void onKeyEvent(KeyEvent keyEvent) throws RemoteException {
            this.mUiHandler.doKeyEvent(keyEvent);
        }
    }

    /* loaded from: classes2.dex */
    private class NavigationBinder extends IInstrumentClusterNavigation.Stub {
        private volatile Pair<Integer, Integer> mNavContextOwner;
        private final NavigationRenderer mNavigationRenderer;

        NavigationBinder(NavigationRenderer navigationRenderer) {
            this.mNavigationRenderer = ThreadSafeNavigationRenderer.createFor(Looper.getMainLooper(), navigationRenderer);
        }

        void setNavigationContextOwner(int uid, int pid) {
            this.mNavContextOwner = new Pair<>(Integer.valueOf(uid), Integer.valueOf(pid));
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onStartNavigation() throws RemoteException {
            assertContextOwnership();
            this.mNavigationRenderer.onStartNavigation();
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onStopNavigation() throws RemoteException {
            assertContextOwnership();
            this.mNavigationRenderer.onStopNavigation();
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onNextManeuverChanged(int event, CharSequence eventName, int turnAngle, int turnNumber, Bitmap image, int turnSide) throws RemoteException {
            assertContextOwnership();
            this.mNavigationRenderer.onNextTurnChanged(event, eventName, turnAngle, turnNumber, image, turnSide);
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onNextManeuverDistanceChanged(int distanceMeters, int timeSeconds, int displayDistanceMillis, int displayDistanceUnit) throws RemoteException {
            assertContextOwnership();
            this.mNavigationRenderer.onNextTurnDistanceChanged(distanceMeters, timeSeconds, displayDistanceMillis, displayDistanceUnit);
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public void onEvent(int eventType, Bundle bundle) throws RemoteException {
            assertContextOwnership();
            this.mNavigationRenderer.onEvent(eventType, bundle);
        }

        @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
        public CarNavigationInstrumentCluster getInstrumentClusterInfo() throws RemoteException {
            return this.mNavigationRenderer.getNavigationProperties();
        }

        private void assertContextOwnership() {
            int uid = getCallingUid();
            int pid = getCallingPid();
            Pair<Integer, Integer> owner = this.mNavContextOwner;
            if (owner == null || ((Integer) owner.first).intValue() != uid || ((Integer) owner.second).intValue() != pid) {
                throw new IllegalStateException("Client (uid:" + uid + ", pid: " + pid + ") isnot an owner of APP_CONTEXT_NAVIGATION");
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class UiHandler extends Handler {
        private static int KEY_EVENT = 0;
        private final WeakReference<InstrumentClusterRenderingService> mRefService;

        UiHandler(InstrumentClusterRenderingService service) {
            this.mRefService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            InstrumentClusterRenderingService service = this.mRefService.get();
            if (service == null) {
                return;
            }
            if (msg.what == KEY_EVENT) {
                service.onKeyEvent((KeyEvent) msg.obj);
                return;
            }
            throw new IllegalArgumentException("Unexpected message: " + msg);
        }

        void doKeyEvent(KeyEvent event) {
            sendMessage(obtainMessage(KEY_EVENT, event));
        }
    }
}
