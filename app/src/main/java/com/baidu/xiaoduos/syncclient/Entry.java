package com.baidu.xiaoduos.syncclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.xiaoduos.syncclient.util.LogUtil;
import com.baidu.xiaoduos.syncclient.util.SystemUtils;
import com.baidu.xiaoduos.syncservice.IAnalyticsManager;
import com.baidu.xiaoduos.syncservice.ISyncManager;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class Entry {
    private static String TAG = "Entry";
    private static final String XIAODUOS_VERSION_PROP = "ro.baidu.xiaoduos.version";
    private ServiceConnection mAnalyticsConnection;
    private IAnalyticsManager mAnalyticsManager;
    private Callback mCallBack;
    private Context mContext;
    private IBinder.DeathRecipient mDeathRecipient;
    private String mPackagename;
    private ServiceConnection mServiceConnection;
    private ISyncManager mSyncService;
    private Timer mTimer;
    private String mVersionName;

    /* loaded from: classes2.dex */
    public interface Callback {
        void onFailure();

        void onSuccess();
    }

    /* loaded from: classes2.dex */
    static class SingletonHolder {
        private static final Entry INSTANCE = new Entry();

        SingletonHolder() {
        }
    }

    private Entry() {
        this.mContext = null;
    }

    public static Entry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getUUID() {
        if (this.mSyncService != null) {
            try {
                String uuid = this.mSyncService.getUUID();
                String str = TAG;
                LogUtil.d(str, "getUUID = " + uuid);
                return uuid;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public String getAK() {
        if (this.mSyncService != null) {
            try {
                String ak = this.mSyncService.getAK();
                String str = TAG;
                LogUtil.d(str, "getAK = " + ak);
                return ak;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public String getCN() {
        if (this.mSyncService != null) {
            try {
                String cn = this.mSyncService.getCN();
                String str = TAG;
                LogUtil.d(str, "getCN = " + cn);
                return cn;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public String getSignPrefix(boolean isSandbox) {
        if (this.mSyncService != null) {
            try {
                String signprefix = this.mSyncService.getSignPrefix(isSandbox);
                String str = TAG;
                LogUtil.d(str, "getSignPrefix = " + signprefix);
                return signprefix;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public String getSignSuffix(boolean isSandbox) {
        if (this.mSyncService != null) {
            try {
                String signsuffix = this.mSyncService.getSignSuffix(isSandbox);
                String str = TAG;
                LogUtil.d(str, "getSignSuffix = " + signsuffix);
                return signsuffix;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public String getVIN() {
        if (this.mSyncService != null) {
            try {
                String vin = this.mSyncService.getVIN();
                String str = TAG;
                LogUtil.d(str, "getVIN = " + vin);
                return vin;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        String str2 = TAG;
        LogUtil.d(str2, "mSyncService = " + this.mSyncService);
        return null;
    }

    public String getDeviceID() {
        if (this.mSyncService != null) {
            try {
                String devID = this.mSyncService.getDeviceID();
                String str = TAG;
                LogUtil.d(str, "getDeviceID = " + devID);
                return devID;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        return null;
    }

    public void updateAccountInfo(String packageName, String uid, String bduss) {
        if (this.mSyncService != null) {
            try {
                this.mSyncService.updateAccountInfo(packageName, uid, bduss);
                String str = TAG;
                LogUtil.d(str, "Package=" + packageName + " uid=" + uid + " bduss=" + bduss);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
        String str2 = TAG;
        LogUtil.d(str2, "mSyncService = " + this.mSyncService);
    }

    public String getBaiduAccountUID() {
        if (this.mSyncService != null) {
            try {
                String accountUid = this.mSyncService.getBaiduAccountUID();
                String str = TAG;
                LogUtil.d(str, "BaiduAccountID = " + accountUid);
                return accountUid;
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
                return null;
            }
        }
        String str2 = TAG;
        LogUtil.d(str2, "mSyncService = " + this.mSyncService);
        return null;
    }

    public void addPushMessageListener(String packageName, OnPushMessageListener listener) {
        if (this.mSyncService != null) {
            try {
                this.mSyncService.addPushMessageListener(packageName, listener);
                String str = TAG;
                LogUtil.d(str, "Package=" + packageName + " listener=" + listener);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void removePushMessageListener(String packageName, OnPushMessageListener listener) {
        if (this.mSyncService != null) {
            try {
                this.mSyncService.removePushMessageListener(packageName, listener);
                String str = TAG;
                LogUtil.d(str, "Package=" + packageName + " listener=" + listener);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public boolean isXiaoduOS() {
        String strOsVer = SystemUtils.getprop(XIAODUOS_VERSION_PROP, SystemUtils.UNKNOWN);
        if (strOsVer != null && !strOsVer.isEmpty() && !strOsVer.equals(SystemUtils.UNKNOWN)) {
            return true;
        }
        return false;
    }

    public void init(Context context, Callback callback) {
        this.mContext = context;
        this.mVersionName = getVersionName(this.mContext);
        this.mPackagename = this.mContext.getPackageName();
        this.mTimer = new Timer();
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.baidu.xiaoduos.syncclient.Entry.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                LogUtil.d(Entry.TAG, " binder Died!");
                Entry.this.reconnectService();
            }
        };
        connectService();
        connectAnalyticsService();
        this.mCallBack = callback;
    }

    private String getVersionName(Context context) {
        try {
            String verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return verName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean isReady() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("isReady() = ");
        sb.append(this.mSyncService != null);
        LogUtil.d(str, sb.toString());
        return this.mSyncService != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectService() {
        TimerTask task = new TimerTask() { // from class: com.baidu.xiaoduos.syncclient.Entry.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogUtil.d(Entry.TAG, "time task: connectService");
                Entry.this.connectService();
            }
        };
        this.mTimer.schedule(task, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectService() {
        Intent intent = new Intent("com.baidu.xiaoduos.SYNC_SERVICE");
        intent.setPackage("com.baidu.xiaoduos.syncservice");
        if (this.mServiceConnection == null) {
            this.mServiceConnection = new ServiceConnection() { // from class: com.baidu.xiaoduos.syncclient.Entry.3
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Entry.this.mSyncService = ISyncManager.Stub.asInterface(service);
                    String str = Entry.TAG;
                    LogUtil.d(str, "connected to sync service: mSyncService =  " + Entry.this.mSyncService);
                    if (Entry.this.mCallBack != null) {
                        try {
                            Entry.this.mCallBack.onSuccess();
                        } catch (Exception e) {
                            LogUtil.e(Entry.TAG, e.toString());
                        }
                    }
                    try {
                        if (Entry.this.mDeathRecipient != null) {
                            LogUtil.d(Entry.TAG, "linkToDeath");
                            service.linkToDeath(Entry.this.mDeathRecipient, 0);
                        }
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        if (Entry.this.mSyncService != null && Entry.this.mSyncService.asBinder() != null && Entry.this.mDeathRecipient != null) {
                            LogUtil.d(Entry.TAG, "unlinkToDeath");
                            Entry.this.mSyncService.asBinder().unlinkToDeath(Entry.this.mDeathRecipient, 0);
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    Entry.this.mSyncService = null;
                    LogUtil.e(Entry.TAG, "disconnected to sync service");
                    if (Entry.this.mCallBack != null) {
                        try {
                            Entry.this.mCallBack.onFailure();
                        } catch (Exception e2) {
                            LogUtil.e(Entry.TAG, e2.toString());
                        }
                    }
                    Entry.this.reconnectService();
                }
            };
        }
        this.mContext.bindService(intent, this.mServiceConnection, 1);
    }

    private void connectAnalyticsService() {
        Intent intent = new Intent("com.baidu.xiaoduos.ANALYTICS_SERVICE");
        intent.setPackage("com.baidu.xiaoduos.syncservice");
        if (this.mAnalyticsConnection == null) {
            this.mAnalyticsConnection = new ServiceConnection() { // from class: com.baidu.xiaoduos.syncclient.Entry.4
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Entry.this.mAnalyticsManager = IAnalyticsManager.Stub.asInterface(service);
                    String str = Entry.TAG;
                    LogUtil.d(str, "connected to mAnalyticsManager: mAnalyticsManager=== =  " + Entry.this.mSyncService);
                    if (Entry.this.mCallBack != null) {
                        try {
                            Entry.this.mCallBack.onSuccess();
                        } catch (Exception e) {
                            LogUtil.e(Entry.TAG, e.toString());
                        }
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName name) {
                    Entry.this.mAnalyticsManager = null;
                    LogUtil.e(Entry.TAG, "disconnected to sync service");
                    if (Entry.this.mCallBack != null) {
                        try {
                            Entry.this.mCallBack.onFailure();
                        } catch (Exception e) {
                            LogUtil.e(Entry.TAG, e.toString());
                        }
                    }
                }
            };
        }
        this.mContext.bindService(intent, this.mAnalyticsConnection, 1);
    }

    public void onEvent(int eventId, EventType type) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onEvent(this.mPackagename, this.mVersionName, eventId, eventType);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void onEvent(String eventId, EventType type) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onStringEvent(this.mPackagename, this.mVersionName, eventId, eventType);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void onEvent(int eventId, EventType type, String attach) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onEventAttach(this.mPackagename, this.mVersionName, eventId, eventType, attach);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void onEvent(String eventId, EventType type, String attach) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onStringEventAttach(this.mPackagename, this.mVersionName, eventId, eventType, attach);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void onPageEvent(int eventId, EventType type, String pageName) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onEventPage(this.mPackagename, this.mVersionName, eventId, eventType, pageName);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public void onPageEvent(String eventId, EventType type, String pageName) {
        int eventType = type.ordinal();
        if (this.mAnalyticsManager != null) {
            try {
                this.mAnalyticsManager.onStringEventPage(this.mPackagename, this.mVersionName, eventId, eventType, pageName);
            } catch (RemoteException e) {
                LogUtil.e(TAG, Log.getStackTraceString(e));
            }
        }
    }
}
