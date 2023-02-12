package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.animation.EasingFunction;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ChartAnimator mAnimator;
    protected ChartTouchListener mChartTouchListener;
    protected T mData;
    protected DefaultValueFormatter mDefaultValueFormatter;
    protected Paint mDescPaint;
    protected Description mDescription;
    private boolean mDragDecelerationEnabled;
    private float mDragDecelerationFrictionCoef;
    protected boolean mDrawMarkers;
    private float mExtraBottomOffset;
    private float mExtraLeftOffset;
    private float mExtraRightOffset;
    private float mExtraTopOffset;
    private OnChartGestureListener mGestureListener;
    protected boolean mHighLightPerTapEnabled;
    protected IHighlighter mHighlighter;
    protected Highlight[] mIndicesToHighlight;
    protected Paint mInfoPaint;
    protected ArrayList<Runnable> mJobs;
    protected Legend mLegend;
    protected LegendRenderer mLegendRenderer;
    protected boolean mLogEnabled;
    protected IMarker mMarker;
    protected float mMaxHighlightDistance;
    private String mNoDataText;
    private boolean mOffsetsCalculated;
    protected DataRenderer mRenderer;
    protected OnChartValueSelectedListener mSelectionListener;
    protected boolean mTouchEnabled;
    private boolean mUnbind;
    protected ViewPortHandler mViewPortHandler;
    protected XAxis mXAxis;

    protected abstract void calcMinMax();

    protected abstract void calculateOffsets();

    public abstract void notifyDataSetChanged();

    public Chart(Context context) {
        super(context);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    public Chart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mLogEnabled = false;
        this.mData = null;
        this.mHighLightPerTapEnabled = true;
        this.mDragDecelerationEnabled = true;
        this.mDragDecelerationFrictionCoef = 0.9f;
        this.mDefaultValueFormatter = new DefaultValueFormatter(0);
        this.mTouchEnabled = true;
        this.mNoDataText = "No chart data available.";
        this.mViewPortHandler = new ViewPortHandler();
        this.mExtraTopOffset = 0.0f;
        this.mExtraRightOffset = 0.0f;
        this.mExtraBottomOffset = 0.0f;
        this.mExtraLeftOffset = 0.0f;
        this.mOffsetsCalculated = false;
        this.mMaxHighlightDistance = 0.0f;
        this.mDrawMarkers = true;
        this.mJobs = new ArrayList<>();
        this.mUnbind = false;
        init();
    }

    public void init() {
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT < 11) {
            this.mAnimator = new ChartAnimator();
        } else {
            this.mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.mikephil.charting.charts.Chart.1
                {
                    Chart.this = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator animation) {
                    Chart.this.postInvalidate();
                }
            });
        }
        Utils.init(getContext());
        this.mMaxHighlightDistance = Utils.convertDpToPixel(500.0f);
        this.mDescription = new Description();
        this.mLegend = new Legend();
        this.mLegendRenderer = new LegendRenderer(this.mViewPortHandler, this.mLegend);
        this.mXAxis = new XAxis();
        this.mDescPaint = new Paint(1);
        this.mInfoPaint = new Paint(1);
        this.mInfoPaint.setColor(Color.rgb(247, 189, 51));
        this.mInfoPaint.setTextAlign(Paint.Align.CENTER);
        this.mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        if (this.mLogEnabled) {
            Log.i("", "Chart.init()");
        }
    }

    public void setData(T data) {
        this.mData = data;
        this.mOffsetsCalculated = false;
        if (data == null) {
            return;
        }
        setupDefaultFormatter(data.getYMin(), data.getYMax());
        for (IDataSet set : this.mData.getDataSets()) {
            if (set.needsFormatter() || set.getValueFormatter() == this.mDefaultValueFormatter) {
                set.setValueFormatter(this.mDefaultValueFormatter);
            }
        }
        notifyDataSetChanged();
        if (this.mLogEnabled) {
            Log.i(LOG_TAG, "Data is set.");
        }
    }

    public void clear() {
        this.mData = null;
        this.mOffsetsCalculated = false;
        this.mIndicesToHighlight = null;
        invalidate();
    }

    public void clearValues() {
        this.mData.clearValues();
        invalidate();
    }

    public boolean isEmpty() {
        return this.mData == null || this.mData.getEntryCount() <= 0;
    }

    protected void setupDefaultFormatter(float min, float max) {
        float reference;
        if (this.mData == null || this.mData.getEntryCount() < 2) {
            reference = Math.max(Math.abs(min), Math.abs(max));
        } else {
            reference = Math.abs(max - min);
        }
        int digits = Utils.getDecimals(reference);
        this.mDefaultValueFormatter.setup(digits);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mData == null) {
            boolean hasText = !TextUtils.isEmpty(this.mNoDataText);
            if (hasText) {
                MPPointF c = getCenter();
                canvas.drawText(this.mNoDataText, c.x, c.y, this.mInfoPaint);
                return;
            }
            return;
        }
        boolean hasText2 = this.mOffsetsCalculated;
        if (!hasText2) {
            calculateOffsets();
            this.mOffsetsCalculated = true;
        }
    }

    public void drawDescription(Canvas c) {
        float x;
        float y;
        if (this.mDescription != null && this.mDescription.isEnabled()) {
            MPPointF position = this.mDescription.getPosition();
            this.mDescPaint.setTypeface(this.mDescription.getTypeface());
            this.mDescPaint.setTextSize(this.mDescription.getTextSize());
            this.mDescPaint.setColor(this.mDescription.getTextColor());
            this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
            if (position == null) {
                x = (getWidth() - this.mViewPortHandler.offsetRight()) - this.mDescription.getXOffset();
                y = (getHeight() - this.mViewPortHandler.offsetBottom()) - this.mDescription.getYOffset();
            } else {
                x = position.x;
                y = position.y;
            }
            c.drawText(this.mDescription.getText(), x, y, this.mDescPaint);
        }
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getMaxHighlightDistance() {
        return this.mMaxHighlightDistance;
    }

    public void setMaxHighlightDistance(float distDp) {
        this.mMaxHighlightDistance = Utils.convertDpToPixel(distDp);
    }

    public Highlight[] getHighlighted() {
        return this.mIndicesToHighlight;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.mHighLightPerTapEnabled;
    }

    public void setHighlightPerTapEnabled(boolean enabled) {
        this.mHighLightPerTapEnabled = enabled;
    }

    public boolean valuesToHighlight() {
        return (this.mIndicesToHighlight == null || this.mIndicesToHighlight.length <= 0 || this.mIndicesToHighlight[0] == null) ? false : true;
    }

    protected void setLastHighlighted(Highlight[] highs) {
        if (highs == null || highs.length <= 0 || highs[0] == null) {
            this.mChartTouchListener.setLastHighlighted(null);
        } else {
            this.mChartTouchListener.setLastHighlighted(highs[0]);
        }
    }

    public void highlightValues(Highlight[] highs) {
        this.mIndicesToHighlight = highs;
        setLastHighlighted(highs);
        invalidate();
    }

    public void highlightValue(float x, int dataSetIndex) {
        highlightValue(x, dataSetIndex, true);
    }

    public void highlightValue(float x, float y, int dataSetIndex) {
        highlightValue(x, y, dataSetIndex, true);
    }

    public void highlightValue(float x, int dataSetIndex, boolean callListener) {
        highlightValue(x, Float.NaN, dataSetIndex, callListener);
    }

    public void highlightValue(float x, float y, int dataSetIndex, boolean callListener) {
        if (dataSetIndex < 0 || dataSetIndex >= this.mData.getDataSetCount()) {
            highlightValue((Highlight) null, callListener);
        } else {
            highlightValue(new Highlight(x, y, dataSetIndex), callListener);
        }
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight high, boolean callListener) {
        Entry e = null;
        if (high == null) {
            this.mIndicesToHighlight = null;
        } else {
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Highlighted: " + high.toString());
            }
            e = this.mData.getEntryForHighlight(high);
            if (e == null) {
                this.mIndicesToHighlight = null;
                high = null;
            } else {
                this.mIndicesToHighlight = new Highlight[]{high};
            }
        }
        setLastHighlighted(this.mIndicesToHighlight);
        if (callListener && this.mSelectionListener != null) {
            if (!valuesToHighlight()) {
                this.mSelectionListener.onNothingSelected();
            } else {
                this.mSelectionListener.onValueSelected(e, high);
            }
        }
        invalidate();
    }

    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (this.mData == null) {
            Log.e(LOG_TAG, "Can't select by touch. No data set.");
            return null;
        }
        return getHighlighter().getHighlight(x, y);
    }

    public void setOnTouchListener(ChartTouchListener l) {
        this.mChartTouchListener = l;
    }

    public ChartTouchListener getOnTouchListener() {
        return this.mChartTouchListener;
    }

    public void drawMarkers(Canvas canvas) {
        if (this.mMarker == null || !isDrawMarkersEnabled() || !valuesToHighlight()) {
            return;
        }
        for (int i = 0; i < this.mIndicesToHighlight.length; i++) {
            Highlight highlight = this.mIndicesToHighlight[i];
            IDataSet set = this.mData.getDataSetByIndex(highlight.getDataSetIndex());
            Entry e = this.mData.getEntryForHighlight(this.mIndicesToHighlight[i]);
            int entryIndex = set.getEntryIndex(e);
            if (e != null && entryIndex <= set.getEntryCount() * this.mAnimator.getPhaseX()) {
                float[] pos = getMarkerPosition(highlight);
                if (this.mViewPortHandler.isInBounds(pos[0], pos[1])) {
                    this.mMarker.refreshContent(e, highlight);
                    this.mMarker.draw(canvas, pos[0], pos[1]);
                }
            }
        }
    }

    protected float[] getMarkerPosition(Highlight high) {
        return new float[]{high.getDrawX(), high.getDrawY()};
    }

    public ChartAnimator getAnimator() {
        return this.mAnimator;
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    public void setDragDecelerationEnabled(boolean enabled) {
        this.mDragDecelerationEnabled = enabled;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public void setDragDecelerationFrictionCoef(float newValue) {
        if (newValue < 0.0f) {
            newValue = 0.0f;
        }
        if (newValue >= 1.0f) {
            newValue = 0.999f;
        }
        this.mDragDecelerationFrictionCoef = newValue;
    }

    public void animateXY(int durationMillisX, int durationMillisY, EasingFunction easingX, EasingFunction easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, EasingFunction easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateXY(int durationMillisX, int durationMillisY, Easing.EasingOption easingX, Easing.EasingOption easingY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY, easingX, easingY);
    }

    public void animateX(int durationMillis, Easing.EasingOption easing) {
        this.mAnimator.animateX(durationMillis, easing);
    }

    public void animateY(int durationMillis, Easing.EasingOption easing) {
        this.mAnimator.animateY(durationMillis, easing);
    }

    public void animateX(int durationMillis) {
        this.mAnimator.animateX(durationMillis);
    }

    public void animateY(int durationMillis) {
        this.mAnimator.animateY(durationMillis);
    }

    public void animateXY(int durationMillisX, int durationMillisY) {
        this.mAnimator.animateXY(durationMillisX, durationMillisY);
    }

    public XAxis getXAxis() {
        return this.mXAxis;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public IValueFormatter getDefaultValueFormatter() {
        return this.mDefaultValueFormatter;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener l) {
        this.mSelectionListener = l;
    }

    public void setOnChartGestureListener(OnChartGestureListener l) {
        this.mGestureListener = l;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public float getYMax() {
        return this.mData.getYMax();
    }

    public float getYMin() {
        return this.mData.getYMin();
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXChartMax() {
        return this.mXAxis.mAxisMaximum;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXChartMin() {
        return this.mXAxis.mAxisMinimum;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public float getXRange() {
        return this.mXAxis.mAxisRange;
    }

    public MPPointF getCenter() {
        return MPPointF.getInstance(getWidth() / 2.0f, getHeight() / 2.0f);
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOffsets() {
        return this.mViewPortHandler.getContentCenter();
    }

    public void setExtraOffsets(float left, float top, float right, float bottom) {
        setExtraLeftOffset(left);
        setExtraTopOffset(top);
        setExtraRightOffset(right);
        setExtraBottomOffset(bottom);
    }

    public void setExtraTopOffset(float offset) {
        this.mExtraTopOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public void setExtraRightOffset(float offset) {
        this.mExtraRightOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public void setExtraBottomOffset(float offset) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public void setExtraLeftOffset(float offset) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(offset);
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public void setLogEnabled(boolean enabled) {
        this.mLogEnabled = enabled;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled;
    }

    public void setNoDataText(String text) {
        this.mNoDataText = text;
    }

    public void setNoDataTextColor(int color) {
        this.mInfoPaint.setColor(color);
    }

    public void setNoDataTextTypeface(Typeface tf) {
        this.mInfoPaint.setTypeface(tf);
    }

    public void setTouchEnabled(boolean enabled) {
        this.mTouchEnabled = enabled;
    }

    public void setMarker(IMarker marker) {
        this.mMarker = marker;
    }

    public IMarker getMarker() {
        return this.mMarker;
    }

    @Deprecated
    public void setMarkerView(IMarker v) {
        setMarker(v);
    }

    @Deprecated
    public IMarker getMarkerView() {
        return getMarker();
    }

    public void setDescription(Description desc) {
        this.mDescription = desc;
    }

    public Description getDescription() {
        return this.mDescription;
    }

    public Legend getLegend() {
        return this.mLegend;
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public RectF getContentRect() {
        return this.mViewPortHandler.getContentRect();
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public void setPaint(Paint p, int which) {
        if (which == 7) {
            this.mInfoPaint = p;
        } else if (which == 11) {
            this.mDescPaint = p;
        }
    }

    public Paint getPaint(int which) {
        if (which != 7) {
            if (which == 11) {
                return this.mDescPaint;
            }
            return null;
        }
        return this.mInfoPaint;
    }

    @Deprecated
    public boolean isDrawMarkerViewsEnabled() {
        return isDrawMarkersEnabled();
    }

    @Deprecated
    public void setDrawMarkerViews(boolean enabled) {
        setDrawMarkers(enabled);
    }

    public boolean isDrawMarkersEnabled() {
        return this.mDrawMarkers;
    }

    public void setDrawMarkers(boolean enabled) {
        this.mDrawMarkers = enabled;
    }

    public T getData() {
        return this.mData;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.mViewPortHandler;
    }

    public DataRenderer getRenderer() {
        return this.mRenderer;
    }

    public void setRenderer(DataRenderer renderer) {
        if (renderer != null) {
            this.mRenderer = renderer;
        }
    }

    public IHighlighter getHighlighter() {
        return this.mHighlighter;
    }

    public void setHighlighter(ChartHighlighter highlighter) {
        this.mHighlighter = highlighter;
    }

    @Override // com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
    public MPPointF getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap returnedBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return returnedBitmap;
    }

    public boolean saveToPath(String title, String pathOnSD) {
        Bitmap b = getChartBitmap();
        try {
            OutputStream stream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + pathOnSD + "/" + title + ".png");
            b.compress(Bitmap.CompressFormat.PNG, 40, stream);
            stream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToGallery(String fileName, String subFolderPath, String fileDescription, Bitmap.CompressFormat format, int quality) {
        String mimeType;
        String str = fileName;
        int quality2 = quality;
        quality2 = (quality2 < 0 || quality2 > 100) ? 50 : 50;
        long currentTime = System.currentTimeMillis();
        File extBaseDir = Environment.getExternalStorageDirectory();
        File file = new File(extBaseDir.getAbsolutePath() + "/DCIM/" + subFolderPath);
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        switch (AnonymousClass2.$SwitchMap$android$graphics$Bitmap$CompressFormat[format.ordinal()]) {
            case 1:
                mimeType = "image/png";
                if (!str.endsWith(".png")) {
                    str = str + ".png";
                    break;
                }
                break;
            case 2:
                mimeType = "image/webp";
                if (!str.endsWith(".webp")) {
                    str = str + ".webp";
                    break;
                }
                break;
            default:
                mimeType = "image/jpeg";
                if (!str.endsWith(".jpg") && !str.endsWith(".jpeg")) {
                    str = str + ".jpg";
                    break;
                }
                break;
        }
        String mimeType2 = mimeType;
        String fileName2 = str;
        String filePath = file.getAbsolutePath() + "/" + fileName2;
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            Bitmap b = getChartBitmap();
            try {
                b.compress(format, quality2, out);
                out.flush();
                out.close();
                long size = new File(filePath).length();
                ContentValues values = new ContentValues(8);
                values.put("title", fileName2);
                values.put("_display_name", fileName2);
                values.put("date_added", Long.valueOf(currentTime));
                values.put("mime_type", mimeType2);
                values.put("description", fileDescription);
                values.put("orientation", (Integer) 0);
                values.put("_data", filePath);
                values.put("_size", Long.valueOf(size));
                return getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) != null;
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                return false;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    /* renamed from: com.github.mikephil.charting.charts.Chart$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat = new int[Bitmap.CompressFormat.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public boolean saveToGallery(String fileName, int quality) {
        return saveToGallery(fileName, "", "MPAndroidChart-Library Save", Bitmap.CompressFormat.JPEG, quality);
    }

    public void removeViewportJob(Runnable job) {
        this.mJobs.remove(job);
    }

    public void clearAllViewportJobs() {
        this.mJobs.clear();
    }

    public void addViewportJob(Runnable job) {
        if (this.mViewPortHandler.hasChartDimens()) {
            post(job);
        } else {
            this.mJobs.add(job);
        }
    }

    public ArrayList<Runnable> getJobs() {
        return this.mJobs;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(left, top, right, bottom);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) Utils.convertDpToPixel(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(size, widthMeasureSpec)), Math.max(getSuggestedMinimumHeight(), resolveSize(size, heightMeasureSpec)));
    }

    @Override // android.view.View
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.mLogEnabled) {
            Log.i(LOG_TAG, "OnSizeChanged()");
        }
        if (w > 0 && h > 0 && w < 10000 && h < 10000) {
            this.mViewPortHandler.setChartDimens(w, h);
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Setting chart dimens, width: " + w + ", height: " + h);
            }
            Iterator<Runnable> it = this.mJobs.iterator();
            while (it.hasNext()) {
                Runnable r = it.next();
                post(r);
            }
            this.mJobs.clear();
        }
        notifyDataSetChanged();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setHardwareAccelerationEnabled(boolean enabled) {
        if (Build.VERSION.SDK_INT < 11) {
            Log.e(LOG_TAG, "Cannot enable/disable hardware acceleration for devices below API level 11.");
        } else if (enabled) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mUnbind) {
            unbindDrawables(this);
        }
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void setUnbindEnabled(boolean enabled) {
        this.mUnbind = enabled;
    }
}
