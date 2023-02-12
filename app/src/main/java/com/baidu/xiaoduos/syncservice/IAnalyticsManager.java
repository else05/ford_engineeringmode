package com.baidu.xiaoduos.syncservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IAnalyticsManager extends IInterface {
    void onEvent(String str, String str2, int i, int i2) throws RemoteException;

    void onEventAttach(String str, String str2, int i, int i2, String str3) throws RemoteException;

    void onEventPage(String str, String str2, int i, int i2, String str3) throws RemoteException;

    void onStringEvent(String str, String str2, String str3, int i) throws RemoteException;

    void onStringEventAttach(String str, String str2, String str3, int i, String str4) throws RemoteException;

    void onStringEventPage(String str, String str2, String str3, int i, String str4) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAnalyticsManager {
        private static final String DESCRIPTOR = "com.baidu.xiaoduos.syncservice.IAnalyticsManager";
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onEventAttach = 2;
        static final int TRANSACTION_onEventPage = 3;
        static final int TRANSACTION_onStringEvent = 4;
        static final int TRANSACTION_onStringEventAttach = 5;
        static final int TRANSACTION_onStringEventPage = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAnalyticsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAnalyticsManager)) {
                return (IAnalyticsManager) iin;
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
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    onEvent(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    String _arg12 = data.readString();
                    int _arg22 = data.readInt();
                    int _arg32 = data.readInt();
                    String _arg4 = data.readString();
                    onEventAttach(_arg02, _arg12, _arg22, _arg32, _arg4);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    String _arg13 = data.readString();
                    int _arg23 = data.readInt();
                    int _arg33 = data.readInt();
                    String _arg42 = data.readString();
                    onEventPage(_arg03, _arg13, _arg23, _arg33, _arg42);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    String _arg14 = data.readString();
                    String _arg24 = data.readString();
                    int _arg34 = data.readInt();
                    onStringEvent(_arg04, _arg14, _arg24, _arg34);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    String _arg15 = data.readString();
                    String _arg25 = data.readString();
                    int _arg35 = data.readInt();
                    String _arg43 = data.readString();
                    onStringEventAttach(_arg05, _arg15, _arg25, _arg35, _arg43);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    String _arg16 = data.readString();
                    String _arg26 = data.readString();
                    int _arg36 = data.readInt();
                    String _arg44 = data.readString();
                    onStringEventPage(_arg06, _arg16, _arg26, _arg36, _arg44);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IAnalyticsManager {
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

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onEvent(String packageName, String versionName, int event, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeInt(event);
                    _data.writeInt(type);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onEventAttach(String packageName, String versionName, int event, int type, String attach) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeInt(event);
                    _data.writeInt(type);
                    _data.writeString(attach);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onEventPage(String packageName, String versionName, int event, int type, String pageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeInt(event);
                    _data.writeInt(type);
                    _data.writeString(pageName);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onStringEvent(String packageName, String versionName, String event, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeString(event);
                    _data.writeInt(type);
                    this.mRemote.transact(4, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onStringEventAttach(String packageName, String versionName, String event, int type, String attach) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeString(event);
                    _data.writeInt(type);
                    _data.writeString(attach);
                    this.mRemote.transact(5, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.baidu.xiaoduos.syncservice.IAnalyticsManager
            public void onStringEventPage(String packageName, String versionName, String event, int type, String pageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(versionName);
                    _data.writeString(event);
                    _data.writeInt(type);
                    _data.writeString(pageName);
                    this.mRemote.transact(6, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
