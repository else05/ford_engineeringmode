package com.android.car.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public abstract class SingleMessageHandler<EventType> implements Handler.Callback {
    private final int mHandledMessageWhat;
    private final Handler mHandler;

    protected abstract void handleEvent(EventType eventtype);

    public SingleMessageHandler(Looper looper, int handledMessage) {
        this.mHandledMessageWhat = handledMessage;
        this.mHandler = new Handler(looper, this);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        if (msg.what == this.mHandledMessageWhat) {
            List<EventType> events = (List) msg.obj;
            events.forEach(new Consumer<EventType>() { // from class: com.android.car.internal.SingleMessageHandler.1
                @Override // java.util.function.Consumer
                public void accept(EventType event) {
                    SingleMessageHandler.this.handleEvent(event);
                }
            });
            return true;
        }
        return true;
    }

    public void sendEvents(List<EventType> events) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(this.mHandledMessageWhat, events));
    }
}
