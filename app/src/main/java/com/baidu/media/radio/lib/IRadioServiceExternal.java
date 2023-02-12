package com.baidu.media.radio.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.media.radio.lib.IPDStatusCallback;
import com.baidu.media.radio.lib.IRadioSeekCallBack;
import com.baidu.media.radio.lib.ISignal2InfoCallback;
import com.baidu.media.radio.lib.ISignalInfoCallback;
import java.util.List;

/* loaded from: classes2.dex */
public interface IRadioServiceExternal extends IInterface {
    int getCurrentFrequency() throws RemoteException;

    int getCurrentRadioBand() throws RemoteException;

    int getCurrentSourceType() throws RemoteException;

    int getPDStauts() throws RemoteException;

    int[] getPara(int i, int i2) throws RemoteException;

    List<String> getTunerList(int i, int i2) throws RemoteException;

    int getUsbPlayMode() throws RemoteException;

    int getUsbPlayPosition() throws RemoteException;

    long getUsbPlayProgress() throws RemoteException;

    boolean isUsbPlaying() throws RemoteException;

    void openRadioBand(int i) throws RemoteException;

    void playPosition(int i) throws RemoteException;

    void scan() throws RemoteException;

    void seek(int i, int i2, IRadioSeekCallBack iRadioSeekCallBack) throws RemoteException;

    void seekRadio(int i) throws RemoteException;

    int setPDStatus(int i) throws RemoteException;

    void setPDStatusCallback(IPDStatusCallback iPDStatusCallback) throws RemoteException;

    int setPara(int i, int[] iArr) throws RemoteException;

    void setSignal2InfoCallback(ISignal2InfoCallback iSignal2InfoCallback) throws RemoteException;

    void setSignalInfoCallback(ISignalInfoCallback iSignalInfoCallback) throws RemoteException;

    int setUsbDesiredOperate(int i, long j) throws RemoteException;

    void stopScan() throws RemoteException;

    void switchListMode(int i) throws RemoteException;

    void tuner(int i, int i2) throws RemoteException;

    void tunerList(boolean z) throws RemoteException;

    void updateSeekUi(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRadioServiceExternal {
        private static final String DESCRIPTOR = "com.baidu.media.radio.lib.IRadioServiceExternal";
        static final int TRANSACTION_getCurrentFrequency = 9;
        static final int TRANSACTION_getCurrentRadioBand = 5;
        static final int TRANSACTION_getCurrentSourceType = 22;
        static final int TRANSACTION_getPDStauts = 24;
        static final int TRANSACTION_getPara = 1;
        static final int TRANSACTION_getTunerList = 20;
        static final int TRANSACTION_getUsbPlayMode = 11;
        static final int TRANSACTION_getUsbPlayPosition = 12;
        static final int TRANSACTION_getUsbPlayProgress = 13;
        static final int TRANSACTION_isUsbPlaying = 10;
        static final int TRANSACTION_openRadioBand = 8;
        static final int TRANSACTION_playPosition = 21;
        static final int TRANSACTION_scan = 15;
        static final int TRANSACTION_seek = 6;
        static final int TRANSACTION_seekRadio = 17;
        static final int TRANSACTION_setPDStatus = 23;
        static final int TRANSACTION_setPDStatusCallback = 26;
        static final int TRANSACTION_setPara = 2;
        static final int TRANSACTION_setSignal2InfoCallback = 25;
        static final int TRANSACTION_setSignalInfoCallback = 3;
        static final int TRANSACTION_setUsbDesiredOperate = 14;
        static final int TRANSACTION_stopScan = 16;
        static final int TRANSACTION_switchListMode = 18;
        static final int TRANSACTION_tuner = 4;
        static final int TRANSACTION_tunerList = 19;
        static final int TRANSACTION_updateSeekUi = 7;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRadioServiceExternal asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IRadioServiceExternal)) {
                return (IRadioServiceExternal) iin;
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
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    int[] _result = getPara(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeIntArray(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int[] _arg12 = data.createIntArray();
                    int _result2 = setPara(_arg02, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    ISignalInfoCallback _arg03 = ISignalInfoCallback.Stub.asInterface(data.readStrongBinder());
                    setSignalInfoCallback(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    tuner(_arg04, _arg13);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getCurrentRadioBand();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _arg14 = data.readInt();
                    IRadioSeekCallBack _arg2 = IRadioSeekCallBack.Stub.asInterface(data.readStrongBinder());
                    seek(_arg05, _arg14, _arg2);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    int _arg15 = data.readInt();
                    updateSeekUi(_arg06, _arg15);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    openRadioBand(_arg07);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getCurrentFrequency();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    boolean isUsbPlaying = isUsbPlaying();
                    reply.writeNoException();
                    reply.writeInt(isUsbPlaying ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = getUsbPlayMode();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getUsbPlayPosition();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    long _result7 = getUsbPlayProgress();
                    reply.writeNoException();
                    reply.writeLong(_result7);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    long _arg16 = data.readLong();
                    int _result8 = setUsbDesiredOperate(_arg08, _arg16);
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    scan();
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    stopScan();
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    seekRadio(_arg09);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    switchListMode(_arg010);
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg011 = data.readInt() != 0;
                    tunerList(_arg011);
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    int _arg17 = data.readInt();
                    List<String> _result9 = getTunerList(_arg012, _arg17);
                    reply.writeNoException();
                    reply.writeStringList(_result9);
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    playPosition(_arg013);
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getCurrentSourceType();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    int _result11 = setPDStatus(_arg014);
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = getPDStauts();
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    ISignal2InfoCallback _arg015 = ISignal2InfoCallback.Stub.asInterface(data.readStrongBinder());
                    setSignal2InfoCallback(_arg015);
                    reply.writeNoException();
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    IPDStatusCallback _arg016 = IPDStatusCallback.Stub.asInterface(data.readStrongBinder());
                    setPDStatusCallback(_arg016);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IRadioServiceExternal {
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

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int[] getPara(int var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    _data.writeInt(var2);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int setPara(int var1, int[] var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    _data.writeIntArray(var2);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void setSignalInfoCallback(ISignalInfoCallback callBack) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callBack != null ? callBack.asBinder() : null);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void tuner(int var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    _data.writeInt(var2);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getCurrentRadioBand() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void seek(int band, int direct, IRadioSeekCallBack callBack) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    _data.writeInt(direct);
                    _data.writeStrongBinder(callBack != null ? callBack.asBinder() : null);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void updateSeekUi(int band, int frequency) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    _data.writeInt(frequency);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void openRadioBand(int band) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getCurrentFrequency() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public boolean isUsbPlaying() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getUsbPlayMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getUsbPlayPosition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public long getUsbPlayProgress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int setUsbDesiredOperate(int desiredPosition, long desiioredTime) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(desiredPosition);
                    _data.writeLong(desiioredTime);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void scan() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void stopScan() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void seekRadio(int direct) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(direct);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void switchListMode(int listType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listType);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void tunerList(boolean isPre) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isPre ? 1 : 0);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public List<String> getTunerList(int band, int listType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(band);
                    _data.writeInt(listType);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void playPosition(int position) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(position);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getCurrentSourceType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int setPDStatus(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public int getPDStauts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void setSignal2InfoCallback(ISignal2InfoCallback callBack) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callBack != null ? callBack.asBinder() : null);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.baidu.media.radio.lib.IRadioServiceExternal
            public void setPDStatusCallback(IPDStatusCallback callBack) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callBack != null ? callBack.asBinder() : null);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
