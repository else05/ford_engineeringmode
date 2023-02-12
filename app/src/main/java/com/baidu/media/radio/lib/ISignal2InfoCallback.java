package com.baidu.media.radio.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISignal2InfoCallback extends IInterface {
    void onSignalChange(RadioSignal2 radioSignal2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISignal2InfoCallback {
        private static final String DESCRIPTOR = "com.baidu.media.radio.lib.ISignal2InfoCallback";
        static final int TRANSACTION_onSignalChange = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISignal2InfoCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ISignal2InfoCallback)) {
                return (ISignal2InfoCallback) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RadioSignal2 _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = RadioSignal2.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            onSignalChange(_arg0);
            reply.writeNoException();
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ISignal2InfoCallback {
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

            @Override // com.baidu.media.radio.lib.ISignal2InfoCallback
            public void onSignalChange(RadioSignal2 radioSignal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (radioSignal != null) {
                        _data.writeInt(1);
                        radioSignal.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
