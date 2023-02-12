package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes2.dex */
public abstract class AxisRenderer extends Renderer {
    protected AxisBase mAxis;
    protected Paint mAxisLabelPaint;
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer trans, AxisBase axis) {
        super(viewPortHandler);
        this.mTrans = trans;
        this.mAxis = axis;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint = new Paint(1);
            this.mGridPaint = new Paint();
            this.mGridPaint.setColor(-7829368);
            this.mGridPaint.setStrokeWidth(1.0f);
            this.mGridPaint.setStyle(Paint.Style.STROKE);
            this.mGridPaint.setAlpha(90);
            this.mAxisLinePaint = new Paint();
            this.mAxisLinePaint.setColor(-16777216);
            this.mAxisLinePaint.setStrokeWidth(1.0f);
            this.mAxisLinePaint.setStyle(Paint.Style.STROKE);
            this.mLimitLinePaint = new Paint(1);
            this.mLimitLinePaint.setStyle(Paint.Style.STROKE);
        }
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }

    public void computeAxis(float min, float max, boolean inverted) {
        if (this.mViewPortHandler != null && this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutY()) {
            MPPointD p1 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD p2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            if (!inverted) {
                min = (float) p2.y;
                max = (float) p1.y;
            } else {
                min = (float) p1.y;
                max = (float) p2.y;
            }
            MPPointD.recycleInstance(p1);
            MPPointD.recycleInstance(p2);
        }
        computeAxisValues(min, max);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void computeAxisValues(float min, float max) {
        boolean n;
        double nextUp;
        int n2;
        float yMax = max;
        int labelCount = this.mAxis.getLabelCount();
        double range = Math.abs(yMax - min);
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
                boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
                if (this.mAxis.isForceLabelsEnabled()) {
                    interval = ((float) range) / (labelCount - 1);
                    this.mAxis.mEntryCount = labelCount;
                    if (this.mAxis.mEntries.length < labelCount) {
                        this.mAxis.mEntries = new float[labelCount];
                    }
                    float v = min;
                    int i = 0;
                    while (i < labelCount) {
                        this.mAxis.mEntries[i] = v;
                        v = (float) (v + interval);
                        i++;
                        range = range;
                    }
                    n2 = labelCount;
                } else {
                    double first = interval == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Math.ceil(min / interval) * interval;
                    if (this.mAxis.isCenterAxisLabelsEnabled()) {
                        first -= interval;
                    }
                    if (interval == Utils.DOUBLE_EPSILON) {
                        n = isCenterAxisLabelsEnabled;
                        nextUp = Utils.DOUBLE_EPSILON;
                    } else {
                        n = isCenterAxisLabelsEnabled;
                        nextUp = Utils.nextUp(Math.floor(yMax / interval) * interval);
                    }
                    double last = nextUp;
                    if (interval != Utils.DOUBLE_EPSILON) {
                        for (double f = first; f <= last; f += interval) {
                            n++;
                        }
                    }
                    int n3 = n;
                    this.mAxis.mEntryCount = n3;
                    if (this.mAxis.mEntries.length < n3) {
                        this.mAxis.mEntries = new float[n3];
                    }
                    double f2 = first;
                    int i2 = 0;
                    while (i2 < n3) {
                        if (f2 == Utils.DOUBLE_EPSILON) {
                            f2 = Utils.DOUBLE_EPSILON;
                        }
                        float yMax2 = yMax;
                        double f3 = f2;
                        this.mAxis.mEntries[i2] = (float) f3;
                        f2 = f3 + interval;
                        i2++;
                        yMax = yMax2;
                        labelCount = labelCount;
                        first = first;
                    }
                    n2 = n3;
                }
                if (interval < 1.0d) {
                    this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(interval));
                } else {
                    this.mAxis.mDecimals = 0;
                }
                if (this.mAxis.isCenterAxisLabelsEnabled()) {
                    if (this.mAxis.mCenteredEntries.length < n2) {
                        this.mAxis.mCenteredEntries = new float[n2];
                    }
                    float offset = ((float) interval) / 2.0f;
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 < n2) {
                            this.mAxis.mCenteredEntries[i4] = this.mAxis.mEntries[i4] + offset;
                            i3 = i4 + 1;
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
        this.mAxis.mEntries = new float[0];
        this.mAxis.mCenteredEntries = new float[0];
        this.mAxis.mEntryCount = 0;
    }
}
