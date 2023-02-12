package com.yfve.engineeringmode;

import android.car.CarInfoManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Rl78Manager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yfve.tools.FileUtil;
import com.yfve.tools.LU;
import java.util.Arrays;

/* loaded from: classes1.dex */
public class SoftwareVersionsFragment extends BaseFragment {
    private TextView mAhuFd02;
    private TextView mApkVersion;
    private TextView mIccid;
    private TextView mMcuVersion;
    private TextView mScreenVersion;
    private TextView mTcuEsn;
    private TextView mbtSoftwareVKernelVersion;
    private TextView mbtSoftwareVModel;
    private TextView mbtSoftwareVVersionNumber;
    private TextView mbtSoftwareVVersionRelease;
    private Rl78Manager mrl78;
    private TextView mtv4gVersion;
    private TextView mtvSpl0;
    private TextView mtvSpl1;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_software_v, (ViewGroup) null);
        this.mbtSoftwareVModel = (TextView) view.findViewById(R.id.tv_software_v_model);
        this.mbtSoftwareVVersionRelease = (TextView) view.findViewById(R.id.tv_software_v_version_release);
        this.mbtSoftwareVKernelVersion = (TextView) view.findViewById(R.id.tv_software_v_kernel_version);
        this.mbtSoftwareVVersionNumber = (TextView) view.findViewById(R.id.tv_software_v_version_number);
        this.mMcuVersion = (TextView) view.findViewById(R.id.mcu_version);
        this.mScreenVersion = (TextView) view.findViewById(R.id.screen_version);
        this.mApkVersion = (TextView) view.findViewById(R.id.apk_version);
        this.mIccid = (TextView) view.findViewById(R.id.tv_iccid);
        this.mTcuEsn = (TextView) view.findViewById(R.id.tv_tcu_esn);
        this.mAhuFd02 = (TextView) view.findViewById(R.id.tv_ahu_fd02);
        this.mtvSpl0 = (TextView) view.findViewById(R.id.tv_spl0);
        this.mtvSpl1 = (TextView) view.findViewById(R.id.tv_spl1);
        this.mtv4gVersion = (TextView) view.findViewById(R.id.tv_4g_v);
        TelephonyManager tm = (TelephonyManager) this.mMainActivity.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.READ_PHONE_STATE") != 0) {
            ActivityCompat.requestPermissions(this.mMainActivity, new String[]{"android.permission.READ_PHONE_STATE"}, 1);
        }
        String strIccid = FileUtil.getInstance().getIccid();
        String str = this.tag;
        LU.w(str, "FileUtil   strIccid==" + strIccid);
        if ("".equals(strIccid) || "0".equals(strIccid)) {
            strIccid = tm.getSimSerialNumber();
        }
        String str2 = this.tag;
        LU.w(str2, "strIccid==" + strIccid);
        this.mIccid.setText(strIccid);
        FileUtil.getInstance();
        String str4gV = FileUtil.get4gV();
        String str3 = this.tag;
        LU.w(str3, "str4gV==" + str4gV);
        this.mtv4gVersion.setText(str4gV);
        this.mApkVersion.setText(getApkVersion());
        setData();
        onServiceConnected();
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_software_v));
    }

    private void setData() {
        this.mbtSoftwareVModel.setText(Build.MODEL);
        this.mbtSoftwareVVersionRelease.setText(Build.VERSION.RELEASE);
        this.mbtSoftwareVKernelVersion.setText(System.getProperty("os.version"));
        this.mbtSoftwareVVersionNumber.setText(Build.DISPLAY);
        try {
            this.mrl78 = (Rl78Manager) this.mMainActivity.getSystemService("Rl78Service");
            if (this.mrl78 != null) {
                this.mrl78.Rl78_connect();
                byte[] bArr = new byte[5];
                byte[] version = this.mrl78.getVer();
                this.mScreenVersion.setText(new String(version));
                this.mrl78.Rl78_disconnect();
            } else {
                this.mScreenVersion.setText("--");
            }
        } catch (Exception e) {
        }
    }

    public void onServiceConnected() {
        LU.w(this.tag, "onServiceConnected !");
        try {
            byte[] data = this.mMainActivity.mCarInfoManager.getBytesProprety(CarInfoManager.ID_INFO_MCU_VERSION);
            String str = this.tag;
            LU.w(str, "onServiceConnected()   getBytesProprety = " + Arrays.toString(data));
            this.mMcuVersion.setText(formatVersion(data));
            String dataFD02 = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.ID_INFO_AUH_CAL_FD02);
            String str2 = this.tag;
            LU.w(str2, "onServiceConnected()   dataFD02 = " + dataFD02);
            this.mAhuFd02.setText(dataFD02);
            String dataSpl0 = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.INFO_SPL_A_VER);
            String str3 = this.tag;
            LU.w(str3, "onServiceConnected()   dataSpl0 = " + dataSpl0);
            this.mtvSpl0.setText(dataSpl0);
            String dataSpl1 = this.mMainActivity.mCarInfoManager.getStringProperty(CarInfoManager.INFO_SPL_B_VER);
            String str4 = this.tag;
            LU.w(str4, "onServiceConnected()   dataSpl1 = " + dataSpl1);
            this.mtvSpl1.setText(dataSpl1);
        } catch (Exception e) {
            String str5 = this.tag;
            LU.e(str5, "onServiceConnected()   mCarInfoManager   Exception == " + e.toString());
        }
        try {
            this.mTcuEsn.setText(formatTcuEsn((String) this.mMainActivity.mCarVendorExtensionManager.getGlobalProperty(String.class, 554699855)));
        } catch (Exception e2) {
            String str6 = this.tag;
            LU.e(str6, "onServiceConnected()   ID_TCU_ESN_VERSION   Exception == " + e2.toString());
        }
    }

    private String formatVersion(byte[] data) {
        try {
            StringBuilder version = new StringBuilder("v");
            for (byte b : data) {
                version.append(String.valueOf((int) b) + ".");
            }
            String str = version.deleteCharAt(version.length() - 1).toString();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String formatTcuEsn(String strTcuEsn) {
        try {
            String[] strTcuEsns = strTcuEsn.split(";");
            for (String strEsn : strTcuEsns) {
                LU.e(this.tag, "formatTcuEsn()  strEsn==" + strEsn);
                if (strEsn.contains("ESN")) {
                    return strEsn.split(":")[1];
                }
            }
            return "NULL";
        } catch (Exception e) {
            return "NULL";
        }
    }

    public String getApkVersion() {
        try {
            PackageManager packageManager = this.mMainActivity.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(this.mMainActivity.getPackageName(), 0);
            String version = packInfo.versionName;
            return version;
        } catch (Exception e) {
            return "NULL";
        }
    }
}
