package com.baidu.xiaoduos.syncclient.util;

import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class SystemUtils {
    public static final String TAG = "SystemUtils";
    public static final String UNKNOWN = "unknown";

    public static String getprop(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) get.invoke(c, key, UNKNOWN);
            LogUtil.i(TAG, key + " = " + value);
            return value;
        } catch (Exception e) {
            LogUtil.e(TAG, "get property error, " + e.getMessage());
            return value;
        }
    }
}
