package com.yfve.engineeringmode;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.yfve.tools.LU;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* loaded from: classes1.dex */
public class WifiDebugFragment extends BaseFragment {
    private TextView mtvIpValue;
    private TextView mtvWifiDebug;
    private TextView mtvWifiInfo;
    private View.OnClickListener mbtOnClickListener = new View.OnClickListener() { // from class: com.yfve.engineeringmode.WifiDebugFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v.getId() == R.id.tv_wifi_debug) {
                try {
                    WifiDebugFragment.this.set("sys.service.use_wifi_adb", "1");
                    WifiDebugFragment.this.set("sys.service.adbd_enable", "0");
                    WifiDebugFragment.this.set("sys.service.adbd_enable", "1");
                    Toast.makeText(WifiDebugFragment.this.mMainActivity, "Succeed", 0).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Method setMethod = null;
    private Method getMethod = null;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_wifi_debug, (ViewGroup) null);
        this.mtvIpValue = (TextView) view.findViewById(R.id.tv_wifi_ip);
        this.mtvWifiDebug = (TextView) view.findViewById(R.id.tv_wifi_debug);
        this.mtvWifiInfo = (TextView) view.findViewById(R.id.tv_wifi_info);
        this.mtvWifiInfo.setText("使用说明\r\n1，将车机与PC连接在同一个局域网下。\r\n2，点击“Wifi Debug”按钮。\r\n3，打开PC上的命令终端，输入命令：adb connect 192.168.xx.xxx\r\n（注：192.168.xx.xxx是界面显示的IP地址，如果获取IP失败，请退出重试并检查Wifi是否连接成功）\r\n");
        String strIpAddress = getLocalIpAddress();
        String str = this.tag;
        LU.w(str, "onCreateView()   strIpAddress==" + strIpAddress);
        this.mtvIpValue.setText(strIpAddress);
        this.mtvWifiDebug.setOnClickListener(this.mbtOnClickListener);
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_wifid));
    }

    public String getIpAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getLocalIpAddress() {
        int ipAddress;
        WifiManager wifiManager = (WifiManager) this.mMainActivity.getApplicationContext().getSystemService("wifi");
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String str = this.tag;
        LU.w(str, "getLocalIpAddress()   wifiInfo==" + wifiInfo);
        if (wifiInfo == null || (ipAddress = wifiInfo.getIpAddress()) == 0) {
            return "获取IP地址失败，请检查并重试";
        }
        return String.format("%d.%d.%d.%d", Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255));
    }

    public void set(String key, String def) {
        try {
            if (this.setMethod == null) {
                this.setMethod = Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class);
            }
            this.setMethod.invoke(null, key, def);
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "Platform error: " + e.toString());
        }
    }

    public String get(String key) {
        try {
            if (this.getMethod == null) {
                this.getMethod = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            }
            return (String) this.getMethod.invoke(null, key);
        } catch (Exception e) {
            String str = this.tag;
            LU.e(str, "Platform error: " + e.toString());
            return "null";
        }
    }
}
