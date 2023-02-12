package android.car;

import android.car.IAppFocusListener;
import android.car.IAppFocusOwnershipCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IAppFocus extends IInterface {
    void abandonAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    int[] getActiveAppTypes() throws RemoteException;

    boolean isOwningFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    void registerFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException;

    int requestAppFocus(IAppFocusOwnershipCallback iAppFocusOwnershipCallback, int i) throws RemoteException;

    void unregisterFocusListener(IAppFocusListener iAppFocusListener, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAppFocus {
        private static final String DESCRIPTOR = "android.car.IAppFocus";
        static final int TRANSACTION_abandonAppFocus = 6;
        static final int TRANSACTION_getActiveAppTypes = 3;
        static final int TRANSACTION_isOwningFocus = 4;
        static final int TRANSACTION_registerFocusListener = 1;
        static final int TRANSACTION_requestAppFocus = 5;
        static final int TRANSACTION_unregisterFocusListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppFocus asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAppFocus)) {
                return (IAppFocus) iin;
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
                    IAppFocusListener _arg0 = IAppFocusListener.Stub.asInterface(data.readStrongBinder());
                    int _arg1 = data.readInt();
                    registerFocusListener(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IAppFocusListener _arg02 = IAppFocusListener.Stub.asInterface(data.readStrongBinder());
                    int _arg12 = data.readInt();
                    unregisterFocusListener(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result = getActiveAppTypes();
                    reply.writeNoException();
                    reply.writeIntArray(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    IAppFocusOwnershipCallback _arg03 = IAppFocusOwnershipCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg13 = data.readInt();
                    boolean isOwningFocus = isOwningFocus(_arg03, _arg13);
                    reply.writeNoException();
                    reply.writeInt(isOwningFocus ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    IAppFocusOwnershipCallback _arg04 = IAppFocusOwnershipCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg14 = data.readInt();
                    int _result2 = requestAppFocus(_arg04, _arg14);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IAppFocusOwnershipCallback _arg05 = IAppFocusOwnershipCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg15 = data.readInt();
                    abandonAppFocus(_arg05, _arg15);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IAppFocus {
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

            @Override // android.car.IAppFocus
            public void registerFocusListener(IAppFocusListener callback, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appType);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public void unregisterFocusListener(IAppFocusListener callback, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appType);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public int[] getActiveAppTypes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public boolean isOwningFocus(IAppFocusOwnershipCallback callback, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appType);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public int requestAppFocus(IAppFocusOwnershipCallback callback, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appType);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.IAppFocus
            public void abandonAppFocus(IAppFocusOwnershipCallback callback, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(appType);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
