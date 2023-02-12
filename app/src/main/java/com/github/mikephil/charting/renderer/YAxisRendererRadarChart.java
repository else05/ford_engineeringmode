package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class YAxisRendererRadarChart extends YAxisRenderer {
    private RadarChart mChart;
    private Path mRenderLimitLinesPathBuffer;

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart chart) {
        super(viewPortHandler, yAxis, null);
        this.mRenderLimitLinesPathBuffer = new Path();
        this.mChart = chart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxisValues(float min, float max) {
        boolean n;
        double last;
        int n2;
        int n3;
        float yMin = min;
        float yMax = max;
        int labelCount = this.mAxis.getLabelCount();
        double range = Math.abs(yMax - yMin);
        if (labelCount != 0 && range > Utils.DOUBLE_EPSILON) {
            if (!Double.isInfinite(range)) {
                double rawInterval = range / labelCount;
                double interval = Utils.roundToNextSignificant(rawInterval);
                if (this.mAxis.isGranularityEnabled()) {
                    interval = interval < ((double) this.mAxis.getGranularity()) ? this.mAxis.getGranularity() : interval;
                }
                double intervalMagnitude = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(interval)));
                int intervalSigDigit = (int) (interval / intervalMagnitude);
                if (intervalSigDigit > 5) {
                    interval = Math.floor(10.0d * intervalMagnitude);
                }
                boolean centeringEnabled = this.mAxis.isCenterAxisLabelsEnabled();
                if (this.mAxis.isForceLabelsEnabled()) {
                    float step = ((float) range) / (labelCount - 1);
                    this.mAxis.mEntryCount = labelCount;
                    if (this.mAxis.mEntries.length < labelCount) {
                        this.mAxis.mEntries = new float[labelCount];
                    }
                    float v = min;
                    int i = 0;
                    while (i < labelCount) {
                        this.mAxis.mEntries[i] = v;
                        v += step;
                        i++;
                        intervalSigDigit = intervalSigDigit;
                    }
                    n3 = labelCount;
                } else {
                    double first = interval == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Math.ceil(yMin / interval) * interval;
                    if (centeringEnabled) {
                        first -= interval;
                    }
                    if (interval == Utils.DOUBLE_EPSILON) {
                        n = centeringEnabled;
                        last = Utils.DOUBLE_EPSILON;
                    } else {
                        n = centeringEnabled;
                        last = Utils.nextUp(Math.floor(yMax / interval) * interval);
                    }
                    if (interval != Utils.DOUBLE_EPSILON) {
                        n2 = n;
                        for (double f = first; f <= last; f += interval) {
                            n2++;
                        }
                    } else {
                        n2 = n;
                    }
                    int n4 = n2 + 1;
                    this.mAxis.mEntryCount = n4;
                    if (this.mAxis.mEntries.length < n4) {
                        this.mAxis.mEntries = new float[n4];
                    }
                    double f2 = first;
                    int i2 = 0;
                    while (i2 < n4) {
                        if (f2 == Utils.DOUBLE_EPSILON) {
                            f2 = Utils.DOUBLE_EPSILON;
                        }
                        float yMin2 = yMin;
                        double f3 = f2;
                        this.mAxis.mEntries[i2] = (float) f3;
                        f2 = f3 + interval;
                        i2++;
                        yMin = yMin2;
                        yMax = yMax;
                        labelCount = labelCount;
                    }
                    n3 = n4;
                }
                if (interval < 1.0d) {
                    this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(interval));
                } else {
                    this.mAxis.mDecimals = 0;
                }
                if (centeringEnabled) {
                    if (this.mAxis.mCenteredEntries.length < n3) {
                        this.mAxis.mCenteredEntries = new float[n3];
                    }
                    float offset = (this.mAxis.mEntries[1] - this.mAxis.mEntries[0]) / 2.0f;
                    for (int i3 = 0; i3 < n3; i3++) {
                        this.mAxis.mCenteredEntries[i3] = this.mAxis.mEntries[i3] + offset;
                    }
                }
                this.mAxis.mAxisMinimum = this.mAxis.mEntries[0];
                this.mAxis.mAxisMaximum = this.mAxis.mEntries[n3 - 1];
                this.mAxis.mAxisRange = Math.abs(this.mAxis.mAxisMaximum - this.mAxis.mAxisMinimum);
                return;
            }
        }
        this.mAxis.mEntries = new float[0];
        this.mAxis.mCenteredEntries = new float[0];
        this.mAxis.mEntryCount = 0;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c) {
        if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled()) {
            return;
        }
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        float factor = this.mChart.getFactor();
        int from = !this.mYAxis.isDrawBottomYLabelEntryEnabled();
        int to = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        for (int j = from; j < to; j++) {
            float r = (this.mYAxis.mEntries[j] - this.mYAxis.mAxisMinimum) * factor;
            Utils.getPosition(center, r, this.mChart.getRotationAngle(), pOut);
            String label = this.mYAxis.getFormattedLabel(j);
            c.drawText(label, pOut.x + 10.0f, pOut.y, this.mAxisLabelPaint);
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null) {
            return;
        }
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < limitLines.size(); i++) {
            LimitLine l = limitLines.get(i);
            if (l.isEnabled()) {
                this.mLimitLinePaint.setColor(l.getLineColor());
                this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
                this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
                float r = (l.getLimit() - this.mChart.getYChartMin()) * factor;
                Path limitPath = this.mRenderLimitLinesPathBuffer;
                limitPath.reset();
                for (int j = 0; j < ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount(); j++) {
                    Utils.getPosition(center, r, (j * sliceangle) + this.mChart.getRotationAngle(), pOut);
                    if (j == 0) {
                        limitPath.moveTo(pOut.x, pOut.y);
                    } else {
                        limitPath.lineTo(pOut.x, pOut.y);
                    }
                }
                limitPath.close();
                c.drawPath(limitPath, this.mLimitLinePaint);
            }
        }
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(pOut);
    }
}
