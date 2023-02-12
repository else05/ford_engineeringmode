package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/* loaded from: classes2.dex */
public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth;
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected int mDataSetIndex;
    protected boolean mInverted;

    public BarBuffer(int size, int dataSetCount, boolean containsStacks) {
        super(size);
        this.mDataSetIndex = 0;
        this.mDataSetCount = 1;
        this.mContainsStacks = false;
        this.mInverted = false;
        this.mBarWidth = 1.0f;
        this.mDataSetCount = dataSetCount;
        this.mContainsStacks = containsStacks;
    }

    public void setBarWidth(float barWidth) {
        this.mBarWidth = barWidth;
    }

    public void setDataSet(int index) {
        this.mDataSetIndex = index;
    }

    public void setInverted(boolean inverted) {
        this.mInverted = inverted;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addBar(float left, float top, float right, float bottom) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = left;
        float[] fArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr2[i2] = top;
        float[] fArr3 = this.buffer;
        int i3 = this.index;
        this.index = i3 + 1;
        fArr3[i3] = right;
        float[] fArr4 = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        fArr4[i4] = bottom;
    }

    @Override // com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet data) {
        float size;
        float f;
        float top;
        float bottom;
        float y;
        float yStart;
        float top2;
        float bottom2;
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
                    float negY2 = 0.0f;
                    int k = 0;
                    while (k < vals.length) {
                        float value = vals[k];
                        if (value == f2 && (negY2 == f2 || yStart2 == f2)) {
                            y = value;
                            yStart = y;
                        } else if (value >= f2) {
                            y = negY2;
                            yStart = negY2 + value;
                            negY2 = yStart;
                        } else {
                            y = yStart2;
                            float yStart3 = yStart2 + Math.abs(value);
                            float yStart4 = Math.abs(value);
                            yStart2 += yStart4;
                            yStart = yStart3;
                        }
                        float left = x - barWidthHalf;
                        float right = x + barWidthHalf;
                        float size3 = size2;
                        if (this.mInverted) {
                            bottom2 = y >= yStart ? y : yStart;
                            top2 = y <= yStart ? y : yStart;
                        } else {
                            top2 = y >= yStart ? y : yStart;
                            bottom2 = y <= yStart ? y : yStart;
                        }
                        addBar(left, this.phaseY * top2, right, bottom2 * this.phaseY);
                        k++;
                        size2 = size3;
                        e = e;
                        f2 = 0.0f;
                    }
                    size = size2;
                }
                float size4 = x - barWidthHalf;
                float right2 = x + barWidthHalf;
                if (this.mInverted) {
                    f = 0.0f;
                    bottom = y2 >= 0.0f ? y2 : 0.0f;
                    top = y2 <= 0.0f ? y2 : 0.0f;
                } else {
                    f = 0.0f;
                    top = y2 >= 0.0f ? y2 : 0.0f;
                    bottom = y2 <= 0.0f ? y2 : 0.0f;
                }
                if (top > f) {
                    top *= this.phaseY;
                } else {
                    bottom *= this.phaseY;
                }
                addBar(size4, top, right2, bottom);
            }
            i++;
            size2 = size;
        }
        reset();
    }
}
