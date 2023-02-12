package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarVendorExtensionManager implements CarManagerBase {
    private static final boolean DBG = false;
    private static final boolean DBG_NAV = false;
    public static final int EA_START = 0;
    public static final int EA_STOP = 1;
    public static final int ID_ADAS_MAP_EH_DATA1 = 560991491;
    public static final int ID_ADAS_MAP_EH_DATA2 = 560991492;
    public static final int ID_AUTH_RX_MESSAGE = 555746329;
    public static final int ID_AUTH_TX_INIT_FINISH = 555746330;
    public static final int ID_AUTH_TX_MESSAGE = 557843480;
    public static final int ID_BUTTON_TYPE_MSG = 554697168;
    public static final int ID_DAY_NIGHT_STATUS = 557846019;
    public static final int ID_DISTANCE_UNITS = 557843475;
    public static final int ID_EA_MODE = 557846029;
    public static final int ID_FORD_4G_SINGAL = 557845413;
    public static final int ID_FORD_SYSTEM_RESET = 557845568;
    public static final int ID_GPS_Data_NAV_MFD_1 = 292582946;
    public static final int ID_GPS_Data_NAV_MFD_2 = 292582947;
    public static final int ID_GPS_Data_NAV_MFD_3 = 292582948;
    public static final int ID_HOTSPOT_BLACKLIST_DEVICES = 554697444;
    public static final int ID_HOTSPOT_CONNECTED_DEVICES = 554697443;
    public static final int ID_HOTSPOT_SSID_ACCOUNT = 554697441;
    public static final int ID_HOTSPOT_SSID_PWD = 554697442;
    public static final int ID_HOTSPOT_STATUS = 557845569;
    public static final int ID_HOTSPOT_STATUS_RSP = 557845578;
    public static final int ID_LOCATION_SERVICES_DATA1 = 560991489;
    public static final int ID_LOCATION_SERVICES_DATA2 = 560991490;
    public static final int ID_Mc_VehTimeFrmtUsrSel_St = 557848144;
    public static final int ID_POWER_CHECK_FAREWELL_END = 557846026;
    public static final int ID_POWER_LOAD_SHED_START_TIMER = 557846027;
    public static final int ID_POWER_LOCK_STATUS = 557846028;
    public static final int ID_POWER_MODE = 557846017;
    public static final int ID_POWER_SET_EVENTS = 557846021;
    public static final int ID_POWER_SYSTEM_10S_RESET = 557846025;
    public static final int ID_POWER_SYSTEM_OFF = 557846024;
    public static final int ID_POWER_TELEPHONE_STATUS = 557846020;
    public static final int ID_POWER_WELCOME_STATUS = 557846018;
    public static final int ID_SYSTEM_TIME_DATE = 554697748;
    public static final int ID_SYSTEM_TIME_STATUS = 555746326;
    public static final int ID_SYSTEM_TIME_TIME = 554697749;
    public static final int ID_SYSTEM_TX_TIME_FORMAT = 557843483;
    public static final int ID_TCS_ON = 557845416;
    public static final int ID_TCU_AVAILABILITY_ST = 557845570;
    public static final int ID_TCU_AVAILABILITY_TIME_OUT = 557845581;
    public static final int ID_TCU_ESN_RQ = 557845584;
    public static final int ID_TCU_ESN_VERSION = 554699855;
    public static final int ID_TCU_SINGAL_STRENGTHS = 557845566;
    public static final int ID_TCU_SINGAL_TYPE = 557845567;
    public static final int ID_V2I_APEDPOS_PC_ACTLARB = 560993844;
    public static final int ID_V2I_BPEDDRVAPPL_D_ACTL = 560993840;
    public static final int ID_V2I_DRVLAMP_D_RQ = 560993848;
    public static final int ID_V2I_DRVSLIPCTLOFFLAMP_D_RQ = 560993849;
    public static final int ID_V2I_LAACTVSTATS_D_DSPLY = 557848118;
    public static final int ID_V2I_STABCTLBRKACTV_B_ACTL = 557848119;
    public static final int ID_V2I_STEPINCOMPANEST_D = 560993843;
    public static final int ID_V2I_STOPSTRT_D_INDIC = 560993842;
    public static final int ID_V2I_TURNLGHTON_B_STAT = 560993845;
    public static final int ID_V2I_WHLROTAT_NO_CNT = 560993841;
    public static final int ID_WIFI_DEVICELIST_RQ = 557845573;
    public static final int ID_WIFI_DEVICELIST_RSP = 554699848;
    public static final int ID_WIFI_INFO_RQ = 554699843;
    public static final int ID_WIFI_INFO_RSP = 554699844;
    public static final int ID_WIFI_MACLIST_RQ = 555748427;
    public static final int ID_WIFI_NEWDEVICELIST_ST = 557845577;
    public static final int ID_WIFI_NODEVC_CNNCT_NO_ACTL = 557845580;
    public static final int ID_WIFI_REMOVE_DEVICE_INDX_RQ = 557845575;
    public static final int ID_WIFI_REMOVE_DEVICE_RQ = 557845574;
    public static final int ID_WIRELESS_CHARGE_STATUS = 557843120;
    private static final int MSG_40MS = 100;
    public static final int SENSOR_TYPE_TIRE_PRESSURE = 660603920;
    public static final int SENSOR_TYPE_TIRE_STATUS = 658506770;
    public static final int SENSOR_TYPE_TIRE_TEMP = 660603921;
    public static final int SENSOR_TYPE_WIRELESS_CHARGE_CAPACITY = 557843121;
    public static final int SIGNAL_NAV_GPS_COMPAS = 557845509;
    private static final int STATE_1 = 103;
    private static final int STATE_2 = 104;
    private static final int STATE_3 = 105;
    private static final int STATE_4 = 106;
    private static final int STATE_5 = 107;
    private static final int STATE_6 = 108;
    private static final int STATE_7 = 109;
    private static final int STATE_8 = 110;
    private static final int STATE_9 = 111;
    private static final int STATE_ERROR = 112;
    private static final int STATE_IDLE = 101;
    private static final int STATE_INIT = 102;
    private static final String TAG = CarVendorExtensionManager.class.getSimpleName();
    public static final int TIRE_PRESSURE_INVALID = 65534;
    public static final int TIRE_PRESSURE_NOTSUPPORT = 65535;
    public static final int TIRE_PRESSURE_UNKNOWN = 65533;
    public static final int VEHICLE_INIT_FINISH = 557843479;
    public static final String WIFI_OPCODE_READ = "0x1";
    public static final String WIFI_OPCODE_WRITEPWD = "0x3";
    public static final String WIFI_OPCODE_WRITESSID = "0x2";
    private int compassDirection;
    private int keyViewIndex;
    private Bundle location1Bundle;
    private Bundle location2Bundle;
    private Bundle location3Bundle;
    @GuardedBy("mLock")
    private ArraySet<CarVendorExtensionCallback> mCallbacks;
    private final CarPropertyManagerBase mPropertyManager;
    private Handler mVendorHandler;
    private Bundle metaDataTimeBundle;
    private int[] skyViewPRNS;
    private int[] skyViewSNRS;
    private HandlerThread vendorThread;
    private final Object mLock = new Object();
    private int state = 101;
    private LocationProtocal mLocationProtocal = new LocationProtocal();
    private GPSParser mGPSParser = new GPSParser();

    /* loaded from: classes2.dex */
    public interface CarVendorExtensionCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public int getCompassDirection() {
        return this.compassDirection;
    }

    public CarVendorExtensionManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, false, TAG);
    }

    public void registerCallback(CarVendorExtensionCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarVendorExtensionManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarVendorExtensionCallback[] callbacks;
                        if (value.getPropertyId() == 557846019) {
                            Log.i(CarVendorExtensionManager.TAG, "carVendorExtension on change event ~~~~~~~~~" + value.toString());
                        }
                        for (CarVendorExtensionCallback listener : CarVendorExtensionManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarVendorExtensionCallback[] callbacks;
                        for (CarVendorExtensionCallback listener : CarVendorExtensionManager.this.getCallbacks()) {
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
            if (this.mCallbacks.contains(callback)) {
                this.mCallbacks.remove(callback);
                if (this.mCallbacks.isEmpty()) {
                    this.mPropertyManager.unregisterCallback();
                    this.mCallbacks = null;
                }
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

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class VendorHandler extends Handler {
        public VendorHandler(Looper looper) {
            super(looper, null);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                CarVendorExtensionManager.this.handleNavigationMessage(null, false);
                if (CarVendorExtensionManager.this.state != 112) {
                    CarVendorExtensionManager.this.mVendorHandler.sendEmptyMessageDelayed(100, 40L);
                    return;
                }
                Log.e(CarVendorExtensionManager.TAG, "quit location thread");
                CarVendorExtensionManager.this.destroy();
            }
        }
    }

    public synchronized void init() {
        if (this.mLocationProtocal == null) {
            this.mLocationProtocal = new LocationProtocal();
        }
        if (this.vendorThread == null) {
            Log.d(TAG, "create thread");
            this.vendorThread = new HandlerThread("vendorExtensionController");
            this.vendorThread.start();
        }
        if (this.mVendorHandler == null) {
            Log.d(TAG, "create handler");
            this.mVendorHandler = new VendorHandler(this.vendorThread.getLooper());
            this.mVendorHandler.sendEmptyMessage(100);
        }
    }

    public synchronized void destroy() {
        this.vendorThread.quit();
        this.vendorThread = null;
        this.mVendorHandler = null;
    }

    public void sendNavToTcu(Bundle mBundle) {
        handleNavigationMessage(mBundle, true);
        init();
    }

    public void sendAdasMapEH1(byte[] val) {
        try {
            if (val.length == 8) {
                setGlobalProperty(byte[].class, 560991491, val);
            } else {
                String str = TAG;
                Log.e(str, "huangfeng sendAdasMapEH1, data size(" + val.length + ") is abnormal!");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void sendAdasMapEH2(byte[] val) {
        try {
            if (val.length == 8) {
                setGlobalProperty(byte[].class, 560991492, val);
            } else {
                String str = TAG;
                Log.e(str, "huangfeng sendAdasMapEH2, data size(" + val.length + ") is abnormal!");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(1:3)(1:57)|4|(1:6)(1:56)|7|(1:55)(2:11|(1:13)(1:54))|14|(1:53)(2:18|(1:20)(1:52))|21|22|23|24|(2:25|26)|27|28|29|(2:30|31)|33|34|35|36|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x025c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x025d, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0282, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0283, code lost:
        r0.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initData(android.os.Bundle r37) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.hardware.CarVendorExtensionManager.initData(android.os.Bundle):void");
    }

    private void sendMetaData() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getMetaDataTime(this.metaDataTimeBundle));
            this.state = 104;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocation1() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocation1());
            this.state = 105;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocation2() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocation2(this.location2Bundle));
            this.state = 106;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocationQuality() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocationQuality());
            this.state = 107;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendSensorQuality() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getSensorQuality());
            this.state = 108;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocation3() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocation3());
            this.state = 109;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocation4() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocation4());
            this.state = 110;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendLocation5() {
        try {
            setGlobalProperty(byte[].class, 560991489, this.mLocationProtocal.getLocation5());
            this.state = 111;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            this.state = 112;
        }
    }

    private void sendSkyViewData() {
        if (this.skyViewPRNS == null || this.skyViewSNRS == null) {
            Log.e(TAG, "SkyView null");
            this.state = 101;
        } else if (this.keyViewIndex >= this.skyViewPRNS.length || this.keyViewIndex >= this.skyViewSNRS.length) {
            this.state = 101;
        } else if (this.keyViewIndex >= 20) {
            Log.e(TAG, "more than 20");
            this.state = 101;
        } else {
            Bundle skyview = new Bundle();
            skyview.putInt("SPID", this.skyViewPRNS[this.keyViewIndex]);
            skyview.putInt("SCTN", this.skyViewSNRS[this.keyViewIndex]);
            this.keyViewIndex++;
            try {
                setGlobalProperty(byte[].class, 560991490, this.mLocationProtocal.getSkyView(skyview));
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                this.state = 112;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleNavigationMessage(Bundle bundle, boolean foreceInit) {
        if (this.state == 112) {
            Log.d(TAG, "carserver is exception");
            return;
        }
        if (foreceInit) {
            this.state = 102;
        }
        switch (this.state) {
            case 102:
                if (bundle == null) {
                    this.state = 101;
                    break;
                } else {
                    initData(bundle);
                    break;
                }
            case 103:
                sendMetaData();
                break;
            case 104:
                sendLocation1();
                break;
            case 105:
                sendLocation2();
                break;
            case 106:
                sendLocationQuality();
                break;
            case 107:
                sendSensorQuality();
                break;
            case 108:
                sendLocation3();
                break;
            case 109:
                sendLocation4();
                break;
            case 110:
                sendLocation5();
                break;
            case 111:
                sendSkyViewData();
                break;
        }
    }
}
