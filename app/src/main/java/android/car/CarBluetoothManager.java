package android.car;

import android.bluetooth.BluetoothDevice;
import android.car.ICarBluetooth;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class CarBluetoothManager implements CarManagerBase {
    public static final int BLUETOOTH_DEVICE_CONNECTION_PRIORITY_0 = 0;
    public static final int BLUETOOTH_DEVICE_CONNECTION_PRIORITY_1 = 1;
    public static final String BLUETOOTH_NO_PRIORITY_DEVICE = "";
    private static final String TAG = "CarBluetoothManager";
    private final Context mContext;
    private final ICarBluetooth mService;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PriorityType {
    }

    public void setBluetoothDeviceConnectionPriority(BluetoothDevice deviceToSet, int profileToSet, int priorityToSet) throws CarNotConnectedException {
        try {
            this.mService.setBluetoothDeviceConnectionPriority(deviceToSet, profileToSet, priorityToSet);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "setBluetoothDeviceConnectionPriority failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public void clearBluetoothDeviceConnectionPriority(int profileToClear, int priorityToClear) throws CarNotConnectedException {
        try {
            this.mService.clearBluetoothDeviceConnectionPriority(profileToClear, priorityToClear);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "clearBluetoothDeviceConnectionPriority failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public boolean isPriorityDevicePresent(int profile, int priorityToCheck) throws CarNotConnectedException {
        try {
            return this.mService.isPriorityDevicePresent(profile, priorityToCheck);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "isPrioritySet failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public String getDeviceNameWithPriority(int profile, int priorityToCheck) throws CarNotConnectedException {
        try {
            return this.mService.getDeviceNameWithPriority(profile, priorityToCheck);
        } catch (RemoteException e) {
            Log.e(CarLibLog.TAG_CAR, "getDeviceNameWithPriority failed", e);
            throw new CarNotConnectedException(e);
        }
    }

    public CarBluetoothManager(IBinder service, Context context) {
        this.mContext = context;
        this.mService = ICarBluetooth.Stub.asInterface(service);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }
}
