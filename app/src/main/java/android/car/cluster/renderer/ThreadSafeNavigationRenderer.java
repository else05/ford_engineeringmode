package android.car.cluster.renderer;

import android.car.navigation.CarNavigationInstrumentCluster;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ThreadSafeNavigationRenderer extends NavigationRenderer {
    private static final int MSG_EVENT = 5;
    private static final int MSG_NAV_NEXT_TURN = 3;
    private static final int MSG_NAV_NEXT_TURN_DISTANCE = 4;
    private static final int MSG_NAV_START = 1;
    private static final int MSG_NAV_STOP = 2;
    private final Handler mHandler;
    private final NavigationRenderer mRenderer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NavigationRenderer createFor(Looper looper, NavigationRenderer renderer) {
        if (renderer == null) {
            return null;
        }
        return new ThreadSafeNavigationRenderer(looper, renderer);
    }

    private ThreadSafeNavigationRenderer(Looper looper, NavigationRenderer renderer) {
        this.mRenderer = renderer;
        this.mHandler = new NavigationRendererHandler(looper, renderer);
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public CarNavigationInstrumentCluster getNavigationProperties() {
        if (this.mHandler.getLooper() == Looper.myLooper()) {
            return this.mRenderer.getNavigationProperties();
        }
        return (CarNavigationInstrumentCluster) runAndWaitResult(this.mHandler, new RunnableWithResult<CarNavigationInstrumentCluster>() { // from class: android.car.cluster.renderer.ThreadSafeNavigationRenderer.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.car.cluster.renderer.ThreadSafeNavigationRenderer.RunnableWithResult
            public CarNavigationInstrumentCluster createResult() {
                return ThreadSafeNavigationRenderer.this.mRenderer.getNavigationProperties();
            }
        });
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onStartNavigation() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onStopNavigation() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onNextTurnChanged(int event, CharSequence eventName, int turnAngle, int turnNumber, Bitmap image, int turnSide) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new NextTurn(event, eventName, turnAngle, turnNumber, image, turnSide)));
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onNextTurnDistanceChanged(int distanceMeters, int timeSeconds, int displayDistanceMillis, int displayDistanceUnit) {
        ManeuverDistance distance = new ManeuverDistance(distanceMeters, timeSeconds, displayDistanceMillis, displayDistanceUnit);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, distance));
    }

    @Override // android.car.cluster.renderer.NavigationRenderer
    public void onEvent(int eventType, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, eventType, 0, bundle));
    }

    /* loaded from: classes2.dex */
    private static class NavigationRendererHandler extends RendererHandler<NavigationRenderer> {
        NavigationRendererHandler(Looper looper, NavigationRenderer renderer) {
            super(looper, renderer);
        }

        @Override // android.car.cluster.renderer.ThreadSafeNavigationRenderer.RendererHandler
        public void handleMessage(Message msg, NavigationRenderer renderer) {
            switch (msg.what) {
                case 1:
                    renderer.onStartNavigation();
                    return;
                case 2:
                    renderer.onStopNavigation();
                    return;
                case 3:
                    NextTurn nt = (NextTurn) msg.obj;
                    renderer.onNextTurnChanged(nt.event, nt.eventName, nt.turnAngle, nt.turnNumber, nt.bitmap, nt.turnSide);
                    return;
                case 4:
                    ManeuverDistance d = (ManeuverDistance) msg.obj;
                    renderer.onNextTurnDistanceChanged(d.meters, d.seconds, d.displayDistanceMillis, d.displayDistanceUnit);
                    return;
                case 5:
                    Bundle bundle = (Bundle) msg.obj;
                    renderer.onEvent(msg.arg1, bundle);
                    return;
                default:
                    throw new IllegalArgumentException("Msg: " + msg.what);
            }
        }
    }

    private static <E> E runAndWaitResult(Handler handler, final RunnableWithResult<E> runnable) {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable wrappedRunnable = new Runnable() { // from class: android.car.cluster.renderer.ThreadSafeNavigationRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                RunnableWithResult.this.run();
                latch.countDown();
            }
        };
        handler.post(wrappedRunnable);
        try {
            latch.await();
            return runnable.getResult();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NextTurn {
        private final Bitmap bitmap;
        private final int event;
        private final CharSequence eventName;
        private final int turnAngle;
        private final int turnNumber;
        private final int turnSide;

        NextTurn(int event, CharSequence eventName, int turnAngle, int turnNumber, Bitmap bitmap, int turnSide) {
            this.event = event;
            this.eventName = eventName;
            this.turnAngle = turnAngle;
            this.turnNumber = turnNumber;
            this.bitmap = bitmap;
            this.turnSide = turnSide;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class RunnableWithResult<T> implements Runnable {
        private volatile T result;

        protected abstract T createResult();

        private RunnableWithResult() {
        }

        @Override // java.lang.Runnable
        public void run() {
            this.result = createResult();
        }

        public T getResult() {
            return this.result;
        }
    }

    /* loaded from: classes2.dex */
    private static abstract class RendererHandler<T> extends Handler {
        private final WeakReference<T> mRendererRef;

        public abstract void handleMessage(Message message, T t);

        RendererHandler(Looper looper, T renderer) {
            super(looper);
            this.mRendererRef = new WeakReference<>(renderer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            T renderer = this.mRendererRef.get();
            if (renderer != null) {
                handleMessage(msg, renderer);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ManeuverDistance {
        final int displayDistanceMillis;
        final int displayDistanceUnit;
        final int meters;
        final int seconds;

        ManeuverDistance(int meters, int seconds, int displayDistanceMillis, int displayDistanceUnit) {
            this.meters = meters;
            this.seconds = seconds;
            this.displayDistanceMillis = displayDistanceMillis;
            this.displayDistanceUnit = displayDistanceUnit;
        }
    }
}
