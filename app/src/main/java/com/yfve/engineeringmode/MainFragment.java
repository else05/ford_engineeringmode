package com.yfve.engineeringmode;

import android.app.Dialog;
import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.CarYFDiagManager;
import android.content.ComponentName;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yfve.adapter.ListTextAdapter;
import com.yfve.tools.LU;
import com.yfve.tools.PublicDefine;
import java.lang.reflect.Method;

/* loaded from: classes1.dex */
public class MainFragment extends BaseFragment {
    private ListView mlvMainListView;
    private int miCurrentLockStatus = 0;
    private int miClickMcuBt = 0;
    private final int BT_UPDATE_MCU = R.styleable.AppCompatTheme_textAppearanceSearchResultTitle;
    private final int START_UPDATE_MCU = 2;
    private final int CAR_TYPE_BLACK_483 = 6;
    private final int CAR_TYPE_BLACK_611 = 8;
    private ListTextAdapter mListTextAdapter = null;
    private String[] mstrsEntrys = new String[0];
    private Dialog mMcuDialog = null;
    private TextView mtvCancel = null;
    private RelativeLayout mrlMcuDialog = null;
    public final String KEY_IS_SHOW_RETURN = "KeyIsShowReturn";
    public final String VALUE_SHOW_RETURN = "Show";
    public final String KEY_IS_START_PLAY = "KeyIsStartPlay";
    public final String VALUE_START_PLAY = "start";
    private AdapterView.OnItemClickListener mlvOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.yfve.engineeringmode.MainFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    MainFragment.this.mMainActivity.switchToFragment(PublicDefine.mstrBezelFragmentName);
                    return;
                case 1:
                    MainFragment.this.startOsDeveloperMode();
                    return;
                case 2:
                    MainFragment.this.startLog();
                    return;
                case 3:
                    MainFragment.this.startUsbUpdate();
                    return;
                case 4:
                    MainFragment.this.miClickMcuBt = R.styleable.AppCompatTheme_textAppearanceSearchResultTitle;
                    if (1 == MainFragment.this.miCurrentLockStatus) {
                        MainFragment.this.startUsbUpdateMcu(2);
                        return;
                    } else {
                        MainFragment.this.mMcuDialog.show();
                        return;
                    }
                default:
                    return;
            }
        }
    };
    public AudioManager mAudioManager = null;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.MainFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.bt_dialog_center_cancel) {
                MainFragment.this.miClickMcuBt = 0;
                if (MainFragment.this.mMcuDialog != null) {
                    MainFragment.this.mMcuDialog.cancel();
                }
            }
        }
    };

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_listview_show, (ViewGroup) null);
        this.mlvMainListView = (ListView) view.findViewById(R.id.lv_main_list);
        this.mstrsEntrys = this.mMainActivity.getResources().getStringArray(R.array.list_em_p2_re);
        this.mListTextAdapter = new ListTextAdapter(this.mMainActivity, this.mstrsEntrys);
        this.mlvMainListView.setAdapter((ListAdapter) this.mListTextAdapter);
        this.mlvMainListView.setOnItemClickListener(this.mlvOnItemClickListener);
        addVipDialog();
        onServiceConnected();
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_em));
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        this.miClickMcuBt = 0;
        this.mMcuDialog.cancel();
    }

    private int getCurSystemSourceType() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) this.mMainActivity.getSystemService(Car.AUDIO_SERVICE);
        }
        Class clazz = this.mAudioManager.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getCurMediaSourceType", new Class[0]);
            return ((Integer) method.invoke(this.mAudioManager, new Object[0])).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int getCurAudioSourceType() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) this.mMainActivity.getSystemService(Car.AUDIO_SERVICE);
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

    public void onServiceConnected() {
        try {
            String str = this.tag;
            LU.w(str, "onServiceConnected()  mYFDiagManager==" + this.mMainActivity.mYFDiagManager + "   mCarInfoManager==" + this.mMainActivity.mCarInfoManager + "   mCarSensorManager==" + this.mMainActivity.mCarSensorManager + "   mCarVendorExtensionManager==" + this.mMainActivity.mCarVendorExtensionManager);
            if (this.mMainActivity.mCarVendorExtensionManager != null) {
                try {
                    this.mMainActivity.mCarVendorExtensionManager.setGlobalProperty(Integer.class, 557845584, 2);
                } catch (Exception e) {
                    String str2 = this.tag;
                    LU.e(str2, "onServiceConnected()   mCarVendorExtensionManager   Exception == " + e.toString());
                }
            }
            if (this.mMainActivity.mYFDiagManager != null) {
                this.mMainActivity.mYFDiagManager.registerCallback(new CarYFDiagManager.CarVendorExtensionCallback() { // from class: com.yfve.engineeringmode.MainFragment.2
                    @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
                    public void onChangeEvent(CarPropertyValue carPropertyValue) {
                        if (carPropertyValue == null) {
                            LU.e(MainFragment.this.tag, "onServiceConnected()  null == carPropertyValue  return");
                            return;
                        }
                        int iPropertyId = carPropertyValue.getPropertyId();
                        if (iPropertyId == 3) {
                            MainFragment.this.miCurrentLockStatus = ((Integer) carPropertyValue.getValue()).intValue();
                            String str3 = MainFragment.this.tag;
                            LU.w(str3, "onChangeEvent()    miCurrentLockStatus==" + MainFragment.this.miCurrentLockStatus);
                            if (1 == MainFragment.this.miCurrentLockStatus) {
                                if (MainFragment.this.mMcuDialog != null) {
                                    MainFragment.this.mMcuDialog.cancel();
                                }
                                if (101 == MainFragment.this.miClickMcuBt) {
                                    MainFragment.this.startUsbUpdateMcu(2);
                                }
                            }
                        }
                    }

                    @Override // android.car.hardware.CarYFDiagManager.CarVendorExtensionCallback
                    public void onErrorEvent(int i, int i1) {
                    }
                });
            }
        } catch (Exception e2) {
            String str3 = this.tag;
            LU.e(str3, "onServiceConnected()   Exception==" + e2.toString());
        }
        if (this.mMainActivity.mYFDiagManager != null) {
            try {
                this.miCurrentLockStatus = this.mMainActivity.mYFDiagManager.getIntProperty(3, 0);
            } catch (Exception e3) {
                this.miCurrentLockStatus = 0;
                String str4 = this.tag;
                LU.e(str4, "onServiceConnected()  getIntProperty   Exception==" + e3.toString());
            }
        } else {
            LU.e(this.tag, "onServiceConnected()  null==mYFDiagManager");
            this.miCurrentLockStatus = 0;
        }
        String str5 = this.tag;
        LU.e(str5, "onServiceConnected !   miCurrentLockStatus==" + this.miCurrentLockStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startOsDeveloperMode() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.baidu.xiaoduos.setting", "com.baidu.xiaoduos.setting.activitys.OSDeveloperModeActvity");
        intent.setComponent(componentName);
        try {
            this.mMainActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLog() {
        try {
            ComponentName componentName = new ComponentName("com.baidu.xiaoduos.setting", "com.baidu.xiaoduos.setting.activitys.DeveloperActivity");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUsbUpdate() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.swv.updatemanage", "com.swv.updatemanage.MainActivity");
        intent.setComponent(componentName);
        try {
            this.mMainActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUsbUpdateMcu(int isUpdateMcu) {
        ComponentName componetName = new ComponentName("com.yfve.usbupdate", "com.yfve.usbupdate.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componetName);
        intent.putExtra("UpdateMcu", isUpdateMcu);
        try {
            this.mMainActivity.startActivity(intent);
        } catch (Exception e) {
            LU.e(this.tag, "com.yfve.usbupdate NOT FOUND");
        }
    }

    private void startSettings() {
        ComponentName componetName = new ComponentName("com.android.settings", "com.android.settings.Settings");
        Intent intent = new Intent();
        intent.setComponent(componetName);
        try {
            this.mMainActivity.startActivity(intent);
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "com.android.settings Exception==" + e.toString());
        }
    }

    private void addVipDialog() {
        this.mMcuDialog = new Dialog(this.mMainActivity);
        Window window = this.mMcuDialog.getWindow();
        LayoutInflater inflater = LayoutInflater.from(this.mMainActivity);
        View viewVipDialog = inflater.inflate(R.layout.layout_dialog, (ViewGroup) null);
        this.mrlMcuDialog = (RelativeLayout) viewVipDialog.findViewById(R.id.rl_mcu_dialog);
        String str = this.tag;
        LU.w(str, "addVipDialog()   mMainActivity.miCarType==" + this.mMainActivity.miCarType);
        if (6 == this.mMainActivity.miCarType || 8 == this.mMainActivity.miCarType) {
            this.mrlMcuDialog.setBackground(this.mMainActivity.getDrawable(R.drawable.background_lk));
        } else {
            this.mrlMcuDialog.setBackground(this.mMainActivity.getDrawable(R.drawable.background_ft));
        }
        this.mtvCancel = (TextView) viewVipDialog.findViewById(R.id.bt_dialog_center_cancel);
        this.mtvCancel.setOnClickListener(this.mbtOnClickListener);
        this.mMcuDialog.setContentView(viewVipDialog);
        this.mMcuDialog.setCanceledOnTouchOutside(false);
        window.setGravity(17);
        WindowManager.LayoutParams windowLayoutParams = window.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowLayoutParams.width = 700;
        windowLayoutParams.height = 400;
        window.setAttributes(windowLayoutParams);
    }
}
