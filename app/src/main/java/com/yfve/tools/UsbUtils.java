package com.yfve.tools;

import android.content.Context;
import android.os.storage.StorageManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes1.dex */
public class UsbUtils {
    public static UsbUtils instance = null;
    public final String TAG = "UsbUtils_zyx";
    private Context mContext;

    public static UsbUtils getInstance(Context context) {
        if (instance == null) {
            instance = new UsbUtils(context);
        }
        return instance;
    }

    private UsbUtils(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public String getUsbPath() {
        try {
            Object obj_DiskInfo = (StorageManager) this.mContext.getSystemService("storage");
            Class class_StorageManager = StorageManager.class;
            int i = 0;
            Method method_getVolumes = class_StorageManager.getMethod("getVolumes", new Class[0]);
            List<Object> getVolumes = (List) method_getVolumes.invoke(obj_DiskInfo, new Object[0]);
            List<String> mountPaths = new ArrayList<>();
            LU.i("UsbUtils_zyx", "---------------------------------------------------------------------------------\n");
            for (Object obj_VolumeInfo : getVolumes) {
                Class class_VolumeInfo = obj_VolumeInfo.getClass();
                LU.i("UsbUtils_zyx", "class_VolumeInfo.toString()== " + class_VolumeInfo.toString());
                Method method_getType = class_VolumeInfo.getMethod("getType", new Class[i]);
                int getType = Integer.valueOf(String.valueOf(method_getType.invoke(obj_VolumeInfo, new Object[i]))).intValue();
                Method method_getState = class_VolumeInfo.getMethod("getState", new Class[i]);
                int getState = Integer.valueOf(String.valueOf(method_getState.invoke(obj_VolumeInfo, new Object[i]))).intValue();
                Method method_getPath = class_VolumeInfo.getMethod("getPath", new Class[i]);
                File getPath = (File) method_getPath.invoke(obj_VolumeInfo, new Object[i]);
                Object obj_StorageManager = obj_DiskInfo;
                Method method_getDisk = class_VolumeInfo.getMethod("getDisk", new Class[0]);
                Object obj_DiskInfo2 = method_getDisk.invoke(obj_VolumeInfo, new Object[0]);
                if (getType == 0 && obj_DiskInfo2 != null && getState == 2) {
                    if (getPath == null) {
                        obj_DiskInfo = obj_StorageManager;
                    } else {
                        Class class_DiskInfo = obj_DiskInfo2.getClass();
                        Class cls = class_StorageManager;
                        Method method = method_getVolumes;
                        Method method_isSd = class_DiskInfo.getMethod("isSd", new Class[0]);
                        Boolean.parseBoolean(String.valueOf(method_isSd.invoke(obj_DiskInfo2, new Object[0])));
                        Method method_isUsb = class_DiskInfo.getMethod("isUsb", new Class[0]);
                        boolean isUSB = Boolean.parseBoolean(String.valueOf(method_isUsb.invoke(obj_DiskInfo2, new Object[0])));
                        List<Object> list = getVolumes;
                        Method method_id = class_DiskInfo.getMethod("getId", new Class[0]);
                        String getid = String.valueOf(method_id.invoke(obj_DiskInfo2, new Object[0]));
                        mountPaths.add(getPath.toString());
                        if (!isUSB || !getid.contains("8,0")) {
                            obj_DiskInfo = obj_StorageManager;
                            class_StorageManager = cls;
                            method_getVolumes = method;
                            getVolumes = list;
                        } else {
                            return getPath.toString();
                        }
                    }
                    i = 0;
                } else {
                    obj_DiskInfo = obj_StorageManager;
                    i = 0;
                }
            }
            return "null";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LU.w("UsbUtils_zyx", "IllegalAccessException");
            return "null";
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            LU.w("UsbUtils_zyx", "NoSuchMethodException");
            return "null";
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            LU.w("UsbUtils_zyx", "InvocationTargetException");
            return "null";
        }
    }
}
