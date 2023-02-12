package android.car;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarBluetooth extends IInterface {
    void clearBluetoothDeviceConnectionPriority(int i, int i2) throws RemoteException;

    String getDeviceNameWithPriority(int i, int i2) throws RemoteException;

    boolean isPriorityDevicePresent(int i, int i2) throws RemoteException;

    void setBluetoothDeviceConnectionPriority(BluetoothDevice bluetoothDevice, int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarBluetooth {
        private static final String DESCRIPTOR = "android.car.ICarBluetooth";
        static final int TRANSACTION_clearBluetoothDeviceConnectionPriority = 2;
        static final int TRANSACTION_getDeviceNameWithPriority = 4;
        static final int TRANSACTION_isPriorityDevicePresent = 3;
        static final int TRANSACTION_setBluetoothDeviceConnectionPriority = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBluetooth asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarBluetooth)) {
                return (ICarBluetooth) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothDevice _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    setBluetoothDeviceConnectionPriority(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg12 = data.readInt();
                    clearBluetoothDeviceConnectionPriority(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _arg13 = data.readInt();
                    boolean isPriorityDevicePresent = isPriorityDevicePresent(_arg03, _arg13);
                    reply.writeNoException();
                    reply.writeInt(isPriorityDevicePresent ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg14 = data.readInt();
                    String _result = getDeviceNameWithPriority(_arg04, _arg14);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarBluetooth {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.ICarBluetooth
            public void setBluetoothDeviceConnectionPriority(BluetoothDevice deviceToSet, int profileToSet, int priorityToSet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (deviceToSet != null) {
                        _data.writeInt(1);
                        deviceToSet.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(profileToSet);
                    _data.writeInt(priorityToSet);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetooth
            public void clearBluetoothDeviceConnectionPriority(int profileToClear, int priorityToClear) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profileToClear);
                    _data.writeInt(priorityToClear);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetooth
            public boolean isPriorityDevicePresent(int profile, int priorityToCheck) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    _data.writeInt(priorityToCheck);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetooth
            public String getDeviceNameWithPriority(int profile, int priorityToCheck) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    _data.writeInt(priorityToCheck);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
