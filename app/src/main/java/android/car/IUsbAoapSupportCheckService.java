package android.car;

import android.hardware.usb.UsbDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IUsbAoapSupportCheckService extends IInterface {
    boolean isDeviceSupported(UsbDevice usbDevice) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IUsbAoapSupportCheckService {
        private static final String DESCRIPTOR = "android.car.IUsbAoapSupportCheckService";
        static final int TRANSACTION_isDeviceSupported = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUsbAoapSupportCheckService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IUsbAoapSupportCheckService)) {
                return (IUsbAoapSupportCheckService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            UsbDevice _arg0;
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = (UsbDevice) UsbDevice.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            boolean isDeviceSupported = isDeviceSupported(_arg0);
            reply.writeNoException();
            reply.writeInt(isDeviceSupported ? 1 : 0);
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IUsbAoapSupportCheckService {
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

            @Override // android.car.IUsbAoapSupportCheckService
            public boolean isDeviceSupported(UsbDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
