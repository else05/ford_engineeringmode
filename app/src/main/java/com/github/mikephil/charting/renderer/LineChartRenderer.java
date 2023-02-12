package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class LineChartRenderer extends LineRadarRenderer {
    protected Path cubicFillPath;
    protected Path cubicPath;
    protected Canvas mBitmapCanvas;
    protected Bitmap.Config mBitmapConfig;
    protected LineDataProvider mChart;
    protected Paint mCirclePaintInner;
    private float[] mCirclesBuffer;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mGenerateFilledPathBuffer;
    private HashMap<IDataSet, DataSetImageCache> mImageCaches;
    private float[] mLineBuffer;

    public LineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mBitmapConfig = Bitmap.Config.ARGB_8888;
        this.cubicPath = new Path();
        this.cubicFillPath = new Path();
        this.mLineBuffer = new float[4];
        this.mGenerateFilledPathBuffer = new Path();
        this.mImageCaches = new HashMap<>();
        this.mCirclesBuffer = new float[2];
        this.mChart = chart;
        this.mCirclePaintInner = new Paint(1);
        this.mCirclePaintInner.setStyle(Paint.Style.FILL);
        this.mCirclePaintInner.setColor(-1);
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
                this.mDrawBitmap = new WeakReference<>(Bitmap.createBitmap(width, height, this.mBitmapConfig));
                this.mBitmapCanvas = new Canvas(this.mDrawBitmap.get());
            } else {
                return;
            }
        }
        this.mDrawBitmap.get().eraseColor(0);
        LineData lineData = this.mChart.getLineData();
        for (T set : lineData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set);
            }
        }
        c.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, this.mRenderPaint);
    }

    protected void drawDataSet(Canvas c, ILineDataSet dataSet) {
        if (dataSet.getEntryCount() < 1) {
            return;
        }
        this.mRenderPaint.setStrokeWidth(dataSet.getLineWidth());
        this.mRenderPaint.setPathEffect(dataSet.getDashPathEffect());
        switch (dataSet.getMode()) {
            case CUBIC_BEZIER:
                drawCubicBezier(dataSet);
                break;
            case HORIZONTAL_BEZIER:
                drawHorizontalBezier(dataSet);
                break;
            default:
                drawLinear(c, dataSet);
                break;
        }
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawHorizontalBezier(ILineDataSet dataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, dataSet);
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            Entry cur = dataSet.getEntryForIndex(this.mXBounds.min);
            this.cubicPath.moveTo(cur.getX(), cur.getY() * phaseY);
            int j = this.mXBounds.min + 1;
            Entry cur2 = cur;
            while (true) {
                int j2 = j;
                if (j2 > this.mXBounds.range + this.mXBounds.min) {
                    break;
                }
                Entry prev = cur2;
                ?? entryForIndex = dataSet.getEntryForIndex(j2);
                float cpx = prev.getX() + ((entryForIndex.getX() - prev.getX()) / 2.0f);
                this.cubicPath.cubicTo(cpx, prev.getY() * phaseY, cpx, entryForIndex.getY() * phaseY, entryForIndex.getX(), entryForIndex.getY() * phaseY);
                j = j2 + 1;
                cur2 = entryForIndex;
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds);
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        trans.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v4, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r14v2, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r15v3, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r3v6 */
    protected void drawCubicBezier(ILineDataSet dataSet) {
        Math.max(0.0f, Math.min(1.0f, this.mAnimator.getPhaseX()));
        float phaseY = this.mAnimator.getPhaseY();
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, dataSet);
        float intensity = dataSet.getCubicIntensity();
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            float curDx = 0.0f;
            int firstIndex = this.mXBounds.min + 1;
            int i = this.mXBounds.min + this.mXBounds.range;
            ?? entryForIndex = dataSet.getEntryForIndex(Math.max(firstIndex - 2, 0));
            ?? entryForIndex2 = dataSet.getEntryForIndex(Math.max(firstIndex - 1, 0));
            Entry next = entryForIndex2;
            if (entryForIndex2 == 0) {
                return;
            }
            Path path = this.cubicPath;
            float prevDx = entryForIndex2.getX();
            float prevDy = entryForIndex2.getY() * phaseY;
            path.moveTo(prevDx, prevDy);
            int j = this.mXBounds.min + 1;
            int nextIndex = -1;
            Entry prev = entryForIndex;
            Entry cur = entryForIndex2;
            while (j <= this.mXBounds.range + this.mXBounds.min) {
                Entry prevPrev = prev;
                Entry prev2 = cur;
                Entry cur2 = nextIndex == j ? next : dataSet.getEntryForIndex(j);
                int i2 = j + 1;
                int nextIndex2 = dataSet.getEntryCount();
                int nextIndex3 = i2 < nextIndex2 ? j + 1 : j;
                ?? entryForIndex3 = dataSet.getEntryForIndex(nextIndex3);
                float prevDx2 = (cur2.getX() - prevPrev.getX()) * intensity;
                float prevDy2 = (cur2.getY() - prevPrev.getY()) * intensity;
                curDx = (entryForIndex3.getX() - prev2.getX()) * intensity;
                float curDy = (entryForIndex3.getY() - prev2.getY()) * intensity;
                this.cubicPath.cubicTo(prev2.getX() + prevDx2, (prev2.getY() + prevDy2) * phaseY, cur2.getX() - curDx, (cur2.getY() - curDy) * phaseY, cur2.getX(), cur2.getY() * phaseY);
                j++;
                nextIndex = nextIndex3;
                prev = prev2;
                cur = cur2;
                next = entryForIndex3;
            }
        }
        if (dataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, dataSet, this.cubicFillPath, trans, this.mXBounds);
        }
        this.mRenderPaint.setColor(dataSet.getColor());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        trans.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawCubicFill(Canvas c, ILineDataSet dataSet, Path spline, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) {
        float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
        spline.lineTo(dataSet.getEntryForIndex(bounds.min + bounds.range).getX(), fillMin);
        spline.lineTo(dataSet.getEntryForIndex(bounds.min).getX(), fillMin);
        spline.close();
        trans.pathValueToPixel(spline);
        Drawable drawable = dataSet.getFillDrawable();
        if (drawable != null) {
            drawFilledPath(c, spline, drawable);
        } else {
            drawFilledPath(c, spline, dataSet.getFillColor(), dataSet.getFillAlpha());
        }
    }

    /* JADX WARN: Type inference failed for: r13v9, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r14v13, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r14v14, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawLinear(Canvas c, ILineDataSet dataSet) {
        Canvas canvas;
        int entryCount = dataSet.getEntryCount();
        boolean isDrawSteppedEnabled = dataSet.isDrawSteppedEnabled();
        char c2 = 4;
        int pointsPerEntryPair = isDrawSteppedEnabled ? 4 : 2;
        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (dataSet.isDashedLineEnabled()) {
            canvas = this.mBitmapCanvas;
        } else {
            canvas = c;
        }
        this.mXBounds.set(this.mChart, dataSet);
        if (dataSet.isDrawFilledEnabled() && entryCount > 0) {
            drawLinearFill(c, dataSet, trans, this.mXBounds);
        }
        char c3 = 0;
        char c4 = 1;
        if (dataSet.getColors().size() > 1) {
            if (this.mLineBuffer.length <= pointsPerEntryPair * 2) {
                this.mLineBuffer = new float[pointsPerEntryPair * 4];
            }
            int j = this.mXBounds.min;
            while (j <= this.mXBounds.range + this.mXBounds.min) {
                ?? entryForIndex = dataSet.getEntryForIndex(j);
                if (entryForIndex != 0) {
                    this.mLineBuffer[c3] = entryForIndex.getX();
                    this.mLineBuffer[c4] = entryForIndex.getY() * phaseY;
                    if (j >= this.mXBounds.max) {
                        this.mLineBuffer[2] = this.mLineBuffer[0];
                        this.mLineBuffer[3] = this.mLineBuffer[c4];
                    } else {
                        ?? entryForIndex2 = dataSet.getEntryForIndex(j + 1);
                        if (entryForIndex2 == 0) {
                            break;
                        } else if (isDrawSteppedEnabled) {
                            this.mLineBuffer[2] = entryForIndex2.getX();
                            this.mLineBuffer[3] = this.mLineBuffer[c4];
                            this.mLineBuffer[c2] = this.mLineBuffer[2];
                            this.mLineBuffer[5] = this.mLineBuffer[3];
                            this.mLineBuffer[6] = entryForIndex2.getX();
                            this.mLineBuffer[7] = entryForIndex2.getY() * phaseY;
                        } else {
                            this.mLineBuffer[2] = entryForIndex2.getX();
                            this.mLineBuffer[3] = entryForIndex2.getY() * phaseY;
                        }
                    }
                    trans.pointValuesToPixel(this.mLineBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mLineBuffer[0])) {
                        break;
                    } else if (this.mViewPortHandler.isInBoundsLeft(this.mLineBuffer[2]) && (this.mViewPortHandler.isInBoundsTop(this.mLineBuffer[c4]) || this.mViewPortHandler.isInBoundsBottom(this.mLineBuffer[3]))) {
                        this.mRenderPaint.setColor(dataSet.getColor(j));
                        canvas.drawLines(this.mLineBuffer, 0, pointsPerEntryPair * 2, this.mRenderPaint);
                    }
                }
                j++;
                c2 = 4;
                c3 = 0;
                c4 = 1;
            }
        } else {
            if (this.mLineBuffer.length < Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 2) {
                this.mLineBuffer = new float[Math.max(entryCount * pointsPerEntryPair, pointsPerEntryPair) * 4];
            }
            if (dataSet.getEntryForIndex(this.mXBounds.min) != 0) {
                int j2 = 0;
                int x = this.mXBounds.min;
                while (x <= this.mXBounds.range + this.mXBounds.min) {
                    ?? entryForIndex3 = dataSet.getEntryForIndex(x == 0 ? 0 : x - 1);
                    ?? entryForIndex4 = dataSet.getEntryForIndex(x);
                    if (entryForIndex3 != 0 && entryForIndex4 != 0) {
                        int j3 = j2 + 1;
                        this.mLineBuffer[j2] = entryForIndex3.getX();
                        int j4 = j3 + 1;
                        this.mLineBuffer[j3] = entryForIndex3.getY() * phaseY;
                        if (isDrawSteppedEnabled) {
                            int j5 = j4 + 1;
                            this.mLineBuffer[j4] = entryForIndex4.getX();
                            int j6 = j5 + 1;
                            this.mLineBuffer[j5] = entryForIndex3.getY() * phaseY;
                            int j7 = j6 + 1;
                            this.mLineBuffer[j6] = entryForIndex4.getX();
                            j4 = j7 + 1;
                            this.mLineBuffer[j7] = entryForIndex3.getY() * phaseY;
                        }
                        int j8 = j4 + 1;
                        this.mLineBuffer[j4] = entryForIndex4.getX();
                        this.mLineBuffer[j8] = entryForIndex4.getY() * phaseY;
                        j2 = j8 + 1;
                    }
                    x++;
                }
                if (j2 > 0) {
                    trans.pointValuesToPixel(this.mLineBuffer);
                    int size = Math.max((this.mXBounds.range + 1) * pointsPerEntryPair, pointsPerEntryPair) * 2;
                    this.mRenderPaint.setColor(dataSet.getColor());
                    canvas.drawLines(this.mLineBuffer, 0, size, this.mRenderPaint);
                }
            }
        }
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawLinearFill(Canvas c, ILineDataSet dataSet, Transformer trans, BarLineScatterCandleBubbleRenderer.XBounds bounds) {
        int currentStartIndex;
        int currentEndIndex;
        Path filled = this.mGenerateFilledPathBuffer;
        int startingIndex = bounds.min;
        int endingIndex = bounds.range + bounds.min;
        int iterations = 0;
        do {
            currentStartIndex = startingIndex + (iterations * 128);
            int currentEndIndex2 = currentStartIndex + 128;
            currentEndIndex = currentEndIndex2 > endingIndex ? endingIndex : currentEndIndex2;
            if (currentStartIndex <= currentEndIndex) {
                generateFilledPath(dataSet, currentStartIndex, currentEndIndex, filled);
                trans.pathValueToPixel(filled);
                Drawable drawable = dataSet.getFillDrawable();
                if (drawable != null) {
                    drawFilledPath(c, filled, drawable);
                } else {
                    drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha());
                }
            }
            iterations++;
        } while (currentStartIndex <= currentEndIndex);
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.github.mikephil.charting.data.Entry] */
    private void generateFilledPath(ILineDataSet dataSet, int startIndex, int endIndex, Path outputPath) {
        float fillMin = dataSet.getFillFormatter().getFillLinePosition(dataSet, this.mChart);
        float phaseY = this.mAnimator.getPhaseY();
        boolean isDrawSteppedEnabled = dataSet.getMode() == LineDataSet.Mode.STEPPED;
        outputPath.reset();
        ?? entryForIndex = dataSet.getEntryForIndex(startIndex);
        outputPath.moveTo(entryForIndex.getX(), fillMin);
        outputPath.lineTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
        Entry currentEntry = null;
        BaseEntry baseEntry = null;
        int x = startIndex + 1;
        while (x <= endIndex) {
            ?? entryForIndex2 = dataSet.getEntryForIndex(x);
            if (isDrawSteppedEnabled && baseEntry != null) {
                outputPath.lineTo(entryForIndex2.getX(), baseEntry.getY() * phaseY);
            }
            outputPath.lineTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
            baseEntry = entryForIndex2;
            x++;
            currentEntry = entryForIndex2;
        }
        if (currentEntry != null) {
            outputPath.lineTo(currentEntry.getX(), fillMin);
        }
        outputPath.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas c) {
        MPPointF iconsOffset;
        int j;
        MPPointF iconsOffset2;
        float[] positions;
        float y;
        float x;
        if (isDrawingValuesAllowed(this.mChart)) {
            List<T> dataSets = this.mChart.getLineData().getDataSets();
            int i = 0;
            while (true) {
                int i2 = i;
                int i3 = dataSets.size();
                if (i2 < i3) {
                    ILineDataSet dataSet = (ILineDataSet) dataSets.get(i2);
                    if (shouldDrawValues(dataSet)) {
                        applyValueTextStyle(dataSet);
                        Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
                        int valOffset = (int) (dataSet.getCircleRadius() * 1.75f);
                        if (!dataSet.isDrawCirclesEnabled()) {
                            valOffset /= 2;
                        }
                        int valOffset2 = valOffset;
                        this.mXBounds.set(this.mChart, dataSet);
                        float[] positions2 = trans.generateTransformedValuesLine(dataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                        MPPointF iconsOffset3 = MPPointF.getInstance(dataSet.getIconsOffset());
                        iconsOffset3.x = Utils.convertDpToPixel(iconsOffset3.x);
                        iconsOffset3.y = Utils.convertDpToPixel(iconsOffset3.y);
                        int j2 = 0;
                        while (true) {
                            int j3 = j2;
                            int j4 = positions2.length;
                            if (j3 < j4) {
                                float x2 = positions2[j3];
                                float y2 = positions2[j3 + 1];
                                if (this.mViewPortHandler.isInBoundsRight(x2)) {
                                    if (this.mViewPortHandler.isInBoundsLeft(x2)) {
                                        if (this.mViewPortHandler.isInBoundsY(y2)) {
                                            ?? entryForIndex = dataSet.getEntryForIndex((j3 / 2) + this.mXBounds.min);
                                            if (dataSet.isDrawValuesEnabled()) {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                                positions = positions2;
                                                drawValue(c, dataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i2, x, y2 - valOffset2, dataSet.getValueTextColor(j3 / 2));
                                            } else {
                                                y = y2;
                                                x = x2;
                                                j = j3;
                                                iconsOffset2 = iconsOffset3;
                                                positions = positions2;
                                            }
                                            if (entryForIndex.getIcon() != null && dataSet.isDrawIconsEnabled()) {
                                                Drawable icon = entryForIndex.getIcon();
                                                Utils.drawImage(c, icon, (int) (x + iconsOffset2.x), (int) (y + iconsOffset2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                            }
                                        } else {
                                            j = j3;
                                            iconsOffset2 = iconsOffset3;
                                            positions = positions2;
                                        }
                                    } else {
                                        j = j3;
                                        iconsOffset2 = iconsOffset3;
                                        positions = positions2;
                                    }
                                    j2 = j + 2;
                                    iconsOffset3 = iconsOffset2;
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
        drawCircles(c);
    }

    /* JADX WARN: Type inference failed for: r16v0, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawCircles(Canvas c) {
        float phaseY;
        List list;
        boolean z;
        DataSetImageCache imageCache;
        List list2;
        boolean z2;
        LineChartRenderer lineChartRenderer = this;
        lineChartRenderer.mRenderPaint.setStyle(Paint.Style.FILL);
        float phaseY2 = lineChartRenderer.mAnimator.getPhaseY();
        float f = 0.0f;
        char c2 = 0;
        lineChartRenderer.mCirclesBuffer[0] = 0.0f;
        boolean z3 = true;
        lineChartRenderer.mCirclesBuffer[1] = 0.0f;
        List dataSets = lineChartRenderer.mChart.getLineData().getDataSets();
        int i = 0;
        while (i < dataSets.size()) {
            ILineDataSet dataSet = (ILineDataSet) dataSets.get(i);
            if (dataSet.isVisible() && dataSet.isDrawCirclesEnabled()) {
                if (dataSet.getEntryCount() != 0) {
                    lineChartRenderer.mCirclePaintInner.setColor(dataSet.getCircleHoleColor());
                    Transformer trans = lineChartRenderer.mChart.getTransformer(dataSet.getAxisDependency());
                    lineChartRenderer.mXBounds.set(lineChartRenderer.mChart, dataSet);
                    float circleRadius = dataSet.getCircleRadius();
                    float circleHoleRadius = dataSet.getCircleHoleRadius();
                    boolean drawCircleHole = (!dataSet.isDrawCircleHoleEnabled() || circleHoleRadius >= circleRadius || circleHoleRadius <= f) ? c2 == 1 ? 1 : 0 : z3;
                    boolean drawTransparentCircleHole = (drawCircleHole && dataSet.getCircleHoleColor() == 1122867) ? z3 : c2 == 1 ? 1 : 0;
                    if (lineChartRenderer.mImageCaches.containsKey(dataSet)) {
                        imageCache = lineChartRenderer.mImageCaches.get(dataSet);
                    } else {
                        imageCache = new DataSetImageCache();
                        lineChartRenderer.mImageCaches.put(dataSet, imageCache);
                    }
                    boolean changeRequired = imageCache.init(dataSet);
                    if (changeRequired) {
                        imageCache.fill(dataSet, drawCircleHole, drawTransparentCircleHole);
                    }
                    int boundsRangeCount = lineChartRenderer.mXBounds.range + lineChartRenderer.mXBounds.min;
                    int j = lineChartRenderer.mXBounds.min;
                    char c3 = c2;
                    while (j <= boundsRangeCount) {
                        ?? entryForIndex = dataSet.getEntryForIndex(j);
                        if (entryForIndex != 0) {
                            lineChartRenderer.mCirclesBuffer[c3] = entryForIndex.getX();
                            lineChartRenderer.mCirclesBuffer[1] = entryForIndex.getY() * phaseY2;
                            trans.pointValuesToPixel(lineChartRenderer.mCirclesBuffer);
                            phaseY = phaseY2;
                            if (!lineChartRenderer.mViewPortHandler.isInBoundsRight(lineChartRenderer.mCirclesBuffer[c3])) {
                                list = dataSets;
                                z = true;
                                break;
                            }
                            if (lineChartRenderer.mViewPortHandler.isInBoundsLeft(lineChartRenderer.mCirclesBuffer[c3])) {
                                if (lineChartRenderer.mViewPortHandler.isInBoundsY(lineChartRenderer.mCirclesBuffer[1])) {
                                    Bitmap circleBitmap = imageCache.getBitmap(j);
                                    if (circleBitmap != null) {
                                        z2 = true;
                                        list2 = dataSets;
                                        c.drawBitmap(circleBitmap, lineChartRenderer.mCirclesBuffer[c3] - circleRadius, lineChartRenderer.mCirclesBuffer[1] - circleRadius, (Paint) null);
                                    }
                                } else {
                                    list2 = dataSets;
                                    z2 = true;
                                }
                                j++;
                                z3 = z2;
                                phaseY2 = phaseY;
                                dataSets = list2;
                                lineChartRenderer = this;
                                c3 = 0;
                            }
                            list2 = dataSets;
                            z2 = true;
                            j++;
                            z3 = z2;
                            phaseY2 = phaseY;
                            dataSets = list2;
                            lineChartRenderer = this;
                            c3 = 0;
                        }
                    }
                }
                phaseY = phaseY2;
                list = dataSets;
                z = z3;
                i++;
                z3 = z;
                phaseY2 = phaseY;
                dataSets = list;
                lineChartRenderer = this;
                f = 0.0f;
                c2 = 0;
            }
            phaseY = phaseY2;
            list = dataSets;
            z = z3;
            i++;
            z3 = z;
            phaseY2 = phaseY;
            dataSets = list;
            lineChartRenderer = this;
            f = 0.0f;
            c2 = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas c, Highlight[] indices) {
        LineData lineData = this.mChart.getLineData();
        for (Highlight high : indices) {
            ILineDataSet set = (ILineDataSet) lineData.getDataSetByIndex(high.getDataSetIndex());
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

    public void setBitmapConfig(Bitmap.Config config) {
        this.mBitmapConfig = config;
        releaseBitmap();
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DataSetImageCache {
        private Bitmap[] circleBitmaps;
        private Path mCirclePathBuffer;

        private DataSetImageCache() {
            this.mCirclePathBuffer = new Path();
        }

        protected boolean init(ILineDataSet set) {
            int size = set.getCircleColorCount();
            if (this.circleBitmaps == null) {
                this.circleBitmaps = new Bitmap[size];
                return true;
            } else if (this.circleBitmaps.length == size) {
                return false;
            } else {
                this.circleBitmaps = new Bitmap[size];
                return true;
            }
        }

        protected void fill(ILineDataSet set, boolean drawCircleHole, boolean drawTransparentCircleHole) {
            int colorCount = set.getCircleColorCount();
            float circleRadius = set.getCircleRadius();
            float circleHoleRadius = set.getCircleHoleRadius();
            for (int i = 0; i < colorCount; i++) {
                Bitmap.Config conf = Bitmap.Config.ARGB_4444;
                Bitmap circleBitmap = Bitmap.createBitmap((int) (circleRadius * 2.1d), (int) (circleRadius * 2.1d), conf);
                Canvas canvas = new Canvas(circleBitmap);
                this.circleBitmaps[i] = circleBitmap;
                LineChartRenderer.this.mRenderPaint.setColor(set.getCircleColor(i));
                if (drawTransparentCircleHole) {
                    this.mCirclePathBuffer.reset();
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Path.Direction.CW);
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Path.Direction.CCW);
                    canvas.drawPath(this.mCirclePathBuffer, LineChartRenderer.this.mRenderPaint);
                } else {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, LineChartRenderer.this.mRenderPaint);
                    if (drawCircleHole) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, LineChartRenderer.this.mCirclePaintInner);
                    }
                }
            }
        }

        protected Bitmap getBitmap(int index) {
            return this.circleBitmaps[index % this.circleBitmaps.length];
        }
    }
}
