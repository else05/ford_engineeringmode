package com.yfve.tools;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAvrcpController;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothPbapClient;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.lang.reflect.Method;
import java.util.Set;

/* loaded from: classes1.dex */
public class BtUtils {
    private static BtUtils mBtUtils = null;
    private BluetoothAdapter mBluetoothAdapter;
    public final String tag = "BtUtils_zyx";
    public final int A2DP_SINK = 11;
    public final int AVRCP_CONTROLLER = 12;
    public final int HEADSET_CLIENT = 16;
    public final int PBAP_CLIENT = 17;
    private BluetoothPbapClient mBluetoothPbapClient = null;
    private BluetoothA2dpSink mBluetoothA2dpSink = null;
    private BluetoothAvrcpController mBluetoothAvrcpController = null;
    private BluetoothHeadsetClient mBluetoothHeadsetClient = null;
    private Context mContext = null;
    private BroadcastReceiver mBtBroadcastReceiver = new BroadcastReceiver() { // from class: com.yfve.tools.BtUtils.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            LU.w("BtUtils_zyx", "onReceive()   action==" + action);
            int hashCode = action.hashCode();
            if (hashCode != -1530327060) {
                if (hashCode == 1123270207 && action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                int blueState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                LU.w("BtUtils_zyx", "onReceive()   ACTION_STATE_CHANGED  blueState==" + blueState);
            }
        }
    };
    private BluetoothProfile.ServiceListener mClientServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.yfve.tools.BtUtils.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            BtUtils.this.mBluetoothAdapter.getProfileConnectionState(profile);
            switch (profile) {
                case 11:
                    BtUtils.this.mBluetoothA2dpSink = (BluetoothA2dpSink) proxy;
                    return;
                case 12:
                    BtUtils.this.mBluetoothAvrcpController = (BluetoothAvrcpController) proxy;
                    return;
                case 13:
                case 14:
                case 15:
                default:
                    return;
                case 16:
                    BtUtils.this.mBluetoothHeadsetClient = (BluetoothHeadsetClient) proxy;
                    return;
                case 17:
                    BtUtils.this.mBluetoothPbapClient = (BluetoothPbapClient) proxy;
                    return;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
        }
    };

    public static BtUtils getInstance() {
        if (mBtUtils == null) {
            synchronized (BtUtils.class) {
                mBtUtils = new BtUtils();
            }
        }
        return mBtUtils;
    }

    public BtUtils() {
        this.mBluetoothAdapter = null;
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isError() {
        if (this.mBluetoothAdapter == null) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        return this.mBluetoothAdapter.isEnabled();
    }

    public boolean getBondedDevices() {
        Set<BluetoothDevice> bBluetoothDevice = this.mBluetoothAdapter.getBondedDevices();
        if (bBluetoothDevice.size() > 0) {
            return true;
        }
        return false;
    }

    public String getProfileConnectionStateA2DP_SINK() {
        int bluetoothA2dpSink = this.mBluetoothAdapter.getProfileConnectionState(11);
        if (2 == bluetoothA2dpSink) {
            return "BluetoothA2dpSink";
        }
        return "";
    }

    public String getProfileConnectionStateAVRCP_CONTROLLER() {
        int bluetoothAvrcpContorller = this.mBluetoothAdapter.getProfileConnectionState(12);
        if (2 == bluetoothAvrcpContorller) {
            return "BluetoothAvrcpController";
        }
        return "";
    }

    public String getProfileConnectionStateHEADSET_CLIENT() {
        int bluetoothHeadsetclient = this.mBluetoothAdapter.getProfileConnectionState(16);
        if (2 == bluetoothHeadsetclient) {
            return "BluetoothHeadsetClient";
        }
        return "";
    }

    public String getProfileConnectionStatePBAP_CLIENT() {
        int bluetoothPbapclient = this.mBluetoothAdapter.getProfileConnectionState(17);
        if (2 == bluetoothPbapclient) {
            return "BluetoothPbapClient";
        }
        return "";
    }

    public boolean isConnected() {
        boolean isConnected = false;
        try {
            Method method = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
            method.setAccessible(true);
            int state = ((Integer) method.invoke(this.mBluetoothAdapter, null)).intValue();
            if (state == 2) {
                Set<BluetoothDevice> devices = this.mBluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
                    method.setAccessible(true);
                    isConnected = ((Boolean) isConnectedMethod.invoke(device, null)).booleanValue();
                    LU.w("BtUtils_zyx", "isConnected()   getName==" + device.getName() + "  getAddress==" + device.getAddress() + "   isConnected==" + isConnected);
                    if (isConnected) {
                        return isConnected;
                    }
                }
            }
        } catch (Exception e) {
            LU.w("BtUtils_zyx", "isConnected()  Exception== " + e.toString());
            e.printStackTrace();
        }
        return isConnected;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mContext.registerReceiver(this.mBtBroadcastReceiver, intentFilter);
        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mClientServiceListener, 11);
        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mClientServiceListener, 12);
        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mClientServiceListener, 16);
        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mClientServiceListener, 17);
    }

    public void unregisterReceiver() {
        this.mContext.unregisterReceiver(this.mBtBroadcastReceiver);
    }
}
