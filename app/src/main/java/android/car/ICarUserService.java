package android.car;

import android.car.ICarBluetoothUserService;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarUserService extends IInterface {
    ICarBluetoothUserService getBluetoothUserService() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarUserService {
        private static final String DESCRIPTOR = "android.car.ICarUserService";
        static final int TRANSACTION_getBluetoothUserService = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarUserService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarUserService)) {
                return (ICarUserService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            ICarBluetoothUserService _result = getBluetoothUserService();
            reply.writeNoException();
            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarUserService {
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

            @Override // android.car.ICarUserService
            public ICarBluetoothUserService getBluetoothUserService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    ICarBluetoothUserService _result = ICarBluetoothUserService.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
