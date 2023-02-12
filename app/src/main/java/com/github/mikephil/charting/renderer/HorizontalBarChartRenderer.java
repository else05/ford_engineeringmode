package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        BarData barData;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        boolean drawBorder = dataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(dataSet.getBarShadowColor());
            BarData barData2 = this.mChart.getBarData();
            float barWidth = barData2.getBarWidth();
            float barWidthHalf = barWidth / 2.0f;
            int i = 0;
            int count = Math.min((int) Math.ceil(dataSet.getEntryCount() * phaseX), dataSet.getEntryCount());
            while (i < count) {
                BarEntry e = (BarEntry) dataSet.getEntryForIndex(i);
                float x = e.getX();
                this.mBarShadowRectBuffer.top = x - barWidthHalf;
                this.mBarShadowRectBuffer.bottom = x + barWidthHalf;
                trans.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
                    barData = barData2;
                    c.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                } else {
                    barData = barData2;
                }
                i++;
                barData2 = barData;
            }
        }
        BarBuffer buffer = this.mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setDataSet(index);
        buffer.setInverted(this.mChart.isInverted(dataSet.getAxisDependency()));
        buffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        buffer.feed(dataSet);
        trans.pointValuesToPixel(buffer.buffer);
        boolean isSingleColor = dataSet.getColors().size() == 1;
        if (isSingleColor) {
            this.mRenderPaint.setColor(dataSet.getColor());
        }
        int j = 0;
        while (true) {
            int j2 = j;
            if (j2 < buffer.size() && this.mViewPortHandler.isInBoundsTop(buffer.buffer[j2 + 3])) {
                if (this.mViewPortHandler.isInBoundsBottom(buffer.buffer[j2 + 1])) {
                    if (!isSingleColor) {
                        this.mRenderPaint.setColor(dataSet.getColor(j2 / 4));
                    }
                    c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mRenderPaint);
                    if (drawBorder) {
                        c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mBarBorderPaint);
                    }
                }
                j = j2 + 4;
            } else {
                return;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        List list;
        MPPointF iconsOffset;
        float posOffset;
        float negOffset;
        float valueOffsetPlus;
        boolean drawValueAboveBar;
        float posOffset2;
        float negOffset2;
        int index;
        float valueOffsetPlus2;
        float[] vals;
        boolean drawValueAboveBar2;
        String formattedValue;
        float posOffset3;
        BarEntry entry;
        float negOffset3;
        float y;
        float x;
        int k;
        float[] transformed;
        BarEntry entry2;
        float y2;
        String formattedValue2;
        float posOffset4;
        float negOffset4;
        float[] vals2;
        float posOffset5;
        float negOffset5;
        BarEntry entry3;
        int j;
        List list2;
        boolean isInverted;
        float halfTextHeight;
        MPPointF iconsOffset2;
        BarBuffer buffer;
        IValueFormatter formatter;
        String formattedValue3;
        float posOffset6;
        IValueFormatter formatter2;
        float negOffset6;
        float posOffset7;
        float negOffset7;
        float val;
        float posOffset8;
        if (!isDrawingValuesAllowed(this.mChart)) {
            return;
        }
        List dataSets = this.mChart.getBarData().getDataSets();
        float valueOffsetPlus3 = Utils.convertDpToPixel(5.0f);
        boolean drawValueAboveBar3 = this.mChart.isDrawValueAboveBarEnabled();
        float negOffset8 = 0.0f;
        float negOffset9 = 0.0f;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.mChart.getBarData().getDataSetCount()) {
                return;
            }
            IBarDataSet dataSet = (IBarDataSet) dataSets.get(i2);
            if (shouldDrawValues(dataSet)) {
                boolean isInverted2 = this.mChart.isInverted(dataSet.getAxisDependency());
                applyValueTextStyle(dataSet);
                float halfTextHeight2 = Utils.calcTextHeight(this.mValuePaint, "10") / 2.0f;
                IValueFormatter formatter3 = dataSet.getValueFormatter();
                BarBuffer buffer2 = this.mBarBuffers[i2];
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                if (dataSet.isStacked()) {
                    list = dataSets;
                    iconsOffset = iconsOffset3;
                    Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                    int bufferIndex = 0;
                    int bufferIndex2 = 0;
                    while (true) {
                        int index2 = bufferIndex2;
                        if (index2 >= dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            posOffset = negOffset9;
                            negOffset = negOffset8;
                            valueOffsetPlus = valueOffsetPlus3;
                            drawValueAboveBar = drawValueAboveBar3;
                            break;
                        }
                        BarEntry entry4 = (BarEntry) dataSet.getEntryForIndex(index2);
                        int color = dataSet.getValueTextColor(index2);
                        float[] vals3 = entry4.getYVals();
                        if (vals3 != null) {
                            float posOffset9 = negOffset9;
                            float negOffset10 = negOffset8;
                            BarEntry entry5 = entry4;
                            index = index2;
                            valueOffsetPlus2 = valueOffsetPlus3;
                            vals = vals3;
                            float[] transformed2 = new float[vals.length * 2];
                            float negY = -entry5.getNegativeSum();
                            int k2 = 0;
                            float posY = 0.0f;
                            float negY2 = negY;
                            int idx = 0;
                            while (k2 < transformed2.length) {
                                float value = vals[idx];
                                if (value == 0.0f && (posY == 0.0f || negY2 == 0.0f)) {
                                    y2 = value;
                                } else if (value >= 0.0f) {
                                    posY += value;
                                    y2 = posY;
                                } else {
                                    y2 = negY2;
                                    negY2 -= value;
                                }
                                transformed2[k2] = y2 * phaseY;
                                k2 += 2;
                                idx++;
                            }
                            trans.pointValuesToPixel(transformed2);
                            int k3 = 0;
                            while (true) {
                                int k4 = k3;
                                int k5 = transformed2.length;
                                if (k4 >= k5) {
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    negOffset9 = posOffset9;
                                    negOffset8 = negOffset10;
                                    break;
                                }
                                float val2 = vals[k4 / 2];
                                BarEntry entry6 = entry5;
                                String formattedValue4 = formatter3.getFormattedValue(val2, entry6, i2, this.mViewPortHandler);
                                float valueTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue4);
                                if (drawValueAboveBar3) {
                                    formattedValue = formattedValue4;
                                    posOffset3 = valueOffsetPlus2;
                                } else {
                                    formattedValue = formattedValue4;
                                    posOffset3 = -(valueTextWidth + valueOffsetPlus2);
                                }
                                if (drawValueAboveBar3) {
                                    entry = entry6;
                                    negOffset3 = -(valueTextWidth + valueOffsetPlus2);
                                } else {
                                    entry = entry6;
                                    negOffset3 = valueOffsetPlus2;
                                }
                                if (isInverted2) {
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    float posOffset10 = (-posOffset3) - valueTextWidth;
                                    float posOffset11 = -negOffset3;
                                    negOffset10 = posOffset11 - valueTextWidth;
                                    posOffset9 = posOffset10;
                                } else {
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    posOffset9 = posOffset3;
                                    negOffset10 = negOffset3;
                                }
                                boolean drawBelow = (val2 == 0.0f && negY2 == 0.0f && posY > 0.0f) || val2 < 0.0f;
                                float x2 = (drawBelow ? negOffset10 : posOffset9) + transformed2[k4];
                                float y3 = (buffer2.buffer[bufferIndex + 1] + buffer2.buffer[bufferIndex + 3]) / 2.0f;
                                if (!this.mViewPortHandler.isInBoundsTop(y3)) {
                                    negOffset9 = posOffset9;
                                    negOffset8 = negOffset10;
                                    break;
                                }
                                if (this.mViewPortHandler.isInBoundsX(x2) && this.mViewPortHandler.isInBoundsBottom(y3)) {
                                    if (dataSet.isDrawValuesEnabled()) {
                                        y = y3;
                                        x = x2;
                                        entry2 = entry;
                                        k = k4;
                                        transformed = transformed2;
                                        drawValue(c, formattedValue, x, y3 + halfTextHeight2, color);
                                    } else {
                                        y = y3;
                                        x = x2;
                                        k = k4;
                                        transformed = transformed2;
                                        entry2 = entry;
                                    }
                                    if (entry2.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                        Drawable icon = entry2.getIcon();
                                        Utils.drawImage(c, icon, (int) (x + iconsOffset.x), (int) (y + iconsOffset.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                } else {
                                    k = k4;
                                    transformed = transformed2;
                                    entry2 = entry;
                                }
                                k3 = k + 2;
                                transformed2 = transformed;
                                entry5 = entry2;
                                drawValueAboveBar3 = drawValueAboveBar2;
                            }
                        } else {
                            posOffset = negOffset9;
                            if (!this.mViewPortHandler.isInBoundsTop(buffer2.buffer[bufferIndex + 1])) {
                                negOffset = negOffset8;
                                valueOffsetPlus = valueOffsetPlus3;
                                drawValueAboveBar = drawValueAboveBar3;
                                break;
                            } else if (this.mViewPortHandler.isInBoundsX(buffer2.buffer[bufferIndex]) && this.mViewPortHandler.isInBoundsBottom(buffer2.buffer[bufferIndex + 1])) {
                                String formattedValue5 = formatter3.getFormattedValue(entry4.getY(), entry4, i2, this.mViewPortHandler);
                                float valueTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue5);
                                if (drawValueAboveBar3) {
                                    formattedValue2 = formattedValue5;
                                    posOffset4 = valueOffsetPlus3;
                                } else {
                                    formattedValue2 = formattedValue5;
                                    posOffset4 = -(valueTextWidth2 + valueOffsetPlus3);
                                }
                                if (drawValueAboveBar3) {
                                    float negOffset11 = valueTextWidth2 + valueOffsetPlus3;
                                    negOffset4 = -negOffset11;
                                } else {
                                    negOffset4 = valueOffsetPlus3;
                                }
                                if (isInverted2) {
                                    vals2 = vals3;
                                    posOffset5 = (-posOffset4) - valueTextWidth2;
                                    negOffset5 = (-negOffset4) - valueTextWidth2;
                                } else {
                                    vals2 = vals3;
                                    posOffset5 = posOffset4;
                                    negOffset5 = negOffset4;
                                }
                                if (dataSet.isDrawValuesEnabled()) {
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    vals = vals2;
                                    entry3 = entry4;
                                    index = index2;
                                    drawValue(c, formattedValue2, buffer2.buffer[bufferIndex + 2] + (entry4.getY() >= 0.0f ? posOffset5 : negOffset5), buffer2.buffer[bufferIndex + 1] + halfTextHeight2, color);
                                } else {
                                    entry3 = entry4;
                                    index = index2;
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    vals = vals2;
                                }
                                if (entry3.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                    Drawable icon2 = entry3.getIcon();
                                    float px = buffer2.buffer[bufferIndex + 2] + (entry3.getY() >= 0.0f ? posOffset5 : negOffset5);
                                    float py = buffer2.buffer[bufferIndex + 1];
                                    Utils.drawImage(c, icon2, (int) (px + iconsOffset.x), (int) (py + iconsOffset.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                }
                                drawValueAboveBar2 = drawValueAboveBar3;
                                negOffset9 = posOffset5;
                                negOffset8 = negOffset5;
                            } else {
                                bufferIndex2 = index2;
                                negOffset9 = posOffset;
                            }
                        }
                        bufferIndex = vals == null ? bufferIndex + 4 : (vals.length * 4) + bufferIndex;
                        bufferIndex2 = index + 1;
                        valueOffsetPlus3 = valueOffsetPlus2;
                        drawValueAboveBar3 = drawValueAboveBar2;
                    }
                    posOffset2 = posOffset;
                    negOffset2 = negOffset;
                } else {
                    int j2 = 0;
                    while (true) {
                        posOffset2 = negOffset9;
                        if (j2 >= buffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            negOffset2 = negOffset8;
                            list = dataSets;
                            iconsOffset = iconsOffset3;
                            break;
                        }
                        float y4 = (buffer2.buffer[j2 + 1] + buffer2.buffer[j2 + 3]) / 2.0f;
                        if (!this.mViewPortHandler.isInBoundsTop(buffer2.buffer[j2 + 1])) {
                            negOffset2 = negOffset8;
                            list = dataSets;
                            iconsOffset = iconsOffset3;
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsX(buffer2.buffer[j2]) && this.mViewPortHandler.isInBoundsBottom(buffer2.buffer[j2 + 1])) {
                            BarEntry entry7 = (BarEntry) dataSet.getEntryForIndex(j2 / 4);
                            float val3 = entry7.getY();
                            String formattedValue6 = formatter3.getFormattedValue(val3, entry7, i2, this.mViewPortHandler);
                            MPPointF iconsOffset4 = iconsOffset3;
                            float valueTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue6);
                            if (drawValueAboveBar3) {
                                formattedValue3 = formattedValue6;
                                posOffset6 = valueOffsetPlus3;
                            } else {
                                formattedValue3 = formattedValue6;
                                posOffset6 = -(valueTextWidth3 + valueOffsetPlus3);
                            }
                            if (drawValueAboveBar3) {
                                formatter2 = formatter3;
                                negOffset6 = -(valueTextWidth3 + valueOffsetPlus3);
                            } else {
                                formatter2 = formatter3;
                                negOffset6 = valueOffsetPlus3;
                            }
                            if (isInverted2) {
                                list2 = dataSets;
                                posOffset7 = (-posOffset6) - valueTextWidth3;
                                negOffset7 = (-negOffset6) - valueTextWidth3;
                            } else {
                                list2 = dataSets;
                                posOffset7 = posOffset6;
                                negOffset7 = negOffset6;
                            }
                            if (dataSet.isDrawValuesEnabled()) {
                                j = j2;
                                val = val3;
                                posOffset8 = posOffset7;
                                iconsOffset2 = iconsOffset4;
                                isInverted = isInverted2;
                                halfTextHeight = halfTextHeight2;
                                formatter = formatter2;
                                buffer = buffer2;
                                drawValue(c, formattedValue3, (val3 >= 0.0f ? posOffset7 : negOffset7) + buffer2.buffer[j2 + 2], y4 + halfTextHeight2, dataSet.getValueTextColor(j2 / 2));
                            } else {
                                j = j2;
                                val = val3;
                                posOffset8 = posOffset7;
                                isInverted = isInverted2;
                                halfTextHeight = halfTextHeight2;
                                iconsOffset2 = iconsOffset4;
                                formatter = formatter2;
                                buffer = buffer2;
                            }
                            if (entry7.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                Drawable icon3 = entry7.getIcon();
                                float px2 = buffer.buffer[j + 2] + (val >= 0.0f ? posOffset8 : negOffset7);
                                float px3 = px2 + iconsOffset2.x;
                                float py2 = y4 + iconsOffset2.y;
                                Utils.drawImage(c, icon3, (int) px3, (int) py2, icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                            }
                            negOffset8 = negOffset7;
                            negOffset9 = posOffset8;
                        } else {
                            j = j2;
                            list2 = dataSets;
                            isInverted = isInverted2;
                            halfTextHeight = halfTextHeight2;
                            negOffset9 = posOffset2;
                            iconsOffset2 = iconsOffset3;
                            buffer = buffer2;
                            formatter = formatter3;
                        }
                        j2 = j + 4;
                        iconsOffset3 = iconsOffset2;
                        buffer2 = buffer;
                        formatter3 = formatter;
                        dataSets = list2;
                        halfTextHeight2 = halfTextHeight;
                        isInverted2 = isInverted;
                    }
                    valueOffsetPlus = valueOffsetPlus3;
                    drawValueAboveBar = drawValueAboveBar3;
                }
                MPPointF.recycleInstance(iconsOffset);
                negOffset9 = posOffset2;
                negOffset8 = negOffset2;
            } else {
                list = dataSets;
                valueOffsetPlus = valueOffsetPlus3;
                drawValueAboveBar = drawValueAboveBar3;
            }
            i = i2 + 1;
            dataSets = list;
            valueOffsetPlus3 = valueOffsetPlus;
            drawValueAboveBar3 = drawValueAboveBar;
        }
    }

    protected void drawValue(Canvas c, String valueText, float x, float y, int color) {
        this.mValuePaint.setColor(color);
        c.drawText(valueText, x, y, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float top = x - barWidthHalf;
        float bottom = x + barWidthHalf;
        this.mBarRect.set(y1, top, y2, bottom);
        trans.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerY(), bar.right);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chart) {
        return ((float) chart.getData().getEntryCount()) < ((float) chart.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
