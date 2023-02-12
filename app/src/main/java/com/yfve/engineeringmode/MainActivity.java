package com.yfve.engineeringmode;

import android.car.Car;
import android.car.CarInfoManager;
import android.car.hardware.CarSensorEvent;
import android.car.hardware.CarSensorManager;
import android.car.hardware.CarVendorExtensionManager;
import android.car.hardware.CarYFDiagManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.protect.A;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;

/* loaded from: classes1.dex */
public class MainActivity extends BaseActivity {
    public Car mCarApiClient;
    public CarInfoManager mCarInfoManager;
    public CarVendorExtensionManager mCarVendorExtensionManager;
    public CarYFDiagManager mYFDiagManager;
    private LinearLayout mllMainHead;
    private RelativeLayout mrlMainActivity;
    private RelativeLayout mrlMainWindow;
    private TextView mtvReturn;
    private TextView mtvTitle;
    private int miClickMcuOrBaidulog = 0;
    private final int START_UPDATE_ALL = 1;
    private final int CAR_TYPE_BLACK_483 = 6;
    private final int CAR_TYPE_BLACK_611 = 8;
    public float miCurrentSpeed = 0.0f;
    public int miCarType = -1;
    public int miCarModeYear = -1;
    public CarSensorManager mCarSensorManager = null;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.MainActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.main_bt_em_return) {
                MainActivity.this.onBackPressed();
            }
        }
    };
    private final String EM = "工程模式";
    private View.OnLongClickListener mbtOnLongClickListener = new View.OnLongClickListener() { // from class: com.yfve.engineeringmode.MainActivity.2
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.main_em_title) {
                String str = MainActivity.this.tag;
                LU.w(str, "onSystemUiVisibilityChange()   mtvTitle.getText().toString()==" + MainActivity.this.mtvTitle.getText().toString());
                if ("工程模式".equals(MainActivity.this.mtvTitle.getText().toString())) {
                    MainActivity.this.startUsbUpdateMcu(1);
                }
            }
            return true;
        }
    };
    private View.OnSystemUiVisibilityChangeListener mOnSystemUiVisibilityChangeListener = new View.OnSystemUiVisibilityChangeListener() { // from class: com.yfve.engineeringmode.MainActivity.3
        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int visibility) {
            View decorView = MainActivity.this.getWindow().getDecorView();
            MainActivity.this.getWindow().addFlags(17563936);
            decorView.setSystemUiVisibility(5894);
        }
    };
    private final ServiceConnection mCarConnectionCallback = new ServiceConnection() { // from class: com.yfve.engineeringmode.MainActivity.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                MainActivity.this.mYFDiagManager = (CarYFDiagManager) MainActivity.this.mCarApiClient.getCarManager(Car.VENDOR_YFDIAG_SERVICE);
                MainActivity.this.mCarSensorManager = (CarSensorManager) MainActivity.this.mCarApiClient.getCarManager(Car.SENSOR_SERVICE);
                MainActivity.this.mCarInfoManager = (CarInfoManager) MainActivity.this.mCarApiClient.getCarManager(Car.INFO_SERVICE);
                MainActivity.this.mCarVendorExtensionManager = (CarVendorExtensionManager) MainActivity.this.mCarApiClient.getCarManager(Car.VENDOR_EXTENSION_SERVICE);
                LU.w(MainActivity.this.tag, "onServiceConnected()  mYFDiagManager==" + MainActivity.this.mYFDiagManager + "   mCarInfoManager==" + MainActivity.this.mCarInfoManager + "   mCarSensorManager==" + MainActivity.this.mCarSensorManager + "   mCarVendorExtensionManager==" + MainActivity.this.mCarVendorExtensionManager);
            } catch (Exception e) {
                LU.e(MainActivity.this.tag, "onServiceConnected()  Exception==" + e.toString());
            }
            if (MainActivity.this.mCarSensorManager != null) {
                try {
                    MainActivity.this.mCarSensorManager.registerListener(MainActivity.this.mOnSensorChangedListener, 2, 0);
                } catch (Exception e2) {
                    LU.e(MainActivity.this.tag, "onServiceConnected()   mCarSensorManager  Exception==" + e2.toString());
                }
            }
            if (MainActivity.this.mCarInfoManager != null) {
                try {
                    byte[] dataType = MainActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.BASIC_INFO_KEY_MODEL_VENDOR);
                    byte[] dataModeYear = MainActivity.this.mCarInfoManager.getBytesProprety(CarInfoManager.BASIC_INFO_KEY_MODEL_YEAR_VENDOR);
                    int iCarModeYear = 0;
                    int iCarType = 0;
                    for (byte b : dataType) {
                        iCarType += b;
                    }
                    for (byte b2 : dataModeYear) {
                        iCarModeYear += b2;
                    }
                    LU.w(MainActivity.this.tag, "onServiceConnected()  mCarInfoManager iCarType = " + iCarType);
                    LU.w(MainActivity.this.tag, "onServiceConnected()  mCarInfoManager iCarModeYear = " + iCarModeYear);
                    MainActivity.this.miCarType = iCarType;
                    MainActivity.this.miCarModeYear = iCarModeYear;
                } catch (Exception e3) {
                    LU.e(MainActivity.this.tag, "onServiceConnected() mCarInfoManager  Exception==" + e3.toString());
                }
            }
            MainActivity.this.setHmiBgColor();
            MainActivity.this.switchToFragment(PublicDefine.mstrMainFragmentName);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            LU.e(MainActivity.this.tag, "onServiceDisconnected()  ");
            Toast.makeText(MainActivity.this, "服务绑定失败，退出请重试", 0).show();
            MainActivity.this.finish();
        }
    };
    private CarSensorManager.OnSensorChangedListener mOnSensorChangedListener = new CarSensorManager.OnSensorChangedListener() { // from class: com.yfve.engineeringmode.MainActivity.5
        @Override // android.car.hardware.CarSensorManager.OnSensorChangedListener
        public void onSensorChanged(CarSensorEvent carSensorEvent) {
            MainActivity.this.miCurrentSpeed = carSensorEvent.floatValues[0];
        }
    };

    public native String stringFromJNI();

    @Override // com.yfve.engineeringmode.BaseActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        A.V(-1425997822, this, new Object[]{savedInstanceState});
    }

    public void setFullscreen(boolean isFullscreen) {
        if (isFullscreen) {
            this.mllMainHead.setVisibility(8);
        } else {
            this.mllMainHead.setVisibility(0);
        }
    }

    public void setTitleContent(String strContent) {
        String str = this.tag;
        StringBuilder sb = new StringBuilder();
        sb.append("setTitleContent()  strContent==");
        sb.append(strContent);
        sb.append("  mtvTitle==null ? ");
        sb.append(this.mtvTitle == null);
        LU.w(str, sb.toString());
        if (this.mtvTitle != null && strContent != null) {
            this.mtvTitle.setText(strContent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUsbUpdateMcu(int isUpdateMcu) {
        ComponentName componetName = new ComponentName("com.yfve.usbupdate", "com.yfve.usbupdate.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componetName);
        intent.putExtra("UpdateMcu", isUpdateMcu);
        try {
            startActivity(intent);
        } catch (Exception e) {
            LU.e(this.tag, "com.yfve.usbupdate NOT FOUND");
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        int iFragmentCount = this.mFragmentManager.getBackStackEntryCount();
        String str = this.tag;
        LU.w(str, "onBackPressed()  iFragmentCount==" + iFragmentCount);
        if (1 >= iFragmentCount) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LU.w(this.tag, "onResume()  ");
        View decorView = getWindow().getDecorView();
        getWindow().addFlags(17563936);
        int uiOptions = 5894;
        uiOptions = (6 == this.miCarType || 8 == this.miCarType) ? 5382 : 5382;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        LU.w(this.tag, "onStop()  ");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        String str = this.tag;
        LU.e(str, "onDestroy()  mCarApiClient==null ? " + this.mCarApiClient);
        if (this.mCarSensorManager != null && this.mOnSensorChangedListener != null) {
            this.mCarSensorManager.unregisterListener(this.mOnSensorChangedListener, 2);
        }
        if (this.mCarApiClient != null) {
            this.mCarApiClient.disconnect();
        }
    }

    public void setHmiBgColor() {
        if (6 == this.miCarType || 8 == this.miCarType) {
            this.mrlMainActivity.setBackground(getDrawable(R.drawable.background_lk));
        } else {
            this.mrlMainActivity.setBackground(getDrawable(R.drawable.background_ft));
        }
    }
}
