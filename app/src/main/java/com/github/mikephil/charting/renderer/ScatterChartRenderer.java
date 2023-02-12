package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer;

    public ScatterChartRenderer(ScatterDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mPixelBuffer = new float[2];
        this.mChart = chart;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (T set : scatterData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r16v0, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawDataSet(Canvas c, IScatterDataSet dataSet) {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        IShapeRenderer renderer = dataSet.getShapeRenderer();
        if (renderer == null) {
            Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
            return;
        }
        int max = (int) Math.min(Math.ceil(dataSet.getEntryCount() * this.mAnimator.getPhaseX()), dataSet.getEntryCount());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < max) {
                ?? entryForIndex = dataSet.getEntryForIndex(i2);
                this.mPixelBuffer[0] = entryForIndex.getX();
                this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
                trans.pointValuesToPixel(this.mPixelBuffer);
                if (viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                    if (viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) && viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                        this.mRenderPaint.setColor(dataSet.getColor(i2 / 2));
                        renderer.renderShape(c, dataSet, this.mViewPortHandler, this.mPixelBuffer[0], this.mPixelBuffer[1], this.mRenderPaint);
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF iconsOffset;
        int j;
        MPPointF iconsOffset2;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getScatterData().getDataSets();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.mChart.getScatterData().getDataSetCount()) {
                    IScatterDataSet dataSet = (IScatterDataSet) dataSets.get(i2);
                    if (shouldDrawValues(dataSet)) {
                        applyValueTextStyle(dataSet);
                        this.mXBounds.set(this.mChart, dataSet);
                        float[] positions = this.mChart.getTransformer(dataSet.getAxisDependency()).generateTransformedValuesScatter(dataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                        float shapeSize = Utils.convertDpToPixel(dataSet.getScatterShapeSize());
                        MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                        iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                        iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                        int j2 = 0;
                        while (true) {
                            int j3 = j2;
                            int j4 = positions.length;
                            if (j3 < j4) {
                                if (this.mViewPortHandler.isInBoundsRight(positions[j3])) {
                                    if (!this.mViewPortHandler.isInBoundsLeft(positions[j3])) {
                                        j = j3;
                                        iconsOffset2 = iconsOffset3;
                                    } else if (this.mViewPortHandler.isInBoundsY(positions[j3 + 1])) {
                                        ?? entryForIndex = dataSet.getEntryForIndex((j3 / 2) + this.mXBounds.min);
                                        if (dataSet.isDrawValuesEnabled()) {
                                            j = j3;
                                            iconsOffset2 = iconsOffset3;
                                            drawValue(c, dataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i2, positions[j3], positions[j3 + 1] - shapeSize, dataSet.getValueTextColor((j3 / 2) + this.mXBounds.min));
                                        } else {
                                            j = j3;
                                            iconsOffset2 = iconsOffset3;
                                        }
                                        if (entryForIndex.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                            Drawable icon = entryForIndex.getIcon();
                                            Utils.drawImage(c, icon, (int) (positions[j] + iconsOffset2.x), (int) (positions[j + 1] + iconsOffset2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight high : indices) {
            IScatterDataSet set = (IScatterDataSet) scatterData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                ?? entryForXValue = set.getEntryForXValue(high.getX(), high.getY());
                if (isInBoundsX(entryForXValue, set)) {
                    MPPointD pix = this.mChart.getTransformer(set.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    high.setDraw((float) pix.x, (float) pix.y);
                    drawHighlightLines(c, (float) pix.x, (float) pix.y, set);
                }
            }
        }
    }
}
