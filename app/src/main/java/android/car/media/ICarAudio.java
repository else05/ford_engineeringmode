package android.car.media;

import android.car.media.ICarAudioCallback;
import android.media.AudioAttributes;
import android.media.IVolumeController;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ICarAudio extends IInterface {
    AudioAttributes getAudioAttributesForCarUsage(int i) throws RemoteException;

    AudioAttributes getAudioAttributesForExternalSource(String str) throws RemoteException;

    AudioAttributes getAudioAttributesForRadio(String str) throws RemoteException;

    String[] getParameterKeys() throws RemoteException;

    String getParameters(String str) throws RemoteException;

    int getStreamMaxVolume(int i) throws RemoteException;

    int getStreamMinVolume(int i) throws RemoteException;

    int getStreamVolume(int i) throws RemoteException;

    String[] getSupportedExternalSourceTypes() throws RemoteException;

    String[] getSupportedRadioTypes() throws RemoteException;

    boolean isMediaMuted() throws RemoteException;

    void registerOnParameterChangeListener(ICarAudioCallback iCarAudioCallback) throws RemoteException;

    boolean setMediaMute(boolean z) throws RemoteException;

    void setParameters(String str) throws RemoteException;

    void setStreamVolume(int i, int i2, int i3) throws RemoteException;

    void setVolumeController(IVolumeController iVolumeController) throws RemoteException;

    void unregisterOnParameterChangeListener(ICarAudioCallback iCarAudioCallback) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarAudio {
        private static final String DESCRIPTOR = "android.car.media.ICarAudio";
        static final int TRANSACTION_getAudioAttributesForCarUsage = 1;
        static final int TRANSACTION_getAudioAttributesForExternalSource = 10;
        static final int TRANSACTION_getAudioAttributesForRadio = 9;
        static final int TRANSACTION_getParameterKeys = 13;
        static final int TRANSACTION_getParameters = 15;
        static final int TRANSACTION_getStreamMaxVolume = 4;
        static final int TRANSACTION_getStreamMinVolume = 5;
        static final int TRANSACTION_getStreamVolume = 6;
        static final int TRANSACTION_getSupportedExternalSourceTypes = 11;
        static final int TRANSACTION_getSupportedRadioTypes = 12;
        static final int TRANSACTION_isMediaMuted = 7;
        static final int TRANSACTION_registerOnParameterChangeListener = 16;
        static final int TRANSACTION_setMediaMute = 8;
        static final int TRANSACTION_setParameters = 14;
        static final int TRANSACTION_setStreamVolume = 2;
        static final int TRANSACTION_setVolumeController = 3;
        static final int TRANSACTION_unregisterOnParameterChangeListener = 17;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarAudio asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarAudio)) {
                return (ICarAudio) iin;
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
                    AudioAttributes _result = getAudioAttributesForCarUsage(_arg0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    setStreamVolume(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    IVolumeController _arg03 = IVolumeController.Stub.asInterface(data.readStrongBinder());
                    setVolumeController(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _result2 = getStreamMaxVolume(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _result3 = getStreamMinVolume(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    int _result4 = getStreamVolume(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    boolean isMediaMuted = isMediaMuted();
                    reply.writeNoException();
                    reply.writeInt(isMediaMuted ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg07 = data.readInt() != 0;
                    boolean mediaMute = setMediaMute(_arg07);
                    reply.writeNoException();
                    reply.writeInt(mediaMute ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    AudioAttributes _result5 = getAudioAttributesForRadio(_arg08);
                    reply.writeNoException();
                    if (_result5 != null) {
                        reply.writeInt(1);
                        _result5.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    AudioAttributes _result6 = getAudioAttributesForExternalSource(_arg09);
                    reply.writeNoException();
                    if (_result6 != null) {
                        reply.writeInt(1);
                        _result6.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result7 = getSupportedExternalSourceTypes();
                    reply.writeNoException();
                    reply.writeStringArray(_result7);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result8 = getSupportedRadioTypes();
                    reply.writeNoException();
                    reply.writeStringArray(_result8);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result9 = getParameterKeys();
                    reply.writeNoException();
                    reply.writeStringArray(_result9);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    setParameters(_arg010);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    String _result10 = getParameters(_arg011);
                    reply.writeNoException();
                    reply.writeString(_result10);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    ICarAudioCallback _arg012 = ICarAudioCallback.Stub.asInterface(data.readStrongBinder());
                    registerOnParameterChangeListener(_arg012);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    ICarAudioCallback _arg013 = ICarAudioCallback.Stub.asInterface(data.readStrongBinder());
                    unregisterOnParameterChangeListener(_arg013);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarAudio {
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

            @Override // android.car.media.ICarAudio
            public AudioAttributes getAudioAttributesForCarUsage(int carUsage) throws RemoteException {
                AudioAttributes _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(carUsage);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (AudioAttributes) AudioAttributes.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setStreamVolume(int streamType, int index, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    _data.writeInt(index);
                    _data.writeInt(flags);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setVolumeController(IVolumeController controller) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(controller != null ? controller.asBinder() : null);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getStreamMaxVolume(int streamType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getStreamMinVolume(int streamType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public int getStreamVolume(int streamType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public boolean isMediaMuted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public boolean setMediaMute(boolean mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute ? 1 : 0);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public AudioAttributes getAudioAttributesForRadio(String radioType) throws RemoteException {
                AudioAttributes _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(radioType);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (AudioAttributes) AudioAttributes.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public AudioAttributes getAudioAttributesForExternalSource(String externalSourceType) throws RemoteException {
                AudioAttributes _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(externalSourceType);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (AudioAttributes) AudioAttributes.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public String[] getSupportedExternalSourceTypes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public String[] getSupportedRadioTypes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public String[] getParameterKeys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void setParameters(String parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(parameters);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public String getParameters(String keys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(keys);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void registerOnParameterChangeListener(ICarAudioCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.media.ICarAudio
            public void unregisterOnParameterChangeListener(ICarAudioCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
