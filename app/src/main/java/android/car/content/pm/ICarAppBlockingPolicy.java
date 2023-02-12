package android.car.content.pm;

import android.car.content.pm.ICarAppBlockingPolicySetter;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarAppBlockingPolicy extends IInterface {
    void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter iCarAppBlockingPolicySetter) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarAppBlockingPolicy {
        private static final String DESCRIPTOR = "android.car.content.pm.ICarAppBlockingPolicy";
        static final int TRANSACTION_setAppBlockingPolicySetter = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarAppBlockingPolicy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarAppBlockingPolicy)) {
                return (ICarAppBlockingPolicy) iin;
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
            ICarAppBlockingPolicySetter _arg0 = ICarAppBlockingPolicySetter.Stub.asInterface(data.readStrongBinder());
            setAppBlockingPolicySetter(_arg0);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarAppBlockingPolicy {
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

            @Override // android.car.content.pm.ICarAppBlockingPolicy
            public void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter setter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(setter != null ? setter.asBinder() : null);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
