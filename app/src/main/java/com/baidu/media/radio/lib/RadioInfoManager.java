package com.baidu.media.radio.lib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.media.radio.lib.IPDStatusCallback;
import com.baidu.media.radio.lib.IRadioScanCallBack;
import com.baidu.media.radio.lib.IRadioSeekCallBack;
import com.baidu.media.radio.lib.IRadioServiceExternal;
import com.baidu.media.radio.lib.ISignal2InfoCallback;
import com.baidu.media.radio.lib.ISignalInfoCallback;
import java.util.List;

/* loaded from: classes2.dex */
public class RadioInfoManager {
    private static final String CLASS_NAME = "com.baidu.media.radio.service.RadioExternalService";
    private static volatile RadioInfoManager INSTANCE = null;
    private static final String PACKAGE_NAME = "com.baidu.car.radio";
    private static final String TAG = RadioInfoManager.class.getSimpleName();
    private IRadioServiceExternal mBinder;
    private ServiceConnectionCallback mConnectCallback;
    private Context mContext;
    private boolean mIsServiceConnected;
    private PDStatusCallback mPDStatusCallback;
    private RadioScanCallBack mRadioScanCallBack;
    private RadioSeekCallBack mRadioSeekCallBack;
    private Signal2InfoCallback mSignal2InfoCallback;
    private SignalInfoCallback mSignalInfoCallback;
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.baidu.media.radio.lib.RadioInfoManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(RadioInfoManager.TAG, "Service is onServiceConnected");
            RadioInfoManager.this.mIsServiceConnected = true;
            RadioInfoManager.this.mBinder = IRadioServiceExternal.Stub.asInterface(iBinder);
            if (RadioInfoManager.this.mConnectCallback != null) {
                RadioInfoManager.this.mConnectCallback.onServiceConnected();
            }
            try {
                if (RadioInfoManager.this.mBinder != null) {
                    RadioInfoManager.this.mBinder.setSignalInfoCallback(RadioInfoManager.this.mISignalInfoCallback);
                    RadioInfoManager.this.mBinder.setSignal2InfoCallback(RadioInfoManager.this.mISignal2InfoCallback);
                    RadioInfoManager.this.mBinder.setPDStatusCallback(RadioInfoManager.this.mIPDStatusCallback);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(RadioInfoManager.TAG, "Service is disconnected");
            RadioInfoManager.this.mIsServiceConnected = false;
            if (RadioInfoManager.this.mConnectCallback != null) {
                RadioInfoManager.this.mConnectCallback.onServiceDisconnected();
            }
        }
    };
    private ISignalInfoCallback mISignalInfoCallback = new ISignalInfoCallback.Stub() { // from class: com.baidu.media.radio.lib.RadioInfoManager.2
        @Override // com.baidu.media.radio.lib.ISignalInfoCallback
        public void onSignalChange(RadioSignal radioSignal) {
            if (RadioInfoManager.this.mSignalInfoCallback != null) {
                RadioInfoManager.this.mSignalInfoCallback.onSignalQualityChange(radioSignal);
            }
        }
    };
    private ISignal2InfoCallback mISignal2InfoCallback = new ISignal2InfoCallback.Stub() { // from class: com.baidu.media.radio.lib.RadioInfoManager.3
        @Override // com.baidu.media.radio.lib.ISignal2InfoCallback
        public void onSignalChange(RadioSignal2 radioSignal) {
            if (RadioInfoManager.this.mSignal2InfoCallback != null) {
                RadioInfoManager.this.mSignal2InfoCallback.onSignalQualityChange(radioSignal);
            }
        }
    };
    private IPDStatusCallback mIPDStatusCallback = new IPDStatusCallback.Stub() { // from class: com.baidu.media.radio.lib.RadioInfoManager.4
        @Override // com.baidu.media.radio.lib.IPDStatusCallback
        public void onPDStatus(PDStatus status) throws RemoteException {
            if (RadioInfoManager.this.mPDStatusCallback != null) {
                RadioInfoManager.this.mPDStatusCallback.onPDStatus(status);
            }
        }
    };
    private IRadioSeekCallBack mIRadioSeekCallBack = new IRadioSeekCallBack.Stub() { // from class: com.baidu.media.radio.lib.RadioInfoManager.5
        @Override // com.baidu.media.radio.lib.IRadioSeekCallBack
        public void onSeek(int frequency) throws RemoteException {
            if (RadioInfoManager.this.mRadioSeekCallBack != null) {
                RadioInfoManager.this.mRadioSeekCallBack.onSeek(frequency);
            }
        }

        @Override // com.baidu.media.radio.lib.IRadioSeekCallBack
        public void onFinish(int frequency) throws RemoteException {
            if (RadioInfoManager.this.mRadioSeekCallBack != null) {
                RadioInfoManager.this.mRadioSeekCallBack.onFinish(frequency);
            }
        }
    };
    private IRadioScanCallBack mIRadioSanCallBack = new IRadioScanCallBack.Stub() { // from class: com.baidu.media.radio.lib.RadioInfoManager.6
        @Override // com.baidu.media.radio.lib.IRadioScanCallBack
        public void onScan(int frequency) throws RemoteException {
            if (RadioInfoManager.this.mRadioScanCallBack != null) {
                RadioInfoManager.this.mRadioScanCallBack.onScan(frequency);
            }
        }

        @Override // com.baidu.media.radio.lib.IRadioScanCallBack
        public void onFinish() throws RemoteException {
            if (RadioInfoManager.this.mRadioScanCallBack != null) {
                RadioInfoManager.this.mRadioScanCallBack.onFinish();
            }
        }
    };

    public static RadioInfoManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RadioInfoManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RadioInfoManager(context);
                }
            }
        }
        return INSTANCE;
    }

    private RadioInfoManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        Log.i(TAG, "init");
        Intent intent = new Intent();
        ComponentName component = new ComponentName(PACKAGE_NAME, CLASS_NAME);
        intent.setComponent(component);
        this.mContext.bindService(intent, this.mServiceConnection, 1);
    }

    public void onServiceConnectCallback(ServiceConnectionCallback callback) {
        this.mConnectCallback = callback;
    }

    public void setSignalInfoCallback(SignalInfoCallback callBack) {
        this.mSignalInfoCallback = callBack;
    }

    public int setPara(int var1, int[] var2) {
        String str = TAG;
        Log.i(str, "setPara() var1 = " + var1 + ", var2" + var2);
        if (this.mBinder == null) {
            Log.e(TAG, "mBinder == null");
            return -1;
        }
        try {
            return this.mBinder.setPara(var1, var2);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "setPara() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public int[] getPara(int var1, int var2) {
        String str = TAG;
        Log.i(str, "getPara() var1 = " + var1 + ", var2" + var2);
        if (this.mBinder == null) {
            Log.e(TAG, "getPara() mBinder == null");
            return new int[0];
        }
        try {
            return this.mBinder.getPara(var1, var2);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "getPara() remote exception >> " + e.getMessage());
            return new int[0];
        }
    }

    public void tuner(int var1, int var2) {
        String str = TAG;
        Log.i(str, "tuner() var1 = " + var1 + ", var2" + var2);
        if (this.mBinder == null) {
            Log.i(TAG, "tuner() mBinder == null");
            return;
        }
        try {
            this.mBinder.tuner(var1, var2);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "tuner() remote exception >> " + e.getMessage());
        }
    }

    public int getCurrentRadioBand() {
        if (this.mBinder == null) {
            Log.e(TAG, "getCurrentRadioBand() mBinder == null");
            return -1;
        }
        try {
            Log.i(TAG, "getCurrentRadioBand()");
            return this.mBinder.getCurrentRadioBand();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getCurrentRadioBand() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public void seek(int band, int direct, RadioSeekCallBack callBack) {
        String str = TAG;
        Log.i(str, "seek() band = " + band + ", direct = " + direct);
        if (this.mBinder == null) {
            Log.e(TAG, "seek() mBinder == null");
        }
        this.mRadioSeekCallBack = callBack;
        try {
            this.mBinder.seek(band, direct, this.mIRadioSeekCallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "seek() remote exception >> " + e.getMessage());
        }
    }

    public void updateSeekUi(int band, int frequency) {
        String str = TAG;
        Log.i(str, "updateSeekUi() band = " + band + ", frequency = " + frequency);
        if (this.mBinder == null) {
            Log.e(TAG, "updateSeekUi() mBinder == null");
        }
        try {
            this.mBinder.updateSeekUi(band, frequency);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "updateSeekUi() remote exception >> " + e.getMessage());
        }
    }

    public void openRadioBand(int band) {
        String str = TAG;
        Log.i(str, "openRadioBand() band = " + band);
        if (this.mBinder == null) {
            Log.e(TAG, "openRadioBand() mBinder == null");
        }
        try {
            this.mBinder.openRadioBand(band);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "openRadioBand() remote exception >> " + e.getMessage());
        }
    }

    public int getCurrentFrequency() {
        if (this.mBinder == null) {
            Log.e(TAG, "getCurrentFrequency() mBinder == null");
            return -1;
        }
        try {
            Log.i(TAG, "getCurrentFrequency()");
            return this.mBinder.getCurrentFrequency();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getCurrentFrequency() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public void scan() {
        Log.i(TAG, "scan()");
        if (this.mBinder == null) {
            Log.e(TAG, "scan() mBinder == null");
        }
        try {
            this.mBinder.scan();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "scan() remote exception >> " + e.getMessage());
        }
    }

    public void stopScan() {
        Log.i(TAG, "stopScan()");
        if (this.mBinder == null) {
            Log.e(TAG, "stopScan() mBinder == null");
            return;
        }
        try {
            this.mBinder.stopScan();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "stopScan() remote exception >> " + e.getMessage());
        }
    }

    public void switchListMode(int listType) {
        String str = TAG;
        Log.i(str, "switchListMode() listType = " + listType);
        if (this.mBinder == null) {
            Log.e(TAG, "switchListMode() mBinder == null");
        }
        try {
            this.mBinder.switchListMode(listType);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "switchListMode() remote exception >> " + e.getMessage());
        }
    }

    public void tunerList(boolean isPre) {
        String str = TAG;
        Log.i(str, "tunerList() isPre = " + isPre);
        if (this.mBinder == null) {
            Log.e(TAG, "tunerList() mBinder == null");
            return;
        }
        try {
            this.mBinder.tunerList(isPre);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "tunerList() remote exception >> " + e.getMessage());
        }
    }

    public void playPosition(int position) {
        String str = TAG;
        Log.i(str, "playPosition() position = " + position);
        if (this.mBinder == null) {
            Log.e(TAG, "playPosition() mBinder == null");
            return;
        }
        try {
            this.mBinder.playPosition(position);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "playPosition() remote exception >> " + e.getMessage());
        }
    }

    public void seekRadio(int direct) {
        String str = TAG;
        Log.i(str, "seekRadio() direct = " + direct);
        if (this.mBinder == null) {
            Log.e(TAG, "seekRadio() mBinder == null");
            return;
        }
        try {
            this.mBinder.seekRadio(direct);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "seekRadio() remote exception >> " + e.getMessage());
        }
    }

    public List<String> getTunerList(int band, int listType) {
        String str = TAG;
        Log.i(str, "getTunerList() band = " + band + ", listType = " + listType);
        if (this.mBinder == null) {
            Log.e(TAG, "getTunerList() mBinder == null");
        }
        try {
            return this.mBinder.getTunerList(band, listType);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "getTunerList() remote exception >> " + e.getMessage());
            return null;
        }
    }

    public boolean isUsbPlaying() {
        Log.e(TAG, "isUsbPlaying()");
        if (this.mBinder == null) {
            Log.e(TAG, "isUsbPlaying() mBinder == null");
            return false;
        }
        try {
            return this.mBinder.isUsbPlaying();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "isUsbPlaying() remote exception >> " + e.getMessage());
            return false;
        }
    }

    public int getUsbPlayMode() {
        Log.i(TAG, "getUsbPlayMode()");
        if (this.mBinder == null) {
            Log.i(TAG, "getUsbPlayMode() mBinder == null");
            return 2;
        }
        try {
            return this.mBinder.getUsbPlayMode();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getUsbPlayMode() remote exception >> " + e.getMessage());
            return 2;
        }
    }

    public int getUsbPlayPosition() {
        Log.i(TAG, "getUsbPlayPosition()");
        if (this.mBinder == null) {
            Log.i(TAG, "mBinder == null");
            return -5;
        }
        try {
            return this.mBinder.getUsbPlayPosition();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getUsbPlayPosition() remote exception >> " + e.getMessage());
            return -5;
        }
    }

    public long getUsbPlayProgress() {
        Log.i(TAG, "getUsbPlayProgress()");
        if (this.mBinder == null) {
            Log.e(TAG, "getUsbPlayProgress() mBinder== null");
            return -5L;
        }
        try {
            return this.mBinder.getUsbPlayProgress();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getUsbPlayProgress() remote exception >> " + e.getMessage());
            return -5L;
        }
    }

    public int setUsbDesiredOperate(int desiredPosition, long desiredTime) {
        String str = TAG;
        Log.i(str, "setUsbDesiredOperate() desiredPosition = " + desiredPosition + ", desiredTime = " + desiredTime);
        if (this.mBinder == null) {
            Log.e(TAG, "setUsbDesiredOperate() mBinder== null");
            return -5;
        }
        try {
            return this.mBinder.setUsbDesiredOperate(desiredPosition, desiredTime);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "setUsbDesiredOperate() remote exception >> " + e.getMessage());
            return -4;
        }
    }

    public int getCurrentSourceType() {
        Log.i(TAG, "getCurrentSourceType()");
        if (this.mBinder == null) {
            Log.e(TAG, "getCurrentSourceType() mBinder== null");
            return -1;
        }
        try {
            return this.mBinder.getCurrentSourceType();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getCurrentSourceType() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public int setPDStatus(int var1) {
        if (this.mBinder == null) {
            Log.e(TAG, "setPDStatus() mBinder == null");
            return -1;
        }
        try {
            String str = TAG;
            Log.i(str, "setPDStatus() var1 = " + var1);
            return this.mBinder.setPDStatus(var1);
        } catch (RemoteException e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "setPDStatus() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public int getPDStauts() {
        if (this.mBinder == null) {
            Log.e(TAG, "getPDStauts() mBinder == null");
            return -1;
        }
        try {
            Log.i(TAG, "getPDStauts()");
            return this.mBinder.getPDStauts();
        } catch (RemoteException e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getPDStauts() remote exception >> " + e.getMessage());
            return -1;
        }
    }

    public void setSignal2InfoCallback(Signal2InfoCallback callBack) {
        Log.i(TAG, "setSignal2InfoCallback");
        this.mSignal2InfoCallback = callBack;
    }

    public void setPDStatusCallback(PDStatusCallback callBack) {
        Log.i(TAG, "setPDStatusCallback");
        this.mPDStatusCallback = callBack;
    }

    public void unbindService() {
        if (this.mServiceConnection != null && this.mIsServiceConnected) {
            this.mContext.unbindService(this.mServiceConnection);
            this.mIsServiceConnected = false;
        }
    }
}
