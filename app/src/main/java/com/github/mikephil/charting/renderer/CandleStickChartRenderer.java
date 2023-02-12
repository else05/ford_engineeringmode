package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private float[] mBodyBuffers;
    protected CandleDataProvider mChart;
    private float[] mCloseBuffers;
    private float[] mOpenBuffers;
    private float[] mRangeBuffers;
    private float[] mShadowBuffers;

    public CandleStickChartRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mShadowBuffers = new float[8];
        this.mBodyBuffers = new float[4];
        this.mRangeBuffers = new float[4];
        this.mOpenBuffers = new float[4];
        this.mCloseBuffers = new float[4];
        this.mChart = chart;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        CandleData candleData = this.mChart.getCandleData();
        for (T set : candleData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set);
            }
        }
    }

    protected void drawDataSet(Canvas c, ICandleDataSet dataSet) {
        int barColor;
        int shadowColor;
        int neutralColor;
        int increasingColor;
        int decreasingColor;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();
        boolean showCandleBar = dataSet.getShowCandleBar();
        this.mXBounds.set(this.mChart, dataSet);
        this.mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());
        for (int j = this.mXBounds.min; j <= this.mXBounds.range + this.mXBounds.min; j++) {
            CandleEntry e = (CandleEntry) dataSet.getEntryForIndex(j);
            if (e != null) {
                float xPos = e.getX();
                float open = e.getOpen();
                float close = e.getClose();
                float high = e.getHigh();
                float low = e.getLow();
                if (!showCandleBar) {
                    this.mRangeBuffers[0] = xPos;
                    this.mRangeBuffers[1] = high * phaseY;
                    this.mRangeBuffers[2] = xPos;
                    this.mRangeBuffers[3] = low * phaseY;
                    this.mOpenBuffers[0] = (xPos - 0.5f) + barSpace;
                    this.mOpenBuffers[1] = open * phaseY;
                    this.mOpenBuffers[2] = xPos;
                    this.mOpenBuffers[3] = open * phaseY;
                    this.mCloseBuffers[0] = (0.5f + xPos) - barSpace;
                    this.mCloseBuffers[1] = close * phaseY;
                    this.mCloseBuffers[2] = xPos;
                    this.mCloseBuffers[3] = close * phaseY;
                    trans.pointValuesToPixel(this.mRangeBuffers);
                    trans.pointValuesToPixel(this.mOpenBuffers);
                    trans.pointValuesToPixel(this.mCloseBuffers);
                    if (open > close) {
                        if (dataSet.getDecreasingColor() == 1122867) {
                            barColor = dataSet.getColor(j);
                        } else {
                            barColor = dataSet.getDecreasingColor();
                        }
                    } else if (open < close) {
                        if (dataSet.getIncreasingColor() == 1122867) {
                            barColor = dataSet.getColor(j);
                        } else {
                            barColor = dataSet.getIncreasingColor();
                        }
                    } else if (dataSet.getNeutralColor() == 1122867) {
                        barColor = dataSet.getColor(j);
                    } else {
                        barColor = dataSet.getNeutralColor();
                    }
                    this.mRenderPaint.setColor(barColor);
                    c.drawLine(this.mRangeBuffers[0], this.mRangeBuffers[1], this.mRangeBuffers[2], this.mRangeBuffers[3], this.mRenderPaint);
                    c.drawLine(this.mOpenBuffers[0], this.mOpenBuffers[1], this.mOpenBuffers[2], this.mOpenBuffers[3], this.mRenderPaint);
                    c.drawLine(this.mCloseBuffers[0], this.mCloseBuffers[1], this.mCloseBuffers[2], this.mCloseBuffers[3], this.mRenderPaint);
                } else {
                    this.mShadowBuffers[0] = xPos;
                    this.mShadowBuffers[2] = xPos;
                    this.mShadowBuffers[4] = xPos;
                    this.mShadowBuffers[6] = xPos;
                    if (open > close) {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = open * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = close * phaseY;
                    } else if (open < close) {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = close * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = open * phaseY;
                    } else {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = open * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = this.mShadowBuffers[3];
                    }
                    trans.pointValuesToPixel(this.mShadowBuffers);
                    if (dataSet.getShadowColorSameAsCandle()) {
                        if (open > close) {
                            Paint paint = this.mRenderPaint;
                            if (dataSet.getDecreasingColor() == 1122867) {
                                decreasingColor = dataSet.getColor(j);
                            } else {
                                decreasingColor = dataSet.getDecreasingColor();
                            }
                            paint.setColor(decreasingColor);
                        } else if (open < close) {
                            Paint paint2 = this.mRenderPaint;
                            if (dataSet.getIncreasingColor() == 1122867) {
                                increasingColor = dataSet.getColor(j);
                            } else {
                                increasingColor = dataSet.getIncreasingColor();
                            }
                            paint2.setColor(increasingColor);
                        } else {
                            Paint paint3 = this.mRenderPaint;
                            if (dataSet.getNeutralColor() == 1122867) {
                                neutralColor = dataSet.getColor(j);
                            } else {
                                neutralColor = dataSet.getNeutralColor();
                            }
                            paint3.setColor(neutralColor);
                        }
                    } else {
                        Paint paint4 = this.mRenderPaint;
                        if (dataSet.getShadowColor() == 1122867) {
                            shadowColor = dataSet.getColor(j);
                        } else {
                            shadowColor = dataSet.getShadowColor();
                        }
                        paint4.setColor(shadowColor);
                    }
                    this.mRenderPaint.setStyle(Paint.Style.STROKE);
                    c.drawLines(this.mShadowBuffers, this.mRenderPaint);
                    this.mBodyBuffers[0] = (xPos - 0.5f) + barSpace;
                    this.mBodyBuffers[1] = close * phaseY;
                    this.mBodyBuffers[2] = (0.5f + xPos) - barSpace;
                    this.mBodyBuffers[3] = open * phaseY;
                    trans.pointValuesToPixel(this.mBodyBuffers);
                    if (open > close) {
                        if (dataSet.getDecreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(dataSet.getColor(j));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getDecreasingColor());
                        }
                        this.mRenderPaint.setStyle(dataSet.getDecreasingPaintStyle());
                        c.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[3], this.mBodyBuffers[2], this.mBodyBuffers[1], this.mRenderPaint);
                    } else if (open < close) {
                        if (dataSet.getIncreasingColor() == 1122867) {
                            this.mRenderPaint.setColor(dataSet.getColor(j));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getIncreasingColor());
                        }
                        this.mRenderPaint.setStyle(dataSet.getIncreasingPaintStyle());
                        c.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
                    } else {
                        if (dataSet.getNeutralColor() == 1122867) {
                            this.mRenderPaint.setColor(dataSet.getColor(j));
                        } else {
                            this.mRenderPaint.setColor(dataSet.getNeutralColor());
                        }
                        c.drawLine(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
                    }
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF iconsOffset;
        int j;
        MPPointF iconsOffset2;
        float y;
        float x;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getCandleData().getDataSets();
            int i = 0;
            while (true) {
                int i2 = i;
                int i3 = dataSets.size();
                if (i2 < i3) {
                    ICandleDataSet dataSet = (ICandleDataSet) dataSets.get(i2);
                    if (shouldDrawValues(dataSet)) {
                        applyValueTextStyle(dataSet);
                        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                        this.mXBounds.set(this.mChart, dataSet);
                        float[] positions = trans.generateTransformedValuesCandle(dataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                        float yOffset = Utils.convertDpToPixel(5.0f);
                        MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                        iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                        iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                        int j2 = 0;
                        while (true) {
                            int j3 = j2;
                            int j4 = positions.length;
                            if (j3 < j4) {
                                float x2 = positions[j3];
                                float y2 = positions[j3 + 1];
                                if (this.mViewPortHandler.isInBoundsRight(x2)) {
                                    if (this.mViewPortHandler.isInBoundsLeft(x2)) {
                                        if (this.mViewPortHandler.isInBoundsY(y2)) {
                                            CandleEntry entry = (CandleEntry) dataSet.getEntryForIndex((j3 / 2) + this.mXBounds.min);
                                            if (dataSet.isDrawValuesEnabled()) {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                                drawValue(c, dataSet.getValueFormatter(), entry.getHigh(), entry, i2, x2, y2 - yOffset, dataSet.getValueTextColor(j3 / 2));
                                            } else {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                            }
                                            if (entry.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                                Drawable icon = entry.getIcon();
                                                Utils.drawImage(c, icon, (int) (x + iconsOffset2.x), (int) (y + iconsOffset2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                            }
                                        } else {
                                            j = j3;
                                            iconsOffset2 = iconsOffset3;
                                        }
                                    } else {
                                        j = j3;
                                        iconsOffset2 = iconsOffset3;
                                    }
                                    j2 = j + 2;
                                    iconsOffset3 = iconsOffset2;
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
        CandleData candleData = this.mChart.getCandleData();
        for (Highlight high : indices) {
            ICandleDataSet set = (ICandleDataSet) candleData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                CandleEntry e = (CandleEntry) set.getEntryForXValue(high.getX(), high.getY());
                if (isInBoundsX(e, set)) {
                    float lowValue = e.getLow() * this.mAnimator.getPhaseY();
                    float highValue = e.getHigh() * this.mAnimator.getPhaseY();
                    float y = (lowValue + highValue) / 2.0f;
                    MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(e.getX(), y);
                    high.setDraw((float) pix.x, (float) pix.y);
                    drawHighlightLines(c, (float) pix.x, (float) pix.y, set);
                }
            }
        }
    }
}
