package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class BubbleChartRenderer extends BarLineScatterCandleBubbleRenderer {
    private float[] _hsvBuffer;
    protected BubbleDataProvider mChart;
    private float[] pointBuffer;
    private float[] sizeBuffer;

    public BubbleChartRenderer(BubbleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.sizeBuffer = new float[4];
        this.pointBuffer = new float[2];
        this._hsvBuffer = new float[3];
        this.mChart = chart;
        this.mRenderPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(Utils.convertDpToPixel(1.5f));
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        BubbleData bubbleData = this.mChart.getBubbleData();
        for (T set : bubbleData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set);
            }
        }
    }

    protected float getShapeSize(float entrySize, float maxSize, float reference, boolean normalizeSize) {
        float factor = normalizeSize ? maxSize == 0.0f ? 1.0f : (float) Math.sqrt(entrySize / maxSize) : entrySize;
        float shapeSize = reference * factor;
        return shapeSize;
    }

    protected void drawDataSet(Canvas c, IBubbleDataSet dataSet) {
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mXBounds.set(this.mChart, dataSet);
        char c2 = 0;
        this.sizeBuffer[0] = 0.0f;
        this.sizeBuffer[2] = 1.0f;
        trans.pointValuesToPixel(this.sizeBuffer);
        boolean normalizeSize = dataSet.isNormalizeSizeEnabled();
        float maxBubbleWidth = Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]);
        float maxBubbleHeight = Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop());
        float referenceSize = Math.min(maxBubbleHeight, maxBubbleWidth);
        int j = this.mXBounds.min;
        while (j <= this.mXBounds.range + this.mXBounds.min) {
            BubbleEntry entry = (BubbleEntry) dataSet.getEntryForIndex(j);
            this.pointBuffer[c2] = entry.getX();
            this.pointBuffer[1] = entry.getY() * phaseY;
            trans.pointValuesToPixel(this.pointBuffer);
            float shapeHalf = getShapeSize(entry.getSize(), dataSet.getMaxSize(), referenceSize, normalizeSize) / 2.0f;
            if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeHalf) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeHalf) && this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[c2] + shapeHalf)) {
                if (!this.mViewPortHandler.isInBoundsRight(this.pointBuffer[c2] - shapeHalf)) {
                    break;
                }
                int color = dataSet.getColor((int) entry.getX());
                this.mRenderPaint.setColor(color);
                c.drawCircle(this.pointBuffer[c2], this.pointBuffer[1], shapeHalf, this.mRenderPaint);
            }
            j++;
            c2 = 0;
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF iconsOffset;
        int j;
        MPPointF iconsOffset2;
        float[] positions;
        float phaseY;
        float y;
        float x;
        BubbleData bubbleData = this.mChart.getBubbleData();
        if (bubbleData != null && isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = bubbleData.getDataSets();
            float lineHeight = Utils.calcTextHeight(this.mValuePaint, "1");
            int i = 0;
            while (true) {
                int i2 = i;
                int i3 = dataSets.size();
                if (i2 < i3) {
                    IBubbleDataSet dataSet = (IBubbleDataSet) dataSets.get(i2);
                    if (shouldDrawValues(dataSet)) {
                        applyValueTextStyle(dataSet);
                        float phaseX = Math.max(0.0f, Math.min(1.0f, this.mAnimator.getPhaseX()));
                        float phaseY2 = this.mAnimator.getPhaseY();
                        this.mXBounds.set(this.mChart, dataSet);
                        float[] positions2 = this.mChart.getTransformer(dataSet.getAxisDependency()).generateTransformedValuesBubble(dataSet, phaseY2, this.mXBounds.min, this.mXBounds.max);
                        float alpha = phaseX == 1.0f ? phaseY2 : phaseX;
                        MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                        iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                        iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                        int j2 = 0;
                        while (true) {
                            int j3 = j2;
                            int j4 = positions2.length;
                            if (j3 < j4) {
                                int valueTextColor = dataSet.getValueTextColor((j3 / 2) + this.mXBounds.min);
                                int valueTextColor2 = Color.argb(Math.round(255.0f * alpha), Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor));
                                float x2 = positions2[j3];
                                float y2 = positions2[j3 + 1];
                                if (this.mViewPortHandler.isInBoundsRight(x2)) {
                                    if (this.mViewPortHandler.isInBoundsLeft(x2)) {
                                        if (this.mViewPortHandler.isInBoundsY(y2)) {
                                            BubbleEntry entry = (BubbleEntry) dataSet.getEntryForIndex((j3 / 2) + this.mXBounds.min);
                                            if (dataSet.isDrawValuesEnabled()) {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                                positions = positions2;
                                                phaseY = phaseY2;
                                                drawValue(c, dataSet.getValueFormatter(), entry.getSize(), entry, i2, x, y2 + (0.5f * lineHeight), valueTextColor2);
                                            } else {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                                positions = positions2;
                                                phaseY = phaseY2;
                                            }
                                            if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                                Drawable icon = entry.getIcon();
                                                Utils.drawImage(c, icon, (int) (x + iconsOffset2.x), (int) (y + iconsOffset2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                            }
                                        } else {
                                            j = j3;
                                            iconsOffset2 = iconsOffset3;
                                            positions = positions2;
                                            phaseY = phaseY2;
                                        }
                                    } else {
                                        j = j3;
                                        iconsOffset2 = iconsOffset3;
                                        positions = positions2;
                                        phaseY = phaseY2;
                                    }
                                    j2 = j + 2;
                                    iconsOffset3 = iconsOffset2;
                                    phaseY2 = phaseY;
                                    positions2 = positions;
                                } else {
                                    iconsOffset = iconsOffset3;
                                    break;
                                }
                            } else {
                                iconsOffset = iconsOffset3;
                                break;
                            }
                        }
                        MPPointF.recycleInstance(iconsOffset);
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        BubbleData bubbleData;
        float phaseY;
        int i;
        BubbleChartRenderer bubbleChartRenderer = this;
        Highlight[] highlightArr = indices;
        BubbleData bubbleData2 = bubbleChartRenderer.mChart.getBubbleData();
        float phaseY2 = bubbleChartRenderer.mAnimator.getPhaseY();
        int length = highlightArr.length;
        char c2 = 0;
        int i2 = 0;
        while (i2 < length) {
            Highlight high = highlightArr[i2];
            IBubbleDataSet set = (IBubbleDataSet) bubbleData2.getDataSetByIndex(high.getDataSetIndex());
            if (set != null) {
                if (set.isHighlightEnabled()) {
                    BubbleEntry entry = (BubbleEntry) set.getEntryForXValue(high.getX(), high.getY());
                    if (entry.getY() == high.getY() && bubbleChartRenderer.isInBoundsX(entry, set)) {
                        Transformer trans = bubbleChartRenderer.mChart.getTransformer(set.getAxisDependency());
                        bubbleChartRenderer.sizeBuffer[c2] = 0.0f;
                        bubbleChartRenderer.sizeBuffer[2] = 1.0f;
                        trans.pointValuesToPixel(bubbleChartRenderer.sizeBuffer);
                        boolean normalizeSize = set.isNormalizeSizeEnabled();
                        float maxBubbleWidth = Math.abs(bubbleChartRenderer.sizeBuffer[2] - bubbleChartRenderer.sizeBuffer[c2]);
                        float maxBubbleHeight = Math.abs(bubbleChartRenderer.mViewPortHandler.contentBottom() - bubbleChartRenderer.mViewPortHandler.contentTop());
                        float referenceSize = Math.min(maxBubbleHeight, maxBubbleWidth);
                        bubbleChartRenderer.pointBuffer[c2] = entry.getX();
                        bubbleChartRenderer.pointBuffer[1] = entry.getY() * phaseY2;
                        trans.pointValuesToPixel(bubbleChartRenderer.pointBuffer);
                        high.setDraw(bubbleChartRenderer.pointBuffer[c2], bubbleChartRenderer.pointBuffer[1]);
                        float shapeHalf = bubbleChartRenderer.getShapeSize(entry.getSize(), set.getMaxSize(), referenceSize, normalizeSize) / 2.0f;
                        if (bubbleChartRenderer.mViewPortHandler.isInBoundsTop(bubbleChartRenderer.pointBuffer[1] + shapeHalf)) {
                            if (bubbleChartRenderer.mViewPortHandler.isInBoundsBottom(bubbleChartRenderer.pointBuffer[1] - shapeHalf) && bubbleChartRenderer.mViewPortHandler.isInBoundsLeft(bubbleChartRenderer.pointBuffer[0] + shapeHalf)) {
                                if (!bubbleChartRenderer.mViewPortHandler.isInBoundsRight(bubbleChartRenderer.pointBuffer[0] - shapeHalf)) {
                                    return;
                                }
                                int originalColor = set.getColor((int) entry.getX());
                                bubbleData = bubbleData2;
                                phaseY = phaseY2;
                                i = length;
                                Color.RGBToHSV(Color.red(originalColor), Color.green(originalColor), Color.blue(originalColor), bubbleChartRenderer._hsvBuffer);
                                float[] fArr = bubbleChartRenderer._hsvBuffer;
                                fArr[2] = fArr[2] * 0.5f;
                                int color = Color.HSVToColor(Color.alpha(originalColor), bubbleChartRenderer._hsvBuffer);
                                bubbleChartRenderer.mHighlightPaint.setColor(color);
                                bubbleChartRenderer.mHighlightPaint.setStrokeWidth(set.getHighlightCircleWidth());
                                c.drawCircle(bubbleChartRenderer.pointBuffer[0], bubbleChartRenderer.pointBuffer[1], shapeHalf, bubbleChartRenderer.mHighlightPaint);
                                i2++;
                                bubbleData2 = bubbleData;
                                phaseY2 = phaseY;
                                length = i;
                                bubbleChartRenderer = this;
                                highlightArr = indices;
                                c2 = 0;
                            }
                        }
                    }
                }
                bubbleData = bubbleData2;
                phaseY = phaseY2;
                i = length;
                i2++;
                bubbleData2 = bubbleData;
                phaseY2 = phaseY;
                length = i;
                bubbleChartRenderer = this;
                highlightArr = indices;
                c2 = 0;
            }
            bubbleData = bubbleData2;
            phaseY = phaseY2;
            i = length;
            i2++;
            bubbleData2 = bubbleData;
            phaseY2 = phaseY;
            length = i;
            bubbleChartRenderer = this;
            highlightArr = indices;
            c2 = 0;
        }
    }
}
