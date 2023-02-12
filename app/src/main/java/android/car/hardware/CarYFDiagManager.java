package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarYFDiagManager implements CarManagerBase {
    private static final boolean DBG = true;
    public static final int EVENT_BEZEL_DIAG_DATA = 554699833;
    public static final int ID_BT_MAC_ADDR = 19;
    public static final int ID_DIAGACTIVEBTPRO_SET = 11;
    public static final int ID_DIAGAUDIOROUTINE_GET = 2;
    public static final int ID_DIAGAUDIOROUTINE_SET = 1;
    public static final int ID_DIAGAUDIOSOURCESELECT_SET = 7;
    public static final int ID_DIAGAUDIOVOLUMELEVEL_SET = 9;
    public static final int ID_DIAGBTSTATUS_SET = 12;
    public static final int ID_DIAGCCPUAHUSWNUMBER_SET = 14;
    public static final int ID_DIAGCELLPHSIGNALSTRENGTH_SET = 13;
    public static final int ID_DIAGDISPLAYSWVER_SET = 15;
    public static final int ID_DIAGECAPPDOWNLOADSTATUS_SET = 8;
    public static final int ID_DIAGLOG959011_SET = 16;
    public static final int ID_DIAGLOG959013_SET = 17;
    public static final int ID_DIAGLOGDTC0x916C04_SET = 4;
    public static final int ID_DIAGLOGDTC0x924611_SET = 6;
    public static final int ID_DIAGLOGDTC0x924613_SET = 5;
    public static final int ID_DIAGOTADUBINFO_SET = 10;
    public static final int ID_DIAGUNLOCKSTATUS_GET = 3;
    public static final int ID_EOL4GADDRESS_GET = 4209;
    public static final int ID_EOL4GMODELONOFF_GET = 4191;
    public static final int ID_EOL4GMODEONOFF_SET = 4197;
    public static final int ID_EOL4GSTRENGTH_GET = 4210;
    public static final int ID_EOLANCMICTESTONOFF_SET = 4199;
    public static final int ID_EOLANCPHONEMICTESTONOFF_GET = 4193;
    public static final int ID_EOLAUDIOBALANCELEVEL_SET = 4126;
    public static final int ID_EOLAUDIOEQONOFF_SET = 4129;
    public static final int ID_EOLAUDIOFADERLEVEL_SET = 4125;
    public static final int ID_EOLAUDIOLOUDNESSSTATUS_SET = 4131;
    public static final int ID_EOLAUDIOMUTESTATUS_SET = 4127;
    public static final int ID_EOLAUDIOOCCPANCYMODE_SET = 4130;
    public static final int ID_EOLAUDIOPATH_SET = 4132;
    public static final int ID_EOLAUDIOSOURCESELECT_SET = 4124;
    public static final int ID_EOLAUDIOSTATUS_GET = 4134;
    public static final int ID_EOLAUDIOSVSTATUS_SET = 4128;
    public static final int ID_EOLAUDIOVOLSOURCE_SET = 4133;
    public static final int ID_EOLAUDIOVRSTATUS_SET = 4123;
    public static final int ID_EOLBTAUDIOPLAYPAUSE_SET = 4101;
    public static final int ID_EOLBTECHOCENCELL_GET = 4110;
    public static final int ID_EOLBTECHOCENCELL_SET = 4109;
    public static final int ID_EOLBTINCOMINGCALL_SET = 4102;
    public static final int ID_EOLBTLICEBSE_GET = 4112;
    public static final int ID_EOLBTLICEBSE_SET = 4177;
    public static final int ID_EOLBTMACADDRESS_GET = 4113;
    public static final int ID_EOLBTMODULEVER_GET = 4108;
    public static final int ID_EOLBTONOFF_SET = 4098;
    public static final int ID_EOLBTPAIREDLIST_SET = 4103;
    public static final int ID_EOLBTPAIREDWITHDEV_GET = 4106;
    public static final int ID_EOLBTPAIREDWITHDEV_SET = 4105;
    public static final int ID_EOLBTPAIRMODEONOFF_SET = 4100;
    public static final int ID_EOLBTPAIRPINCODE_GET = 4107;
    public static final int ID_EOLBTPATH_SET = 4099;
    public static final int ID_EOLBTSIGNALSTRENGTH_GET = 4111;
    public static final int ID_EOLBTSTATUS_GET = 4104;
    public static final int ID_EOLBTTESTMODE_SET = 4097;
    public static final int ID_EOLBUTTONSTATUS_GET = 4194;
    public static final int ID_EOLBUTTONTESTONOFF_SET = 4201;
    public static final int ID_EOLDISPLAYHARDWAREVER_GET = 4205;
    public static final int ID_EOLDISPLAYPARTTERN_SET = 4152;
    public static final int ID_EOLDISPLAYSWVERSION_SET = 4207;
    public static final int ID_EOLDISPLAYTYPE_SET = 4153;
    public static final int ID_EOLFACTORYRESET_SET = 4146;
    public static final int ID_EOLGNSSANTENNACONNECTION_GET = 4186;
    public static final int ID_EOLGNSSDATAINFORMATION_GET = 4187;
    public static final int ID_EOLGPSTIME_GET = 4188;
    public static final int ID_EOLGPSTIME_SET = 4196;
    public static final int ID_EOLGYRODATAINFORMATION2_GET = 4192;
    public static final int ID_EOLGYROTESTSTARTSTOP_SET = 4198;
    public static final int ID_EOLHARDLINETEST_GET = 4195;
    public static final int ID_EOLICCID_SET = 4176;
    public static final int ID_EOLIMEI_GET = 4204;
    public static final int ID_EOLLOADSHED_SET = 4148;
    public static final int ID_EOLLOGIN_SET = 4147;
    public static final int ID_EOLLOWPOWERDISMODE_SET = 4145;
    public static final int ID_EOLPHONEMICTESTONOFF_SET = 4200;
    public static final int ID_EOLPROGRAMNAME_GET = 4206;
    public static final int ID_EOLRVCONOFF_GET = 4189;
    public static final int ID_EOLRVCONOFF_SET = 4190;
    public static final int ID_EOLSTARTPCALIBRATION_SET = 4173;
    public static final int ID_EOLSURROUNDSOUNDMODE_SET = 4203;
    public static final int ID_EOLTESTSCREENILL_SET = 4154;
    public static final int ID_EOLTFTILLONOFF_SET = 4151;
    public static final int ID_EOLTFTSTATUS_GET = 4150;
    public static final int ID_EOLTOUCHPANNELMODE2_GET = 4156;
    public static final int ID_EOLTOUCHPANNELMODE3_GET = 4157;
    public static final int ID_EOLTOUCHPANNELSTATUS_GET = 4155;
    public static final int ID_EOLTPMODULEVERSION_GET = 4149;
    public static final int ID_EOLTPSELFTESTVALLI_GET = 4158;
    public static final int ID_EOLTPTESTCELL_SET = 4160;
    public static final int ID_EOLTPTESTMODE2ONOFF_SET = 4161;
    public static final int ID_EOLTPTESTMODE3ONOFF_SET = 4162;
    public static final int ID_EOLTPTESTONOFF_SET = 4159;
    public static final int ID_EOLTUNERAUTOSTORE_SET = 4143;
    public static final int ID_EOLTUNERBAND_SET = 4141;
    public static final int ID_EOLTUNERCURRENTSTATUS_GET = 4138;
    public static final int ID_EOLTUNERFREQUENCY_SET = 4140;
    public static final int ID_EOLTUNERLIST_GET = 4139;
    public static final int ID_EOLTUNERLIST_SET = 4144;
    public static final int ID_EOLTUNERSEEK_SET = 4142;
    public static final int ID_EOLUSBCURRENTSTATUS_GET = 4183;
    public static final int ID_EOLUSBDESIREDFILETIME_SET = 4182;
    public static final int ID_EOLUSBFORWARDREWIND_SET = 4180;
    public static final int ID_EOLUSBPLAYMODE_SET = 4179;
    public static final int ID_EOLUSBPLAYPAUSE_SET = 4178;
    public static final int ID_EOLUSBPLAYSTATUS_GET = 4184;
    public static final int ID_EOLUSBSKIPTRACK_SET = 4181;
    public static final int ID_EOLUSBSLOT_GET = 4185;
    public static final int ID_EOLVCOMBATTERY_GET = 4171;
    public static final int ID_EOLVCOMBATTERY_SET = 4172;
    public static final int ID_EOLWIFIADDRESS_GET = 4208;
    public static final int ID_EOLWIFIBAND_SET = 4118;
    public static final int ID_EOLWIFICHANNEL_SET = 4119;
    public static final int ID_EOLWIFIMODULEVERSION_GET = 4114;
    public static final int ID_EOLWIFINAME_GET = 4115;
    public static final int ID_EOLWIFIPASSWORD_GET = 4121;
    public static final int ID_EOLWIFIPASSWORD_SET = 4167;
    public static final int ID_EOLWIFIPERNO_GET = 4116;
    public static final int ID_EOLWIFISIGNALSTRENGHT_GET = 4122;
    public static final int ID_EOLWIFISTATUS_GET = 4202;
    public static final int ID_EOLWIFITESTMODE_SET = 4117;
    public static final int ID_EOLWIFITODEFAULTSTATUS_SET = 4168;
    public static final int ID_EOLWIFIWORKINGMODE_SET = 4120;
    public static final int ID_ICCID = 18;
    public static final int ID_IMAX8CAPSIZE_SET = 21;
    public static final int ID_OPERATINGSYSPN_SET = 22;
    public static final int ID_WIFI_MAC_ADDR = 20;
    private static final String TAG = CarYFDiagManager.class.getSimpleName();
    @GuardedBy("mLock")
    private ArraySet<CarVendorExtensionCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarYFDiagManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarYFDiagManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarVendorExtensionCallback[] callbacks;
                        Log.i(CarYFDiagManager.TAG, "CarYFDiagManager on change event ~~~~~~~~~");
                        for (CarVendorExtensionCallback listener : CarYFDiagManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarVendorExtensionCallback[] callbacks;
                        Log.i(CarYFDiagManager.TAG, "CarYFDiagManager on change event ~~~~~~~~~");
                        for (CarVendorExtensionCallback listener : CarYFDiagManager.this.getCallbacks()) {
                            listener.onErrorEvent(propertyId, zone);
                        }
                    }
                });
                this.mCallbacks = new ArraySet<>(1);
            }
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(CarVendorExtensionCallback callback) {
        synchronized (this.mLock) {
            this.mCallbacks.remove(callback);
            if (this.mCallbacks.isEmpty()) {
                this.mPropertyManager.unregisterCallback();
                this.mCallbacks = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarVendorExtensionCallback[] getCallbacks() {
        CarVendorExtensionCallback[] carVendorExtensionCallbackArr;
        synchronized (this.mLock) {
            carVendorExtensionCallbackArr = (CarVendorExtensionCallback[]) this.mCallbacks.toArray(new CarVendorExtensionCallback[this.mCallbacks.size()]);
        }
        return carVendorExtensionCallbackArr;
    }

    public List<CarPropertyConfig> getProperties() throws CarNotConnectedException {
        return this.mPropertyManager.getPropertyList();
    }

    public <E> E getGlobalProperty(Class<E> propertyClass, int propId) throws CarNotConnectedException {
        return (E) getProperty(propertyClass, propId, 0);
    }

    public <E> E getProperty(Class<E> propertyClass, int propId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.getProperty(propertyClass, propId, area).getValue();
    }

    public Boolean getBooleanProperty(int propId, int area) throws CarNotConnectedException {
        return Boolean.valueOf(this.mPropertyManager.getBooleanProperty(propId, area));
    }

    public Float getFloatProperty(int propId, int area) throws CarNotConnectedException {
        return Float.valueOf(this.mPropertyManager.getFloatProperty(propId, area));
    }

    public int getIntProperty(int propId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.getIntProperty(propId, area);
    }

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        Log.i(TAG, "setGlobalProperty");
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        Log.i(TAG, "setProperty");
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    public void setBooleanProperty(int prop, int area, boolean val) throws CarNotConnectedException {
        setProperty(Boolean.class, prop, area, Boolean.valueOf(val));
    }

    public void setByteProperty(int prop, int area, byte[] val) throws CarNotConnectedException {
        setProperty(byte[].class, prop, area, val);
    }

    public void setFloatProperty(int prop, int area, float val) throws CarNotConnectedException {
        setProperty(Float.class, prop, area, Float.valueOf(val));
    }

    public void setIntProperty(int prop, int area, int val) throws CarNotConnectedException {
        setProperty(Integer.class, prop, area, Integer.valueOf(val));
    }

    public void setStringProperty(int prop, int area, String val) throws CarNotConnectedException {
        setProperty(String.class, prop, area, val);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }
}
