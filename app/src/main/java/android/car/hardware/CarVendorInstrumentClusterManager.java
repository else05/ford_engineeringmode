package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarVendorInstrumentClusterManager implements CarManagerBase {
    private static final boolean DBG = true;
    public static final int EVENT_ACTIVE_PROJECTION_MODE_ST = 554699826;
    public static final int EVENT_BT_CALLER_IDENTIFICATION_ST = 554699825;
    public static final int EVENT_BT_CALL_IDENTIFICATION2_ST = 554699827;
    public static final int EVENT_BT_PHONE_NAME_RSP = 554699829;
    public static final int EVENT_CURRENT_STREET_NAME2_ST = 554699819;
    public static final int EVENT_CURRENT_STREET_NAME_ST = 554699816;
    public static final int EVENT_DESTINATION_INFO_ST = 554699815;
    public static final int EVENT_GET_PHONE_NAME_RQ = 554699828;
    public static final int EVENT_GET_TU_PRESENT_INFO_RSP = 554699822;
    public static final int EVENT_INITIATE_BT_CALL_RQ = 554699830;
    public static final int EVENT_INITIATE_BT_CALL_RSP = 554699831;
    public static final int EVENT_LBP1_ITEMINFO_FOR_ONLY_RADIO_RSP = 554699861;
    public static final int EVENT_LBP1_ITEMINFO_RSP = 554699832;
    public static final int EVENT_LBP3_ITEMINFO_FOR_ONLY_RADIO_RSP = 554699863;
    public static final int EVENT_MDE_MEIDAPLAYER_REPEATER_ST = 554699824;
    public static final int EVENT_MEDIA_INFORMATION2_ST = 554699823;
    public static final int EVENT_MEDIA_INFORMATION_ST = 554699821;
    public static final int EVENT_NAVIGATION_SYMBOL_INFO_ST = 554699817;
    public static final int EVENT_STREET_NAME2_ST = 554699820;
    public static final int EVENT_STREET_NAME_ST = 554699818;
    public static final int INFO_VEHICLE_MODEL = 560991318;
    public static final int LBS1_MEDIA_LIST_UPDATE = 561015896;
    public static final int LBS2_RADIO_LIST_UPDATE = 561015897;
    public static final int SIGNAL_ACU_ARResponse_Rsp = 557845540;
    public static final int SIGNAL_BT_END_TEL_SERVICE_RQ = 560991258;
    public static final int SIGNAL_BT_END_TEL_SERVICE_RSP = 557845531;
    public static final int SIGNAL_BT_INCOMMING_CALL_RQ = 560991260;
    public static final int SIGNAL_BT_INCOMMING_CALL_RSP = 557845533;
    public static final int SIGNAL_CANCEL_CURR_WAY_PT_RQ = 560991243;
    public static final int SIGNAL_CANCEL_ROUTE_RQ = 560991242;
    public static final int SIGNAL_MC_BTINCALLOPTIONS_RQ = 561016832;
    public static final int SIGNAL_MC_BTINCALLOPTIONS_RSQ = 561016833;
    public static final int SIGNAL_MD_DURATION = 557845519;
    public static final int SIGNAL_MD_NUMBER_OF_TRACK = 557845518;
    public static final int SIGNAL_MD_PROGRESS = 557845520;
    public static final int SIGNAL_MD_REPEAT = 557845586;
    public static final int SIGNAL_MD_SHUF = 557845587;
    public static final int SIGNAL_MD_SRC_NAME = 557845585;
    public static final int SIGNAL_MD_TRACK_NUMBER = 557845517;
    public static final int SIGNAL_MICO_PHONE_MUTE_RQ = 560991262;
    public static final int SIGNAL_MICO_PHONE_MUTE_RSP = 557845535;
    public static final int SIGNAL_NAV_ACTIVE_STATUS = 557845510;
    public static final int SIGNAL_NAV_ERROR_STATUS = 557845513;
    public static final int SIGNAL_NAV_REMAIN_DAY = 557845506;
    public static final int SIGNAL_NAV_REMAIN_DIS = 557845505;
    public static final int SIGNAL_NAV_REMAIN_DIS_UNIT = 557845504;
    public static final int SIGNAL_NAV_REMAIN_HOURS = 557845507;
    public static final int SIGNAL_NAV_REMAIN_MIN = 557845508;
    public static final int SIGNAL_NAV_WAY_PT_ACTIVE_STATUS = 557845511;
    public static final int SIGNAL_NAV_WAY_PT_CANCEL_STATUS = 557845512;
    public static final int SIGNAL_NW_ACU_AR = 557845541;
    public static final int SIGNAL_NW_ACU_RU = 557845539;
    public static final int SIGNAL_PH_BT_CONNECT_STATUS = 557845524;
    public static final int SIGNAL_PH_BT_INCALL_STUTAS = 557845522;
    public static final int SIGNAL_PH_CALL_DURATION = 557845523;
    public static final int SIGNAL_PH_COMMUNICATE_STATUS = 557845528;
    public static final int SIGNAL_PH_DEVICE_NUMBER = 557845525;
    public static final int SIGNAL_PH_NET_STATUS = 557845526;
    public static final int SIGNAL_PH_POWER_STATUS = 557845527;
    public static final int SIGNAL_PH_SIGNAL_STRENGTH = 557845529;
    public static final int SIGNAL_REPEAD_GUIDDANCE_RQ = 560991244;
    public static final int SIGNAL_RX_NW_LBCLIENT1_REQUEST = 560991270;
    public static final int SIGNAL_RX_NW_LBCLIENT3_REQUEST = 560991316;
    public static final int SIGNAL_SET_TUNER_BAND_RQ = 560991264;
    public static final int SIGNAL_SWITCH_MEDIA_SOURCE_RQ = 560991265;
    public static final int SIGNAL_SWITCH_PHONE_MODEl_RQ = 560991266;
    public static final int SIGNAL_SWITCH_PROGRAM_RQ = 560991249;
    private static final String TAG = CarVendorInstrumentClusterManager.class.getSimpleName();
    private static final int VEHICLE_C519_TYPE = 2;
    private static final int VEHICLE_CD391_TYPE = 4;
    private static final int VEHICLE_CD539_TYPE = 1;
    private static final int VEHICLE_CX482_TYPE = 5;
    private static final int VEHICLE_CX483_TYPE = 6;
    private static final int VEHICLE_D568_TYPE = 3;
    private static final int VEHICLE_DEFAULT_TYPE = 0;
    private static final int VEHICLE_U540_TYPE = 9;
    private static final int VEHICLE_U611_TYPE = 8;
    private static final int VEHICLE_U625_TYPE = 7;
    @GuardedBy("mLock")
    private ArraySet<CarVendorExtensionCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarVendorInstrumentClusterManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarVendorInstrumentClusterManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarVendorExtensionCallback[] callbacks;
                        for (CarVendorExtensionCallback listener : CarVendorInstrumentClusterManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarVendorExtensionCallback[] callbacks;
                        for (CarVendorExtensionCallback listener : CarVendorInstrumentClusterManager.this.getCallbacks()) {
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

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    public void sendEvent(int eventType, Bundle bundle) throws CarNotConnectedException {
        try {
            switch (eventType) {
                case 554699815:
                    String value = "SignalID:0x77;field:{";
                    bundle.getString("Totaltime");
                    String value2 = (((value + "DistUnits:" + bundle.getString("DistUnits")) + "};") + "TotalDistTraveled:" + bundle.getString("TotalDistTraveled") + ";") + "Destination:" + bundle.getString("Destination") + ";";
                    Log.d(TAG, value2);
                    setGlobalProperty(String.class, 554699815, value2);
                    return;
                case 554699816:
                    String value3 = "SignalID:0x78;SpeedLimit:" + bundle.getString("SpeedLimit") + ";";
                    String value4 = value3 + "CurrentStreetName:" + bundle.getString("CurrentStreetName") + ";";
                    Log.d(TAG, value4);
                    setGlobalProperty(String.class, 554699816, value4);
                    return;
                case 554699817:
                    String value5 = "SignalID:0x22;field:{";
                    String value6 = ((((((value5 + "UnitOfLength:" + bundle.getString("UnitOfLength") + ";") + "PropertyOfDistance:" + bundle.getString("PropertyOfDistance") + ";") + "};") + "DistanceToNextManeuver:" + bundle.getString("DistanceToNextManeuver") + ";") + "BargraphSteps:" + bundle.getString("BargraphSteps") + ";") + "NumberOfStreetSegments:" + bundle.getString("NumberOfStreetSegments") + ";") + "StreetSegments:" + bundle.getString("StreetSegments") + ";";
                    Log.d(TAG, value6);
                    setGlobalProperty(String.class, 554699817, value6);
                    return;
                case 554699818:
                    String value7 = "SignalID:0x20;StreetName:" + bundle.getString("StreetName") + ";";
                    Log.d(TAG, value7);
                    setGlobalProperty(String.class, 554699818, value7);
                    return;
                case 554699819:
                    String value8 = "SignalID:0xAD;SpeedLimit:" + bundle.getString("SpeedLimit") + ";";
                    String value9 = value8 + "CurentStreetName2:" + bundle.getString("CurentStreetName2") + ";";
                    Log.d(TAG, value9);
                    setGlobalProperty(String.class, 554699819, value9);
                    return;
                case 554699820:
                    String value10 = "SignalID:0xAC;StreetName2:" + bundle.getString("StreetName2") + ";";
                    Log.d(TAG, value10);
                    setGlobalProperty(String.class, 554699820, value10);
                    return;
                case 554699821:
                    String value11 = "SignalID:0x79;MetadataIcon_1:" + bundle.getString("MetadataIcon_1") + ";";
                    String value12 = (((value11 + "MetadataIcon_2:" + bundle.getString("MetadataIcon_2") + ";") + "Metadata1:" + bundle.getString("Metadata1") + ";") + "Metadata2:" + bundle.getString("Metadata2") + ";") + "SourceInformation:" + bundle.getString("SourceInformation") + ";";
                    Log.d(TAG, value12);
                    setGlobalProperty(String.class, 554699821, value12);
                    return;
                case 554699822:
                    Log.d(TAG, "SignalID:0x5F;");
                    setGlobalProperty(String.class, 554699822, "SignalID:0x5F;");
                    return;
                case 554699823:
                    String value13 = "SignalID:0xB3;MetadataIcon_1:" + bundle.getString("MetadataIcon_1") + ";";
                    String value14 = (((value13 + "MetadataIcon_2:" + bundle.getString("MetadataIcon_2") + ";") + "Metadata1:" + bundle.getString("Metadata1") + ";") + "Metadata2:" + bundle.getString("Metadata2") + ";") + "SourceInformation:" + bundle.getString("SourceInformation") + ";";
                    Log.d(TAG, value14);
                    setGlobalProperty(String.class, 554699823, value14);
                    return;
                case 554699824:
                    Log.d(TAG, "SignalID:0xA9;");
                    setGlobalProperty(String.class, 554699824, "SignalID:0xA9;");
                    return;
                case 554699825:
                    String value15 = "SignalID:0x50;field:{";
                    String value16 = (((((((value15 + "BTDevie_Index:" + bundle.getString("BTDevie_Index") + ";") + "};") + "field1:{") + "PhoneType:" + bundle.getString("PhoneType") + ";") + "Validity:" + bundle.getString("Validity") + ";") + "};") + "CallIDNumber:" + bundle.getString("CallIDNumber") + ";") + "CallIDName:" + bundle.getString("CallIDName") + ";";
                    Log.d(TAG, value16);
                    setGlobalProperty(String.class, 554699825, value16);
                    return;
                case 554699826:
                    String value17 = "SignalID:0xA7;field:{";
                    String value18 = ((value17 + "ActiveProjectionMode:" + bundle.getString("ActiveProjectionMode") + ";") + "};") + "ProjectionModeName:" + bundle.getString("ProjectionModeName") + ";";
                    Log.d(TAG, value18);
                    setGlobalProperty(String.class, 554699826, value18);
                    return;
                case 554699827:
                    String value19 = "SignalID:0xB2;field:{";
                    String value20 = ((((((value19 + "BTDevie_Index:" + bundle.getString("BTDevie_Index") + ";") + "field1:{") + "PhoneType:" + bundle.getString("PhoneType") + ";") + "Validity:" + bundle.getString("Validity") + ";") + "};") + "CallIDNumber:" + bundle.getString("CallIDNumber") + ";") + "CallIDName:" + bundle.getString("CallIDName") + ";";
                    Log.d(TAG, value20);
                    setGlobalProperty(String.class, 554699827, value20);
                    return;
                case 554699828:
                case 554699830:
                default:
                    Log.d(TAG, "default");
                    return;
                case 554699829:
                    String value21 = "SignalID:0xB5;BTPhoneName:" + bundle.getString("BTPhoneName") + ";";
                    Log.d(TAG, value21);
                    setGlobalProperty(String.class, 554699829, value21);
                    return;
                case 554699831:
                    String value22 = "SignalID:0x4F;CES:" + bundle.getString("CES") + ";";
                    Log.d(TAG, value22);
                    setGlobalProperty(String.class, 554699831, value22);
                    return;
                case 554699832:
                    String value23 = "SignalID:0x76;Utilization:" + bundle.getString("Utilization") + ";";
                    String value24 = (((((((value23 + "CES:" + bundle.getString("CES") + ";") + "OpCodeRsp:" + bundle.getString("OpCodeRsp") + ";") + "RspListServ:" + bundle.getString("RspListServ") + ";") + "ActiveListID:" + bundle.getString("ActiveListID") + ";") + "ParentListID:" + bundle.getString("ParentListID") + ";") + "NbrOfItemsRtn:" + bundle.getString("NbrOfItemsRtn") + ";") + "NbrOfItemsInSelection:" + bundle.getString("NbrOfItemsInSelection") + ";") + "List:" + bundle.getString("List") + ";";
                    if (bundle.getString("RspListServ").equals("0x05") && true == isPhase2Project()) {
                        Log.d(TAG, "The current list is phase2 cluster radio, so it needs to send through the 2B1 signal!");
                        setGlobalProperty(String.class, 554699861, value24);
                        setGlobalProperty(String.class, 554699863, value24);
                    } else if (bundle.getString("RspListServ").equals("0x0A") && true == isPhase2Project()) {
                        Log.d(TAG, "The current list is phase2 racm radio, so it needs to send through the 255 signal!");
                        setGlobalProperty(String.class, 554699863, value24);
                    } else {
                        Log.d(TAG, "The current list isn't phase2 radio, so it needs to send through the 2B7 signal!");
                        setGlobalProperty(String.class, 554699832, value24);
                    }
                    Log.d(TAG, value24);
                    return;
            }
        } catch (Exception e) {
        }
    }

    private boolean isPhase2Project() {
        try {
            int vehicleModel = ((byte[]) getGlobalProperty(byte[].class, 560991318))[0];
            String str = TAG;
            Log.d(str, " vehicle model is " + vehicleModel);
            switch (vehicleModel) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    return false;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return true;
                default:
                    String str2 = TAG;
                    Log.d(str2, "input vehicle type[" + vehicleModel + "] is not supported");
                    return false;
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }
}
