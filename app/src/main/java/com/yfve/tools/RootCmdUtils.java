package com.yfve.tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes1.dex */
public class RootCmdUtils {
    private static final String TAG = "RootCmd_zyx";
    private static boolean mHaveRoot = false;

    public static boolean haveRoot() {
        if (!mHaveRoot) {
            int ret = execRootCmdSilent("echo test");
            if (ret != -1) {
                LU.i(TAG, "have root!");
                mHaveRoot = true;
            } else {
                LU.i(TAG, "not root!");
            }
        } else {
            LU.i(TAG, "mHaveRoot = true, have root!");
        }
        return mHaveRoot;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0048 -> B:23:0x0058). Please submit an issue!!! */
    public static int execRootCmdSilent(String cmd) {
        int result = -1;
        DataOutputStream dos = null;
        try {
            try {
                try {
                    Process p = Runtime.getRuntime().exec("adb root");
                    dos = new DataOutputStream(p.getOutputStream());
                    LU.i(TAG, cmd);
                    dos.writeBytes(cmd + "\n");
                    dos.flush();
                    dos.writeBytes("exit\n");
                    dos.flush();
                    p.waitFor();
                    result = p.exitValue();
                    dos.close();
                } catch (Throwable th) {
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (dos != null) {
                    dos.close();
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return result;
    }

    public static String execRootCmd(String cmd) {
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            try {
                try {
                    Process p = Runtime.getRuntime().exec("adb root");
                    dos = new DataOutputStream(p.getOutputStream());
                    dis = new DataInputStream(p.getInputStream());
                    LU.i(TAG, cmd);
                    dos.writeBytes(cmd + "\n");
                    dos.flush();
                    dos.writeBytes("exit\n");
                    dos.flush();
                    while (true) {
                        String line = dis.readLine();
                        if (line == null) {
                            break;
                        }
                        LU.d("result", line);
                        result = result + line;
                    }
                    p.waitFor();
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dis.close();
                } catch (Throwable th) {
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (dis != null) {
                        try {
                            dis.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                if (dis != null) {
                    dis.close();
                }
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
        return result;
    }
}
