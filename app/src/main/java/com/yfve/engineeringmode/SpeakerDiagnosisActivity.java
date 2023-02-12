package com.yfve.engineeringmode;

import android.car.Car;
import android.car.CarInfoManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.CarSensorEvent;
import android.car.hardware.CarSensorManager;
import android.car.hardware.CarYFDiagManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.automotive.vehicle.V2_0.VehicleArea;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.media.radio.lib.StatusCode;
import com.baidu.protect.A;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;
import java.lang.reflect.Method;

/* loaded from: classes1.dex */
public class SpeakerDiagnosisActivity extends AppCompatActivity {
    private AudioFocusRequest mAudioFocusRequest;
    private AudioManager mAudioManager;
    private Car mCarApiClient;
    private CarInfoManager mCarInfoManager;
    private TextView mOutSpeakerState;
    private Button mSpeakOff;
    private Button mSpeakOn;
    private CarYFDiagManager mYFDiagManager;
    private LinearLayout mllBtOnOff;
    private LinearLayout mllSwvAll;
    private LinearLayout mllSwvF;
    private LinearLayout mllSwvR;
    private RelativeLayout mrlSDActivity;
    private TextView mtvReturn;
    private TextView mtvSWV0;
    private TextView mtvSWV1;
    private TextView mtvSWV2;
    private TextView mtvSWV3;
    private TextView mtvSWV4;
    private TextView mtvSWV5;
    private TextView mtvSWV6;
    private TextView mtvSWV7;
    public final String tag = getClass().getSimpleName() + "zyx";
    public CarSensorManager mCarSensorManager = null;
    private int miGetSwaVolumeValue = -1;
    private boolean mbIsSendValueFinish = false;
    private float miCurrentSpeed = 0.0f;
    private int miCarModeF_TWEETER = -1;
    private int miCarModeR_TWEETER = -1;
    private final int HAVE_TWEETER = 1;
    private final int SPEAKER_RETURN = 8;
    private final int CAR_TYPE_BLACK_483 = 6;
    private final int CAR_TYPE_BLACK_611 = 8;
    public final String KEY_IS_SHOW_RETURN = "KeyIsShowReturn";
    public final String VALUE_SHOW_RETURN = "Show";
    public final String VALUE_GONE_RETURN = "gone";
    public final String KEY_IS_START_PLAY = "KeyIsStartPlay";
    public final String VALUE_START_PLAY = "start";
    private String mstrsStartPlay = "";
    private int migetStreamVolume = -1;
    private boolean mbisStreamMute = false;
    private View.OnSystemUiVisibilityChangeListener mOnSystemUiVisibilityChangeListener = new View.OnSystemUiVisibilityChangeListener() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.1
        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int visibility) {
            String str = SpeakerDiagnosisActivity.this.tag;
            LU.w(str, "onSystemUiVisibilityChange()   visibility======" + visibility);
            if (visibility != 4 && visibility != 1024) {
                View decorView = SpeakerDiagnosisActivity.this.getWindow().getDecorView();
                SpeakerDiagnosisActivity.this.getWindow().addFlags(17563936);
                decorView.setSystemUiVisibility(5894);
            }
        }
    };
    AudioManager.OnAudioFocusChangeListener mafChanggeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.2
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case StatusCode.DESIRED_TIME_OUT /* -3 */:
                case StatusCode.ARRAY_INDEX_OUT /* -2 */:
                case -1:
                default:
                    return;
            }
        }
    };
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id != R.id.sd_bt_em_return) {
                switch (id) {
                    case R.id.speaker_off /* 2131230898 */:
                        try {
                            SpeakerDiagnosisActivity.this.mYFDiagManager.setIntProperty(1, 0, 0);
                            LU.w(SpeakerDiagnosisActivity.this.tag, "onClick: speaker_off!!!");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case R.id.speaker_on /* 2131230899 */:
                        try {
                            SpeakerDiagnosisActivity.this.mYFDiagManager.setIntProperty(1, 0, 1);
                            LU.w(SpeakerDiagnosisActivity.this.tag, "onClick: speaker_on!!!");
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            }
            try {
                SpeakerDiagnosisActivity.this.mYFDiagManager.setIntProperty(1, 0, 0);
                LU.w(SpeakerDiagnosisActivity.this.tag, "onClick: return >>  speaker_off!!!");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            SpeakerDiagnosisActivity.this.finish();
        }
    };
    private final ServiceConnection mCarConnectionCallback = new ServiceConnection() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.5
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            int iCarType;
            try {
                LU.w(SpeakerDiagnosisActivity.this.tag, "onServiceConnected() ");
                SpeakerDiagnosisActivity.this.mYFDiagManager = (CarYFDiagManager) SpeakerDiagnosisActivity.this.mCarApiClient.getCarManager(Car.VENDOR_YFDIAG_SERVICE);
                SpeakerDiagnosisActivity.this.mCarInfoManager = (CarInfoManager) SpeakerDiagnosisActivity.this.mCarApiClient.getCarManager(Car.INFO_SERVICE);
                SpeakerDiagnosisActivity.this.mCarSensorManager = (CarSensorManager) SpeakerDiagnosisActivity.this.mCarApiClient.getCarManager(Car.SENSOR_SERVICE);
                if (SpeakerDiagnosisActivity.this.mCarInfoManager != null) {
                    try {
                        int[] iGetSwaVolumeValues = SpeakerDiagnosisActivity.this.mCarInfoManager.getIntsProprety(CarInfoManager.INFO_CAL_FD03);
                        SpeakerDiagnosisActivity.this.miGetSwaVolumeValue = iGetSwaVolumeValues[0];
                        if (SpeakerDiagnosisActivity.this.miGetSwaVolumeValue >= 0) {
                            SpeakerDiagnosisActivity.this.mbIsSendValueFinish = true;
                        }
                        LU.w(SpeakerDiagnosisActivity.this.tag, "onChangeEvent: miGetSwaVolumeValue== " + SpeakerDiagnosisActivity.this.miGetSwaVolumeValue + "   mbIsSendValueFinish==" + SpeakerDiagnosisActivity.this.mbIsSendValueFinish);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (SpeakerDiagnosisActivity.this.mCarSensorManager != null) {
                    try {
                        SpeakerDiagnosisActivity.this.mCarSensorManager.registerListener(SpeakerDiagnosisActivity.this.mOnSensorChangedListener, 2, 0);
                    } catch (Exception e2) {
                        LU.e(SpeakerDiagnosisActivity.this.tag, "onServiceConnected()   mCarSensorManager  Exception==" + e2.toString());
                    }
                }
                if (SpeakerDiagnosisActivity.this.mCarInfoManager != null) {
                    try {
                        byte[] data = SpeakerDiagnosisActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.BASIC_INFO_KEY_MODEL_VENDOR);
                        iCarType = 0;
                        for (byte b : data) {
                            iCarType += b;
                        }
                        LU.w(SpeakerDiagnosisActivity.this.tag, "onServiceConnected()  mCarInfoManager iCarType = " + iCarType);
                    } catch (Exception e3) {
                        LU.e(SpeakerDiagnosisActivity.this.tag, "onServiceConnected(cartype) mCarInfoManager  Exception==" + e3.toString());
                    }
                    try {
                        if (6 != iCarType && 8 != iCarType) {
                            SpeakerDiagnosisActivity.this.mrlSDActivity.setBackground(SpeakerDiagnosisActivity.this.getDrawable(R.drawable.background_ft));
                            byte[] dataF = SpeakerDiagnosisActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.INFO_KEY_CONFIG_FRONT_TWEETER);
                            byte[] dataR = SpeakerDiagnosisActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.INFO_KEY_CONFIG_REAR_TWEETER);
                            SpeakerDiagnosisActivity.this.miCarModeF_TWEETER = dataF[0];
                            SpeakerDiagnosisActivity.this.miCarModeR_TWEETER = dataR[0];
                        }
                        byte[] dataF2 = SpeakerDiagnosisActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.INFO_KEY_CONFIG_FRONT_TWEETER);
                        byte[] dataR2 = SpeakerDiagnosisActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.INFO_KEY_CONFIG_REAR_TWEETER);
                        SpeakerDiagnosisActivity.this.miCarModeF_TWEETER = dataF2[0];
                        SpeakerDiagnosisActivity.this.miCarModeR_TWEETER = dataR2[0];
                    } catch (Exception e4) {
                        LU.e(SpeakerDiagnosisActivity.this.tag, "onServiceConnected(tweeter) mCarInfoManager  Exception==" + e4.toString());
                    }
                    SpeakerDiagnosisActivity.this.mrlSDActivity.setBackground(SpeakerDiagnosisActivity.this.getDrawable(R.drawable.background_lk));
                }
                LU.w(SpeakerDiagnosisActivity.this.tag, "onServiceConnected() miCarModeF_TWEETER == " + SpeakerDiagnosisActivity.this.miCarModeF_TWEETER + "    miCarModeR_TWEETER==" + SpeakerDiagnosisActivity.this.miCarModeR_TWEETER);
                if (1 != SpeakerDiagnosisActivity.this.miCarModeF_TWEETER) {
                    SpeakerDiagnosisActivity.this.mllSwvF.setVisibility(8);
                } else {
                    SpeakerDiagnosisActivity.this.mllSwvF.setVisibility(0);
                }
                if (1 != SpeakerDiagnosisActivity.this.miCarModeR_TWEETER) {
                    SpeakerDiagnosisActivity.this.mllSwvR.setVisibility(8);
                } else {
                    SpeakerDiagnosisActivity.this.mllSwvR.setVisibility(0);
                }
                SpeakerDiagnosisActivity.this.mSpeakOn.setEnabled(true);
                SpeakerDiagnosisActivity.this.mSpeakOff.setEnabled(true);
                if (SpeakerDiagnosisActivity.this.mYFDiagManager != null) {
                    SpeakerDiagnosisActivity.this.mYFDiagManager.registerCallback(SpeakerDiagnosisActivity.this.mCarVendorExtensionCallback);
                }
            } catch (Exception e5) {
                LU.e(SpeakerDiagnosisActivity.this.tag, "onServiceConnected()  Exception == " + e5.toString());
                Toast.makeText((Context) SpeakerDiagnosisActivity.this, (CharSequence) "服务绑定失败，退出请重试", 0).show();
                SpeakerDiagnosisActivity.this.finish();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    private CarSensorManager.OnSensorChangedListener mOnSensorChangedListener = new CarSensorManager.OnSensorChangedListener() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.6
        @Override // android.car.hardware.CarSensorManager.OnSensorChangedListener
        public void onSensorChanged(CarSensorEvent carSensorEvent) {
            SpeakerDiagnosisActivity.this.miCurrentSpeed = carSensorEvent.floatValues[0];
            if (5.0f < SpeakerDiagnosisActivity.this.miCurrentSpeed) {
                try {
                    SpeakerDiagnosisActivity.this.mYFDiagManager.setIntProperty(1, 0, 0);
                    LU.w(SpeakerDiagnosisActivity.this.tag, "onSensorChanged() miCurrentSpeed>5 : speaker_off!!!");
                    Toast.makeText((Context) SpeakerDiagnosisActivity.this, (CharSequence) "车速大于5km/h，强制退出", 0).show();
                    SpeakerDiagnosisActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private CarYFDiagManager.CarVendorExtensionCallback mCarVendorExtensionCallback = new CarYFDiagManager.CarVendorExtensionCallback() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.7
        @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            int idProperty = carPropertyValue.getPropertyId();
            String str = SpeakerDiagnosisActivity.this.tag;
            LU.e(str, "onChangeEvent()  idProperty = " + idProperty);
            if (idProperty != 2) {
                if (idProperty == 554699833) {
                    if (SpeakerDiagnosisActivity.this.miGetSwaVolumeValue >= 0) {
                        LU.e(SpeakerDiagnosisActivity.this.tag, "onChangeEvent() need sendBroadcastToEol");
                        if (SpeakerDiagnosisActivity.this.mbIsSendValueFinish) {
                            SpeakerDiagnosisActivity.this.sendBroadcastToEol("com.yfve.intent.action.START_SWA_GET_VOLUME_VALUE", "getVolumeValue", SpeakerDiagnosisActivity.this.miGetSwaVolumeValue);
                            SpeakerDiagnosisActivity.this.mbIsSendValueFinish = false;
                        }
                    }
                    SpeakerDiagnosisActivity.this.mOutSpeakerState.setVisibility(0);
                    SpeakerDiagnosisActivity.this.mllSwvAll.setVisibility(8);
                    String strValueOUT = (String) carPropertyValue.getValue();
                    if (strValueOUT == null || "".equals(strValueOUT)) {
                        LU.e(SpeakerDiagnosisActivity.this.tag, "inner speaker data is null!");
                        return;
                    }
                    String temp = strValueOUT.substring(76);
                    String str2 = SpeakerDiagnosisActivity.this.tag;
                    LU.w(str2, "onChangeEvent() inner speaker data is: " + temp);
                    SpeakerDiagnosisActivity.this.mOutSpeakerState.setText(temp);
                    return;
                }
                return;
            }
            int igetValueIN = ((Integer) carPropertyValue.getValue()).intValue();
            String str3 = SpeakerDiagnosisActivity.this.tag;
            LU.w(str3, "onChangeEvent()   igetValueIN = " + igetValueIN + "    mbIsSendValueFinish==" + SpeakerDiagnosisActivity.this.mbIsSendValueFinish);
            if (8 != igetValueIN) {
                if (SpeakerDiagnosisActivity.this.miGetSwaVolumeValue >= 0) {
                    LU.e(SpeakerDiagnosisActivity.this.tag, "onChangeEvent() need sendBroadcastToEol");
                    if (SpeakerDiagnosisActivity.this.mbIsSendValueFinish) {
                        SpeakerDiagnosisActivity.this.sendBroadcastToEol("com.yfve.intent.action.START_SWA_GET_VOLUME_VALUE", "getVolumeValue", SpeakerDiagnosisActivity.this.miGetSwaVolumeValue);
                        SpeakerDiagnosisActivity.this.mbIsSendValueFinish = false;
                    }
                }
                SpeakerDiagnosisActivity.this.mOutSpeakerState.setVisibility(8);
                SpeakerDiagnosisActivity.this.mllSwvAll.setVisibility(0);
                SpeakerDiagnosisActivity.this.notifyInnerSpeaker(igetValueIN);
                return;
            }
            String str4 = SpeakerDiagnosisActivity.this.tag;
            LU.w(str4, "onChangeEvent()     OUT_SPEAKER_STATE == " + PublicDefine.OUT_SPEAKER_STATE + "  INNER_SPEAKER_STATE==" + PublicDefine.INNER_SPEAKER_STATE);
            if (SpeakerDiagnosisActivity.this.miGetSwaVolumeValue >= 0) {
                LU.e(SpeakerDiagnosisActivity.this.tag, "onChangeEvent() need sendBroadcastToEol");
                SpeakerDiagnosisActivity.this.sendBroadcastToEol("com.yfve.intent.action.EOL_END_SWA");
            }
            if (!"".equals(PublicDefine.OUT_SPEAKER_STATE)) {
                PublicDefine.OUT_SPEAKER_STATE = "";
                SpeakerDiagnosisActivity.this.finish();
            } else if (-1 != PublicDefine.INNER_SPEAKER_STATE) {
                PublicDefine.INNER_SPEAKER_STATE = -1;
                SpeakerDiagnosisActivity.this.finish();
            } else {
                SpeakerDiagnosisActivity.this.finish();
            }
        }

        @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
        public void onErrorEvent(int i, int i1) {
        }
    };
    private final String ACTION_EOL_START_SWA_GET_VOLUME_VALUE = "com.yfve.intent.action.START_SWA_GET_VOLUME_VALUE";
    private final String ACTION_EOL_END_SWA = "com.yfve.intent.action.EOL_END_SWA";
    private final String EOL_GET_VOLUME_VALUE_KEY = "getVolumeValue";
    private final int FLAG_RECEIVER_INCLUDE_BACKGROUND = VehicleArea.GLOBAL;

    protected void onCreate(Bundle savedInstanceState) {
        A.V(-1425997821, this, new Object[]{savedInstanceState});
    }

    private void initView() {
        this.mSpeakOn = (Button) findViewById(R.id.speaker_on);
        this.mSpeakOff = (Button) findViewById(R.id.speaker_off);
        this.mOutSpeakerState = (TextView) findViewById(R.id.out_speaker);
        this.mllSwvAll = (LinearLayout) findViewById(R.id.ll_swv_in_all);
        this.mllSwvF = (LinearLayout) findViewById(R.id.ll_swv_in_f);
        this.mllSwvR = (LinearLayout) findViewById(R.id.ll_swv_in_r);
        this.mllBtOnOff = (LinearLayout) findViewById(R.id.ll_bt_onoff);
        this.mtvSWV0 = (TextView) findViewById(R.id.tv_swv_in0);
        this.mtvSWV1 = (TextView) findViewById(R.id.tv_swv_in1);
        this.mtvSWV2 = (TextView) findViewById(R.id.tv_swv_in2);
        this.mtvSWV3 = (TextView) findViewById(R.id.tv_swv_in3);
        this.mtvSWV4 = (TextView) findViewById(R.id.tv_swv_in4);
        this.mtvSWV5 = (TextView) findViewById(R.id.tv_swv_in5);
        this.mtvSWV6 = (TextView) findViewById(R.id.tv_swv_in6);
        this.mtvSWV7 = (TextView) findViewById(R.id.tv_swv_in7);
        this.mtvReturn.setOnClickListener(this.mbtOnClickListener);
        this.mSpeakOn.setOnClickListener(this.mbtOnClickListener);
        this.mSpeakOff.setOnClickListener(this.mbtOnClickListener);
    }

    private void initDate() {
        this.mSpeakOn.setEnabled(false);
        this.mSpeakOff.setEnabled(false);
        if (PublicDefine.INNER_SPEAKER_STATE != -1) {
            this.mOutSpeakerState.setVisibility(8);
            this.mllSwvAll.setVisibility(0);
            notifyInnerSpeaker(PublicDefine.INNER_SPEAKER_STATE);
        } else if (!PublicDefine.OUT_SPEAKER_STATE.equals("")) {
            this.mOutSpeakerState.setVisibility(0);
            this.mllSwvAll.setVisibility(8);
            this.mOutSpeakerState.setText(PublicDefine.OUT_SPEAKER_STATE);
        }
    }

    public void onResume() {
        super.onResume();
        PublicDefine.mSdWindowIsShow = true;
        this.mAudioManager = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        this.migetStreamVolume = getLastAudibleStreamVolume(3);
        this.mbisStreamMute = this.mAudioManager.isStreamMute(3);
        String str = this.tag;
        LU.e(str, "onResume !!    OUT_SPEAKER_STATE==" + PublicDefine.OUT_SPEAKER_STATE + "  INNER_SPEAKER_STATE==" + PublicDefine.INNER_SPEAKER_STATE + "    migetStreamVolume==" + this.migetStreamVolume + "  mbisStreamMute==" + this.mbisStreamMute);
        if (!"".equals(PublicDefine.OUT_SPEAKER_STATE)) {
            this.mtvReturn.setVisibility(8);
        } else if (-1 == PublicDefine.INNER_SPEAKER_STATE) {
            this.mAudioManager.setStreamMute(3, true);
            this.mtvReturn.setVisibility(0);
        } else {
            this.mtvReturn.setVisibility(8);
        }
    }

    public void onPause() {
        super.onPause();
        LU.e(this.tag, "onPause !!!");
        PublicDefine.mSdWindowIsShow = false;
        PublicDefine.OUT_SPEAKER_STATE = "";
        PublicDefine.INNER_SPEAKER_STATE = -1;
    }

    public void onStop() {
        super.onStop();
        LU.e(this.tag, "onStop !!!");
        if (this.mYFDiagManager != null) {
            try {
                this.mYFDiagManager.setIntProperty(1, 0, 0);
                LU.e(this.tag, "onStop: speaker_off!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mYFDiagManager != null && this.mCarVendorExtensionCallback != null) {
            this.mYFDiagManager.unregisterCallback(this.mCarVendorExtensionCallback);
        }
        if (this.mCarSensorManager != null && this.mOnSensorChangedListener != null) {
            this.mCarSensorManager.unregisterListener(this.mOnSensorChangedListener, 2);
        }
        if (this.mAudioManager != null) {
            String str = this.tag;
            LU.e(str, "onDestroy()   mbisStreamMute==" + this.mbisStreamMute);
            if (!this.mbisStreamMute) {
                new Thread(new Runnable() { // from class: com.yfve.engineeringmode.SpeakerDiagnosisActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Thread.sleep(1000L);
                            SpeakerDiagnosisActivity.this.mAudioManager.setStreamVolume(3, SpeakerDiagnosisActivity.this.migetStreamVolume, 0);
                            SpeakerDiagnosisActivity.this.mAudioManager.setStreamMute(3, SpeakerDiagnosisActivity.this.mbisStreamMute);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return;
            }
            this.mAudioManager.setStreamVolume(3, this.migetStreamVolume, 0);
            this.mAudioManager.setStreamMute(3, this.mbisStreamMute);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyInnerSpeaker(int pos) {
        this.mtvSWV0.setSelected(false);
        this.mtvSWV1.setSelected(false);
        this.mtvSWV2.setSelected(false);
        this.mtvSWV3.setSelected(false);
        this.mtvSWV4.setSelected(false);
        this.mtvSWV5.setSelected(false);
        this.mtvSWV6.setSelected(false);
        this.mtvSWV7.setSelected(false);
        switch (pos) {
            case 0:
                this.mtvSWV0.setSelected(true);
                return;
            case 1:
                this.mtvSWV1.setSelected(true);
                return;
            case 2:
                this.mtvSWV2.setSelected(true);
                return;
            case 3:
                this.mtvSWV3.setSelected(true);
                return;
            case 4:
                this.mtvSWV4.setSelected(true);
                return;
            case 5:
                this.mtvSWV5.setSelected(true);
                return;
            case 6:
                this.mtvSWV6.setSelected(true);
                return;
            case 7:
                this.mtvSWV7.setSelected(true);
                return;
            default:
                return;
        }
    }

    private int getLastAudibleStreamVolume(int streamType) {
        Class clazz = this.mAudioManager.getClass();
        try {
            Method method = clazz.getMethod("getLastAudibleStreamVolume", Integer.TYPE);
            int igetVolume = ((Integer) method.invoke(this.mAudioManager, Integer.valueOf(streamType))).intValue();
            return igetVolume;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcastToEol(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addFlags(VehicleArea.GLOBAL);
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcastToEol(String action, String strKey, int strValue) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addFlags(VehicleArea.GLOBAL);
        intent.putExtra(strKey, strValue);
        sendBroadcast(intent);
    }
}
