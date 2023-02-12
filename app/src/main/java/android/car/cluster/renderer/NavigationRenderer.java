package android.car.cluster.renderer;

import android.car.navigation.CarNavigationInstrumentCluster;
import android.graphics.Bitmap;
import android.os.Bundle;

/* loaded from: classes2.dex */
public abstract class NavigationRenderer {
    public abstract CarNavigationInstrumentCluster getNavigationProperties();

    public abstract void onNextTurnChanged(int i, CharSequence charSequence, int i2, int i3, Bitmap bitmap, int i4);

    public abstract void onNextTurnDistanceChanged(int i, int i2, int i3, int i4);

    public abstract void onStartNavigation();

    public abstract void onStopNavigation();

    public void onEvent(int eventType, Bundle bundle) {
    }
}
