package android.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICar extends IInterface {
    int getCarConnectionType() throws RemoteException;

    IBinder getCarService(String str) throws RemoteException;

    void setCarServiceHelper(IBinder iBinder) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICar {
        private static final String DESCRIPTOR = "android.car.ICar";
        static final int TRANSACTION_getCarConnectionType = 3;
        static final int TRANSACTION_getCarService = 2;
        static final int TRANSACTION_setCarServiceHelper = 1;

        public Stub() {
            attachInterface(this, "android.car.ICar");
        }

        public static ICar asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.car.ICar");
            if (iin != null && (iin instanceof ICar)) {
                return (ICar) iin;
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
                reply.writeString("android.car.ICar");
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface("android.car.ICar");
                    IBinder _arg0 = data.readStrongBinder();
                    setCarServiceHelper(_arg0);
                    return true;
                case 2:
                    data.enforceInterface("android.car.ICar");
                    String _arg02 = data.readString();
                    IBinder _result = getCarService(_arg02);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result);
                    return true;
                case 3:
                    data.enforceInterface("android.car.ICar");
                    int _result2 = getCarConnectionType();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICar {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.car.ICar";
            }

            @Override // android.car.ICar
            public void setCarServiceHelper(IBinder helper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.car.ICar");
                    _data.writeStrongBinder(helper);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.ICar
            public IBinder getCarService(String serviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.car.ICar");
                    _data.writeString(serviceName);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.ICar
            public int getCarConnectionType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.car.ICar");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
