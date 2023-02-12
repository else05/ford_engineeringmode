package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/* loaded from: classes2.dex */
public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int size, int dataSetCount, boolean containsStacks) {
        super(size, dataSetCount, containsStacks);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.mikephil.charting.buffer.BarBuffer, com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet data) {
        float size;
        float f;
        float right;
        float left;
        float y;
        float yStart;
        float right2;
        float left2;
        float size2 = data.getEntryCount() * this.phaseX;
        float barWidthHalf = this.mBarWidth / 2.0f;
        int i = 0;
        while (i < size2) {
            BarEntry e = (BarEntry) data.getEntryForIndex(i);
            if (e == null) {
                size = size2;
            } else {
                float x = e.getX();
                float y2 = e.getY();
                float[] vals = e.getYVals();
                float f2 = 0.0f;
                if (!this.mContainsStacks) {
                    size = size2;
                } else if (vals == null) {
                    size = size2;
                } else {
                    float negY = -e.getNegativeSum();
                    float yStart2 = negY;
                    float posY = 0.0f;
                    int k = 0;
                    while (k < vals.length) {
                        float value = vals[k];
                        if (value >= f2) {
                            y = posY;
                            yStart = posY + value;
                            posY = yStart;
                        } else {
                            y = yStart2;
                            float yStart3 = yStart2 + Math.abs(value);
                            float yStart4 = Math.abs(value);
                            yStart2 += yStart4;
                            yStart = yStart3;
                        }
                        float bottom = x - barWidthHalf;
                        float top = x + barWidthHalf;
                        float size3 = size2;
                        if (this.mInverted) {
                            left2 = y >= yStart ? y : yStart;
                            right2 = y <= yStart ? y : yStart;
                        } else {
                            right2 = y >= yStart ? y : yStart;
                            left2 = y <= yStart ? y : yStart;
                        }
                        addBar(left2 * this.phaseY, top, this.phaseY * right2, bottom);
                        k++;
                        size2 = size3;
                        e = e;
                        f2 = 0.0f;
                    }
                    size = size2;
                }
                float size4 = x - barWidthHalf;
                float top2 = x + barWidthHalf;
                if (this.mInverted) {
                    f = 0.0f;
                    left = y2 >= 0.0f ? y2 : 0.0f;
                    right = y2 <= 0.0f ? y2 : 0.0f;
                } else {
                    f = 0.0f;
                    right = y2 >= 0.0f ? y2 : 0.0f;
                    left = y2 <= 0.0f ? y2 : 0.0f;
                }
                if (right > f) {
                    right *= this.phaseY;
                } else {
                    left *= this.phaseY;
                }
                addBar(left, top2, right, size4);
            }
            i++;
            size2 = size;
        }
        reset();
    }
}
