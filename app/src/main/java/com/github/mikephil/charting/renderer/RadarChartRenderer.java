package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yfve.engineeringmode.R;

/* loaded from: classes2.dex */
public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer;
    protected Path mDrawHighlightCirclePathBuffer;
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mDrawDataSetSurfacePathBuffer = new Path();
        this.mDrawHighlightCirclePathBuffer = new Path();
        this.mChart = chart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, (int) R.styleable.AppCompatTheme_windowFixedWidthMinor));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Paint.Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int mostEntries = radarData.getMaxEntryCountSet().getEntryCount();
        for (IRadarDataSet set : radarData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set, mostEntries);
            }
        }
    }

    protected void drawDataSet(Canvas c, IRadarDataSet dataSet, int mostEntries) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        Path surface = this.mDrawDataSetSurfacePathBuffer;
        surface.reset();
        boolean hasMovedToPoint = false;
        for (int j = 0; j < dataSet.getEntryCount(); j++) {
            this.mRenderPaint.setColor(dataSet.getColor(j));
            RadarEntry e = (RadarEntry) dataSet.getEntryForIndex(j);
            Utils.getPosition(center, (e.getY() - this.mChart.getYChartMin()) * factor * phaseY, (j * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut);
            if (!Float.isNaN(pOut.x)) {
                if (!hasMovedToPoint) {
                    surface.moveTo(pOut.x, pOut.y);
                    hasMovedToPoint = true;
                } else {
                    surface.lineTo(pOut.x, pOut.y);
                }
            }
        }
        int j2 = dataSet.getEntryCount();
        if (j2 > mostEntries) {
            surface.lineTo(center.x, center.y);
        }
        surface.close();
        if (dataSet.isDrawFilledEnabled()) {
            Drawable drawable = dataSet.getFillDrawable();
            if (drawable == null) {
                drawFilledPath(c, surface, dataSet.getFillColor(), dataSet.getFillAlpha());
            } else {
                drawFilledPath(c, surface, drawable);
            }
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (!dataSet.isDrawFilledEnabled() || dataSet.getFillAlpha() < 255) {
            c.drawPath(surface, this.mRenderPaint);
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        int i;
        MPPointF center;
        MPPointF pOut;
        IRadarDataSet dataSet;
        int i2;
        MPPointF pIcon;
        MPPointF center2;
        MPPointF pOut2;
        int j;
        MPPointF center3;
        MPPointF center4;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center5 = this.mChart.getCenterOffsets();
        MPPointF pOut3 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF pIcon2 = MPPointF.getInstance(0.0f, 0.0f);
        float yoffset = Utils.convertDpToPixel(5.0f);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < ((RadarData) this.mChart.getData()).getDataSetCount()) {
                IRadarDataSet dataSet2 = ((RadarData) this.mChart.getData()).getDataSetByIndex(i4);
                if (!shouldDrawValues(dataSet2)) {
                    i = i4;
                    center = center5;
                    pOut = pOut3;
                } else {
                    applyValueTextStyle(dataSet2);
                    MPPointF iconsOffset = MPPointF.getInstance(dataSet2.getIconsOffset());
                    iconsOffset.x = Utils.convertDpToPixel(iconsOffset.x);
                    iconsOffset.y = Utils.convertDpToPixel(iconsOffset.y);
                    int j2 = 0;
                    while (true) {
                        int j3 = j2;
                        int j4 = dataSet2.getEntryCount();
                        if (j3 >= j4) {
                            break;
                        }
                        RadarEntry entry = (RadarEntry) dataSet2.getEntryForIndex(j3);
                        Utils.getPosition(center5, (entry.getY() - this.mChart.getYChartMin()) * factor * phaseY, (j3 * sliceangle * phaseX) + this.mChart.getRotationAngle(), pOut3);
                        if (dataSet2.isDrawValuesEnabled()) {
                            pOut2 = pOut3;
                            j = j3;
                            center2 = center5;
                            center3 = iconsOffset;
                            dataSet = dataSet2;
                            i2 = i4;
                            pIcon = pIcon2;
                            drawValue(c, dataSet2.getValueFormatter(), entry.getY(), entry, i4, pOut3.x, pOut3.y - yoffset, dataSet2.getValueTextColor(j3));
                        } else {
                            dataSet = dataSet2;
                            i2 = i4;
                            pIcon = pIcon2;
                            center2 = center5;
                            pOut2 = pOut3;
                            j = j3;
                            center3 = iconsOffset;
                        }
                        if (entry.getIcon() == null || !dataSet.isDrawIconsEnabled()) {
                            center4 = center2;
                            pIcon2 = pIcon;
                        } else {
                            Drawable icon = entry.getIcon();
                            center4 = center2;
                            pIcon2 = pIcon;
                            Utils.getPosition(center4, (entry.getY() * factor * phaseY) + center3.y, (j * sliceangle * phaseX) + this.mChart.getRotationAngle(), pIcon2);
                            pIcon2.y += center3.x;
                            Utils.drawImage(c, icon, (int) pIcon2.x, (int) pIcon2.y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                        }
                        j2 = j + 1;
                        iconsOffset = center3;
                        pOut3 = pOut2;
                        dataSet2 = dataSet;
                        center5 = center4;
                        i4 = i2;
                    }
                    i = i4;
                    center = center5;
                    pOut = pOut3;
                    MPPointF center6 = iconsOffset;
                    MPPointF.recycleInstance(center6);
                }
                i3 = i + 1;
                center5 = center;
                pOut3 = pOut;
            } else {
                MPPointF.recycleInstance(center5);
                MPPointF.recycleInstance(pOut3);
                MPPointF.recycleInstance(pIcon2);
                return;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
        drawWeb(c);
    }

    protected void drawWeb(Canvas c) {
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationangle = this.mChart.getRotationAngle();
        MPPointF center = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int xIncrements = this.mChart.getSkipWebLineCount() + 1;
        int maxEntryCount = ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount();
        MPPointF p = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < maxEntryCount; i += xIncrements) {
            Utils.getPosition(center, this.mChart.getYRange() * factor, (i * sliceangle) + rotationangle, p);
            c.drawLine(center.x, center.y, p.x, p.y, this.mWebPaint);
        }
        MPPointF.recycleInstance(p);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int labelCount = this.mChart.getYAxis().mEntryCount;
        MPPointF p1out = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF p2out = MPPointF.getInstance(0.0f, 0.0f);
        for (int j = 0; j < labelCount; j++) {
            int i2 = 0;
            while (i2 < ((RadarData) this.mChart.getData()).getEntryCount()) {
                float r = (this.mChart.getYAxis().mEntries[j] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(center, r, (i2 * sliceangle) + rotationangle, p1out);
                Utils.getPosition(center, r, ((i2 + 1) * sliceangle) + rotationangle, p2out);
                c.drawLine(p1out.x, p1out.y, p2out.x, p2out.y, this.mWebPaint);
                i2++;
                sliceangle = sliceangle;
                factor = factor;
                rotationangle = rotationangle;
            }
        }
        MPPointF.recycleInstance(p1out);
        MPPointF.recycleInstance(p2out);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        int i;
        int i2;
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = indices.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            Highlight high = indices[i4];
            IRadarDataSet set = radarData.getDataSetByIndex(high.getDataSetIndex());
            if (set != null && set.isHighlightEnabled()) {
                RadarEntry e = (RadarEntry) set.getEntryForIndex((int) high.getX());
                if (isInBoundsX(e, set)) {
                    float y = e.getY() - this.mChart.getYChartMin();
                    Utils.getPosition(center, y * factor * this.mAnimator.getPhaseY(), (high.getX() * sliceangle * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), pOut);
                    high.setDraw(pOut.x, pOut.y);
                    drawHighlightLines(c, pOut.x, pOut.y, set);
                    if (set.isDrawHighlightCircleEnabled() && !Float.isNaN(pOut.x) && !Float.isNaN(pOut.y)) {
                        int strokeColor = set.getHighlightCircleStrokeColor();
                        if (strokeColor == 1122867) {
                            strokeColor = set.getColor(i3);
                        }
                        if (set.getHighlightCircleStrokeAlpha() < 255) {
                            strokeColor = ColorTemplate.colorWithAlpha(strokeColor, set.getHighlightCircleStrokeAlpha());
                        }
                        i = i4;
                        i2 = 0;
                        drawHighlightCircle(c, pOut, set.getHighlightCircleInnerRadius(), set.getHighlightCircleOuterRadius(), set.getHighlightCircleFillColor(), strokeColor, set.getHighlightCircleStrokeWidth());
                        i4 = i + 1;
                        i3 = i2;
                    }
                }
            }
            i = i4;
            i2 = i3;
            i4 = i + 1;
            i3 = i2;
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    public void drawHighlightCircle(Canvas c, MPPointF point, float innerRadius, float outerRadius, int fillColor, int strokeColor, float strokeWidth) {
        c.save();
        float outerRadius2 = Utils.convertDpToPixel(outerRadius);
        float innerRadius2 = Utils.convertDpToPixel(innerRadius);
        if (fillColor != 1122867) {
            Path p = this.mDrawHighlightCirclePathBuffer;
            p.reset();
            p.addCircle(point.x, point.y, outerRadius2, Path.Direction.CW);
            if (innerRadius2 > 0.0f) {
                p.addCircle(point.x, point.y, innerRadius2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(fillColor);
            this.mHighlightCirclePaint.setStyle(Paint.Style.FILL);
            c.drawPath(p, this.mHighlightCirclePaint);
        }
        if (strokeColor != 1122867) {
            this.mHighlightCirclePaint.setColor(strokeColor);
            this.mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(strokeWidth));
            c.drawCircle(point.x, point.y, outerRadius2, this.mHighlightCirclePaint);
        }
        c.restore();
    }
}
