package com.yfve.tools;

import android.content.Context;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes1.dex */
public class FileUtil {
    public static final String SAVE_OS_RESULR_FOR_ENTRY = "usb_ota_entry";
    private static FileUtil mFileUtil = null;
    public final String tag = getClass().getSimpleName() + "zyx";
    public final String path = "/sdcard";
    public FileOutputStream mFileOutputStream = null;
    public FileDescriptor mFileDescriptor = null;

    public static FileUtil getInstance() {
        if (mFileUtil == null) {
            synchronized (FileUtil.class) {
                mFileUtil = new FileUtil();
            }
        }
        return mFileUtil;
    }

    public void init(String fileName) {
        try {
            File file = new File(new File("/sdcard"), fileName);
            String str = this.tag;
            LU.w(str, "init()   file==" + file.getPath() + "  " + file.exists());
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            this.mFileOutputStream = new FileOutputStream(file, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(StringBuffer stringBuffer) {
        if (this.mFileOutputStream == null) {
            return;
        }
        try {
            this.mFileOutputStream.write(stringBuffer.toString().getBytes());
            if (this.mFileDescriptor == null) {
                this.mFileDescriptor = this.mFileOutputStream.getFD();
            }
            this.mFileDescriptor.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (this.mFileOutputStream == null) {
            return;
        }
        try {
            this.mFileDescriptor = null;
            this.mFileOutputStream.close();
            this.mFileOutputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get4gV() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "os_4g_v");
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void saveFirstSendFile(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mode");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write("100".getBytes());
            outStream.getFD();
            FileDescriptor fd = outStream.getFD();
            fd.sync();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFirstSendFile(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mode");
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void deleteFirstSendFile(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mode");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LU.e("zyx", "deleteFirstSendFile()   Exception==" + e.toString());
        }
    }

    public static void saveMacAddress(Context context, String strValue) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mac_value");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(strValue.getBytes());
            outStream.getFD();
            FileDescriptor fd = outStream.getFD();
            fd.sync();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMacAddress(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mac_value");
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void deleteMacAddress(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wifi_mac_value");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LU.e("zyx", "deleteFirstSendFile()   Exception==" + e.toString());
        }
    }

    public static void saveMcuF188File(Context context, String strValue) {
        try {
            File file = new File(context.getFilesDir(), "mcu_f188");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(strValue.getBytes());
            outStream.getFD();
            FileDescriptor fd = outStream.getFD();
            fd.sync();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMcuF188File(Context context) {
        try {
            File file = new File(context.getFilesDir(), "mcu_f188");
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void deleteMcuF188File(Context context) {
        try {
            File file = new File(context.getFilesDir(), "mcu_f188");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LU.e("zyx", "deleteFirstSendFile()   Exception==" + e.toString());
        }
    }

    public void saveFile(String str, String fileName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.getFD();
            FileDescriptor fd = outStream.getFD();
            fd.sync();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletefile(String fileName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getOsUpdateResultEntryFile(String fileName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getFile(String fileName) {
        try {
            File file = new File(new File("/sdcard"), fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveIccid(String strValue) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "os_iccid");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(strValue.getBytes());
            outStream.getFD();
            FileDescriptor fd = outStream.getFD();
            fd.sync();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIccid() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "os_iccid");
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int len = fis.read(b);
                if (len != -1) {
                    baos.write(b, 0, len);
                } else {
                    byte[] data = baos.toByteArray();
                    baos.close();
                    fis.close();
                    return new String(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
