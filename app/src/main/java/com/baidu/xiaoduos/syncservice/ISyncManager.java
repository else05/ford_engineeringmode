package com.baidu.xiaoduos.syncservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.xiaoduos.syncservice.IPushMessageListener;

/* loaded from: classes2.dex */
public interface ISyncManager extends IInterface {
    void addPushMessageListener(String str, IPushMessageListener iPushMessageListener) throws RemoteException;

    String getAK() throws RemoteException;

    String getBaiduAccountUID() throws RemoteException;

    String getCN() throws RemoteException;

    String getDeviceID() throws RemoteException;

    String getSignPrefix(boolean z) throws RemoteException;

    String getSignSuffix(boolean z) throws RemoteException;

    String getUUID() throws RemoteException;

    String getVIN() throws RemoteException;

    void removePushMessageListener(String str, IPushMessageListener iPushMessageListener) throws RemoteException;

    void updateAccountInfo(String str, String str2, String str3) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISyncManager {
        private static final String DESCRIPTOR = "com.baidu.xiaoduos.syncservice.ISyncManager";
        static final int TRANSACTION_addPushMessageListener = 10;
        static final int TRANSACTION_getAK = 5;
        static final int TRANSACTION_getBaiduAccountUID = 8;
        static final int TRANSACTION_getCN = 4;
        static final int TRANSACTION_getDeviceID = 3;
        static final int TRANSACTION_getSignPrefix = 6;
        static final int TRANSACTION_getSignSuffix = 7;
        static final int TRANSACTION_getUUID = 1;
        static final int TRANSACTION_getVIN = 2;
        static final int TRANSACTION_removePushMessageListener = 11;
        static final int TRANSACTION_updateAccountInfo = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISyncManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ISyncManager)) {
                return (ISyncManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _result = getUUID();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = getVIN();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = getDeviceID();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _result4 = getCN();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getAK();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt() != 0;
                    String _result6 = getSignPrefix(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt() != 0;
                    String _result7 = getSignSuffix(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result7);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _result8 = getBaiduAccountUID();
                    reply.writeNoException();
                    reply.writeString(_result8);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    String _arg1 = data.readString();
                    String _arg2 = data.readString();
                    updateAccountInfo(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    IPushMessageListener _arg12 = IPushMessageListener.Stub.asInterface(data.readStrongBinder());
                    addPushMessageListener(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    IPushMessageListener _arg13 = IPushMessageListener.Stub.asInterface(data.readStrongBinder());
                    removePushMessageListener(_arg04, _arg13);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ISyncManager {
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

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getUUID() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getVIN() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getDeviceID() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getCN() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getAK() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getSignPrefix(boolean isSandbox) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isSandbox ? 1 : 0);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getSignSuffix(boolean isSandbox) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isSandbox ? 1 : 0);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public String getBaiduAccountUID() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public void updateAccountInfo(String packageName, String uid, String bduss) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(uid);
                    _data.writeString(bduss);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public void addPushMessageListener(String packageName, IPushMessageListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.ISyncManager
            public void removePushMessageListener(String packageName, IPushMessageListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
