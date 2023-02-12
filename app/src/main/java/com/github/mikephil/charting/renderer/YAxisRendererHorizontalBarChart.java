package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes2.dex */
public class YAxisRendererHorizontalBarChart extends YAxisRenderer {
    protected Path mDrawZeroLinePathBuffer;
    protected float[] mRenderLimitLinesBuffer;
    protected Path mRenderLimitLinesPathBuffer;

    public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
        this.mDrawZeroLinePathBuffer = new Path();
        this.mRenderLimitLinesPathBuffer = new Path();
        this.mRenderLimitLinesBuffer = new float[4];
        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float yMin, float yMax, boolean inverted) {
        if (this.mViewPortHandler.contentHeight() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutX()) {
            MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
            if (!inverted) {
                yMin = (float) p1.x;
                yMax = (float) p2.x;
            } else {
                yMin = (float) p2.x;
                yMax = (float) p1.x;
            }
            MPPointD.recycleInstance(p1);
            MPPointD.recycleInstance(p2);
        }
        computeAxisValues(yMin, yMax);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas c) {
        float yPos;
        if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawLabelsEnabled()) {
            return;
        }
        float[] positions = getTransformedPositions();
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        this.mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
        float baseYOffset = Utils.convertDpToPixel(2.5f);
        float textHeight = Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
        YAxis.AxisDependency dependency = this.mYAxis.getAxisDependency();
        YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
        if (dependency == YAxis.AxisDependency.LEFT) {
            if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
                yPos = this.mViewPortHandler.contentTop() - baseYOffset;
            } else {
                yPos = this.mViewPortHandler.contentTop() - baseYOffset;
            }
        } else if (labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART) {
            yPos = this.mViewPortHandler.contentBottom() + textHeight + baseYOffset;
        } else {
            yPos = this.mViewPortHandler.contentBottom() + textHeight + baseYOffset;
        }
        drawYLabels(c, yPos, positions, this.mYAxis.getYOffset());
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLine(Canvas c) {
        if (!this.mYAxis.isEnabled() || !this.mYAxis.isDrawAxisLineEnabled()) {
            return;
        }
        this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
        this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
        if (this.mYAxis.getAxisDependency() == YAxis.AxisDependency.LEFT) {
            c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
        } else {
            c.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        int from = !this.mYAxis.isDrawBottomYLabelEntryEnabled();
        int to = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        for (int i = from; i < to; i++) {
            String text = this.mYAxis.getFormattedLabel(i);
            c.drawText(text, positions[i * 2], fixedPosition - offset, this.mAxisLabelPaint);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected float[] getTransformedPositions() {
        if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2) {
            this.mGetTransformedPositionsBuffer = new float[this.mYAxis.mEntryCount * 2];
        }
        float[] positions = this.mGetTransformedPositionsBuffer;
        for (int i = 0; i < positions.length; i += 2) {
            positions[i] = this.mYAxis.mEntries[i / 2];
        }
        this.mTrans.pointValuesToPixel(positions);
        return positions;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0f);
        return this.mGridClippingRect;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected Path linePath(Path p, int i, float[] positions) {
        p.moveTo(positions[i], this.mViewPortHandler.contentTop());
        p.lineTo(positions[i], this.mViewPortHandler.contentBottom());
        return p;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    protected void drawZeroLine(Canvas c) {
        int clipRestoreCount = c.save();
        this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mZeroLineClippingRect.inset(-this.mYAxis.getZeroLineWidth(), 0.0f);
        c.clipRect(this.mLimitLineClippingRect);
        MPPointD pos = this.mTrans.getPixelForValues(0.0f, 0.0f);
        this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
        this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
        Path zeroLinePath = this.mDrawZeroLinePathBuffer;
        zeroLinePath.reset();
        zeroLinePath.moveTo(((float) pos.x) - 1.0f, this.mViewPortHandler.contentTop());
        zeroLinePath.lineTo(((float) pos.x) - 1.0f, this.mViewPortHandler.contentBottom());
        c.drawPath(zeroLinePath, this.mZeroLinePaint);
        c.restoreToCount(clipRestoreCount);
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas c) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null || limitLines.size() <= 0) {
            return;
        }
        float[] pts = this.mRenderLimitLinesBuffer;
        float f = 0.0f;
        char c2 = 0;
        pts[0] = 0.0f;
        char c3 = 1;
        pts[1] = 0.0f;
        char c4 = 2;
        pts[2] = 0.0f;
        char c5 = 3;
        pts[3] = 0.0f;
        Path limitLinePath = this.mRenderLimitLinesPathBuffer;
        limitLinePath.reset();
        int i = 0;
        while (i < limitLines.size()) {
            LimitLine l = limitLines.get(i);
            if (l.isEnabled()) {
                int clipRestoreCount = c.save();
                this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                this.mLimitLineClippingRect.inset(-l.getLineWidth(), f);
                c.clipRect(this.mLimitLineClippingRect);
                pts[c2] = l.getLimit();
                pts[c4] = l.getLimit();
                this.mTrans.pointValuesToPixel(pts);
                pts[c3] = this.mViewPortHandler.contentTop();
                pts[c5] = this.mViewPortHandler.contentBottom();
                limitLinePath.moveTo(pts[c2], pts[c3]);
                limitLinePath.lineTo(pts[c4], pts[c5]);
                this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
                this.mLimitLinePaint.setColor(l.getLineColor());
                this.mLimitLinePaint.setPathEffect(l.getDashPathEffect());
                this.mLimitLinePaint.setStrokeWidth(l.getLineWidth());
                c.drawPath(limitLinePath, this.mLimitLinePaint);
                limitLinePath.reset();
                String label = l.getLabel();
                if (label != null && !label.equals("")) {
                    this.mLimitLinePaint.setStyle(l.getTextStyle());
                    this.mLimitLinePaint.setPathEffect(null);
                    this.mLimitLinePaint.setColor(l.getTextColor());
                    this.mLimitLinePaint.setTypeface(l.getTypeface());
                    this.mLimitLinePaint.setStrokeWidth(0.5f);
                    this.mLimitLinePaint.setTextSize(l.getTextSize());
                    float xOffset = l.getLineWidth() + l.getXOffset();
                    float yOffset = Utils.convertDpToPixel(2.0f) + l.getYOffset();
                    LimitLine.LimitLabelPosition position = l.getLabelPosition();
                    if (position == LimitLine.LimitLabelPosition.RIGHT_TOP) {
                        float labelLineHeight = Utils.calcTextHeight(this.mLimitLinePaint, label);
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c.drawText(label, pts[c2] + xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight, this.mLimitLinePaint);
                    } else if (position == LimitLine.LimitLabelPosition.RIGHT_BOTTOM) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.LEFT);
                        c.drawText(label, pts[0] + xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
                    } else if (position == LimitLine.LimitLabelPosition.LEFT_TOP) {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        float labelLineHeight2 = Utils.calcTextHeight(this.mLimitLinePaint, label);
                        c.drawText(label, pts[0] - xOffset, this.mViewPortHandler.contentTop() + yOffset + labelLineHeight2, this.mLimitLinePaint);
                    } else {
                        this.mLimitLinePaint.setTextAlign(Paint.Align.RIGHT);
                        c2 = 0;
                        c.drawText(label, pts[0] - xOffset, this.mViewPortHandler.contentBottom() - yOffset, this.mLimitLinePaint);
                    }
                    c2 = 0;
                }
                c.restoreToCount(clipRestoreCount);
            }
            i++;
            f = 0.0f;
            c3 = 1;
            c4 = 2;
            c5 = 3;
        }
    }
}
