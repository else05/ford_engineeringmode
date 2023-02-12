package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;

/* loaded from: classes2.dex */
public interface GenericLifecycleObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
