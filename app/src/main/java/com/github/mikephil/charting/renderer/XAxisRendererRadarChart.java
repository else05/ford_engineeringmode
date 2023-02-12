package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes2.dex */
public class XAxisRendererRadarChart extends XAxisRenderer {
    private RadarChart mChart;

    public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart chart) {
        super(viewPortHandler, xAxis, null);
        this.mChart = chart;
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c) {
        if (!this.mXAxis.isEnabled() || !this.mXAxis.isDrawLabelsEnabled()) {
            return;
        }
        float labelRotationAngleDegrees = this.mXAxis.getLabelRotationAngle();
        MPPointF drawLabelAnchor = MPPointF.getInstance(0.5f, 0.25f);
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
        float sliceangle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF center = this.mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0.0f, 0.0f);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < ((RadarData) this.mChart.getData()).getMaxEntryCountSet().getEntryCount()) {
                String label = this.mXAxis.getValueFormatter().getFormattedValue(i2, this.mXAxis);
                float angle = ((i2 * sliceangle) + this.mChart.getRotationAngle()) % 360.0f;
                Utils.getPosition(center, (this.mChart.getYRange() * factor) + (this.mXAxis.mLabelRotatedWidth / 2.0f), angle, pOut);
                drawLabel(c, label, pOut.x, pOut.y - (this.mXAxis.mLabelRotatedHeight / 2.0f), drawLabelAnchor, labelRotationAngleDegrees);
                i = i2 + 1;
            } else {
                MPPointF.recycleInstance(center);
                MPPointF.recycleInstance(pOut);
                MPPointF.recycleInstance(drawLabelAnchor);
                return;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas c) {
    }
}
