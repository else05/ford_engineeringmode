package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect;
    private RectF mBarShadowRectBuffer;
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mBarRect = new RectF();
        this.mBarShadowRectBuffer = new RectF();
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Paint.Style.FILL);
        this.mBarBorderPaint = new Paint(1);
        this.mBarBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(set.getEntryCount() * 4 * (set.isStacked() ? set.getStackSize() : 1), barData.getDataSetCount(), set.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(i);
            if (set.isVisible()) {
                drawDataSet(c, set, i);
            }
        }
    }

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
                this.mBarShadowRectBuffer.left = x - barWidthHalf;
                this.mBarShadowRectBuffer.right = x + barWidthHalf;
                trans.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
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
            if (j2 < buffer.size()) {
                if (this.mViewPortHandler.isInBoundsLeft(buffer.buffer[j2 + 2])) {
                    if (this.mViewPortHandler.isInBoundsRight(buffer.buffer[j2])) {
                        if (!isSingleColor) {
                            this.mRenderPaint.setColor(dataSet.getColor(j2 / 4));
                        }
                        c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mRenderPaint);
                        if (drawBorder) {
                            c.drawRect(buffer.buffer[j2], buffer.buffer[j2 + 1], buffer.buffer[j2 + 2], buffer.buffer[j2 + 3], this.mBarBorderPaint);
                        }
                    } else {
                        return;
                    }
                }
                j = j2 + 4;
            } else {
                return;
            }
        }
    }

    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf, Transformer trans) {
        float left = x - barWidthHalf;
        float right = x + barWidthHalf;
        this.mBarRect.set(left, y1, right, y2);
        trans.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF iconsOffset;
        List list;
        float valueOffsetPlus;
        boolean drawValueAboveBar;
        int index;
        float valueOffsetPlus2;
        boolean drawValueAboveBar2;
        float[] vals;
        Transformer trans;
        int k;
        float[] transformed;
        float y;
        float y2;
        int index2;
        Transformer trans2;
        float x;
        int j;
        MPPointF iconsOffset2;
        float valueTextHeight;
        List list2;
        BarBuffer buffer;
        float x2;
        if (!isDrawingValuesAllowed(this.mChart)) {
            return;
        }
        List dataSets = this.mChart.getBarData().getDataSets();
        float valueOffsetPlus3 = Utils.convertDpToPixel(4.5f);
        boolean drawValueAboveBar3 = this.mChart.isDrawValueAboveBarEnabled();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.mChart.getBarData().getDataSetCount()) {
                return;
            }
            IBarDataSet dataSet = (IBarDataSet) dataSets.get(i2);
            if (shouldDrawValues(dataSet)) {
                applyValueTextStyle(dataSet);
                boolean isInverted = this.mChart.isInverted(dataSet.getAxisDependency());
                float valueTextHeight2 = Utils.calcTextHeight(this.mValuePaint, "8");
                float posOffset = drawValueAboveBar3 ? -valueOffsetPlus3 : valueTextHeight2 + valueOffsetPlus3;
                float negOffset = drawValueAboveBar3 ? valueTextHeight2 + valueOffsetPlus3 : -valueOffsetPlus3;
                if (isInverted) {
                    posOffset = (-posOffset) - valueTextHeight2;
                    negOffset = (-negOffset) - valueTextHeight2;
                }
                float posOffset2 = posOffset;
                float negOffset2 = negOffset;
                BarBuffer buffer2 = this.mBarBuffers[i2];
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                if (!dataSet.isStacked()) {
                    int j2 = 0;
                    while (true) {
                        int j3 = j2;
                        if (j3 >= buffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            iconsOffset = iconsOffset3;
                            list = dataSets;
                            break;
                        }
                        float x3 = (buffer2.buffer[j3] + buffer2.buffer[j3 + 2]) / 2.0f;
                        if (!this.mViewPortHandler.isInBoundsRight(x3)) {
                            iconsOffset = iconsOffset3;
                            list = dataSets;
                            break;
                        }
                        if (!this.mViewPortHandler.isInBoundsY(buffer2.buffer[j3 + 1])) {
                            j = j3;
                            iconsOffset2 = iconsOffset3;
                            valueTextHeight = valueTextHeight2;
                            list2 = dataSets;
                            buffer = buffer2;
                        } else if (this.mViewPortHandler.isInBoundsLeft(x3)) {
                            BarEntry entry = (BarEntry) dataSet.getEntryForIndex(j3 / 4);
                            float val = entry.getY();
                            if (dataSet.isDrawValuesEnabled()) {
                                x2 = x3;
                                j = j3;
                                iconsOffset2 = iconsOffset3;
                                list2 = dataSets;
                                buffer = buffer2;
                                valueTextHeight = valueTextHeight2;
                                drawValue(c, dataSet.getValueFormatter(), val, entry, i2, x2, val >= 0.0f ? buffer2.buffer[j3 + 1] + posOffset2 : buffer2.buffer[j3 + 3] + negOffset2, dataSet.getValueTextColor(j3 / 4));
                            } else {
                                x2 = x3;
                                j = j3;
                                iconsOffset2 = iconsOffset3;
                                valueTextHeight = valueTextHeight2;
                                list2 = dataSets;
                                buffer = buffer2;
                            }
                            if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                Drawable icon = entry.getIcon();
                                float px = x2;
                                float py = val >= 0.0f ? buffer.buffer[j + 1] + posOffset2 : buffer.buffer[j + 3] + negOffset2;
                                float px2 = px + iconsOffset2.x;
                                float px3 = iconsOffset2.y;
                                Utils.drawImage(c, icon, (int) px2, (int) (py + px3), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        } else {
                            j = j3;
                            iconsOffset2 = iconsOffset3;
                            valueTextHeight = valueTextHeight2;
                            list2 = dataSets;
                            buffer = buffer2;
                        }
                        j2 = j + 4;
                        buffer2 = buffer;
                        iconsOffset3 = iconsOffset2;
                        valueTextHeight2 = valueTextHeight;
                        dataSets = list2;
                    }
                } else {
                    iconsOffset = iconsOffset3;
                    list = dataSets;
                    Transformer trans3 = this.mChart.getTransformer(dataSet.getAxisDependency());
                    int bufferIndex = 0;
                    int index3 = 0;
                    while (true) {
                        int index4 = index3;
                        if (index4 >= dataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            valueOffsetPlus = valueOffsetPlus3;
                            drawValueAboveBar = drawValueAboveBar3;
                            break;
                        }
                        BarEntry entry2 = (BarEntry) dataSet.getEntryForIndex(index4);
                        float[] vals2 = entry2.getYVals();
                        float x4 = (buffer2.buffer[bufferIndex] + buffer2.buffer[bufferIndex + 2]) / 2.0f;
                        int color = dataSet.getValueTextColor(index4);
                        if (vals2 != null) {
                            float x5 = x4;
                            index = index4;
                            valueOffsetPlus2 = valueOffsetPlus3;
                            drawValueAboveBar2 = drawValueAboveBar3;
                            vals = vals2;
                            trans = trans3;
                            float[] transformed2 = new float[vals.length * 2];
                            float negY = -entry2.getNegativeSum();
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
                                transformed2[k2 + 1] = y2 * phaseY;
                                k2 += 2;
                                idx++;
                            }
                            trans.pointValuesToPixel(transformed2);
                            int k3 = 0;
                            while (true) {
                                int k4 = k3;
                                int k5 = transformed2.length;
                                if (k4 >= k5) {
                                    break;
                                }
                                float val2 = vals[k4 / 2];
                                boolean drawBelow = (val2 == 0.0f && negY2 == 0.0f && posY > 0.0f) || val2 < 0.0f;
                                float y3 = transformed2[k4 + 1] + (drawBelow ? negOffset2 : posOffset2);
                                float x6 = x5;
                                if (!this.mViewPortHandler.isInBoundsRight(x6)) {
                                    break;
                                }
                                if (!this.mViewPortHandler.isInBoundsY(y3)) {
                                    x5 = x6;
                                    k = k4;
                                    transformed = transformed2;
                                } else if (this.mViewPortHandler.isInBoundsLeft(x6)) {
                                    if (dataSet.isDrawValuesEnabled()) {
                                        x5 = x6;
                                        y = y3;
                                        k = k4;
                                        transformed = transformed2;
                                        drawValue(c, dataSet.getValueFormatter(), vals[k4 / 2], entry2, i2, x5, y, color);
                                    } else {
                                        x5 = x6;
                                        y = y3;
                                        k = k4;
                                        transformed = transformed2;
                                    }
                                    if (entry2.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = entry2.getIcon();
                                        Utils.drawImage(c, icon2, (int) (x5 + iconsOffset.x), (int) (y + iconsOffset.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                } else {
                                    x5 = x6;
                                    k = k4;
                                    transformed = transformed2;
                                }
                                k3 = k + 2;
                                transformed2 = transformed;
                            }
                        } else if (!this.mViewPortHandler.isInBoundsRight(x4)) {
                            break;
                        } else {
                            if (!this.mViewPortHandler.isInBoundsY(buffer2.buffer[bufferIndex + 1])) {
                                index2 = index4;
                                valueOffsetPlus2 = valueOffsetPlus3;
                                drawValueAboveBar2 = drawValueAboveBar3;
                                trans2 = trans3;
                            } else if (this.mViewPortHandler.isInBoundsLeft(x4)) {
                                if (dataSet.isDrawValuesEnabled()) {
                                    x = x4;
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    vals = vals2;
                                    index = index4;
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    trans = trans3;
                                    drawValue(c, dataSet.getValueFormatter(), entry2.getY(), entry2, i2, x, buffer2.buffer[bufferIndex + 1] + (entry2.getY() >= 0.0f ? posOffset2 : negOffset2), color);
                                } else {
                                    x = x4;
                                    index = index4;
                                    valueOffsetPlus2 = valueOffsetPlus3;
                                    drawValueAboveBar2 = drawValueAboveBar3;
                                    vals = vals2;
                                    trans = trans3;
                                }
                                if (entry2.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = entry2.getIcon();
                                    float px4 = x;
                                    float py2 = buffer2.buffer[bufferIndex + 1] + (entry2.getY() >= 0.0f ? posOffset2 : negOffset2);
                                    float px5 = px4 + iconsOffset.x;
                                    float px6 = iconsOffset.y;
                                    Utils.drawImage(c, icon3, (int) px5, (int) (py2 + px6), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                index2 = index4;
                                valueOffsetPlus2 = valueOffsetPlus3;
                                drawValueAboveBar2 = drawValueAboveBar3;
                                trans2 = trans3;
                            }
                            trans3 = trans2;
                            index3 = index2;
                            valueOffsetPlus3 = valueOffsetPlus2;
                            drawValueAboveBar3 = drawValueAboveBar2;
                        }
                        bufferIndex = vals == null ? bufferIndex + 4 : bufferIndex + (vals.length * 4);
                        index3 = index + 1;
                        trans3 = trans;
                        valueOffsetPlus3 = valueOffsetPlus2;
                        drawValueAboveBar3 = drawValueAboveBar2;
                    }
                }
                valueOffsetPlus = valueOffsetPlus3;
                drawValueAboveBar = drawValueAboveBar3;
                MPPointF.recycleInstance(iconsOffset);
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

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float y1;
        float f;
        float y12;
        float y2;
        BarData barData = this.mChart.getBarData();
        for (Highlight high : indices) {
            IBarDataSet set = (IBarDataSet) barData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                BarEntry e = (BarEntry) set.getEntryForXValue(high.getX(), high.getY());
                if (isInBoundsX(e, set)) {
                    Transformer trans = this.mChart.getTransformer(set.getAxisDependency());
                    this.mHighlightPaint.setColor(set.getHighLightColor());
                    this.mHighlightPaint.setAlpha(set.getHighLightAlpha());
                    boolean isStack = high.getStackIndex() >= 0 && e.isStacked();
                    if (isStack) {
                        if (this.mChart.isHighlightFullBarEnabled()) {
                            y1 = e.getPositiveSum();
                            f = -e.getNegativeSum();
                        } else {
                            Range range = e.getRanges()[high.getStackIndex()];
                            float y13 = range.from;
                            float y22 = range.to;
                            y2 = y22;
                            y12 = y13;
                            prepareBarHighlight(e.getX(), y12, y2, barData.getBarWidth() / 2.0f, trans);
                            setHighlightDrawPos(high, this.mBarRect);
                            c.drawRect(this.mBarRect, this.mHighlightPaint);
                        }
                    } else {
                        y1 = e.getY();
                        f = 0.0f;
                    }
                    y12 = y1;
                    y2 = f;
                    prepareBarHighlight(e.getX(), y12, y2, barData.getBarWidth() / 2.0f, trans);
                    setHighlightDrawPos(high, this.mBarRect);
                    c.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        high.setDraw(bar.centerX(), bar.top);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
    }
}
