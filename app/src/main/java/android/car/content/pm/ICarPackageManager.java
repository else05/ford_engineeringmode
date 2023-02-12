package android.car.content.pm;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarPackageManager extends IInterface {
    boolean isActivityAllowedWhileDriving(String str, String str2) throws RemoteException;

    boolean isActivityBackedBySafeActivity(ComponentName componentName) throws RemoteException;

    boolean isServiceAllowedWhileDriving(String str, String str2) throws RemoteException;

    void setAppBlockingPolicy(String str, CarAppBlockingPolicy carAppBlockingPolicy, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarPackageManager {
        private static final String DESCRIPTOR = "android.car.content.pm.ICarPackageManager";
        static final int TRANSACTION_isActivityAllowedWhileDriving = 2;
        static final int TRANSACTION_isActivityBackedBySafeActivity = 4;
        static final int TRANSACTION_isServiceAllowedWhileDriving = 3;
        static final int TRANSACTION_setAppBlockingPolicy = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPackageManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPackageManager)) {
                return (ICarPackageManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    CarAppBlockingPolicy _arg1 = data.readInt() != 0 ? CarAppBlockingPolicy.CREATOR.createFromParcel(data) : null;
                    int _arg2 = data.readInt();
                    setAppBlockingPolicy(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    String _arg12 = data.readString();
                    boolean isActivityAllowedWhileDriving = isActivityAllowedWhileDriving(_arg02, _arg12);
                    reply.writeNoException();
                    reply.writeInt(isActivityAllowedWhileDriving ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    String _arg13 = data.readString();
                    boolean isServiceAllowedWhileDriving = isServiceAllowedWhileDriving(_arg03, _arg13);
                    reply.writeNoException();
                    reply.writeInt(isServiceAllowedWhileDriving ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ComponentName _arg04 = data.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(data) : null;
                    boolean isActivityBackedBySafeActivity = isActivityBackedBySafeActivity(_arg04);
                    reply.writeNoException();
                    reply.writeInt(isActivityBackedBySafeActivity ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarPackageManager {
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

            @Override // android.car.content.pm.ICarPackageManager
            public void setAppBlockingPolicy(String packageName, CarAppBlockingPolicy policy, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (policy != null) {
                        _data.writeInt(1);
                        policy.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.content.pm.ICarPackageManager
            public boolean isActivityAllowedWhileDriving(String packageName, String className) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(className);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.content.pm.ICarPackageManager
            public boolean isServiceAllowedWhileDriving(String packageName, String className) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(className);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.content.pm.ICarPackageManager
            public boolean isActivityBackedBySafeActivity(ComponentName activityName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (activityName != null) {
                        _data.writeInt(1);
                        activityName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
