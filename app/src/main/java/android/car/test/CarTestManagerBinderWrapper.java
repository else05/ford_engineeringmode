package android.car.test;

import android.car.CarManagerBase;
import android.os.IBinder;

/* loaded from: classes2.dex */
public class CarTestManagerBinderWrapper implements CarManagerBase {
    public final IBinder binder;

    public CarTestManagerBinderWrapper(IBinder binder) {
        this.binder = binder;
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }
}
