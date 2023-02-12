package com.yfve.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yfve.engineeringmode.CarInfoService;

/* loaded from: classes1.dex */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private final String tag = "MyBroadcastReceiver_zyx";
    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                LU.e("MyBroadcastReceiver_zyx", "onReceive(EngineeringMode) ++++ ");
                FileUtil.saveFirstSendFile(context);
                Intent intentstartCarInfoService = new Intent(context, CarInfoService.class);
                context.startService(intentstartCarInfoService);
            }
        } catch (Exception e) {
        }
    }
}
