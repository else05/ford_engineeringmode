package android.car.cluster.renderer;

import android.car.navigation.CarNavigationInstrumentCluster;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public interface IInstrumentClusterNavigation extends IInterface {
    CarNavigationInstrumentCluster getInstrumentClusterInfo() throws RemoteException;

    void onEvent(int i, Bundle bundle) throws RemoteException;

    void onNextManeuverChanged(int i, CharSequence charSequence, int i2, int i3, Bitmap bitmap, int i4) throws RemoteException;

    void onNextManeuverDistanceChanged(int i, int i2, int i3, int i4) throws RemoteException;

    void onStartNavigation() throws RemoteException;

    void onStopNavigation() throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInstrumentClusterNavigation {
        private static final String DESCRIPTOR = "android.car.cluster.renderer.IInstrumentClusterNavigation";
        static final int TRANSACTION_getInstrumentClusterInfo = 6;
        static final int TRANSACTION_onEvent = 5;
        static final int TRANSACTION_onNextManeuverChanged = 3;
        static final int TRANSACTION_onNextManeuverDistanceChanged = 4;
        static final int TRANSACTION_onStartNavigation = 1;
        static final int TRANSACTION_onStopNavigation = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstrumentClusterNavigation asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IInstrumentClusterNavigation)) {
                return (IInstrumentClusterNavigation) iin;
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
                    onStartNavigation();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onStopNavigation();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    CharSequence _arg1 = data.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data) : null;
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    Bitmap _arg4 = data.readInt() != 0 ? (Bitmap) Bitmap.CREATOR.createFromParcel(data) : null;
                    int _arg5 = data.readInt();
                    onNextManeuverChanged(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg12 = data.readInt();
                    int _arg22 = data.readInt();
                    int _arg32 = data.readInt();
                    onNextManeuverDistanceChanged(_arg02, _arg12, _arg22, _arg32);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    Bundle _arg13 = data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null;
                    onEvent(_arg03, _arg13);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    CarNavigationInstrumentCluster _result = getInstrumentClusterInfo();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IInstrumentClusterNavigation {
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

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public void onStartNavigation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public void onStopNavigation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public void onNextManeuverChanged(int event, CharSequence eventName, int turnAngle, int turnNumber, Bitmap image, int turnSide) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(event);
                    if (eventName != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(eventName, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(turnAngle);
                    _data.writeInt(turnNumber);
                    if (image != null) {
                        _data.writeInt(1);
                        image.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(turnSide);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public void onNextManeuverDistanceChanged(int distanceMeters, int timeSeconds, int displayDistanceMillis, int displayDistanceUnit) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(distanceMeters);
                    _data.writeInt(timeSeconds);
                    _data.writeInt(displayDistanceMillis);
                    _data.writeInt(displayDistanceUnit);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public void onEvent(int eventType, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(eventType);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.car.cluster.renderer.IInstrumentClusterNavigation
            public CarNavigationInstrumentCluster getInstrumentClusterInfo() throws RemoteException {
                CarNavigationInstrumentCluster _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarNavigationInstrumentCluster.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
