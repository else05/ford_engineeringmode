package android.car.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes2.dex */
public interface ICarSensorEventListener extends IInterface {
    void onSensorChanged(List<CarSensorEvent> list) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICarSensorEventListener {
        private static final String DESCRIPTOR = "android.car.hardware.ICarSensorEventListener";
        static final int TRANSACTION_onSensorChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSensorEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarSensorEventListener)) {
                return (ICarSensorEventListener) iin;
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
            List<CarSensorEvent> _arg0 = data.createTypedArrayList(CarSensorEvent.CREATOR);
            onSensorChanged(_arg0);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements ICarSensorEventListener {
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

            @Override // android.car.hardware.ICarSensorEventListener
            public void onSensorChanged(List<CarSensorEvent> events) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(events);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
