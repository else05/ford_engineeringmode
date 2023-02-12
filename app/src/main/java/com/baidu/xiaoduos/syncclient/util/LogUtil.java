package com.baidu.xiaoduos.syncclient.util;

import android.util.Log;

/* loaded from: classes2.dex */
public class LogUtil {
    private static final String DEFAULT_TAG = "SYNC_CLIENT_";
    private static int LOG_LEVEL = 2;
    @Deprecated
    public static final String TAG = "tag";

    public static void setLogLevel(int logLevel) {
        LOG_LEVEL = logLevel;
    }

    public static void dumpException(Throwable t) {
        if (isLoggable(5)) {
            StringBuilder err = new StringBuilder(256);
            err.append("Got exception: ");
            err.append(t.toString());
            err.append("\n");
            System.out.println(err.toString());
            t.printStackTrace(System.out);
        }
    }

    public static void v(String aTag, String aMsg) {
        log(2, aTag, aMsg, null);
    }

    public static void v(String aTag, String aMsg, Throwable aThrowable) {
        log(2, aTag, aMsg, aThrowable);
    }

    public static void d(String aTag, String aMsg) {
        log(3, aTag, aMsg, null);
    }

    public static void d(String aTag, String aMsg, Throwable aThrowable) {
        log(3, aTag, aMsg, aThrowable);
    }

    public static void i(String aTag, String aMsg) {
        log(4, aTag, aMsg, null);
    }

    public static void i(String aTag, String aMsg, Throwable aThrowable) {
        log(4, aTag, aMsg, aThrowable);
    }

    public static void w(String aTag, String aMsg) {
        log(5, aTag, aMsg, null);
    }

    public static void w(String aTag, String aMsg, Throwable aThrowable) {
        log(5, aTag, aMsg, aThrowable);
    }

    public static void e(String aTag, String aMsg) {
        log(6, aTag, aMsg, null);
    }

    public static void e(String aTag, String aMsg, Throwable aThrowable) {
        log(6, aTag, aMsg, aThrowable);
    }

    @Deprecated
    public static void log(int aLogLevel, String aTag, String aMessage) {
        log(aLogLevel, aTag, aMessage, null);
    }

    @Deprecated
    public static void log(int aLogLevel, String aTag, String aMessage, Throwable aThrowable) {
        if (!isLoggable(aLogLevel)) {
            return;
        }
        String aMessage2 = formatLogMsg(aMessage, 3);
        switch (aLogLevel) {
            case 2:
                Log.v(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
            case 3:
                Log.d(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
            case 4:
                Log.i(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
            case 5:
                Log.w(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
            case 6:
                Log.e(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
            default:
                Log.e(DEFAULT_TAG + aTag, aMessage2, aThrowable);
                return;
        }
    }

    public static void method() {
        StackTraceElement s;
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack != null && 2 <= stack.length && (s = stack[1]) != null) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "+++++" + methodName);
        }
    }

    public static void enter() {
        StackTraceElement s;
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack != null && 2 <= stack.length && (s = stack[1]) != null) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "====>" + methodName);
        }
    }

    public static void leave() {
        StackTraceElement s;
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack != null && 2 <= stack.length && (s = stack[1]) != null) {
            String className = s.getClassName();
            String methodName = s.getMethodName();
            d(className, "<====" + methodName);
        }
    }

    public static boolean isLoggable(int aLevel) {
        return aLevel >= LOG_LEVEL;
    }

    private static String formatLogMsg(String msg, int index) {
        StackTraceElement[] stackTraces = new Throwable().getStackTrace();
        String fileName = "null";
        String methodName = "null";
        int lineNumber = 0;
        if (stackTraces.length > index) {
            StackTraceElement stackTrace = stackTraces[index];
            fileName = stackTrace.getFileName();
            methodName = stackTrace.getMethodName();
            lineNumber = stackTrace.getLineNumber();
        }
        if (fileName != null) {
            fileName = fileName.replace(".java", "");
        }
        return String.format("[%s.%s():%d]%s", fileName, methodName, Integer.valueOf(lineNumber), msg);
    }
}
