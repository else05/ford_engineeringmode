package android.car.cluster;

import android.graphics.Rect;
import android.os.Bundle;

/* loaded from: classes2.dex */
public class ClusterActivityState {
    private static final String KEY_EXTRAS = "android.car:activityState.extras";
    private static final String KEY_UNOBSCURED_BOUNDS = "android.car:activityState.unobscured";
    private static final String KEY_VISIBLE = "android.car:activityState.visible";
    private Bundle mExtras;
    private Rect mUnobscuredBounds;
    private boolean mVisible = true;

    public boolean isVisible() {
        return this.mVisible;
    }

    public Rect getUnobscuredBounds() {
        return this.mUnobscuredBounds;
    }

    public ClusterActivityState setVisible(boolean visible) {
        this.mVisible = visible;
        return this;
    }

    public ClusterActivityState setUnobscuredBounds(Rect unobscuredBounds) {
        this.mUnobscuredBounds = unobscuredBounds;
        return this;
    }

    public ClusterActivityState setExtras(Bundle bundle) {
        this.mExtras = bundle;
        return this;
    }

    private ClusterActivityState() {
    }

    public static ClusterActivityState create(boolean visible, Rect unobscuredBounds) {
        return new ClusterActivityState().setVisible(visible).setUnobscuredBounds(unobscuredBounds);
    }

    public static ClusterActivityState fromBundle(Bundle bundle) {
        return new ClusterActivityState().setVisible(bundle.getBoolean(KEY_VISIBLE, true)).setUnobscuredBounds((Rect) bundle.getParcelable(KEY_UNOBSCURED_BOUNDS)).setExtras(bundle.getBundle(KEY_EXTRAS));
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putBoolean(KEY_VISIBLE, this.mVisible);
        b.putParcelable(KEY_UNOBSCURED_BOUNDS, this.mUnobscuredBounds);
        b.putBundle(KEY_EXTRAS, this.mExtras);
        return b;
    }

    public String toString() {
        return getClass().getSimpleName() + " {visible: " + this.mVisible + ", unobscuredBounds: " + this.mUnobscuredBounds + " }";
    }
}
