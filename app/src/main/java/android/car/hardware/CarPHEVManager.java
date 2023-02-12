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
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarPHEVManager implements CarManagerBase {
    private static final boolean DBG = true;
    public static final int PHEV_BATTPWLVL2_PC_DSPLY = 560996424;
    public static final int PHEV_BATTTRACSOC_PC_DSLY = 560996404;
    public static final int PHEV_CENTERSTACKRING_D_ACTL = 560996436;
    public static final int PHEV_CHARGEINPWTYPE_D_ACTL = 560996403;
    public static final int PHEV_CHARGER_NOW_DURATION = 560996456;
    public static final int PHEV_CHARGER_PROFILE_LOCATION_FROM_APIM = 554708073;
    public static final int PHEV_CHARGER_PROFILE_LOCATION_FROM_TCU = 554708074;
    public static final int PHEV_CHARGER_PROFILE_UPDATE = 560996455;
    public static final int PHEV_CHARGE_END_TIME = 560996402;
    public static final int PHEV_CHARGE_START_TIME = 560996401;
    public static final int PHEV_CHARGE_STATUS = 560996405;
    public static final int PHEV_CHRGSTATDSPLY_D_RQ = 560996437;
    public static final int PHEV_CHRGSTAT_D2_DSPLY = 560996454;
    public static final int PHEV_CLIMPWLVL_PC_DSPLY = 560996425;
    public static final int PHEV_CURRNT_CHARGE_ID = 560996433;
    public static final int PHEV_EFFWHLLVL2_PC_DSPLY = 560996423;
    public static final int PHEV_ENGACTV_B_DSPLY = 560996428;
    public static final int PHEV_ENGONMSG1_D_DSPLY = 560996415;
    public static final int PHEV_ENGONMSG2_D_DSPLY = 560996416;
    public static final int PHEV_ENGPWLVL_PC_DSPLY = 560996432;
    public static final int PHEV_ESTIMATED_CHARGER_TIME = 560996434;
    public static final int PHEV_GO_TIME_STATE_1 = 560996438;
    public static final int PHEV_GO_TIME_STATE_10 = 560996447;
    public static final int PHEV_GO_TIME_STATE_11 = 560996448;
    public static final int PHEV_GO_TIME_STATE_12 = 560996449;
    public static final int PHEV_GO_TIME_STATE_13 = 560996450;
    public static final int PHEV_GO_TIME_STATE_14 = 560996451;
    public static final int PHEV_GO_TIME_STATE_2 = 560996439;
    public static final int PHEV_GO_TIME_STATE_3 = 560996440;
    public static final int PHEV_GO_TIME_STATE_4 = 560996441;
    public static final int PHEV_GO_TIME_STATE_5 = 560996442;
    public static final int PHEV_GO_TIME_STATE_6 = 560996443;
    public static final int PHEV_GO_TIME_STATE_7 = 560996444;
    public static final int PHEV_GO_TIME_STATE_8 = 560996445;
    public static final int PHEV_GO_TIME_STATE_9 = 560996446;
    public static final int PHEV_HYBMDESTAT_D_DSPLY = 560996417;
    public static final int PHEV_LVACSYPWLVL_PC_DSPLY = 560996422;
    public static final int PHEV_MTRPWLVL_PC_DSPLY = 560996426;
    public static final int PHEV_NTFCTNCONFLICT1_D_RQ = 560996435;
    public static final int PHEV_NXTUSGSOCEST_PC_DSPLY = 560996453;
    public static final int PHEV_PLGACTVARB_B_ACTL = 560996421;
    public static final int PHEV_PLGACTVARB_B_DSPLY = 560996407;
    public static final int PHEV_PWFLOWBATTMTR_D_DSPLY = 560996418;
    public static final int PHEV_PWFLOWENGDRV_D_DSPLY = 560996429;
    public static final int PHEV_PWFLOWENGFUEL_D_DSPLY = 560996430;
    public static final int PHEV_PWFLOWENGMTR_D_DSPLY = 560996431;
    public static final int PHEV_PWFLOWMTRDRV_D_DSPLY = 560996419;
    public static final int PHEV_PWFLOWPLGBATT_D_DSPLY = 560996420;
    public static final int PHEV_PWFLWBATTCLIMT_B_DSPLY = 560996412;
    public static final int PHEV_PWFLWBATTGEN_D_DSPLY = 560996413;
    public static final int PHEV_PWFLWBATT_D_DSPLY = 560996408;
    public static final int PHEV_PWFLWFUELBATT_B_DSPLY = 560996410;
    public static final int PHEV_PWFLWFUELCLIMT_B_DSPLY = 560996411;
    public static final int PHEV_PWFLWFUELDRV_D_DSPLY = 560996409;
    public static final int PHEV_PWPCKTQRDY_B_DSPLY = 560996427;
    public static final int PHEV_PWRFLOWTXT_D_DSPLY = 560996414;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_1 = 560996368;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_10 = 560996377;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_2 = 560996369;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_3 = 560996370;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_4 = 560996371;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_5 = 560996372;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_6 = 560996373;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_7 = 560996374;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_8 = 560996375;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_9 = 560996376;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_1 = 560996384;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_10 = 560996393;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_2 = 560996385;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_3 = 560996386;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_4 = 560996387;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_5 = 560996388;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_6 = 560996389;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_7 = 560996390;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_8 = 560996391;
    public static final int PHEV_SAVED_CHARGE_LOCATIONS_SETTING_9 = 560996392;
    public static final int PHEV_SETUP_CHARGE = 560996400;
    public static final int PHEV_SET_GO_TIME = 560996452;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_1 = 560996352;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_10 = 560996361;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_2 = 560996353;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_3 = 560996354;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_4 = 560996355;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_5 = 560996356;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_6 = 560996357;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_7 = 560996358;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_8 = 560996359;
    public static final int PHEV_UNSAVED_CHARGE_LOCATIONS_9 = 560996360;
    public static final int PHEV_VEHELRNGE_L_DSPLY = 560996406;
    private static final String TAG = CarPHEVManager.class.getSimpleName();
    @GuardedBy("mLock")
    private ArraySet<CarVendorExtensionCallback> mCallbacks;
    ArrayList<PHEVChargeLocationSetting> mChargeLocationSetting;
    ArrayList<PHEVDepartureTime> mDepartureTime;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;
    ArrayList<PHEVChargeLocation> msavedLocationList;
    ArrayList<PHEVChargeLocation> munsavedLocationList;

    /* loaded from: classes2.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarPHEVManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarPHEVManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarVendorExtensionCallback[] callbacks;
                        for (CarVendorExtensionCallback listener : CarPHEVManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarVendorExtensionCallback[] callbacks;
                        for (CarVendorExtensionCallback listener : CarPHEVManager.this.getCallbacks()) {
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

    public ArrayList<PHEVChargeLocation> getUnsavedChargeLocationList(int managerID) throws Exception {
        if (this.munsavedLocationList == null) {
            this.munsavedLocationList = new ArrayList<>();
        }
        this.munsavedLocationList.clear();
        for (int i = 0; i < 10; i++) {
            byte[] values = (byte[]) getGlobalProperty(byte[].class, 560996352 + i);
            PHEVChargeLocation loc = new PHEVChargeLocation(values);
            if (loc.isValid()) {
                Log.w("CAR.L.PHEV", "getUnsavedChargeLocationList, location info: " + loc.toString());
                this.munsavedLocationList.add(loc);
            }
        }
        return this.munsavedLocationList;
    }

    public ArrayList<PHEVChargeLocation> getSavedChargeLocationList() throws Exception {
        if (this.msavedLocationList == null) {
            this.msavedLocationList = new ArrayList<>();
        }
        this.msavedLocationList.clear();
        for (int i = 0; i < 10; i++) {
            byte[] values = (byte[]) getGlobalProperty(byte[].class, 560996368 + i);
            PHEVChargeLocation loc = new PHEVChargeLocation(values);
            if (loc.isValid()) {
                Log.w("CAR.L.PHEV", "getSavedChargeLocationList, location info: " + loc.toString());
                this.msavedLocationList.add(loc);
            }
        }
        return this.msavedLocationList;
    }

    public ArrayList<PHEVChargeLocationSetting> getChargeLocationSettingList() throws Exception {
        if (this.mChargeLocationSetting == null) {
            this.mChargeLocationSetting = new ArrayList<>();
        }
        this.mChargeLocationSetting.clear();
        for (int i = 0; i < 10; i++) {
            byte[] values = (byte[]) getGlobalProperty(byte[].class, 560996384 + i);
            PHEVChargeLocationSetting set = new PHEVChargeLocationSetting(values);
            if (set.isValid()) {
                Log.w("CAR.L.PHEV", "getChargeLocationSettingList, location setting info: " + set.toString());
                this.mChargeLocationSetting.add(set);
            }
        }
        return this.mChargeLocationSetting;
    }

    public ArrayList<PHEVDepartureTime> getDepartureTimeList() throws Exception {
        if (this.mDepartureTime == null) {
            this.mDepartureTime = new ArrayList<>();
        }
        this.mDepartureTime.clear();
        for (int i = 0; i < 14; i++) {
            byte[] values = (byte[]) getGlobalProperty(byte[].class, 560996438 + i);
            PHEVDepartureTime set = new PHEVDepartureTime(values);
            if (set.isValid()) {
                Log.w("CAR.L.PHEV", "getDepartureTimeList, departure time info: " + set.toString());
                this.mDepartureTime.add(set);
            }
        }
        return this.mDepartureTime;
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

    public void setUpCharge(int[] val) throws Exception {
        if (val.length >= 9) {
            String inputStr = "";
            for (int i = 0; i < 9; i++) {
                inputStr = inputStr + Integer.toHexString(val[i]) + ",";
            }
            Log.w("CAR.L.PHEV", "setUpCharge, input int[]: " + inputStr);
            byte[] bval = new byte[15];
            int i2 = 0 + 1;
            bval[0] = (byte) val[0];
            int i3 = i2 + 1;
            bval[i2] = (byte) val[1];
            int i4 = i3 + 1;
            bval[i3] = (byte) val[2];
            int i5 = i4 + 1;
            bval[i4] = (byte) (val[3] >> 24);
            int i6 = i5 + 1;
            bval[i5] = (byte) (val[3] >> 16);
            int i7 = i6 + 1;
            bval[i6] = (byte) (val[3] >> 8);
            int i8 = i7 + 1;
            bval[i7] = (byte) val[3];
            int i9 = i8 + 1;
            bval[i8] = (byte) (val[4] >> 24);
            int i10 = i9 + 1;
            bval[i9] = (byte) (val[4] >> 16);
            int i11 = i10 + 1;
            bval[i10] = (byte) (val[4] >> 8);
            int i12 = i11 + 1;
            bval[i11] = (byte) val[4];
            int i13 = i12 + 1;
            bval[i12] = (byte) val[5];
            int i14 = i13 + 1;
            bval[i13] = (byte) val[6];
            int i15 = i14 + 1;
            bval[i14] = (byte) val[7];
            int i16 = i15 + 1;
            bval[i15] = (byte) val[8];
            setGlobalProperty(byte[].class, 560996400, bval);
            return;
        }
        Log.i("CAR.L.PHEV", "setUpCharge, input data size is abnormal!");
    }

    public void updateChargeLocationName(Bundle bundle) throws CarNotConnectedException {
        try {
            String value = "SignalID:0xB8;Utilization:" + bundle.getString("Utilization") + ";";
            String value2 = ((((value + "CES:" + bundle.getString("CES") + ";") + "OpCode:" + bundle.getString("OpCode") + ";") + "Coding:" + bundle.getString("Coding") + ";") + "NumberOfItems:" + bundle.getString("NumberOfItems") + ";") + "List:" + bundle.getString("List") + ";";
            setGlobalProperty(String.class, 554708073, value2);
            Log.d("CAR.L.PHEV", value2);
        } catch (Exception e) {
            Log.e("CAR.L.PHEV", "updateChargeLocationName exception " + e.getMessage());
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }
}
