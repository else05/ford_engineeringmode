package com.yfve.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes1.dex */
public class UsbListenerReceiver extends BroadcastReceiver {
    public UsbListener mUsbListener;
    public final String tag = "UsbListenerReceiver66666";

    /* loaded from: classes1.dex */
    public interface UsbListener {
        void onExist();

        void onNotExist();
    }

    public UsbListenerReceiver(UsbListener usbListener) {
        this.mUsbListener = null;
        this.mUsbListener = usbListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String strReceiverAction = intent.getAction();
        String strPath = intent.getData().getPath();
        LU.w("UsbListenerReceiver66666", "onReceive()  strReceiverAction" + strReceiverAction + "   strPath==" + strPath);
        if (strReceiverAction.equals("android.intent.action.MEDIA_MOUNTED")) {
            LU.w("UsbListenerReceiver66666", "onReceive()      usb Exist");
            this.mUsbListener.onExist();
        } else if (strReceiverAction.equals("android.intent.action.MEDIA_UNMOUNTED")) {
            LU.w("UsbListenerReceiver66666", "onReceive()      usb not Exist");
            this.mUsbListener.onNotExist();
        } else if (!strReceiverAction.equals("android.intent.action.MEDIA_SCANNER_FINISHED") && !strReceiverAction.equals("android.intent.action.MEDIA_EJECT") && !strReceiverAction.equals("android.intent.action.MEDIA_REMOVED")) {
            strReceiverAction.equals("android.intent.action.MEDIA_CHECKING");
        }
    }
}
