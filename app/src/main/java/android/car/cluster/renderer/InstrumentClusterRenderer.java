package android.car.cluster.renderer;

import android.content.Context;

/* loaded from: classes2.dex */
public abstract class InstrumentClusterRenderer {
    private NavigationRenderer mNavigationRenderer;

    protected abstract NavigationRenderer createNavigationRenderer();

    public abstract void onCreate(Context context);

    public abstract void onStart();

    public abstract void onStop();

    public synchronized NavigationRenderer getNavigationRenderer() {
        return this.mNavigationRenderer;
    }

    public final synchronized void initialize() {
        this.mNavigationRenderer = createNavigationRenderer();
    }
}
