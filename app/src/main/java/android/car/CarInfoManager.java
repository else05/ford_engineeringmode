package android.car;

import android.car.ICarInfo;
import android.car.annotation.FutureFeature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;

/* loaded from: classes2.dex */
public final class CarInfoManager implements CarManagerBase {
    public static final int BASIC_INFO_KEY_ANIMATION = 117440518;
    public static final int BASIC_INFO_KEY_BODY_STYLE = 117440515;
    public static final int BASIC_INFO_KEY_COLOR = 117440517;
    public static final int BASIC_INFO_KEY_DISPLAY_VARIANTS = 117440520;
    public static final int BASIC_INFO_KEY_ESN = 117440592;
    public static final int BASIC_INFO_KEY_HMI = 117440519;
    public static final String BASIC_INFO_KEY_MANUFACTURER = "android.car.manufacturer";
    public static final String BASIC_INFO_KEY_MODEL = "android.car.model";
    public static final int BASIC_INFO_KEY_MODEL_VENDOR = 117440513;
    public static final String BASIC_INFO_KEY_MODEL_YEAR = "android.car.model-year";
    public static final int BASIC_INFO_KEY_MODEL_YEAR_VENDOR = 117440514;
    public static final int BASIC_INFO_KEY_SERIES = 117440516;
    public static final String BASIC_INFO_KEY_VEHICLE_ID = "android.car.vehicle-id";
    public static final int BASIC_INFO_KEY_VIN = 117440591;
    public static final int DEFAULT_VALUE = 17;
    public static final int ID_INFO_ACC_MENU = 117440622;
    public static final int ID_INFO_ADAPTIVE_CRUISE = 117440679;
    public static final int ID_INFO_ADJ_SPEED_LIMITER_DEVICE = 117440624;
    public static final int ID_INFO_ADPT_HEADLAMPS_FEATURE = 117440674;
    public static final int ID_INFO_ADPT_HEAD_LAMP_CONTROL = 117440623;
    public static final int ID_INFO_ADPT_HEAD_LAMP_TRAFFIC = 117440629;
    public static final int ID_INFO_ADVANCETRAC_CONTROL = 117440625;
    public static final int ID_INFO_ADVTRAC_HARD_BUTTON_CONTROL = 117440677;
    public static final int ID_INFO_AES_NO_OVERRIDE = 117440620;
    public static final int ID_INFO_AES_OVERRIDE = 117440621;
    public static final int ID_INFO_AIR_SUSP_AUTO_HEIGHT_SUMA = 117440704;
    public static final int ID_INFO_AIR_SUSP_CARGO_LOADING_SUMA = 117440708;
    public static final int ID_INFO_AIR_SUSP_SUMA_CONTROL = 117440703;
    public static final int ID_INFO_AM_PRESET1 = 117440582;
    public static final int ID_INFO_AM_PRESET2 = 117440583;
    public static final int ID_INFO_AM_PRESET3 = 117440584;
    public static final int ID_INFO_AM_PRESET4 = 117440585;
    public static final int ID_INFO_AM_PRESET5 = 117440586;
    public static final int ID_INFO_AM_PRESET6 = 117440587;
    public static final int ID_INFO_ANC_OR_ESE_BODY_STYLE = 117440613;
    public static final int ID_INFO_ANC_OR_ESE_BRANDING = 117440615;
    public static final int ID_INFO_ANC_OR_ESE_ENGINE = 117440618;
    public static final int ID_INFO_ANC_OR_ESE_EQ_CARLINE = 117440612;
    public static final int ID_INFO_ANC_OR_ESE_EQ_SPECIAL_MODES = 117440617;
    public static final int ID_INFO_ANC_OR_ESE_GEARBOX = 117440619;
    public static final int ID_INFO_ANC_OR_ESE_SPEAK_CONFIG = 117440614;
    public static final int ID_INFO_ANC_OR_ESE_SWP = 117440616;
    public static final int ID_INFO_APPROACH_DETECTION_CONTROL = 117440633;
    public static final int ID_INFO_AUH_ASS = 117440608;
    public static final int ID_INFO_AUH_CAL_F10A = 117440611;
    public static final int ID_INFO_AUH_CAL_F16B = 117440713;
    public static final int ID_INFO_AUH_CAL_F16C = 117440714;
    public static final int ID_INFO_AUH_CAL_F16E = 117440718;
    public static final int ID_INFO_AUH_CAL_FD02 = 117440719;
    public static final int ID_INFO_AUH_CCPU = 117440610;
    public static final int ID_INFO_AUH_HW = 117440609;
    public static final int ID_INFO_AUH_VMCU = 117440607;
    public static final int ID_INFO_AUTOLAMP_DELAY = 117440627;
    public static final int ID_INFO_AUTOLOCK_CONTROL = 117440628;
    public static final int ID_INFO_AUTOUNLOCK_CONTROL = 117440631;
    public static final int ID_INFO_AUTO_HIGH_BEAM_CONTROL = 117440626;
    public static final int ID_INFO_AUTO_HIGH_BEAM_MENU = 117440702;
    public static final int ID_INFO_AUTO_HOLD = 117440664;
    public static final int ID_INFO_AUTO_RELOCK = 117440630;
    public static final int ID_INFO_AUTO_START_STOP = 117440690;
    public static final int ID_INFO_BLINDSPOT = 117440657;
    public static final int ID_INFO_BTT_LITE = 117440705;
    public static final int ID_INFO_CENTERSTACK_SETTING = 117440695;
    public static final int ID_INFO_CHARGE_PORT_LOCK = 117440712;
    public static final int ID_INFO_CITY_SAFETY = 117440632;
    public static final int ID_INFO_CLIMATE_AUTO_LEVELS = 117440709;
    public static final int ID_INFO_COURTESY_WIPE_AFTER_WASH = 117440634;
    public static final int ID_INFO_DAYTIME_RUNLAMP_CONTROL = 117440641;
    public static final int ID_INFO_DID_WEIGHT_FACTOR_DP = 117440717;
    public static final int ID_INFO_DIGITAL_SCENT = 117440724;
    public static final int ID_INFO_DO_NOT_DISTURB = 117440645;
    public static final int ID_INFO_DRIVER_ALERT_SYSTEM = 117440635;
    public static final int ID_INFO_DSP_INNER_VERSION = 117440589;
    public static final int ID_INFO_EASY_ENTRY_EXIT = 117440636;
    public static final int ID_INFO_EVA_STEERING_ASSIST = 117440671;
    public static final int ID_INFO_EV_AND_MODE = 117440687;
    public static final int ID_INFO_EV_RANGE_RING = 117440710;
    public static final int ID_INFO_FCW_BRAKING_ONOFF = 117440668;
    public static final int ID_INFO_FM_PRESET1 = 117440593;
    public static final int ID_INFO_FM_PRESET10 = 117440602;
    public static final int ID_INFO_FM_PRESET11 = 117440603;
    public static final int ID_INFO_FM_PRESET12 = 117440604;
    public static final int ID_INFO_FM_PRESET2 = 117440594;
    public static final int ID_INFO_FM_PRESET3 = 117440595;
    public static final int ID_INFO_FM_PRESET4 = 117440596;
    public static final int ID_INFO_FM_PRESET5 = 117440597;
    public static final int ID_INFO_FM_PRESET6 = 117440598;
    public static final int ID_INFO_FM_PRESET7 = 117440599;
    public static final int ID_INFO_FM_PRESET8 = 117440600;
    public static final int ID_INFO_FM_PRESET9 = 117440601;
    public static final int ID_INFO_FORWARD_COLLISION_WARNING = 117440637;
    public static final int ID_INFO_FUEL_OPERATED_HEATER = 117440638;
    public static final int ID_INFO_FUEL_OPERATED_PARK_HEATER = 117440646;
    public static final int ID_INFO_GRADE_ASSIST = 117440700;
    public static final int ID_INFO_HILL_DESCENT_CONTROL = 117440685;
    public static final int ID_INFO_HILL_START_ASSIST = 117440665;
    public static final int ID_INFO_INTLIG_ACCESS_MENU = 117440661;
    public static final int ID_INFO_INTLIG_ADPT_CRUISE_CONTROL = 117440673;
    public static final int ID_INFO_INTLIG_SPEED_ASSISTANCE = 117440658;
    public static final int ID_INFO_KEYPAD_OR_PAAK = 117440711;
    public static final int ID_INFO_KEY_FREE = 117440699;
    public static final int ID_INFO_LANEA_NCAP_AID = 117440697;
    public static final int ID_INFO_LANEA_NCAP_ALERT = 117440698;
    public static final int ID_INFO_LANE_ASSIST_HAPTIC_INSITY = 117440672;
    public static final int ID_INFO_LANE_CHANGE_ASSIST = 117440675;
    public static final int ID_INFO_LANE_KEEPING_SENTIVTY = 117440676;
    public static final int ID_INFO_LIN_VERSION = 117440605;
    public static final int ID_INFO_LOCKING_FDB_AUDIBLE = 117440669;
    public static final int ID_INFO_LOCKING_FDB_VISUAL = 117440670;
    public static final int ID_INFO_MCU_VERSION = 117440588;
    public static final int ID_INFO_MHEV_START_STOP_THRESHOLD = 117440707;
    public static final int ID_INFO_MIRRORS_AUTOFOLD = 117440643;
    public static final int ID_INFO_MIRRORS_REVERSE_TILT = 117440644;
    public static final int ID_INFO_MYKEY_MENU = 117440666;
    public static final int ID_INFO_ONE_OR_TWO_STAGE_UNLOCK = 117440701;
    public static final int ID_INFO_ONE_OR_TWO_STAGE_UNLOCKING = 117440647;
    public static final int ID_INFO_PARK_LOCK_CONTROL_ALLW = 117440689;
    public static final int ID_INFO_PASGER_AIRBAG_SETTINGS = 117440706;
    public static final int ID_INFO_PERIMETER_ALARM_CONTROL = 117440648;
    public static final int ID_INFO_PERIM_ALARM_GUARD_REMINDER = 117440691;
    public static final int ID_INFO_POWER_LIFTGATE_CONTROL = 117440649;
    public static final int ID_INFO_PRECOLLISION = 117440660;
    public static final int ID_INFO_PREDICTIVE_LIGHTS = 117440696;
    public static final int ID_INFO_PROGNOSTIC = 117440723;
    public static final int ID_INFO_RAIN_SENSOR = 117440667;
    public static final int ID_INFO_REAR_BRAKE_ASSIST = 117440686;
    public static final int ID_INFO_RESERVED1 = 117440725;
    public static final int ID_INFO_RESERVED2 = 117440726;
    public static final int ID_INFO_RESERVED3 = 117440727;
    public static final int ID_INFO_RRGW = 117440650;
    public static final int ID_INFO_RS_CLIMATE_SETTINGS = 117440651;
    public static final int ID_INFO_RS_DRIVER_SEAT = 117440652;
    public static final int ID_INFO_RS_FEATURE = 117440653;
    public static final int ID_INFO_RS_PASSENGER_SEAT = 117440654;
    public static final int ID_INFO_RS_REAR_DEFROST = 117440655;
    public static final int ID_INFO_RS_STEERING_WHEEL = 117440656;
    public static final int ID_INFO_RUNING_BOARD_CONTROL = 117440688;
    public static final int ID_INFO_SILENT_MODE_CONTROL = 117440662;
    public static final int ID_INFO_TEMPORARY_MOBILITY_KIT = 117440642;
    public static final int ID_INFO_TPMS_BY_LOCATION = 117440693;
    public static final int ID_INFO_TPMS_PLA_PRESSURE_DISPLAY = 117440694;
    public static final int ID_INFO_TPMS_RESET = 117440663;
    public static final int ID_INFO_TRACTION_CONTROL = 117440678;
    public static final int ID_INFO_TRAFFIC_SIGN_RECOG = 117440692;
    public static final int ID_INFO_TRAILER_BLIND_SPOT = 117440683;
    public static final int ID_INFO_TRAILER_SWAY = 117440659;
    public static final int ID_INFO_TSR_NCAP_ADAPTATIONS = 117440680;
    public static final int ID_INFO_TSR_OVERSPEED_CHIME = 117440681;
    public static final int ID_INFO_TWO_HAUL = 117440682;
    public static final int ID_INFO_WIFI_MSG = 117440715;
    public static final int ID_INFO_WINDOWS_REMOTE_CLOSE = 117440640;
    public static final int ID_INFO_WINDOWS_REMOTE_OPEN = 117440639;
    public static final int ID_INFO_WRONG_WAY_ALERT = 117440684;
    public static final int INFO_CAL_FD03 = 117440722;
    public static final int INFO_KEY_ACTIVE_GET = 117440716;
    public static final int INFO_KEY_ACTIVE_SET = 117440590;
    public static final int INFO_KEY_ANC_OR_ESE = 117440560;
    public static final int INFO_KEY_CONFIG_360_CAMERA = 117440531;
    public static final int INFO_KEY_CONFIG_A2B = 117440581;
    public static final int INFO_KEY_CONFIG_AMBIENT_LIGHT = 117440525;
    public static final int INFO_KEY_CONFIG_AM_ANTENNA_TEST = 117440574;
    public static final int INFO_KEY_CONFIG_ANC_LEFT_FRONT_MIX = 117440569;
    public static final int INFO_KEY_CONFIG_ANC_REAR_MIX = 117440571;
    public static final int INFO_KEY_CONFIG_ANC_RIGHT_FRONT_MIX = 117440570;
    public static final int INFO_KEY_CONFIG_ANTENNA_TEST_VALUE = 117440573;
    public static final int INFO_KEY_CONFIG_APA = 117440532;
    public static final int INFO_KEY_CONFIG_APNURL = 117440549;
    public static final int INFO_KEY_CONFIG_BT_TURNING = 117440552;
    public static final int INFO_KEY_CONFIG_CGEA_VERSION = 117440548;
    public static final int INFO_KEY_CONFIG_CHIME_STRATEGY = 117440565;
    public static final int INFO_KEY_CONFIG_CLIMATE_DOMAIN = 117440535;
    public static final int INFO_KEY_CONFIG_CTA = 117440533;
    public static final int INFO_KEY_CONFIG_EFP = 117440522;
    public static final int INFO_KEY_CONFIG_EQ = 117440576;
    public static final int INFO_KEY_CONFIG_EXENDED_PLAY = 117440553;
    public static final int INFO_KEY_CONFIG_FANSPEED_VR = 117440540;
    public static final int INFO_KEY_CONFIG_FM_SEEK_SENSITIVY = 117440578;
    public static final int INFO_KEY_CONFIG_FRESH_AIR_CABIN = 117440539;
    public static final int INFO_KEY_CONFIG_FRONT_SPEAKER = 117440561;
    public static final int INFO_KEY_CONFIG_FRONT_SPK_DETECT = 117440563;
    public static final int INFO_KEY_CONFIG_FRONT_TWEETER = 117440567;
    public static final int INFO_KEY_CONFIG_FUEL_TYPE = 117440545;
    public static final int INFO_KEY_CONFIG_GAIN_ADJUST = 117440575;
    public static final int INFO_KEY_CONFIG_GYRO = 117440543;
    public static final int INFO_KEY_CONFIG_HEATED_SW = 117440538;
    public static final int INFO_KEY_CONFIG_HEATED_WIND = 117440541;
    public static final int INFO_KEY_CONFIG_HEAT_COOL_SEAT = 117440537;
    public static final int INFO_KEY_CONFIG_HIGH_MODE_AM = 117440580;
    public static final int INFO_KEY_CONFIG_ILLUMINATION = 117440547;
    public static final int INFO_KEY_CONFIG_LIST_BROWSER = 117440542;
    public static final int INFO_KEY_CONFIG_LOW_FUEL = 117440544;
    public static final int INFO_KEY_CONFIG_LOW_MODE_AM = 117440579;
    public static final int INFO_KEY_CONFIG_MC_SEAT = 117440526;
    public static final int INFO_KEY_CONFIG_MT_OR_AT = 117440534;
    public static final int INFO_KEY_CONFIG_MYKEY = 117440546;
    public static final int INFO_KEY_CONFIG_PDC_HMI = 117440529;
    public static final int INFO_KEY_CONFIG_PDC_RVC = 117440530;
    public static final int INFO_KEY_CONFIG_QUANTUM_LOGIC_IMMERSION = 117440556;
    public static final int INFO_KEY_CONFIG_QUANTUM_LOGIC_SURROUNDING = 117440557;
    public static final int INFO_KEY_CONFIG_REAR_EFP = 117440523;
    public static final int INFO_KEY_CONFIG_REAR_HVAC = 117440536;
    public static final int INFO_KEY_CONFIG_REAR_SPEAKER = 117440562;
    public static final int INFO_KEY_CONFIG_REAR_SPK_DETECT = 117440564;
    public static final int INFO_KEY_CONFIG_REAR_TWEETER = 117440568;
    public static final int INFO_KEY_CONFIG_REVEL = 117440559;
    public static final int INFO_KEY_CONFIG_SMART_DSP = 117440554;
    public static final int INFO_KEY_CONFIG_SOUND_MODE = 117440555;
    public static final int INFO_KEY_CONFIG_SWC = 117440521;
    public static final int INFO_KEY_CONFIG_TCS = 117440527;
    public static final int INFO_KEY_CONFIG_TCU = 117440528;
    public static final int INFO_KEY_CONFIG_THX = 117440558;
    public static final int INFO_KEY_CONFIG_TPMS = 117440550;
    public static final int INFO_KEY_CONFIG_TSR = 1835031;
    public static final int INFO_KEY_CONFIG_TURN_CHIMES = 117440566;
    public static final int INFO_KEY_CONFIG_WACM = 117440524;
    public static final int INFO_KEY_ENABLE_OCCU_PHONE = 117440572;
    public static final int INFO_KEY_PHONE_LEVEL = 117440577;
    public static final String INFO_KEY_PRODUCT_CONFIGURATION = "android.car.product-config";
    public static final int INFO_SPL_A_VER = 117440720;
    public static final int INFO_SPL_B_VER = 117440721;
    @GuardedBy("this")
    private Bundle mBasicInfo;
    private final ICarInfo mService;

    public String getManufacturer() throws CarNotConnectedException {
        return getBasicInfo().getString(BASIC_INFO_KEY_MANUFACTURER);
    }

    public String getModel() throws CarNotConnectedException {
        return getBasicInfo().getString(BASIC_INFO_KEY_MODEL);
    }

    public String getModelYear() throws CarNotConnectedException {
        return getBasicInfo().getString(BASIC_INFO_KEY_MODEL_YEAR);
    }

    public String getVehicleId() throws CarNotConnectedException {
        return getBasicInfo().getString(BASIC_INFO_KEY_VEHICLE_ID);
    }

    public String getStringProperty(int propertyId) throws CarNotConnectedException {
        try {
            Log.i("getStringProprety", "propertyId=" + propertyId);
            String value = this.mService.getStringProprety(propertyId);
            return value;
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return "";
        }
    }

    public int getIntProprety(int propertyId) throws CarNotConnectedException {
        try {
            Log.i("getIntProprety", "propertyId=" + propertyId);
            int value = this.mService.getIntProprety(propertyId);
            return value;
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return 17;
        }
    }

    public byte[] getBytesProprety(int propertyId) throws CarNotConnectedException {
        byte[] value = new byte[4];
        try {
            Log.i("getBytesProprety", "propertyId=" + propertyId);
            byte[] value2 = this.mService.getBytesProprety(propertyId);
            return value2;
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return value;
        }
    }

    public int[] getIntsProprety(int propertyId) throws CarNotConnectedException {
        int[] value = new int[4];
        try {
            Log.i("getBytesProprety", "propertyId=" + propertyId);
            int[] value2 = this.mService.getIntsProprety(propertyId);
            return value2;
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return value;
        }
    }

    @FutureFeature
    public String getProductConfiguration() throws CarNotConnectedException {
        try {
            return this.mService.getStringInfo(INFO_KEY_PRODUCT_CONFIGURATION);
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
            return null;
        }
    }

    private synchronized Bundle getBasicInfo() throws CarNotConnectedException {
        if (this.mBasicInfo != null) {
            return this.mBasicInfo;
        }
        try {
            try {
                this.mBasicInfo = this.mService.getBasicInfo();
            } catch (IllegalStateException e) {
                CarApiUtil.checkCarNotConnectedExceptionFromCarService(e);
            }
            return this.mBasicInfo;
        } catch (RemoteException e2) {
            throw new CarNotConnectedException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarInfoManager(IBinder service) {
        this.mService = ICarInfo.Stub.asInterface(service);
    }

    public void setIntProprety(int propertyId, int area, int val) throws CarNotConnectedException {
        try {
            Log.i("setIntProprety", "value=" + val + ",propertyId=" + propertyId + ",area=" + area);
            this.mService.setIntProprety(propertyId, area, val);
        } catch (RemoteException e) {
            throw new CarNotConnectedException(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        synchronized (this) {
            this.mBasicInfo = null;
        }
    }
}
