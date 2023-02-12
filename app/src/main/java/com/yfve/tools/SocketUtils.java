package com.yfve.tools;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes1.dex */
public class SocketUtils {
    public static SocketUtils instance = null;
    public Map<String, IFromServweCallBack> mIFromServweCallBack;
    public final String tag = getClass().getSimpleName() + "_zyx";
    private ReadWriteThread mReadWriteThread = null;

    public static SocketUtils getInstance() {
        if (instance == null) {
            instance = new SocketUtils();
        }
        return instance;
    }

    public SocketUtils() {
        this.mIFromServweCallBack = null;
        if (this.mIFromServweCallBack == null) {
            this.mIFromServweCallBack = new HashMap();
        }
    }

    public void onStartThread() {
        if (this.mReadWriteThread == null) {
            this.mReadWriteThread = new ReadWriteThread();
            this.mReadWriteThread.start();
        }
    }

    public void sendValueToServer(String strToSer) {
        try {
            this.mReadWriteThread.setValueToServer(strToSer);
        } catch (Exception e) {
        }
    }

    public void onServerResultUpdate(String strToCle) {
        if (this.mIFromServweCallBack != null) {
            for (IFromServweCallBack iFromServweCallBack : this.mIFromServweCallBack.values()) {
                iFromServweCallBack.onResultUpdate(strToCle);
            }
        }
    }

    public void registerDataListener(String strClassName, IFromServweCallBack iFromServweCallBack) {
        if (this.mIFromServweCallBack != null) {
            this.mIFromServweCallBack.put(strClassName, iFromServweCallBack);
            for (String strName : this.mIFromServweCallBack.keySet()) {
                String str = this.tag;
                LU.w(str, "registerDataListener()+++   strName==" + strName);
            }
        }
    }

    public void unregisterDataListener(String strClassName) {
        this.mIFromServweCallBack.remove(strClassName);
        for (String strName : this.mIFromServweCallBack.keySet()) {
            String str = this.tag;
            LU.e(str, "unregisterDataListener()+++   strName" + strName);
        }
    }
}
