package com.yfve.engineeringmode;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.car.Car;
import android.car.CarInfoManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.CarVendorExtensionManager;
import android.car.hardware.CarYFDiagManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Rl78Manager;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import com.baidu.xiaoduos.syncclient.Entry;
import com.baidu.xiaoduos.syncclient.EventType;
import com.yfve.tools.FileUtil;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes1.dex */
public class CarInfoService extends Service {
    private CarVendorExtensionManager carVendorExtensionManager;
    private boolean mbIsEntryInit;
    private Rl78Manager mrl78;
    private Entry msyncEntry;
    public final String tag = "CarInfoService_zyx";
    private final String ACTION_UNSUPPORTED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    private final String ACTION_NO_RESPONSE = "android.intent.action.USB_DEVICE_NO_RESPONSE";
    private final String ACTION_EOL_NEED_GET_SCREEN_VERSION = "com.yfve.intent.action.EOL_NEED_GET_SCREEN_VERSION";
    private final String ACTION_EOL_NEED_GET_SCREEN_RESULT = "com.yfve.intent.action.EOL_NEED_GET_SCREEN_RESULT";
    private final String EOL_EXTRA_KEY = "getScreenVersionResult";
    private final int EOL_EXTRA_VALUE_ERROR = 0;
    private final int EOL_EXTRA_VALUE_SUCCESS = 1;
    private final float DEVICE_SIZE_32_OR_64 = 16.0f;
    private boolean mbDeviceSizeIs64G = false;
    private String mstrWifiMac = "0";
    private String mstrBtAddress = "0";
    private TelephonyManager mTelephonyManager = null;
    public AudioManager mAudioManager = null;
    private WifiManager mwifiManager = null;
    private boolean mbIsGetStateWifi = false;
    private Car mCarApiClient = null;
    private CarYFDiagManager mCarYFDiagManager = null;
    private CarInfoManager mCarInfoManager = null;
    private final int EVENT_ID = 32100023;
    private final String EVENT_ID_VMCU_CCPU = "OTA900001";
    private final String KEY_IS_SHOW_RETURN = "KeyIsShowReturn";
    private final String VALUE_GONE_RETURN = "gone";
    private final String IS_FIRST_SEND_DIAGNOSIS = "100";
    private String mstr8033Value = "";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.yfve.engineeringmode.CarInfoService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String strReceiverAction = intent.getAction();
            LU.w("CarInfoService_zyx", "onReceive()   strReceiverAction==" + strReceiverAction);
            if ("com.yfve.intent.action.EOL_NEED_GET_SCREEN_VERSION".equals(strReceiverAction)) {
                byte[] byteGetScreenVersion = CarInfoService.this.getByteScreenVersion();
                LU.w("CarInfoService_zyx", "onReceive()   byteGetScreenVersion==" + new String(byteGetScreenVersion));
                CarInfoService.this.setByteProperty(15, 0, byteGetScreenVersion);
            }
        }
    };
    private CarVendorExtensionManager.CarVendorExtensionCallback vendorExtensionCallback = new CarVendorExtensionManager.CarVendorExtensionCallback() { // from class: com.yfve.engineeringmode.CarInfoService.3
        @Override // android.car.hardware.CarVendorExtensionManager.CarVendorExtensionCallback
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            if (554697168 == carPropertyValue.getPropertyId()) {
                try {
                    int iCurAudioSourceType = CarInfoService.this.getCurAudioSourceType();
                    int iCurAudioUsage = CarInfoService.this.getCurAudioUsage();
                    int igetSystemSourceType = CarInfoService.this.getSystemSourceType(iCurAudioSourceType, iCurAudioUsage);
                    LU.w("CarInfoService_zyx", "onChangeEvent()   igetSystemSourceType==" + igetSystemSourceType);
                    String contentKey = ((String) carPropertyValue.getValue()) + "-" + igetSystemSourceType;
                    LU.w("CarInfoService_zyx", "onChangeEvent()   igetSystemSourceType==" + igetSystemSourceType + "   contentKey==" + contentKey);
                    CarInfoService.this.setKeyEvent(32100023, EventType.KEY_TYPE, contentKey);
                } catch (Exception e) {
                    LU.w("CarInfoService_zyx", "onChangeEvent()   Exception==" + e.toString());
                }
            }
        }

        @Override // android.car.hardware.CarVendorExtensionManager.CarVendorExtensionCallback
        public void onErrorEvent(int i, int i1) {
        }
    };
    private final ServiceConnection mCarConnectionCallback = new ServiceConnection() { // from class: com.yfve.engineeringmode.CarInfoService.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                CarInfoService.this.mCarYFDiagManager = (CarYFDiagManager) CarInfoService.this.mCarApiClient.getCarManager(Car.VENDOR_YFDIAG_SERVICE);
                if (CarInfoService.this.mCarYFDiagManager != null) {
                    CarInfoService.this.mCarYFDiagManager.registerCallback(CarInfoService.this.diagCallback);
                    CarInfoService.this.carVendorExtensionManager = (CarVendorExtensionManager) CarInfoService.this.mCarApiClient.getCarManager(Car.VENDOR_EXTENSION_SERVICE);
                    if (CarInfoService.this.carVendorExtensionManager != null) {
                        CarInfoService.this.carVendorExtensionManager.registerCallback(CarInfoService.this.vendorExtensionCallback);
                        CarInfoService.this.mCarInfoManager = (CarInfoManager) CarInfoService.this.mCarApiClient.getCarManager(Car.INFO_SERVICE);
                        if (CarInfoService.this.mCarInfoManager == null) {
                            LU.e("CarInfoService_zyx", "onServiceConnected()   carVendorExtensionManager== null");
                            return;
                        }
                        String strFirstSendMode = FileUtil.getFirstSendFile(CarInfoService.this);
                        LU.w("CarInfoService_zyx", "onServiceConnected()  strFirstSendMode==" + strFirstSendMode);
                        if ("100".equals(strFirstSendMode)) {
                            String strVersion = CarInfoService.this.get("ro.build.id");
                            if (strVersion != null && !"".equals(strVersion) && !"null".equals(strVersion)) {
                                LU.w("CarInfoService_zyx", "baidu  strVersion==" + strVersion);
                                CarInfoService.this.setByteProperty(14, 0, strVersion.getBytes());
                            }
                            CarInfoService.this.getBtAddress();
                            if (!"".equals(CarInfoService.this.mstrBtAddress) && CarInfoService.this.mstrBtAddress != null) {
                                LU.w("CarInfoService_zyx", "bt address==" + CarInfoService.this.mstrBtAddress);
                                CarInfoService.this.setByteProperty(19, 0, CarInfoService.this.mstrBtAddress.getBytes());
                            }
                            CarInfoService.this.setMemoryInfo();
                            CarInfoService.this.set8033ValueToMcu();
                            byte[] byteGetScreenVersion = CarInfoService.this.getByteScreenVersion();
                            LU.w("CarInfoService_zyx", "byteGetScreenVersion==" + new String(byteGetScreenVersion));
                            CarInfoService.this.setByteProperty(15, 0, byteGetScreenVersion);
                            CarInfoService.this.tryGetMAC();
                            String vmcu = CarInfoService.this.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_VMCU);
                            String strFileMcuF188 = FileUtil.getMcuF188File(CarInfoService.this);
                            LU.w("CarInfoService_zyx", "vmcu==" + vmcu + "   strFileMcuF188==" + strFileMcuF188);
                            if (!"".equals(vmcu) && !vmcu.equals(strFileMcuF188)) {
                                FileUtil.saveMcuF188File(CarInfoService.this, vmcu);
                                String vmcu2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())) + "-" + vmcu;
                                LU.w("CarInfoService_zyx", "vmcu==" + vmcu2);
                                CarInfoService.this.setVmcuEvent("OTA900001", EventType.KEY_TYPE, vmcu2);
                            }
                            String strFileOsUpdateResult = FileUtil.getOsUpdateResultEntryFile(FileUtil.SAVE_OS_RESULR_FOR_ENTRY);
                            LU.w("CarInfoService_zyx", "strFileOsUpdateResult==" + strFileOsUpdateResult + "  mstr8033Value==" + CarInfoService.this.mstr8033Value);
                            if (!"".equals(strFileOsUpdateResult) && strFileOsUpdateResult != null) {
                                if ("".equals(CarInfoService.this.mstr8033Value) || CarInfoService.this.mstr8033Value == null) {
                                    CarInfoService.this.mstr8033Value = "NULL";
                                }
                                String strFileOsUpdateResult2 = strFileOsUpdateResult + "-" + CarInfoService.this.mstr8033Value;
                                LU.w("CarInfoService_zyx", "strFileOsUpdateResult==" + strFileOsUpdateResult2);
                                CarInfoService.this.setCcpuEvent("OTA900001", EventType.KEY_TYPE, strFileOsUpdateResult2);
                                FileUtil.deletefile(FileUtil.SAVE_OS_RESULR_FOR_ENTRY);
                            }
                            FileUtil.deleteFirstSendFile(CarInfoService.this);
                            return;
                        }
                        return;
                    }
                    LU.e("CarInfoService_zyx", "onServiceConnected()   carVendorExtensionManager== null");
                    return;
                }
                LU.e("CarInfoService_zyx", "onServiceConnected()   mCarYFDiagManager== null");
            } catch (Exception e) {
                LU.e("CarInfoService_zyx", "onServiceConnected()  Exception" + e.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LU.e("CarInfoService_zyx", "onServiceDisconnected() +++++ ");
        }
    };
    private final CarYFDiagManager.CarVendorExtensionCallback diagCallback = new CarYFDiagManager.CarVendorExtensionCallback() { // from class: com.yfve.engineeringmode.CarInfoService.5
        @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
        public void onChangeEvent(CarPropertyValue pv) {
            int id = pv.getPropertyId();
            LU.w("CarInfoService_zyx", "onChangeEvent: command = " + id + "  mSdWindowIsShow =" + PublicDefine.mSdWindowIsShow + "   pv.getValue()==" + pv.getValue());
            if (PublicDefine.mSdWindowIsShow) {
                return;
            }
            Intent intent = new Intent(CarInfoService.this, SpeakerDiagnosisActivity.class);
            intent.putExtra("KeyIsShowReturn", "gone");
            if (id == 2) {
                if (8 != ((Integer) pv.getValue()).intValue()) {
                    LU.w("CarInfoService_zyx", "onChangeEvent: getValue = " + pv.getValue());
                    PublicDefine.INNER_SPEAKER_STATE = ((Integer) pv.getValue()).intValue();
                    CarInfoService.this.startActivity(intent);
                }
            } else if (id == 554699833) {
                String temp = (String) pv.getValue();
                PublicDefine.OUT_SPEAKER_STATE = temp.substring(76);
                LU.w("CarInfoService_zyx", "onChangeEvent: OUT_SPEAKER_STATE = " + PublicDefine.OUT_SPEAKER_STATE);
                CarInfoService.this.startActivity(intent);
            }
        }

        @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
        public void onErrorEvent(int i, int i1) {
        }
    };
    private boolean mbIsFirstOpenWifi = false;
    private final int CAR_SOURCE_TYPE_NONE = 0;
    private final int CAR_SOURCE_TYPE_FM = 2;
    private final int CAR_SOURCE_TYPE_MEDIA_MUTE = 998;
    private final int CAR_SOURCE_TYPE_BAIDU_TING = 5;
    private final int CAR_SOURCE_TYPE_BTA = 13;
    private final int CAR_SOURCE_TYPE_USB_0 = 14;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LU.w("CarInfoService_zyx", "CarInfoService   onCreate()+++++++++++++++++  ");
        try {
            this.msyncEntry = Entry.getInstance();
            this.msyncEntry.init(getApplicationContext(), new Entry.Callback() { // from class: com.yfve.engineeringmode.CarInfoService.1
                @Override // com.baidu.xiaoduos.syncclient.Entry.Callback
                public void onSuccess() {
                    LU.w("CarInfoService_zyx", "syncEntry.init onSuccess()  EVENT_ID==32100023");
                    CarInfoService.this.mbIsEntryInit = true;
                }

                @Override // com.baidu.xiaoduos.syncclient.Entry.Callback
                public void onFailure() {
                    LU.w("CarInfoService_zyx", "syncEntry.init onFailure()  ");
                    CarInfoService.this.mbIsEntryInit = false;
                }
            });
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "onCreate() Exception == " + e.toString());
        }
        this.mrl78 = (Rl78Manager) getSystemService("Rl78Service");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.yfve.intent.action.EOL_NEED_GET_SCREEN_VERSION");
        registerReceiver(this.mBroadcastReceiver, intentFilter);
        if (this.mTelephonyManager == null) {
            this.mTelephonyManager = (TelephonyManager) getSystemService("phone");
        }
        this.mCarApiClient = Car.createCar(this, this.mCarConnectionCallback);
        if (this.mCarApiClient != null) {
            this.mCarApiClient.connect();
        }
    }

    public void setByteProperty(int prop, int area, byte[] val) {
        try {
            if (this.mCarYFDiagManager != null) {
                this.mCarYFDiagManager.setByteProperty(prop, area, val);
            } else {
                LU.e("CarInfoService_zyx", "setByteProperty()   mCarYFDiagManager == null");
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "setByteProperty()   Exception==" + e.toString());
        }
    }

    public void setKeyEvent(int eventId, EventType type, String attach) {
        try {
            if (this.msyncEntry != null) {
                LU.e("CarInfoService_zyx", "setOnEvent()   attach == " + attach);
                Entry entry = this.msyncEntry;
                entry.onEvent(eventId, type, "{\"key\":\"" + attach + "\"}");
            } else {
                LU.e("CarInfoService_zyx", "setOnEvent()   msyncEntry == null");
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "setOnEvent()   Exception==" + e.toString());
        }
    }

    public void setVmcuEvent(String eventId, EventType type, String attach) {
        try {
            if (this.msyncEntry != null) {
                LU.e("CarInfoService_zyx", "setOnEvent()   attach == " + attach);
                Entry entry = this.msyncEntry;
                entry.onEvent(eventId, type, "{\"vmcu\":\"" + attach + "\"}");
            } else {
                LU.e("CarInfoService_zyx", "setOnEvent()   msyncEntry == null");
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "setOnEvent()   Exception==" + e.toString());
        }
    }

    public void setCcpuEvent(String eventId, EventType type, String attach) {
        try {
            if (this.msyncEntry != null) {
                LU.e("CarInfoService_zyx", "setOnEvent()   attach == " + attach);
                Entry entry = this.msyncEntry;
                entry.onEvent(eventId, type, "{\"ccpu\":\"" + attach + "\"}");
            } else {
                LU.e("CarInfoService_zyx", "setOnEvent()   msyncEntry == null");
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "setOnEvent()   Exception==" + e.toString());
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LU.e("CarInfoService_zyx", " onStartCommand()++ ");
        return super.onStartCommand(intent, flags, startId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryGetMAC() {
        String strMacAddress = FileUtil.getMacAddress(this);
        LU.w("CarInfoService_zyx", "tryGetMAC(1)       strMacAddress==" + strMacAddress);
        if ("".equals(strMacAddress)) {
            this.mwifiManager = (WifiManager) getSystemService("wifi");
            new Thread(new Runnable() { // from class: com.yfve.engineeringmode.CarInfoService.6
                @Override // java.lang.Runnable
                public void run() {
                    int stateWifi = CarInfoService.this.mwifiManager.getWifiState();
                    LU.w("CarInfoService_zyx", "tryGetMAC(1)       stateWifi==" + stateWifi);
                    if (stateWifi == 3 || stateWifi == 2) {
                        CarInfoService.this.mbIsFirstOpenWifi = true;
                        if (stateWifi == 2) {
                            CarInfoService.this.mbIsGetStateWifi = true;
                            while (CarInfoService.this.mbIsGetStateWifi) {
                                try {
                                    Thread.sleep(1000L);
                                    stateWifi = CarInfoService.this.mwifiManager.getWifiState();
                                    if (stateWifi != 3) {
                                        CarInfoService.this.mbIsGetStateWifi = true;
                                    } else {
                                        CarInfoService.this.mbIsGetStateWifi = false;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        CarInfoService.this.mbIsFirstOpenWifi = false;
                        CarInfoService.this.mbIsGetStateWifi = true;
                        CarInfoService.this.mwifiManager.setWifiEnabled(true);
                        while (CarInfoService.this.mbIsGetStateWifi) {
                            try {
                                Thread.sleep(1000L);
                                stateWifi = CarInfoService.this.mwifiManager.getWifiState();
                                if (stateWifi != 3) {
                                    CarInfoService.this.mbIsGetStateWifi = true;
                                } else {
                                    CarInfoService.this.mbIsGetStateWifi = false;
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    LU.w("CarInfoService_zyx", "tryGetMAC(2)       stateWifi==" + stateWifi);
                    WifiInfo wifiInfo = CarInfoService.this.mwifiManager.getConnectionInfo();
                    if (wifiInfo != null) {
                        CarInfoService.this.mstrWifiMac = wifiInfo.getMacAddress();
                        LU.w("CarInfoService_zyx", "tryGetMAC()       mstrMac==" + CarInfoService.this.mstrWifiMac);
                        if (!"".equals(CarInfoService.this.mstrWifiMac) && CarInfoService.this.mstrWifiMac != null && !"0".equals(CarInfoService.this.mstrWifiMac)) {
                            try {
                                FileUtil.saveMacAddress(CarInfoService.this, CarInfoService.this.mstrWifiMac);
                                CarInfoService.this.setByteProperty(20, 0, CarInfoService.this.mstrWifiMac.getBytes());
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            LU.w("CarInfoService_zyx", "tryGetMAC(2)       getMacAddress==" + FileUtil.getMacAddress(CarInfoService.this));
                        }
                    }
                    LU.w("CarInfoService_zyx", "tryGetMAC()       mbIsFirstOpenWifi==" + CarInfoService.this.mbIsFirstOpenWifi);
                    if (!CarInfoService.this.mbIsFirstOpenWifi) {
                        CarInfoService.this.mwifiManager.setWifiEnabled(false);
                    }
                }
            }).start();
            return;
        }
        try {
            setByteProperty(20, 0, strMacAddress.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBtAddress() {
        Method method;
        Object obj;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Field field = BluetoothAdapter.class.getDeclaredField("mService");
            field.setAccessible(true);
            Object bluetoothManagerService = field.get(bluetoothAdapter);
            if (bluetoothManagerService != null && (method = bluetoothManagerService.getClass().getMethod("getAddress", new Class[0])) != null && (obj = method.invoke(bluetoothManagerService, new Object[0])) != null) {
                this.mstrBtAddress = obj.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LU.e("CarInfoService_zyx", " onDestroy()++++");
        if (this.mCarApiClient != null) {
            this.mCarApiClient.disconnect();
        }
        unregisterReceiver(this.mBroadcastReceiver);
        System.exit(0);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getByteScreenVersion() {
        byte[] version = new byte[5];
        try {
            if (this.mrl78 != null) {
                this.mrl78.Rl78_connect();
                version = this.mrl78.getVer();
                this.mrl78.Rl78_disconnect();
            } else {
                LU.e("CarInfoService_zyx", " getByteScreenVersion()  mrl78 == null ");
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", " getByteScreenVersion()  Exception e == " + e.toString());
        }
        return version;
    }

    private String getScreenVersion() {
        try {
            if (this.mrl78 != null) {
                this.mrl78.Rl78_connect();
                byte[] bArr = new byte[5];
                byte[] version = this.mrl78.getVer();
                this.mrl78.Rl78_disconnect();
                return new String(version);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void set8033ValueToMcu() {
        try {
            if (this.mbDeviceSizeIs64G) {
                this.mstr8033Value = get("ro.vendor.yfve.ccpupn.adasmap");
            } else {
                this.mstr8033Value = get("ro.vendor.yfve.ccpupn");
            }
            LU.w("CarInfoService_zyx", "set8033ValueToMcu()  mstr8033Value==" + this.mstr8033Value);
            if (!"".equals(this.mstr8033Value) && this.mstr8033Value != null && !"NULL".equals(this.mstr8033Value)) {
                LU.e("CarInfoService_zyx", "set8033ValueToMcu()  need set setByteProperty");
                this.mCarYFDiagManager.setByteProperty(22, 0, this.mstr8033Value.getBytes());
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "set8033ToMcu()  Exception e==" + e.toString());
        }
    }

    private void getDataSizeNeedReset(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        long available = availableBlocks * blockSize;
        LU.w("CarInfoService_zyx", "getDataSizeNeedReset()     available==" + available);
        if (available <= 1073741824) {
            LU.w("CarInfoService_zyx", "getDataSizeNeedReset()     need master reset");
            Intent intent = new Intent("android.intent.action.FACTORY_RESET");
            intent.setPackage("android");
            intent.addFlags(268435456);
            intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
            sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMemoryInfo() {
        try {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            long totalBytes = stat.getTotalBytes();
            float fTotal = ((float) totalBytes) / 1.0E9f;
            LU.e("CarInfoService_zyx", "getMemoryInfo()     fTotal==" + fTotal);
            getDataSizeNeedReset(stat);
            byte[] fTotals = new byte[1];
            if (fTotal > 16.0f) {
                LU.e("CarInfoService_zyx", "getMemoryInfo()  size is 64");
                this.mbDeviceSizeIs64G = true;
                fTotals[0] = 2;
                setByteProperty(21, 0, fTotals);
            } else {
                LU.e("CarInfoService_zyx", "getMemoryInfo()  size is 32");
                this.mbDeviceSizeIs64G = false;
                fTotals[0] = 1;
                setByteProperty(21, 0, fTotals);
            }
        } catch (Exception e) {
            LU.e("CarInfoService_zyx", "getMemoryInfo()  Exception e==" + e.toString());
        }
    }

    public void sendBroadcast(String action, String strKey, int strValue) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(strKey, strValue);
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurAudioSourceType() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        }
        Class clazz = this.mAudioManager.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getCurAudioSourceType", new Class[0]);
            return ((Integer) method.invoke(this.mAudioManager, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurAudioUsage() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        }
        Class clazz = this.mAudioManager.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getCurAudioUsage", new Class[0]);
            return ((Integer) method.invoke(this.mAudioManager, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String get(String key) {
        Method getMethod = null;
        if (0 == 0) {
            try {
                getMethod = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            } catch (Exception e) {
                return "null";
            }
        }
        return (String) getMethod.invoke(null, key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSystemSourceType(int iCurAudioSourceType, int iCurAudioUsage) {
        if (iCurAudioUsage == 16 || iCurAudioUsage == 2 || iCurAudioUsage == 12) {
            if (iCurAudioUsage == 2) {
                return 4;
            }
            if (iCurAudioUsage == 12) {
                return 6;
            }
            if (iCurAudioUsage == 16) {
                return 3;
            }
            return 0;
        } else if (iCurAudioSourceType == 998) {
            return 1;
        } else {
            if (iCurAudioSourceType == 2) {
                return 2;
            }
            if (iCurAudioSourceType == 5) {
                return 5;
            }
            if (iCurAudioSourceType == 13) {
                return 7;
            }
            if (iCurAudioSourceType == 14) {
                return 8;
            }
            return 0;
        }
    }
}
