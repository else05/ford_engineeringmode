package android.car.cluster;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IInstrumentClusterManagerCallback extends IInterface {
    void setClusterActivityState(String str, Bundle bundle) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInstrumentClusterManagerCallback {
        private static final String DESCRIPTOR = "android.car.cluster.IInstrumentClusterManagerCallback";
        static final int TRANSACTION_setClusterActivityState = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentClusterManagerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInstrumentClusterManagerCallback)) {
                return (IInstrumentClusterManagerCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg1;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            if (data.readInt() != 0) {
                _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            setClusterActivityState(_arg0, _arg1);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IInstrumentClusterManagerCallback {
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

            @Override // android.car.cluster.IInstrumentClusterManagerCallback
            public void setClusterActivityState(String category, Bundle clusterActivityState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(category);
                    if (clusterActivityState != null) {
                        _data.writeInt(1);
                        clusterActivityState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
