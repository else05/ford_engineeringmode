package com.yfve.engineeringmode;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.yfve.tools.IFromServweCallBack;
import com.yfve.tools.LU;
import com.yfve.tools.SocketUtils;

/* loaded from: classes1.dex */
public class WifiSettingsFragment extends BaseFragment {
    private EditText metChannel;
    private EditText metWorkMode;
    private TextView mtvChannelSet;
    private TextView mtvMacAddress;
    private TextView mtvModeVersion;
    private TextView mtvSignalStrength;
    private TextView mtvWifiBand;
    private TextView mtvWifiBand24;
    private TextView mtvWifiBand50;
    private TextView mtvWifiChannel;
    private TextView mtvWifiName;
    private TextView mtvWifiOff;
    private TextView mtvWifiOn;
    private TextView mtvWifiSwitch;
    private TextView mtvWorkMode;
    private TextView mtvWorkSet;
    private WifiManager mwifiManager = null;
    private String mstrMacAddress = "";
    private int miStateWifi = -1;
    private String mstrBandWifi = "";
    private boolean mbWifiSwitch = false;
    private final String STR_WIFI_VERSION = "1";
    private final String STR_WIFI_BAND_SET = "2";
    private final String STR_WIFI_BAND = "3";
    private final String STR_WIFI_CHANNEL_SET = "4";
    private final String STR_WIFI_CHANNEL = "5";
    private final String STR_WIFI_WORK_MODE_SET = "6";
    private final String STR_WIFI_WORK_MODE = "7";
    private final int INT_WIFI_VERSION = 1;
    private final int INT_WIFI_BAND = 3;
    private final int INT_WIFI_CHANNEL = 5;
    private final int INT_WIFI_WORK_MODE = 7;
    private Handler mHandler = null;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.WifiSettingsFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_wifi_band_24 /* 2131230757 */:
                    if (!WifiSettingsFragment.this.mbWifiSwitch) {
                        WifiSettingsFragment.this.onToast("请先打开wifi");
                        return;
                    } else {
                        SocketUtils.getInstance().sendValueToServer("2,1");
                        return;
                    }
                case R.id.bt_wifi_band_50 /* 2131230758 */:
                    if (!WifiSettingsFragment.this.mbWifiSwitch) {
                        WifiSettingsFragment.this.onToast("请先打开wifi");
                        return;
                    } else {
                        SocketUtils.getInstance().sendValueToServer("2,2");
                        return;
                    }
                case R.id.bt_wifi_channel_set /* 2131230759 */:
                    if (!WifiSettingsFragment.this.mbWifiSwitch) {
                        WifiSettingsFragment.this.onToast("请先打开wifi");
                    } else if ("1".equals(WifiSettingsFragment.this.mstrBandWifi)) {
                        String strChannel = WifiSettingsFragment.this.metChannel.getText().toString();
                        try {
                            int iChannel = Integer.parseInt(strChannel);
                            if (iChannel > 13 || iChannel < 1) {
                                WifiSettingsFragment.this.onToast("请输入有效值");
                            } else {
                                String str = WifiSettingsFragment.this.tag;
                                LU.i(str, "onClick()  strChannel==" + strChannel);
                                SocketUtils socketUtils = SocketUtils.getInstance();
                                socketUtils.sendValueToServer("4," + strChannel);
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        WifiSettingsFragment.this.onToast("请切换至2.4GHz状态下再设置");
                    }
                    WifiSettingsFragment.this.metChannel.setText("");
                    return;
                case R.id.bt_wifi_off /* 2131230760 */:
                    if (WifiSettingsFragment.this.mbWifiSwitch) {
                        WifiSettingsFragment.this.mwifiManager.setWifiEnabled(false);
                        return;
                    }
                    return;
                case R.id.bt_wifi_on /* 2131230761 */:
                    if (!WifiSettingsFragment.this.mbWifiSwitch) {
                        WifiSettingsFragment.this.mwifiManager.setWifiEnabled(true);
                        return;
                    }
                    return;
                case R.id.bt_wifi_work_set /* 2131230762 */:
                    if (WifiSettingsFragment.this.mbWifiSwitch) {
                        String strwork = WifiSettingsFragment.this.metWorkMode.getText().toString();
                        try {
                            int iwork = Integer.parseInt(strwork);
                            if (iwork > 4 || iwork < 1) {
                                WifiSettingsFragment.this.onToast("请输入有效值");
                            } else {
                                String str2 = WifiSettingsFragment.this.tag;
                                LU.i(str2, "onClick()  strwork==" + strwork);
                                SocketUtils socketUtils2 = SocketUtils.getInstance();
                                socketUtils2.sendValueToServer("6," + strwork);
                            }
                        } catch (Exception e2) {
                        }
                    } else {
                        WifiSettingsFragment.this.onToast("请先打开wifi");
                    }
                    WifiSettingsFragment.this.metWorkMode.setText("");
                    return;
                default:
                    return;
            }
        }
    };
    IFromServweCallBack mIFromServweCallBack = new IFromServweCallBack() { // from class: com.yfve.engineeringmode.WifiSettingsFragment.3
        @Override // com.yfve.tools.IFromServweCallBack
        public void onResultUpdate(String strResult) {
            WifiSettingsFragment.this.handleSocketDate(strResult);
        }
    };
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.yfve.engineeringmode.WifiSettingsFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            String strgetAction = intent.getAction();
            int hashCode = strgetAction.hashCode();
            if (hashCode != -1875733435) {
                if (hashCode == -385684331 && strgetAction.equals("android.net.wifi.RSSI_CHANGED")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (strgetAction.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    c = 0;
                }
                c = 65535;
            }
            switch (c) {
                case 0:
                    switch (intent.getIntExtra("wifi_state", 4)) {
                        case 0:
                            WifiSettingsFragment.this.mbWifiSwitch = false;
                            WifiSettingsFragment.this.mtvWifiSwitch.setText("OFF");
                            WifiSettingsFragment.this.mtvModeVersion.setText("--");
                            WifiSettingsFragment.this.mtvWifiBand.setText("--");
                            WifiSettingsFragment.this.mtvWifiChannel.setText("--");
                            WifiSettingsFragment.this.mtvWorkMode.setText("--");
                            return;
                        case 1:
                            WifiSettingsFragment.this.mbWifiSwitch = false;
                            WifiSettingsFragment.this.mtvWifiSwitch.setText("OFF");
                            WifiSettingsFragment.this.mtvModeVersion.setText("--");
                            WifiSettingsFragment.this.mtvWifiBand.setText("--");
                            WifiSettingsFragment.this.mtvWifiChannel.setText("--");
                            WifiSettingsFragment.this.mtvWorkMode.setText("--");
                            return;
                        case 2:
                            WifiSettingsFragment.this.mbWifiSwitch = false;
                            WifiSettingsFragment.this.mtvWifiSwitch.setText("OFF");
                            return;
                        case 3:
                            WifiSettingsFragment.this.mbWifiSwitch = true;
                            WifiSettingsFragment.this.mtvWifiSwitch.setText("ON");
                            SocketUtils.getInstance().sendValueToServer("0");
                            if (WifiSettingsFragment.this.mwifiManager != null) {
                                WifiInfo wifiInfo = WifiSettingsFragment.this.mwifiManager.getConnectionInfo();
                                WifiSettingsFragment.this.mstrMacAddress = wifiInfo.getMacAddress();
                                WifiSettingsFragment.this.mtvMacAddress.setText(WifiSettingsFragment.this.mstrMacAddress);
                                LU.w(WifiSettingsFragment.this.tag, "wifiInfo.getSSID()==" + wifiInfo.getSSID());
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                case 1:
                    WifiInfo info = WifiSettingsFragment.this.mwifiManager.getConnectionInfo();
                    if (info.getBSSID() != null) {
                        int strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
                        int speed = info.getLinkSpeed();
                        String ssid = info.getSSID();
                        int iRssi = info.getRssi();
                        LU.w(WifiSettingsFragment.this.tag, "strength==" + strength + "   speed==" + speed + "   units==Mbps  ssid==" + ssid + "  iRssi==" + iRssi);
                        if (WifiSettingsFragment.this.mtvSignalStrength != null) {
                            WifiSettingsFragment.this.mtvSignalStrength.setText(iRssi + "");
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    @Override // android.app.Fragment
    @SuppressLint({"WrongConstant"})
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_wifi_settings, (ViewGroup) null);
        SocketUtils.getInstance().onStartThread();
        SocketUtils.getInstance().registerDataListener("WifiSettingsFragment", this.mIFromServweCallBack);
        this.mwifiManager = (WifiManager) this.mMainActivity.getSystemService("wifi");
        this.miStateWifi = this.mwifiManager.getWifiState();
        reBroadcastReceiver();
        this.mHandler = new Handler() { // from class: com.yfve.engineeringmode.WifiSettingsFragment.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 1) {
                    WifiSettingsFragment.this.mtvModeVersion.setText((String) msg.obj);
                } else if (i != 3) {
                    if (i == 5) {
                        WifiSettingsFragment.this.mtvWifiChannel.setText((String) msg.obj);
                    } else if (i == 7) {
                        WifiSettingsFragment.this.mtvWorkMode.setText((String) msg.obj);
                    }
                } else {
                    WifiSettingsFragment.this.mstrBandWifi = (String) msg.obj;
                    if ("1".equals(WifiSettingsFragment.this.mstrBandWifi)) {
                        WifiSettingsFragment.this.mtvWifiBand.setText("2.4GHz");
                    } else if ("2".equals(WifiSettingsFragment.this.mstrBandWifi)) {
                        WifiSettingsFragment.this.mtvWifiBand.setText("5GHz");
                    }
                }
            }
        };
        this.mtvModeVersion = (TextView) view.findViewById(R.id.tv_wifi_version);
        this.mtvWifiName = (TextView) view.findViewById(R.id.tv_wifi_name);
        this.mtvWifiSwitch = (TextView) view.findViewById(R.id.tv_wifi_test_mode);
        this.mtvWifiBand = (TextView) view.findViewById(R.id.tv_wifi_band);
        this.mtvWifiChannel = (TextView) view.findViewById(R.id.tv_wifi_channel);
        this.mtvWorkMode = (TextView) view.findViewById(R.id.tv_wifi_working_mode);
        this.mtvSignalStrength = (TextView) view.findViewById(R.id.tv_wifi_signal_strength);
        this.mtvMacAddress = (TextView) view.findViewById(R.id.tv_wifi_mac_address);
        this.mtvWifiOn = (TextView) view.findViewById(R.id.bt_wifi_on);
        this.mtvWifiOff = (TextView) view.findViewById(R.id.bt_wifi_off);
        this.mtvWifiBand24 = (TextView) view.findViewById(R.id.bt_wifi_band_24);
        this.mtvWifiBand50 = (TextView) view.findViewById(R.id.bt_wifi_band_50);
        this.mtvChannelSet = (TextView) view.findViewById(R.id.bt_wifi_channel_set);
        this.mtvWorkSet = (TextView) view.findViewById(R.id.bt_wifi_work_set);
        this.metChannel = (EditText) view.findViewById(R.id.et_wifi_channel_set);
        this.metWorkMode = (EditText) view.findViewById(R.id.et_wifi_work_set);
        this.mtvWifiOn.setOnClickListener(this.mbtOnClickListener);
        this.mtvWifiOff.setOnClickListener(this.mbtOnClickListener);
        this.mtvWifiBand24.setOnClickListener(this.mbtOnClickListener);
        this.mtvWifiBand50.setOnClickListener(this.mbtOnClickListener);
        this.mtvChannelSet.setOnClickListener(this.mbtOnClickListener);
        this.mtvWorkSet.setOnClickListener(this.mbtOnClickListener);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_bd_wifi_set));
        if (this.miStateWifi == 3 || this.miStateWifi == 2) {
            this.mbWifiSwitch = true;
            this.mtvWifiSwitch.setText("ON");
            SocketUtils.getInstance().sendValueToServer("0");
            WifiInfo wifiInfo = this.mwifiManager.getConnectionInfo();
            this.mstrMacAddress = wifiInfo.getMacAddress();
            this.mtvMacAddress.setText(this.mstrMacAddress);
            String str = this.tag;
            LU.w(str, "wifiInfo.getSSID()==" + wifiInfo.getSSID());
            String str2 = this.tag;
            LU.w(str2, "wifiInfo.getBSSID()==" + wifiInfo.getBSSID());
            return;
        }
        this.mbWifiSwitch = false;
        this.mtvWifiSwitch.setText("OFF");
        this.mtvModeVersion.setText("--");
        this.mtvWifiBand.setText("--");
        this.mtvWifiChannel.setText("--");
        this.mtvWorkMode.setText("--");
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        unreBroadcastReceiver();
        SocketUtils.getInstance().unregisterDataListener("WifiSettingsFragment");
    }

    private void reBroadcastReceiver() {
        IntentFilter filter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mMainActivity.registerReceiver(this.mBroadcastReceiver, filter);
    }

    private void unreBroadcastReceiver() {
        this.mMainActivity.unregisterReceiver(this.mBroadcastReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToast(String str) {
        Toast.makeText(this.mMainActivity, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008d, code lost:
        if (r3.equals("1") != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleSocketDate(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yfve.engineeringmode.WifiSettingsFragment.handleSocketDate(java.lang.String):void");
    }
}
