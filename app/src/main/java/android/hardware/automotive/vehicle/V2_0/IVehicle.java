package android.hardware.automotive.vehicle.V2_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes2.dex */
public interface IVehicle extends IBase {
    public static final String kInterfaceName = "android.hardware.automotive.vehicle@2.0::IVehicle";

    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface getCallback {
        void onValues(int i, VehiclePropValue vehiclePropValue);
    }

    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface getPropConfigsCallback {
        void onValues(int i, ArrayList<VehiclePropConfig> arrayList);
    }

    IHwBinder asBinder();

    String debugDump() throws RemoteException;

    void get(VehiclePropValue vehiclePropValue, getCallback getcallback) throws RemoteException;

    ArrayList<VehiclePropConfig> getAllPropConfigs() throws RemoteException;

    DebugInfo getDebugInfo() throws RemoteException;

    ArrayList<byte[]> getHashChain() throws RemoteException;

    void getPropConfigs(ArrayList<Integer> arrayList, getPropConfigsCallback getpropconfigscallback) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String interfaceDescriptor() throws RemoteException;

    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    void notifySyspropsChanged() throws RemoteException;

    void ping() throws RemoteException;

    int set(VehiclePropValue vehiclePropValue) throws RemoteException;

    void setHALInstrumentation() throws RemoteException;

    int subscribe(IVehicleCallback iVehicleCallback, ArrayList<SubscribeOptions> arrayList) throws RemoteException;

    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    int unsubscribe(IVehicleCallback iVehicleCallback, int i) throws RemoteException;

    static IVehicle asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IVehicle queryLocalInterface = binder.queryLocalInterface(kInterfaceName);
        if (queryLocalInterface != null && (queryLocalInterface instanceof IVehicle)) {
            return queryLocalInterface;
        }
        IVehicle proxy = new Proxy(binder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                String descriptor = it.next();
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IVehicle castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IVehicle getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IVehicle getService() throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, "default"));
    }

    /* loaded from: classes2.dex */
    public static final class Proxy implements IVehicle {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(remote);
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of android.hardware.automotive.vehicle@2.0::IVehicle]@Proxy";
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public ArrayList<VehiclePropConfig> getAllPropConfigs() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<VehiclePropConfig> _hidl_out_propConfigs = VehiclePropConfig.readVectorFromParcel(_hidl_reply);
                return _hidl_out_propConfigs;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public void getPropConfigs(ArrayList<Integer> props, getPropConfigsCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            _hidl_request.writeInt32Vector(props);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                ArrayList<VehiclePropConfig> _hidl_out_propConfigs = VehiclePropConfig.readVectorFromParcel(_hidl_reply);
                _hidl_cb.onValues(_hidl_out_status, _hidl_out_propConfigs);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public void get(VehiclePropValue requestedPropValue, getCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            requestedPropValue.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                VehiclePropValue _hidl_out_propValue = new VehiclePropValue();
                _hidl_out_propValue.readFromParcel(_hidl_reply);
                _hidl_cb.onValues(_hidl_out_status, _hidl_out_propValue);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public int set(VehiclePropValue propValue) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            propValue.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public int subscribe(IVehicleCallback callback, ArrayList<SubscribeOptions> options) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());
            SubscribeOptions.writeVectorToParcel(_hidl_request, options);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public int unsubscribe(IVehicleCallback callback, int propId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());
            _hidl_request.writeInt32(propId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public String debugDump() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IVehicle.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_s = _hidl_reply.readString();
                return _hidl_out_s;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                int _hidl_vec_size = _hidl_blob.getInt32(8L);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    byte[] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    long _hidl_array_offset_12 = _hidl_array_offset_1;
                    for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                        _hidl_vec_element[_hidl_index_1_0] = childBlob.getInt8(_hidl_array_offset_12);
                        _hidl_array_offset_12++;
                    }
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends HwBinder implements IVehicle {
        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public IHwBinder asBinder() {
            return this;
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IVehicle.kInterfaceName, "android.hidl.base@1.0::IBase"));
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final String interfaceDescriptor() {
            return IVehicle.kInterfaceName;
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-51, -32, 120, 126, 75, -12, -76, 80, -87, -50, -71, 1, 29, 38, -104, -64, 6, 19, 34, -21, -120, 38, 33, -24, -101, 112, 89, 75, 11, 124, 101, -59}, new byte[]{-67, -38, -74, 24, 77, 122, 52, 109, -90, -96, 125, -64, -126, -116, -15, -102, 105, 111, 76, -86, 54, 17, -59, 31, 46, 20, 86, 90, 20, -76, 15, -39}));
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final void setHALInstrumentation() {
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final void ping() {
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = -1;
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final void notifySyspropsChanged() {
            SystemProperties.reportSyspropChanged();
        }

        @Override // android.hardware.automotive.vehicle.V2_0.IVehicle
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IVehicle.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, final HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            if (_hidl_code == 256067662) {
                _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                ArrayList<String> _hidl_out_descriptors = interfaceChain();
                _hidl_reply.writeStatus(0);
                _hidl_reply.writeStringVector(_hidl_out_descriptors);
                _hidl_reply.send();
            } else if (_hidl_code == 256131655) {
                _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                _hidl_reply.writeStatus(0);
                _hidl_reply.send();
            } else if (_hidl_code == 256136003) {
                _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                String _hidl_out_descriptor = interfaceDescriptor();
                _hidl_reply.writeStatus(0);
                _hidl_reply.writeString(_hidl_out_descriptor);
                _hidl_reply.send();
            } else if (_hidl_code == 256398152) {
                _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                _hidl_reply.writeStatus(0);
                HwBlob _hidl_blob = new HwBlob(16);
                int _hidl_vec_size = _hidl_out_hashchain.size();
                _hidl_blob.putInt32(8L, _hidl_vec_size);
                _hidl_blob.putBool(12L, false);
                HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    long _hidl_array_offset_12 = _hidl_array_offset_1;
                    for (int _hidl_index_1_0 = 0; _hidl_index_1_0 < 32; _hidl_index_1_0++) {
                        childBlob.putInt8(_hidl_array_offset_12, _hidl_out_hashchain.get(_hidl_index_0)[_hidl_index_1_0]);
                        _hidl_array_offset_12++;
                    }
                }
                _hidl_blob.putBlob(0L, childBlob);
                _hidl_reply.writeBuffer(_hidl_blob);
                _hidl_reply.send();
            } else if (_hidl_code == 256462420) {
                _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                setHALInstrumentation();
            } else if (_hidl_code != 256660548 && _hidl_code != 256921159) {
                if (_hidl_code == 257049926) {
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    DebugInfo _hidl_out_info = getDebugInfo();
                    _hidl_reply.writeStatus(0);
                    _hidl_out_info.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                } else if (_hidl_code != 257120595) {
                    switch (_hidl_code) {
                        case 1:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            ArrayList<VehiclePropConfig> _hidl_out_propConfigs = getAllPropConfigs();
                            _hidl_reply.writeStatus(0);
                            VehiclePropConfig.writeVectorToParcel(_hidl_reply, _hidl_out_propConfigs);
                            _hidl_reply.send();
                            return;
                        case 2:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            ArrayList<Integer> props = _hidl_request.readInt32Vector();
                            getPropConfigs(props, new getPropConfigsCallback() { // from class: android.hardware.automotive.vehicle.V2_0.IVehicle.Stub.1
                                @Override // android.hardware.automotive.vehicle.V2_0.IVehicle.getPropConfigsCallback
                                public void onValues(int status, ArrayList<VehiclePropConfig> propConfigs) {
                                    _hidl_reply.writeStatus(0);
                                    _hidl_reply.writeInt32(status);
                                    VehiclePropConfig.writeVectorToParcel(_hidl_reply, propConfigs);
                                    _hidl_reply.send();
                                }
                            });
                            return;
                        case 3:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            VehiclePropValue requestedPropValue = new VehiclePropValue();
                            requestedPropValue.readFromParcel(_hidl_request);
                            get(requestedPropValue, new getCallback() { // from class: android.hardware.automotive.vehicle.V2_0.IVehicle.Stub.2
                                @Override // android.hardware.automotive.vehicle.V2_0.IVehicle.getCallback
                                public void onValues(int status, VehiclePropValue propValue) {
                                    _hidl_reply.writeStatus(0);
                                    _hidl_reply.writeInt32(status);
                                    propValue.writeToParcel(_hidl_reply);
                                    _hidl_reply.send();
                                }
                            });
                            return;
                        case 4:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            VehiclePropValue propValue = new VehiclePropValue();
                            propValue.readFromParcel(_hidl_request);
                            int _hidl_out_status = set(propValue);
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(_hidl_out_status);
                            _hidl_reply.send();
                            return;
                        case 5:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            IVehicleCallback callback = IVehicleCallback.asInterface(_hidl_request.readStrongBinder());
                            ArrayList<SubscribeOptions> options = SubscribeOptions.readVectorFromParcel(_hidl_request);
                            int _hidl_out_status2 = subscribe(callback, options);
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(_hidl_out_status2);
                            _hidl_reply.send();
                            return;
                        case 6:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            IVehicleCallback callback2 = IVehicleCallback.asInterface(_hidl_request.readStrongBinder());
                            int propId = _hidl_request.readInt32();
                            int _hidl_out_status3 = unsubscribe(callback2, propId);
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(_hidl_out_status3);
                            _hidl_reply.send();
                            return;
                        case 7:
                            _hidl_request.enforceInterface(IVehicle.kInterfaceName);
                            String _hidl_out_s = debugDump();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeString(_hidl_out_s);
                            _hidl_reply.send();
                            return;
                        default:
                            return;
                    }
                } else {
                    _hidl_request.enforceInterface("android.hidl.base@1.0::IBase");
                    notifySyspropsChanged();
                }
            }
        }
    }
}
