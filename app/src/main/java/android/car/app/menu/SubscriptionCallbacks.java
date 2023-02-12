package android.car.app.menu;

import android.os.Bundle;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class SubscriptionCallbacks {
    public abstract void onChildChanged(String str, Bundle bundle);

    public abstract void onChildrenLoaded(String str, List<Bundle> list);

    public abstract void onError(String str);
}
