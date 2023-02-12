package android.car.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* loaded from: classes2.dex */
public interface ICarInputListener extends IInterface {
    void onKeyEvent(KeyEvent keyEvent, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarInputListener {
        private static final String DESCRIPTOR = "android.car.input.ICarInputListener";
        static final int TRANSACTION_onKeyEvent = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarInputListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarInputListener)) {
                return (ICarInputListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            KeyEvent _arg0;
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            int _arg1 = data.readInt();
            onKeyEvent(_arg0, _arg1);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarInputListener {
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

            @Override // android.car.input.ICarInputListener
            public void onKeyEvent(KeyEvent keyEvent, int targetDisplay) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        _data.writeInt(1);
                        keyEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(targetDisplay);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
