package com.android.car.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Process;

/* loaded from: classes2.dex */
public class CarPermission {
    private final Context mContext;
    private final String mName;

    public CarPermission(Context context, String name) {
        this.mContext = context;
        this.mName = name;
    }

    public boolean checkGranted() {
        return this.mName == null || Binder.getCallingUid() == Process.myUid() || this.mContext.checkCallingOrSelfPermission(this.mName) == 0;
    }

    public void assertGranted() {
        if (checkGranted()) {
            return;
        }
        throw new SecurityException("client does not have permission:" + this.mName + " pid:" + Binder.getCallingPid() + " uid:" + Binder.getCallingUid());
    }
}
