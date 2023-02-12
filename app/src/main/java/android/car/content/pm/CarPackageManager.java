package android.car.content.pm;

import android.car.CarApiUtil;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.content.pm.ICarPackageManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class CarPackageManager implements CarManagerBase {
    public static final int FLAG_SET_POLICY_ADD = 2;
    public static final int FLAG_SET_POLICY_REMOVE = 4;
    public static final int FLAG_SET_POLICY_WAIT_FOR_CHANGE = 1;
    private final Context mContext;
    private final ICarPackageManager mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SetPolicyFlags {
    }

    public CarPackageManager(IBinder service, Context context) {
        this.mService = ICarPackageManager.Stub.asInterface(service);
        this.mContext = context;
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    public void setAppBlockingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) throws CarNotConnectedException, SecurityException, IllegalArgumentException {
        if ((flags & 1) != 0 && Looper.getMainLooper().isCurrentThread()) {
            throw new IllegalStateException("FLAG_SET_POLICY_WAIT_FOR_CHANGE cannot be used in main thread");
        }
        try {
            this.mService.setAppBlockingPolicy(packageName, policy, flags);
        } catch (RemoteException e) {
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public boolean isActivityBackedBySafeActivity(ComponentName activityName) throws CarNotConnectedException {
        try {
            return this.mService.isActivityBackedBySafeActivity(activityName);
        } catch (RemoteException e) {
            return true;
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return true;
        }
    }

    public boolean isActivityAllowedWhileDriving(String packageName, String className) throws CarNotConnectedException {
        try {
            return this.mService.isActivityAllowedWhileDriving(packageName, className);
        } catch (RemoteException e) {
            return false;
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }

    public boolean isServiceAllowedWhileDriving(String packageName, String className) throws CarNotConnectedException {
        try {
            return this.mService.isServiceAllowedWhileDriving(packageName, className);
        } catch (RemoteException e) {
            return false;
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return false;
        }
    }
}
