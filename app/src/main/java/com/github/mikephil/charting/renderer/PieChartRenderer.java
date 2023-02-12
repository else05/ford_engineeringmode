package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.yfve.engineeringmode.R;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds;
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer;
    protected RectF mDrawHighlightedRectF;
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath;
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer;
    private Path mPathBuffer;
    private RectF[] mRectBuffer;
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public PieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mCenterTextLastBounds = new RectF();
        this.mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
        this.mPathBuffer = new Path();
        this.mInnerRectBuffer = new RectF();
        this.mHoleCirclePath = new Path();
        this.mDrawCenterTextPathBuffer = new Path();
        this.mDrawHighlightedRectF = new RectF();
        this.mChart = chart;
        this.mHolePaint = new Paint(1);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint = new Paint(1);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle);
        this.mCenterTextPaint = new TextPaint(1);
        this.mCenterTextPaint.setColor(-16777216);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint = new Paint(1);
        this.mEntryLabelsPaint.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValueLinePaint = new Paint(1);
        this.mValueLinePaint.setStyle(Paint.Style.STROKE);
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas c) {
        int width = (int) this.mViewPortHandler.getChartWidth();
        int height = (int) this.mViewPortHandler.getChartHeight();
        if (this.mDrawBitmap == null || this.mDrawBitmap.get().getWidth() != width || this.mDrawBitmap.get().getHeight() != height) {
            if (width > 0 && height > 0) {
                this.mDrawBitmap = new WeakReference<>(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444));
                this.mBitmapCanvas = new Canvas(this.mDrawBitmap.get());
            } else {
                return;
            }
        }
        this.mDrawBitmap.get().eraseColor(0);
        PieData pieData = (PieData) this.mChart.getData();
        for (IPieDataSet set : pieData.getDataSets()) {
            if (set.isVisible() && set.getEntryCount() > 0) {
                drawDataSet(c, set);
            }
        }
    }

    protected float calculateMinimumRadiusForSpacedSlice(MPPointF center, float radius, float angle, float arcStartPointX, float arcStartPointY, float startAngle, float sweepAngle) {
        float angleMiddle = startAngle + (sweepAngle / 2.0f);
        float arcEndPointX = center.x + (((float) Math.cos((startAngle + sweepAngle) * 0.017453292f)) * radius);
        float arcEndPointY = center.y + (((float) Math.sin((startAngle + sweepAngle) * 0.017453292f)) * radius);
        float arcMidPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * radius);
        float arcMidPointY = center.y + (((float) Math.sin(0.017453292f * angleMiddle)) * radius);
        double basePointsDistance = Math.sqrt(Math.pow(arcEndPointX - arcStartPointX, 2.0d) + Math.pow(arcEndPointY - arcStartPointY, 2.0d));
        float containedTriangleHeight = (float) ((basePointsDistance / 2.0d) * Math.tan(((180.0d - angle) / 2.0d) * 0.017453292519943295d));
        float spacedRadius = radius - containedTriangleHeight;
        return (float) (spacedRadius - Math.sqrt(Math.pow(arcMidPointX - ((arcEndPointX + arcStartPointX) / 2.0f), 2.0d) + Math.pow(arcMidPointY - ((arcEndPointY + arcStartPointY) / 2.0f), 2.0d)));
    }

    protected float getSliceSpace(IPieDataSet dataSet) {
        if (!dataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return dataSet.getSliceSpace();
        }
        float spaceSizeRatio = dataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension();
        float minValueRatio = (dataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f;
        if (spaceSizeRatio > minValueRatio) {
            return 0.0f;
        }
        float sliceSpace = dataSet.getSliceSpace();
        return sliceSpace;
    }

    protected void drawDataSet(Canvas c, IPieDataSet dataSet) {
        float f;
        int j;
        int visibleAngleCount;
        float radius;
        float rotationAngle;
        float phaseX;
        RectF circleBox;
        int entryCount;
        float[] drawAngles;
        int visibleAngleCount2;
        MPPointF center;
        float f2;
        float startAngleOuter;
        float arcStartPointX;
        float sweepAngleOuter;
        float phaseX2;
        float innerRadius;
        MPPointF center2;
        float sweepAngleOuter2;
        int i;
        float sliceSpaceAngleInner;
        MPPointF center3;
        PieChartRenderer pieChartRenderer = this;
        IPieDataSet iPieDataSet = dataSet;
        float rotationAngle2 = pieChartRenderer.mChart.getRotationAngle();
        float phaseX3 = pieChartRenderer.mAnimator.getPhaseX();
        float phaseY = pieChartRenderer.mAnimator.getPhaseY();
        RectF circleBox2 = pieChartRenderer.mChart.getCircleBox();
        int entryCount2 = dataSet.getEntryCount();
        float[] drawAngles2 = pieChartRenderer.mChart.getDrawAngles();
        MPPointF center4 = pieChartRenderer.mChart.getCenterCircleBox();
        float radius2 = pieChartRenderer.mChart.getRadius();
        int i2 = 1;
        boolean drawInnerArc = pieChartRenderer.mChart.isDrawHoleEnabled() && !pieChartRenderer.mChart.isDrawSlicesUnderHoleEnabled();
        if (!drawInnerArc) {
            f = 0.0f;
        } else {
            f = (pieChartRenderer.mChart.getHoleRadius() / 100.0f) * radius2;
        }
        float userInnerRadius = f;
        int visibleAngleCount3 = 0;
        for (int visibleAngleCount4 = 0; visibleAngleCount4 < entryCount2; visibleAngleCount4++) {
            if (Math.abs(iPieDataSet.getEntryForIndex(visibleAngleCount4).getY()) > Utils.FLOAT_EPSILON) {
                visibleAngleCount3++;
            }
        }
        float sliceSpace = visibleAngleCount3 <= 1 ? 0.0f : pieChartRenderer.getSliceSpace(iPieDataSet);
        float angle = 0.0f;
        int j2 = 0;
        while (true) {
            int j3 = j2;
            if (j3 < entryCount2) {
                float sliceAngle = drawAngles2[j3];
                float innerRadius2 = userInnerRadius;
                Entry e = iPieDataSet.getEntryForIndex(j3);
                if (Math.abs(e.getY()) <= Utils.FLOAT_EPSILON || pieChartRenderer.mChart.needsHighlight(j3)) {
                    j = j3;
                    visibleAngleCount = i2;
                    radius = radius2;
                    rotationAngle = rotationAngle2;
                    phaseX = phaseX3;
                    circleBox = circleBox2;
                    entryCount = entryCount2;
                    drawAngles = drawAngles2;
                    visibleAngleCount2 = visibleAngleCount3;
                    center = center4;
                } else {
                    int accountForSliceSpacing = (sliceSpace <= 0.0f || sliceAngle > 180.0f) ? 0 : i2;
                    pieChartRenderer.mRenderPaint.setColor(iPieDataSet.getColor(j3));
                    if (visibleAngleCount3 == i2) {
                        f2 = 0.0f;
                    } else {
                        f2 = sliceSpace / (radius2 * 0.017453292f);
                    }
                    float sliceSpaceAngleOuter = f2;
                    float startAngleOuter2 = rotationAngle2 + ((angle + (sliceSpaceAngleOuter / 2.0f)) * phaseY);
                    float sweepAngleOuter3 = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                    if (sweepAngleOuter3 < 0.0f) {
                        sweepAngleOuter3 = 0.0f;
                    }
                    pieChartRenderer.mPathBuffer.reset();
                    int visibleAngleCount5 = visibleAngleCount3;
                    float arcStartPointX2 = center4.x + (((float) Math.cos(startAngleOuter2 * 0.017453292f)) * radius2);
                    entryCount = entryCount2;
                    drawAngles = drawAngles2;
                    float arcStartPointY = center4.y + (((float) Math.sin(startAngleOuter2 * 0.017453292f)) * radius2);
                    if (sweepAngleOuter3 >= 360.0f && sweepAngleOuter3 % 360.0f <= Utils.FLOAT_EPSILON) {
                        pieChartRenderer.mPathBuffer.addCircle(center4.x, center4.y, radius2, Path.Direction.CW);
                    } else {
                        pieChartRenderer.mPathBuffer.moveTo(arcStartPointX2, arcStartPointY);
                        pieChartRenderer.mPathBuffer.arcTo(circleBox2, startAngleOuter2, sweepAngleOuter3);
                    }
                    float sweepAngleOuter4 = sweepAngleOuter3;
                    pieChartRenderer.mInnerRectBuffer.set(center4.x - innerRadius2, center4.y - innerRadius2, center4.x + innerRadius2, center4.y + innerRadius2);
                    if (drawInnerArc) {
                        if (innerRadius2 > 0.0f || accountForSliceSpacing != 0) {
                            if (accountForSliceSpacing != 0) {
                                sweepAngleOuter2 = sweepAngleOuter4;
                                j = j3;
                                phaseX = phaseX3;
                                circleBox = circleBox2;
                                visibleAngleCount2 = visibleAngleCount5;
                                i = 1;
                                radius = radius2;
                                center2 = center4;
                                float minSpacedRadius = calculateMinimumRadiusForSpacedSlice(center4, radius2, sliceAngle * phaseY, arcStartPointX2, arcStartPointY, startAngleOuter2, sweepAngleOuter2);
                                if (minSpacedRadius < 0.0f) {
                                    minSpacedRadius = -minSpacedRadius;
                                }
                                innerRadius2 = Math.max(innerRadius2, minSpacedRadius);
                            } else {
                                center2 = center4;
                                phaseX = phaseX3;
                                circleBox = circleBox2;
                                j = j3;
                                visibleAngleCount2 = visibleAngleCount5;
                                sweepAngleOuter2 = sweepAngleOuter4;
                                i = 1;
                                radius = radius2;
                            }
                            if (visibleAngleCount2 == i || innerRadius2 == 0.0f) {
                                sliceSpaceAngleInner = 0.0f;
                            } else {
                                sliceSpaceAngleInner = sliceSpace / (innerRadius2 * 0.017453292f);
                            }
                            float startAngleInner = ((angle + (sliceSpaceAngleInner / 2.0f)) * phaseY) + rotationAngle2;
                            float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                            if (sweepAngleInner < 0.0f) {
                                sweepAngleInner = 0.0f;
                            }
                            float endAngleInner = startAngleInner + sweepAngleInner;
                            if (sweepAngleOuter2 >= 360.0f && sweepAngleOuter2 % 360.0f <= Utils.FLOAT_EPSILON) {
                                visibleAngleCount = i;
                                pieChartRenderer = this;
                                center3 = center2;
                                pieChartRenderer.mPathBuffer.addCircle(center3.x, center3.y, innerRadius2, Path.Direction.CCW);
                                rotationAngle = rotationAngle2;
                            } else {
                                visibleAngleCount = i;
                                center3 = center2;
                                pieChartRenderer = this;
                                rotationAngle = rotationAngle2;
                                pieChartRenderer.mPathBuffer.lineTo(center3.x + (((float) Math.cos(endAngleInner * 0.017453292f)) * innerRadius2), center3.y + (((float) Math.sin(endAngleInner * 0.017453292f)) * innerRadius2));
                                pieChartRenderer.mPathBuffer.arcTo(pieChartRenderer.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                            }
                            center = center3;
                            pieChartRenderer.mPathBuffer.close();
                            pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
                        } else {
                            startAngleOuter = startAngleOuter2;
                            arcStartPointX = arcStartPointX2;
                            rotationAngle = rotationAngle2;
                            phaseX = phaseX3;
                            circleBox = circleBox2;
                            j = j3;
                            visibleAngleCount2 = visibleAngleCount5;
                            sweepAngleOuter = sweepAngleOuter4;
                            visibleAngleCount = 1;
                            phaseX2 = innerRadius2;
                            radius = radius2;
                        }
                    } else {
                        startAngleOuter = startAngleOuter2;
                        arcStartPointX = arcStartPointX2;
                        rotationAngle = rotationAngle2;
                        phaseX = phaseX3;
                        circleBox = circleBox2;
                        j = j3;
                        visibleAngleCount2 = visibleAngleCount5;
                        sweepAngleOuter = sweepAngleOuter4;
                        visibleAngleCount = 1;
                        phaseX2 = innerRadius2;
                        radius = radius2;
                    }
                    if (sweepAngleOuter % 360.0f > Utils.FLOAT_EPSILON) {
                        if (accountForSliceSpacing != 0) {
                            float angleMiddle = startAngleOuter + (sweepAngleOuter / 2.0f);
                            innerRadius = phaseX2;
                            center = center4;
                            float sliceSpaceOffset = calculateMinimumRadiusForSpacedSlice(center4, radius, sliceAngle * phaseY, arcStartPointX, arcStartPointY, startAngleOuter, sweepAngleOuter);
                            float arcEndPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * sliceSpaceOffset);
                            float arcEndPointY = center.y + (((float) Math.sin(angleMiddle * 0.017453292f)) * sliceSpaceOffset);
                            pieChartRenderer.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                        } else {
                            innerRadius = phaseX2;
                            center = center4;
                            pieChartRenderer.mPathBuffer.lineTo(center.x, center.y);
                        }
                    } else {
                        innerRadius = phaseX2;
                        center = center4;
                    }
                    pieChartRenderer.mPathBuffer.close();
                    pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
                }
                angle += sliceAngle * phaseX;
                j2 = j + 1;
                center4 = center;
                visibleAngleCount3 = visibleAngleCount2;
                radius2 = radius;
                i2 = visibleAngleCount;
                entryCount2 = entryCount;
                drawAngles2 = drawAngles;
                circleBox2 = circleBox;
                phaseX3 = phaseX;
                rotationAngle2 = rotationAngle;
                iPieDataSet = dataSet;
            } else {
                MPPointF.recycleInstance(center4);
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x036b  */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawValues(android.graphics.Canvas r70) {
        /*
            Method dump skipped, instructions count: 1009
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.PieChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    protected void drawEntryLabel(Canvas c, String label, float x, float y) {
        c.drawText(label, x, y, this.mEntryLabelsPaint);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas c) {
        drawHole(c);
        c.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(c);
    }

    protected void drawHole(Canvas c) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
            MPPointF center = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0) {
                this.mBitmapCanvas.drawCircle(center.x, center.y, holeRadius, this.mHolePaint);
            }
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                float secondHoleRadius = (this.mChart.getTransparentCircleRadius() / 100.0f) * radius;
                this.mTransparentCirclePaint.setAlpha((int) (alpha * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(center.x, center.y, secondHoleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle(center.x, center.y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(center);
        }
    }

    protected void drawCenterText(Canvas c) {
        float radius;
        RectF boundingRect;
        RectF holeRect;
        CharSequence centerText = this.mChart.getCenterText();
        if (!this.mChart.isDrawCenterTextEnabled() || centerText == null) {
            return;
        }
        MPPointF center = this.mChart.getCenterCircleBox();
        MPPointF offset = this.mChart.getCenterTextOffset();
        float x = center.x + offset.x;
        float y = center.y + offset.y;
        if (this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled()) {
            radius = this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
        } else {
            radius = this.mChart.getRadius();
        }
        float innerRadius = radius;
        RectF holeRect2 = this.mRectBuffer[0];
        holeRect2.left = x - innerRadius;
        holeRect2.top = y - innerRadius;
        holeRect2.right = x + innerRadius;
        holeRect2.bottom = y + innerRadius;
        RectF boundingRect2 = this.mRectBuffer[1];
        boundingRect2.set(holeRect2);
        float radiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
        if (radiusPercent > Utils.DOUBLE_EPSILON) {
            boundingRect2.inset((boundingRect2.width() - (boundingRect2.width() * radiusPercent)) / 2.0f, (boundingRect2.height() - (boundingRect2.height() * radiusPercent)) / 2.0f);
        }
        if (centerText.equals(this.mCenterTextLastValue) && boundingRect2.equals(this.mCenterTextLastBounds)) {
            boundingRect = boundingRect2;
            holeRect = holeRect2;
        } else {
            this.mCenterTextLastBounds.set(boundingRect2);
            this.mCenterTextLastValue = centerText;
            float width = this.mCenterTextLastBounds.width();
            boundingRect = boundingRect2;
            holeRect = holeRect2;
            this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil(width), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
        float layoutHeight = this.mCenterTextLayout.getHeight();
        c.save();
        if (Build.VERSION.SDK_INT >= 18) {
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(holeRect, Path.Direction.CW);
            c.clipPath(path);
        }
        RectF boundingRect3 = boundingRect;
        c.translate(boundingRect3.left, boundingRect3.top + ((boundingRect3.height() - layoutHeight) / 2.0f));
        this.mCenterTextLayout.draw(c);
        c.restore();
        MPPointF.recycleInstance(center);
        MPPointF.recycleInstance(offset);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        float f;
        int i;
        RectF highlightedCircleBox;
        boolean z;
        float phaseX;
        float[] drawAngles;
        float[] absoluteAngles;
        float angle;
        int visibleAngleCount;
        float shift;
        float sweepAngleShifted;
        int visibleAngleCount2;
        int i2;
        float minSpacedRadius;
        float f2;
        Highlight[] highlightArr = indices;
        float phaseX2 = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles2 = this.mChart.getDrawAngles();
        float[] absoluteAngles2 = this.mChart.getAbsoluteAngles();
        MPPointF center = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        int i3 = 1;
        boolean drawInnerArc = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        boolean z2 = false;
        if (!drawInnerArc) {
            f = 0.0f;
        } else {
            f = (this.mChart.getHoleRadius() / 100.0f) * radius;
        }
        float userInnerRadius = f;
        RectF highlightedCircleBox2 = this.mDrawHighlightedRectF;
        highlightedCircleBox2.set(0.0f, 0.0f, 0.0f, 0.0f);
        int i4 = 0;
        while (true) {
            int i5 = i4;
            int i6 = highlightArr.length;
            if (i5 < i6) {
                int index = (int) highlightArr[i5].getX();
                if (index < drawAngles2.length) {
                    IPieDataSet set = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr[i5].getDataSetIndex());
                    if (set == null) {
                        i = i5;
                        highlightedCircleBox = highlightedCircleBox2;
                        z = z2;
                        phaseX = phaseX2;
                        drawAngles = drawAngles2;
                        absoluteAngles = absoluteAngles2;
                    } else if (set.isHighlightEnabled()) {
                        int entryCount = set.getEntryCount();
                        int visibleAngleCount3 = 0;
                        for (int visibleAngleCount4 = 0; visibleAngleCount4 < entryCount; visibleAngleCount4++) {
                            if (Math.abs(set.getEntryForIndex(visibleAngleCount4).getY()) > Utils.FLOAT_EPSILON) {
                                visibleAngleCount3++;
                            }
                        }
                        if (index == 0) {
                            angle = 0.0f;
                        } else {
                            angle = absoluteAngles2[index - 1] * phaseX2;
                        }
                        float angle2 = angle;
                        float sliceSpace = visibleAngleCount3 <= i3 ? 0.0f : set.getSliceSpace();
                        float sliceAngle = drawAngles2[index];
                        float shift2 = set.getSelectionShift();
                        float highlightedRadius = radius + shift2;
                        highlightedCircleBox2.set(this.mChart.getCircleBox());
                        highlightedCircleBox2.inset(-shift2, -shift2);
                        boolean accountForSliceSpacing = sliceSpace > 0.0f && sliceAngle <= 180.0f;
                        this.mRenderPaint.setColor(set.getColor(index));
                        float sliceSpaceAngleOuter = visibleAngleCount3 == 1 ? 0.0f : sliceSpace / (radius * 0.017453292f);
                        float sliceSpaceAngleShifted = visibleAngleCount3 == 1 ? 0.0f : sliceSpace / (highlightedRadius * 0.017453292f);
                        float startAngleOuter = rotationAngle + ((angle2 + (sliceSpaceAngleOuter / 2.0f)) * phaseY);
                        float sweepAngleOuter = (sliceAngle - sliceSpaceAngleOuter) * phaseY;
                        if (sweepAngleOuter < 0.0f) {
                            sweepAngleOuter = 0.0f;
                        }
                        float sweepAngleOuter2 = sweepAngleOuter;
                        float startAngleShifted = ((angle2 + (sliceSpaceAngleShifted / 2.0f)) * phaseY) + rotationAngle;
                        float sweepAngleShifted2 = (sliceAngle - sliceSpaceAngleShifted) * phaseY;
                        z = false;
                        if (sweepAngleShifted2 < 0.0f) {
                            sweepAngleShifted2 = 0.0f;
                        }
                        phaseX = phaseX2;
                        float sweepAngleShifted3 = sweepAngleShifted2;
                        this.mPathBuffer.reset();
                        if (sweepAngleOuter2 >= 360.0f && sweepAngleOuter2 % 360.0f <= Utils.FLOAT_EPSILON) {
                            i = i5;
                            this.mPathBuffer.addCircle(center.x, center.y, highlightedRadius, Path.Direction.CW);
                            visibleAngleCount = visibleAngleCount3;
                            shift = shift2;
                        } else {
                            i = i5;
                            visibleAngleCount = visibleAngleCount3;
                            shift = shift2;
                            this.mPathBuffer.moveTo(center.x + (((float) Math.cos(startAngleShifted * 0.017453292f)) * highlightedRadius), center.y + (((float) Math.sin(startAngleShifted * 0.017453292f)) * highlightedRadius));
                            this.mPathBuffer.arcTo(highlightedCircleBox2, startAngleShifted, sweepAngleShifted3);
                        }
                        float sliceSpaceRadius = 0.0f;
                        if (accountForSliceSpacing) {
                            highlightedCircleBox = highlightedCircleBox2;
                            visibleAngleCount2 = visibleAngleCount;
                            drawAngles = drawAngles2;
                            i2 = 1;
                            sweepAngleShifted = userInnerRadius;
                            sliceSpaceRadius = calculateMinimumRadiusForSpacedSlice(center, radius, sliceAngle * phaseY, center.x + (((float) Math.cos(startAngleOuter * 0.017453292f)) * radius), center.y + (((float) Math.sin(startAngleOuter * 0.017453292f)) * radius), startAngleOuter, sweepAngleOuter2);
                        } else {
                            highlightedCircleBox = highlightedCircleBox2;
                            drawAngles = drawAngles2;
                            sweepAngleShifted = userInnerRadius;
                            visibleAngleCount2 = visibleAngleCount;
                            i2 = 1;
                        }
                        this.mInnerRectBuffer.set(center.x - sweepAngleShifted, center.y - sweepAngleShifted, center.x + sweepAngleShifted, center.y + sweepAngleShifted);
                        if (drawInnerArc) {
                            if (sweepAngleShifted > 0.0f || accountForSliceSpacing) {
                                if (accountForSliceSpacing) {
                                    float minSpacedRadius2 = sliceSpaceRadius;
                                    if (minSpacedRadius2 < 0.0f) {
                                        minSpacedRadius2 = -minSpacedRadius2;
                                    }
                                    minSpacedRadius = Math.max(sweepAngleShifted, minSpacedRadius2);
                                } else {
                                    minSpacedRadius = sweepAngleShifted;
                                }
                                if (visibleAngleCount2 != i2 && minSpacedRadius != 0.0f) {
                                    f2 = sliceSpace / (minSpacedRadius * 0.017453292f);
                                } else {
                                    f2 = 0.0f;
                                }
                                float sliceSpaceAngleInner = f2;
                                float startAngleInner = ((angle2 + (sliceSpaceAngleInner / 2.0f)) * phaseY) + rotationAngle;
                                float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                                if (sweepAngleInner < 0.0f) {
                                    sweepAngleInner = 0.0f;
                                }
                                float endAngleInner = startAngleInner + sweepAngleInner;
                                if (sweepAngleOuter2 >= 360.0f && sweepAngleOuter2 % 360.0f <= Utils.FLOAT_EPSILON) {
                                    this.mPathBuffer.addCircle(center.x, center.y, minSpacedRadius, Path.Direction.CCW);
                                    absoluteAngles = absoluteAngles2;
                                } else {
                                    absoluteAngles = absoluteAngles2;
                                    this.mPathBuffer.lineTo(center.x + (((float) Math.cos(endAngleInner * 0.017453292f)) * minSpacedRadius), center.y + (((float) Math.sin(endAngleInner * 0.017453292f)) * minSpacedRadius));
                                    this.mPathBuffer.arcTo(this.mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                                }
                                this.mPathBuffer.close();
                                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                            } else {
                                absoluteAngles = absoluteAngles2;
                            }
                        } else {
                            absoluteAngles = absoluteAngles2;
                        }
                        if (sweepAngleOuter2 % 360.0f > Utils.FLOAT_EPSILON) {
                            if (accountForSliceSpacing) {
                                float angleMiddle = startAngleOuter + (sweepAngleOuter2 / 2.0f);
                                float arcEndPointX = center.x + (((float) Math.cos(angleMiddle * 0.017453292f)) * sliceSpaceRadius);
                                float arcEndPointY = center.y + (((float) Math.sin(angleMiddle * 0.017453292f)) * sliceSpaceRadius);
                                this.mPathBuffer.lineTo(arcEndPointX, arcEndPointY);
                            } else {
                                this.mPathBuffer.lineTo(center.x, center.y);
                            }
                        }
                        this.mPathBuffer.close();
                        this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                    }
                    i4 = i + 1;
                    z2 = z;
                    phaseX2 = phaseX;
                    highlightedCircleBox2 = highlightedCircleBox;
                    drawAngles2 = drawAngles;
                    absoluteAngles2 = absoluteAngles;
                    i3 = 1;
                    highlightArr = indices;
                }
                i = i5;
                highlightedCircleBox = highlightedCircleBox2;
                z = z2;
                phaseX = phaseX2;
                drawAngles = drawAngles2;
                absoluteAngles = absoluteAngles2;
                i4 = i + 1;
                z2 = z;
                phaseX2 = phaseX;
                highlightedCircleBox2 = highlightedCircleBox;
                drawAngles2 = drawAngles;
                absoluteAngles2 = absoluteAngles;
                i3 = 1;
                highlightArr = indices;
            } else {
                MPPointF.recycleInstance(center);
                return;
            }
        }
    }

    protected void drawRoundedSlices(Canvas c) {
        float[] drawAngles;
        float angle;
        if (!this.mChart.isDrawRoundedSlicesEnabled()) {
            return;
        }
        IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
        if (!dataSet.isVisible()) {
            return;
        }
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        MPPointF center = this.mChart.getCenterCircleBox();
        float r = this.mChart.getRadius();
        float circleRadius = (r - ((this.mChart.getHoleRadius() * r) / 100.0f)) / 2.0f;
        float[] drawAngles2 = this.mChart.getDrawAngles();
        float angle2 = this.mChart.getRotationAngle();
        int j = 0;
        while (j < dataSet.getEntryCount()) {
            float sliceAngle = drawAngles2[j];
            Entry e = dataSet.getEntryForIndex(j);
            if (Math.abs(e.getY()) > Utils.FLOAT_EPSILON) {
                float x = (float) (((r - circleRadius) * Math.cos(Math.toRadians((angle2 + sliceAngle) * phaseY))) + center.x);
                drawAngles = drawAngles2;
                angle = angle2;
                float y = (float) (((r - circleRadius) * Math.sin(Math.toRadians((angle2 + sliceAngle) * phaseY))) + center.y);
                this.mRenderPaint.setColor(dataSet.getColor(j));
                this.mBitmapCanvas.drawCircle(x, y, circleRadius, this.mRenderPaint);
            } else {
                drawAngles = drawAngles2;
                angle = angle2;
            }
            angle2 = angle + (sliceAngle * phaseX);
            j++;
            drawAngles2 = drawAngles;
        }
        MPPointF.recycleInstance(center);
    }

    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            this.mBitmapCanvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        if (this.mDrawBitmap != null) {
            this.mDrawBitmap.get().recycle();
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
