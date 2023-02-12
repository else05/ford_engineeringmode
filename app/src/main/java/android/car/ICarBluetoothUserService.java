package android.car;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarBluetoothUserService extends IInterface {
    void bluetoothConnectToProfile(int i, BluetoothDevice bluetoothDevice) throws RemoteException;

    void closeBluetoothConnectionProxy() throws RemoteException;

    boolean isBluetoothConnectionProxyAvailable(int i) throws RemoteException;

    void setProfilePriority(int i, BluetoothDevice bluetoothDevice, int i2) throws RemoteException;

    void setupBluetoothConnectionProxy() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarBluetoothUserService {
        private static final String DESCRIPTOR = "android.car.ICarBluetoothUserService";
        static final int TRANSACTION_bluetoothConnectToProfile = 4;
        static final int TRANSACTION_closeBluetoothConnectionProxy = 2;
        static final int TRANSACTION_isBluetoothConnectionProxyAvailable = 3;
        static final int TRANSACTION_setProfilePriority = 5;
        static final int TRANSACTION_setupBluetoothConnectionProxy = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBluetoothUserService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarBluetoothUserService)) {
                return (ICarBluetoothUserService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BluetoothDevice _arg1;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    setupBluetoothConnectionProxy();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    closeBluetoothConnectionProxy();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    boolean isBluetoothConnectionProxyAvailable = isBluetoothConnectionProxyAvailable(_arg0);
                    reply.writeNoException();
                    reply.writeInt(isBluetoothConnectionProxyAvailable ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    _arg1 = data.readInt() != 0 ? (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data) : null;
                    bluetoothConnectToProfile(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    _arg1 = data.readInt() != 0 ? (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data) : null;
                    int _arg2 = data.readInt();
                    setProfilePriority(_arg03, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarBluetoothUserService {
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

            @Override // android.car.ICarBluetoothUserService
            public void setupBluetoothConnectionProxy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public void closeBluetoothConnectionProxy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public boolean isBluetoothConnectionProxyAvailable(int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public void bluetoothConnectToProfile(int profile, BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICarBluetoothUserService
            public void setProfilePriority(int profile, BluetoothDevice device, int priority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(profile);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(priority);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
