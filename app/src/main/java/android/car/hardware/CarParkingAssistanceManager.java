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
public final class CarParkingAssistanceManager implements CarManagerBase {
    public static final int APA_DRV_ASST_MNU_NOT_PRESSED = 0;
    public static final int APA_DRV_ASST_MNU_PRESSED = 1;
    public static final int APA_GEAR_RVRSE_ACTIVE_CONFIRMED = 3;
    public static final int APA_GEAR_RVRSE_ACTIVE_NOT_CONFIRMED = 2;
    public static final int APA_GEAR_RVRSE_INACTIVE_CONFIRMED = 1;
    public static final int APA_GEAR_RVRSE_INACTIVE_NOT_CONFIRMED = 0;
    public static final int APA_MODE_STATE_FAULTY = 7;
    public static final int APA_MODE_STATE_INACTIVE = 0;
    public static final int APA_MODE_STATE_NOTUSED1 = 4;
    public static final int APA_MODE_STATE_NOTUSED2 = 5;
    public static final int APA_MODE_STATE_OFF = 6;
    public static final int APA_MODE_STATE_POA = 3;
    public static final int APA_MODE_STATE_PPA = 2;
    public static final int APA_MODE_STATE_SAPP = 1;
    public static final int APA_PRK_AID_FRONT_DISABLED = 0;
    public static final int APA_PRK_AID_FRONT_ENABLED = 1;
    public static final int APA_PRK_AID_FRONT_FAULTED = 3;
    public static final int APA_PRK_AID_FRONT_UNUSED = 2;
    public static final int APA_PRK_AID_REAR_DISABLED = 0;
    public static final int APA_PRK_AID_REAR_ENABLED = 1;
    public static final int APA_PRK_AID_REAR_FAULTED = 3;
    public static final int APA_PRK_AID_REAR_UNUSED = 2;
    public static final int APA_PRK_AID_SWTCH_NOT_PRESSED = 0;
    public static final int APA_PRK_AID_SWTCH_PRESSED = 1;
    public static final int APA_PRK_AID_SWTCH_RQ_MENU_NOT_PRESSED = 0;
    public static final int APA_PRK_AID_SWTCH_RQ_MENU_PRESSED = 1;
    public static final int APA_REAR_CAMRA_NOT_PRESSED = 0;
    public static final int APA_REAR_CAMRA_PRESSED = 1;
    public static final int APA_SCAN_SIDE_LEFT = 1;
    public static final int APA_SCAN_SIDE_NOSIDE = 3;
    public static final int APA_SCAN_SIDE_NULL = 0;
    public static final int APA_SCAN_SIDE_RIGHT = 2;
    public static final int APA_SWTCH_MNU_NOTUSED1 = 2;
    public static final int APA_SWTCH_MNU_NOTUSED2 = 3;
    public static final int APA_SWTCH_MNU_NOT_PRESSED = 0;
    public static final int APA_SWTCH_MNU_PRESSED = 1;
    public static final int APA_SYSTEM_ACCESSORY_CMD_CHECKFOROBJECT = 4;
    public static final int APA_SYSTEM_ACCESSORY_CMD_CLOSEDOOR = 7;
    public static final int APA_SYSTEM_ACCESSORY_CMD_NOREQUEST = 1;
    public static final int APA_SYSTEM_ACCESSORY_CMD_NULL = 0;
    public static final int APA_SYSTEM_ACCESSORY_CMD_PRESSAPABUTTON = 3;
    public static final int APA_SYSTEM_ACCESSORY_CMD_SELECTSIDE = 2;
    public static final int APA_SYSTEM_ACCESSORY_CMD_SELECTSIDELEFT = 5;
    public static final int APA_SYSTEM_ACCESSORY_CMD_SELECTSIDERIGHT = 6;
    public static final int APA_SYSTEM_ACTIVE_SIDE_DRIVER_SIDE = 3;
    public static final int APA_SYSTEM_ACTIVE_SIDE_NO_SIDE = 1;
    public static final int APA_SYSTEM_ACTIVE_SIDE_NULL = 0;
    public static final int APA_SYSTEM_ACTIVE_SIDE_PASSENGER_SIDE = 2;
    public static final int APA_SYSTEM_DRIVE_CMD_DRIVEBACKWARD = 4;
    public static final int APA_SYSTEM_DRIVE_CMD_DRIVEFORWARD = 3;
    public static final int APA_SYSTEM_DRIVE_CMD_NOREQUEST = 1;
    public static final int APA_SYSTEM_DRIVE_CMD_NOTUSED_2 = 6;
    public static final int APA_SYSTEM_DRIVE_CMD_NOTUSED_3 = 7;
    public static final int APA_SYSTEM_DRIVE_CMD_NULL = 0;
    public static final int APA_SYSTEM_DRIVE_CMD_RELEASEBRAKE = 5;
    public static final int APA_SYSTEM_DRIVE_CMD_STOP = 2;
    public static final int APA_SYSTEM_GEAR_CMD_NOREQUEST = 1;
    public static final int APA_SYSTEM_GEAR_CMD_NOTUSED_1 = 6;
    public static final int APA_SYSTEM_GEAR_CMD_NOTUSED_2 = 7;
    public static final int APA_SYSTEM_GEAR_CMD_NULL = 0;
    public static final int APA_SYSTEM_GEAR_CMD_SHIFT_TO_D = 3;
    public static final int APA_SYSTEM_GEAR_CMD_SHIFT_TO_N = 4;
    public static final int APA_SYSTEM_GEAR_CMD_SHIFT_TO_P = 5;
    public static final int APA_SYSTEM_GEAR_CMD_SHIFT_TO_R = 2;
    public static final int APA_SYSTEM_MODE_AVAIL_NONE = 1;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED1 = 9;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED2 = 10;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED3 = 11;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED4 = 12;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED5 = 13;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED6 = 14;
    public static final int APA_SYSTEM_MODE_AVAIL_NOTUSED7 = 15;
    public static final int APA_SYSTEM_MODE_AVAIL_NULL = 0;
    public static final int APA_SYSTEM_MODE_AVAIL_POA = 6;
    public static final int APA_SYSTEM_MODE_AVAIL_PPA = 5;
    public static final int APA_SYSTEM_MODE_AVAIL_PPA_POA = 8;
    public static final int APA_SYSTEM_MODE_AVAIL_SAPP = 4;
    public static final int APA_SYSTEM_MODE_AVAIL_SAPP_POA = 7;
    public static final int APA_SYSTEM_MODE_AVAIL_SAPP_PPA = 2;
    public static final int APA_SYSTEM_MODE_AVAIL_SAPP_PPA_POA = 3;
    public static final int APA_SYSTEM_MODE_NOTUSED1 = 5;
    public static final int APA_SYSTEM_MODE_NOTUSED2 = 6;
    public static final int APA_SYSTEM_MODE_NOTUSED3 = 7;
    public static final int APA_SYSTEM_MODE_NULL = 0;
    public static final int APA_SYSTEM_MODE_OFF = 1;
    public static final int APA_SYSTEM_MODE_POA = 4;
    public static final int APA_SYSTEM_MODE_PPA = 3;
    public static final int APA_SYSTEM_MODE_SAPP = 2;
    public static final int APA_SYSTEM_MSG_TXT_ACCELPEDALINACTIVE = 9;
    public static final int APA_SYSTEM_MSG_TXT_BRAKINGACITVE = 6;
    public static final int APA_SYSTEM_MSG_TXT_HIGHINCLINATION = 5;
    public static final int APA_SYSTEM_MSG_TXT_NONE = 1;
    public static final int APA_SYSTEM_MSG_TXT_NOTUSED = 10;
    public static final int APA_SYSTEM_MSG_TXT_NULL = 0;
    public static final int APA_SYSTEM_MSG_TXT_SPEEDLIMITEXCEEDED = 4;
    public static final int APA_SYSTEM_MSG_TXT_STEERINGINTERACTION = 7;
    public static final int APA_SYSTEM_MSG_TXT_TCSDISABLE = 3;
    public static final int APA_SYSTEM_MSG_TXT_WHEELSLIP = 2;
    public static final int APA_SYSTEM_MSG_TXT_WRONGDIRECTION = 8;
    public static final int APA_SYSTEM_OPERATIONAL_APACANCELLED = 4;
    public static final int APA_SYSTEM_OPERATIONAL_FAULTY = 7;
    public static final int APA_SYSTEM_OPERATIONAL_FINISHED = 6;
    public static final int APA_SYSTEM_OPERATIONAL_NOTACCESSIBLE = 5;
    public static final int APA_SYSTEM_OPERATIONAL_NULL = 0;
    public static final int APA_SYSTEM_OPERATIONAL_OFF = 1;
    public static final int APA_SYSTEM_OPERATIONAL_ON = 2;
    public static final int APA_SYSTEM_OPERATIONAL_VERSPEED = 3;
    public static final int APA_SYSTEM_PARK_SLOT_STS_NOPARKSLOT = 1;
    public static final int APA_SYSTEM_PARK_SLOT_STS_NULL = 0;
    public static final int APA_SYSTEM_PARK_SLOT_STS_PARKSLOTFOUND = 2;
    public static final int APA_SYSTEM_PARK_SLOT_STS_PARKSLOTREADY = 3;
    public static final int APA_SYSTEM_POA_STS_NOTCONFIGURED = 3;
    public static final int APA_SYSTEM_POA_STS_NOTSELECTABLE = 2;
    public static final int APA_SYSTEM_POA_STS_NULL = 0;
    public static final int APA_SYSTEM_POA_STS_SELECTABLE = 1;
    public static final int APA_SYSTEM_PPA_STS_NOTCONFIGURED = 3;
    public static final int APA_SYSTEM_PPA_STS_NOTSELECTABLE = 2;
    public static final int APA_SYSTEM_PPA_STS_NULL = 0;
    public static final int APA_SYSTEM_PPA_STS_SELECTABLE = 1;
    public static final int APA_SYSTEM_SAPP_STS_NOTCONFIGURED = 3;
    public static final int APA_SYSTEM_SAPP_STS_NOTSELECTABLE = 2;
    public static final int APA_SYSTEM_SAPP_STS_NULL = 0;
    public static final int APA_SYSTEM_SAPP_STS_SELECTABLE = 1;
    public static final int APA_SYSTEM_SCAN_MODE_NOTSCANNING = 1;
    public static final int APA_SYSTEM_SCAN_MODE_NULL = 0;
    public static final int APA_SYSTEM_SCAN_MODE_SCANNING = 2;
    public static final int APA_SYSTEM_SCAN_MODE_STEERING = 3;
    public static final int APA_SYSTEM_STEERING_CMD_NOREQUEST = 1;
    public static final int APA_SYSTEM_STEERING_CMD_NULL = 0;
    public static final int APA_SYSTEM_STEERING_CMD_REMOVEHANDS = 2;
    public static final int APA_SYSTEM_STEERING_CMD_TAKECONTROL = 3;
    public static final int APA_SYSTEM_TAR_DISTANCE_OFF = 0;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP1 = 1;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP10 = 10;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP11 = 11;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP12 = 12;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP13 = 13;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP14 = 14;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP15 = 15;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP2 = 2;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP3 = 3;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP4 = 4;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP5 = 5;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP6 = 6;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP7 = 7;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP8 = 8;
    public static final int APA_SYSTEM_TAR_DISTANCE_STEP9 = 9;
    public static final int APA_VIDEO_VIEW_SWITCH_NOT_PRESSED = 0;
    public static final int APA_VIDEO_VIEW_SWITCH_PRESSED = 1;
    public static final int CAMERA_BACK = 4;
    public static final int CAMERA_BACK_WIDE = 5;
    public static final int CAMERA_CTRL_REMOVE_COVER_deault = 1;
    public static final int CAMERA_CTRL_REQUEST_deafault = 1;
    public static final int CAMERA_CTRL_RESPONSE_noReverse = 0;
    public static final int CAMERA_CTRL_RESPONSE_reversing_no_remove = 2;
    public static final int CAMERA_CTRL_RESPONSE_reversing_remove = 1;
    public static final int CAMERA_CTRL_RElEASE_default = 1;
    public static final int CAMERA_FRONT = 2;
    public static final int CAMERA_FRONT_WIDE = 1;
    public static final int CAMERA_PANORAMIC = 3;
    public static final int CTA_SIGNAL_STATE_OFF = 0;
    public static final int CTA_SIGNAL_STATE_ON = 1;
    private static final boolean DBG = true;
    public static final int ID_APA_ACCESSORY_CMD = 557845433;
    public static final int ID_APA_ACTIVE_SIDE = 557845440;
    public static final int ID_APA_DRIVE_CMD = 557845430;
    public static final int ID_APA_DRV_ASST_MNU = 557843123;
    public static final int ID_APA_Frnt_CAMRA = 557843133;
    public static final int ID_APA_GEAR_CMD = 557845431;
    public static final int ID_APA_GEAR_RVRSE = 557845442;
    public static final int ID_APA_INIT = 557845441;
    public static final int ID_APA_MODE_AVAIL = 557845439;
    public static final int ID_APA_MODE_STATE_REQ = 557843111;
    public static final int ID_APA_MSG_TXT = 557845435;
    public static final int ID_APA_PARK_SLOT_STS = 557845429;
    public static final int ID_APA_POA_STS = 557845428;
    public static final int ID_APA_PPA_STS = 557845427;
    public static final int ID_APA_PRK_AID_FRONT = 557843126;
    public static final int ID_APA_PRK_AID_REAR = 557843127;
    public static final int ID_APA_PRK_AID_SWTCH = 557843124;
    public static final int ID_APA_PRK_AID_SWTCH_RQ_MENU = 557843125;
    public static final int ID_APA_REAR_CAMRA = 557843122;
    public static final int ID_APA_SAPP_STS = 557845426;
    public static final int ID_APA_SCAN_MODE_STS = 557845424;
    public static final int ID_APA_SCAN_SIDE = 557845425;
    public static final int ID_APA_STEERING_CMD = 557845432;
    public static final int ID_APA_SWTCH_MNU = 557843128;
    public static final int ID_APA_SYSTEM_OPT_STS = 557843114;
    public static final int ID_APA_SYSTEM_STATUS = 557843112;
    public static final int ID_APA_TAR_DISTANCE = 557845434;
    public static final int ID_APA_VIDEO_VIEW_SWITCH = 557846546;
    public static final int ID_BAIDU_TO_YFVE_CAMERA_CTRL_REMOVE_COVER = 289411193;
    public static final int ID_BAIDU_TO_YFVE_CAMERA_CTRL_REQUEST = 289411192;
    public static final int ID_BPA_STAT_SETTING = 560988864;
    public static final int ID_CTA_LEFT_SIGNAL = 557843113;
    public static final int ID_CTA_LEFT_STAT = 557843138;
    public static final int ID_CTA_RIGHT_SIGNAL = 557845438;
    public static final int ID_CTA_RIGHT_STAT = 557843139;
    public static final int ID_CURRENT_VIDEO_STS = 557843107;
    public static final int ID_DIGITAL_OVERLAY = 557843135;
    public static final int ID_DIGITAL_ZOOM = 557843134;
    public static final int ID_PARKING_ASSISTANCE_STATUS_CODE = 557843110;
    public static final int ID_RADAR_SIGNAL = 557908640;
    public static final int ID_RADAR_STATE_SET = 557843137;
    public static final int ID_RADAR_STATUS = 557843105;
    public static final int ID_RBA_ALRT_DSPLY = 557843129;
    public static final int ID_RBA_MNU = 557843130;
    public static final int ID_RBA_RQ = 557843132;
    public static final int ID_RBA_STAT = 557843131;
    public static final int ID_SOD_LEFT_STAT = 557843140;
    public static final int ID_SOD_RIGHT_STAT = 557843141;
    public static final int ID_VIDEO_SWITCH = 560988834;
    public static final int ID_YFVE_TO_BAIDU_CAMERA_CTRL_RESPONSE = 289411200;
    public static final int ID_YFVE_TO_BAIDU_CAMERA_CTRL_RElEASE = 289411201;
    public static final int ID_ZOOM_LEVEL_SELECT = 557843108;
    public static final int ID_ZOOM_LEVEL_STATUS = 557843109;
    public static final int RADAR_DISTANCE_NOOBJECTINSECTOR = 13;
    public static final int RADAR_DISTANCE_OFF = 0;
    public static final int RADAR_DISTANCE_ZONE_1 = 1;
    public static final int RADAR_DISTANCE_ZONE_10 = 10;
    public static final int RADAR_DISTANCE_ZONE_11 = 11;
    public static final int RADAR_DISTANCE_ZONE_12 = 12;
    public static final int RADAR_DISTANCE_ZONE_13 = 13;
    public static final int RADAR_DISTANCE_ZONE_14 = 14;
    public static final int RADAR_DISTANCE_ZONE_15 = 15;
    public static final int RADAR_DISTANCE_ZONE_2 = 2;
    public static final int RADAR_DISTANCE_ZONE_3 = 3;
    public static final int RADAR_DISTANCE_ZONE_4 = 4;
    public static final int RADAR_DISTANCE_ZONE_5 = 5;
    public static final int RADAR_DISTANCE_ZONE_6 = 6;
    public static final int RADAR_DISTANCE_ZONE_7 = 7;
    public static final int RADAR_DISTANCE_ZONE_8 = 8;
    public static final int RADAR_DISTANCE_ZONE_9 = 9;
    public static final int RADAR_STATUS_ALL_PARK_SENSORS_OFF = 0;
    public static final int RADAR_STATUS_ALL_SNS_BLK = 15;
    public static final int RADAR_STATUS_FAIL_MODE_NO_CHIME = 10;
    public static final int RADAR_STATUS_FAIL_MODE_WITH_CHIME = 9;
    public static final int RADAR_STATUS_NOTUSED2 = 4;
    public static final int RADAR_STATUS_NOTUSED3 = 7;
    public static final int RADAR_STATUS_NOT_AVAIL_TRLR_ATTCHD = 11;
    public static final int RADAR_STATUS_PARK_SYS_ALTERNATE_MODE = 6;
    public static final int RADAR_STATUS_RESET_MESSAGE_WARN = 3;
    public static final int RADAR_STATUS_R_SNSRS_OFF_F_SNSRS_ON = 2;
    public static final int RADAR_STATUS_R_SNSRS_ON_F_SNSRS_OFF = 1;
    public static final int RADAR_STATUS_R_SNSRS_ON_F_SNSRS_ON = 5;
    public static final int RADAR_STATUS_R_SNS_BLK_F_SNS_ON = 13;
    public static final int RADAR_STATUS_R_SNS_INACTIVE_TRLR_ATCH = 12;
    public static final int RADAR_STATUS_R_SNS_ON_F_SNS_BLK = 14;
    public static final int RADAR_STATUS_R_SNS_TRLR_F_SNS_BLK = 8;
    public static final int RARAR_DISTANCE_NOTFULLYSCANNEDYET = 14;
    public static final int RARAR_DISTANCE_NOTUSED = 15;
    public static final int RBA_ALRT_DSPLY_BOTH = 3;
    public static final int RBA_ALRT_DSPLY_GRAPHIC = 1;
    public static final int RBA_ALRT_DSPLY_OFF = 0;
    public static final int RBA_ALRT_DSPLY_TEXT = 2;
    public static final int RBA_MNU_ACTIVE = 2;
    public static final int RBA_MNU_INACTIVE = 1;
    public static final int RBA_MNU_NONE = 0;
    public static final int RBA_MNU_UNUSED = 3;
    public static final int RBA_RQ_NULL = 0;
    public static final int RBA_RQ_OFF = 1;
    public static final int RBA_RQ_ON = 2;
    public static final int RBA_RQ_UNUSED = 3;
    public static final int RBA_STAT_DISABLED = 2;
    public static final int RBA_STAT_OFF = 0;
    public static final int RBA_STAT_ON = 1;
    public static final int RBA_STAT_UNUSED = 3;
    private static final String TAG = CarParkingAssistanceManager.class.getSimpleName();
    public static final int ZOOM_LEVEL_STATE_INVALID = 6;
    public static final int ZOOM_LEVEL_STATE_LEVEL_I = 1;
    public static final int ZOOM_LEVEL_STATE_LEVEL_II = 2;
    public static final int ZOOM_LEVEL_STATE_LEVEL_III = 3;
    public static final int ZOOM_LEVEL_STATE_LEVEL_IV = 4;
    public static final int ZOOM_LEVEL_STATE_LEVEL_V = 5;
    public static final int ZOOM_LEVEL_STATE_OFF = 0;
    public static final int ZOOM_LEVEL_STATE_UNKNOWN = 7;
    @GuardedBy("mLock")
    private ArraySet<CarVendorExtensionCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarParkingAssistanceManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarParkingAssistanceManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarVendorExtensionCallback[] callbacks;
                        Log.e(CarParkingAssistanceManager.TAG, "car parking assistance on change event PropertyId:" + value.getPropertyId());
                        for (CarVendorExtensionCallback listener : CarParkingAssistanceManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarVendorExtensionCallback[] callbacks;
                        Log.e(CarParkingAssistanceManager.TAG, "car parking assistance on error event ~~~~~~~~~");
                        for (CarVendorExtensionCallback listener : CarParkingAssistanceManager.this.getCallbacks()) {
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
        String str = TAG;
        Log.e(str, "getProperty_propId_Value: " + propId + "_" + this.mPropertyManager.getProperty(propertyClass, propId, area).getValue());
        return this.mPropertyManager.getProperty(propertyClass, propId, area).getValue();
    }

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        Log.e(TAG, "car parking assistance manager on disconnected~~~~~~~~ ");
        this.mPropertyManager.onCarDisconnected();
    }
}
