package com.github.mikephil.charting.highlight;

/* loaded from: classes2.dex */
public final class Range {
    public float from;
    public float to;

    public Range(float from, float to) {
        this.from = from;
        this.to = to;
    }

    public boolean contains(float value) {
        if (value > this.from && value <= this.to) {
            return true;
        }
        return false;
    }

    public boolean isLarger(float value) {
        return value > this.to;
    }

    public boolean isSmaller(float value) {
        return value < this.from;
    }
}
