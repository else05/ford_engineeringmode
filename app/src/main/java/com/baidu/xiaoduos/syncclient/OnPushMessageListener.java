package com.baidu.xiaoduos.syncclient;

import com.baidu.xiaoduos.syncservice.IPushMessageListener;

/* loaded from: classes2.dex */
public abstract class OnPushMessageListener extends IPushMessageListener.Stub {
    @Override // com.baidu.xiaoduos.syncservice.IPushMessageListener
    public abstract void onPushMessageListener(String str, String str2);
}
