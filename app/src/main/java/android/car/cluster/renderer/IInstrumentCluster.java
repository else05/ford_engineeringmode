package android.car.cluster.renderer;

import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* loaded from: classes2.dex */
public interface IInstrumentCluster extends IInterface {
    IInstrumentClusterNavigation getNavigationService() throws RemoteException;

    void onKeyEvent(KeyEvent keyEvent) throws RemoteException;

    void setNavigationContextOwner(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInstrumentCluster {
        private static final String DESCRIPTOR = "android.car.cluster.renderer.IInstrumentCluster";
        static final int TRANSACTION_getNavigationService = 1;
        static final int TRANSACTION_onKeyEvent = 3;
        static final int TRANSACTION_setNavigationContextOwner = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentCluster asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInstrumentCluster)) {
                return (IInstrumentCluster) iin;
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
                    IInstrumentClusterNavigation _result = getNavigationService();
                    reply.writeNoException();
                    reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    setNavigationContextOwner(_arg0, _arg1);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    KeyEvent _arg02 = data.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(data) : null;
                    onKeyEvent(_arg02);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IInstrumentCluster {
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

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public IInstrumentClusterNavigation getNavigationService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    IInstrumentClusterNavigation _result = IInstrumentClusterNavigation.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public void setNavigationContextOwner(int uid, int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(pid);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentCluster
            public void onKeyEvent(KeyEvent keyEvent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        _data.writeInt(1);
                        keyEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
