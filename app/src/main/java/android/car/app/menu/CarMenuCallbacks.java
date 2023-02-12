package android.car.app.menu;

import android.os.Bundle;

/* loaded from: classes2.dex */
public abstract class CarMenuCallbacks {
    public abstract RootMenu getRootMenu(Bundle bundle);

    public abstract void onCarMenuClosed();

    public abstract void onCarMenuClosing();

    public abstract void onCarMenuOpened();

    public abstract void onCarMenuOpening();

    public abstract void onItemClicked(String str);

    public abstract boolean onItemLongClicked(String str);

    public abstract boolean onMenuClicked();

    public abstract void subscribe(String str, SubscriptionCallbacks subscriptionCallbacks);

    public abstract void unsubscribe(String str, SubscriptionCallbacks subscriptionCallbacks);
}
