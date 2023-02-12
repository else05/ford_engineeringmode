package com.android.car.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class CarRatedListeners<EventListenerType> {
    private int mUpdateRate;
    private final Map<EventListenerType, Integer> mListenersToRate = new HashMap(4);
    protected long mLastUpdateTime = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public CarRatedListeners(int rate) {
        this.mUpdateRate = rate;
    }

    public boolean contains(EventListenerType listener) {
        return this.mListenersToRate.containsKey(listener);
    }

    public int getRate() {
        return this.mUpdateRate;
    }

    public boolean remove(EventListenerType listener) {
        this.mListenersToRate.remove(listener);
        if (this.mListenersToRate.isEmpty()) {
            return false;
        }
        Integer updateRate = (Integer) Collections.min(this.mListenersToRate.values());
        if (updateRate.intValue() != this.mUpdateRate) {
            this.mUpdateRate = updateRate.intValue();
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.mListenersToRate.isEmpty();
    }

    public boolean addAndUpdateRate(EventListenerType listener, int updateRate) {
        Integer oldUpdateRate = this.mListenersToRate.put(listener, Integer.valueOf(updateRate));
        if (this.mUpdateRate > updateRate) {
            this.mUpdateRate = updateRate;
            return true;
        } else if (oldUpdateRate != null && oldUpdateRate.intValue() == this.mUpdateRate) {
            this.mUpdateRate = ((Integer) Collections.min(this.mListenersToRate.values())).intValue();
            return false;
        } else {
            return false;
        }
    }

    public Collection<EventListenerType> getListeners() {
        return this.mListenersToRate.keySet();
    }
}
