package android.car.cluster;

import android.car.cluster.IInstrumentClusterManagerCallback;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IInstrumentClusterManagerService extends IInterface {
    void registerCallback(IInstrumentClusterManagerCallback iInstrumentClusterManagerCallback) throws RemoteException;

    void startClusterActivity(Intent intent) throws RemoteException;

    void unregisterCallback(IInstrumentClusterManagerCallback iInstrumentClusterManagerCallback) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInstrumentClusterManagerService {
        private static final String DESCRIPTOR = "android.car.cluster.IInstrumentClusterManagerService";
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_startClusterActivity = 1;
        static final int TRANSACTION_unregisterCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentClusterManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInstrumentClusterManagerService)) {
                return (IInstrumentClusterManagerService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Intent _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Intent) Intent.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    startClusterActivity(_arg0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IInstrumentClusterManagerCallback _arg02 = IInstrumentClusterManagerCallback.Stub.asInterface(data.readStrongBinder());
                    registerCallback(_arg02);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IInstrumentClusterManagerCallback _arg03 = IInstrumentClusterManagerCallback.Stub.asInterface(data.readStrongBinder());
                    unregisterCallback(_arg03);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IInstrumentClusterManagerService {
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

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void startClusterActivity(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void registerCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.IInstrumentClusterManagerService
            public void unregisterCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
