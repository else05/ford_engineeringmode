package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class MarkerImage implements IMarker {
    private Context mContext;
    private Drawable mDrawable;
    private WeakReference<Chart> mWeakChart;
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private FSize mSize = new FSize();
    private Rect mDrawableBoundsCache = new Rect();

    public MarkerImage(Context context, int drawableResourceId) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 21) {
            this.mDrawable = this.mContext.getResources().getDrawable(drawableResourceId, null);
        } else {
            this.mDrawable = this.mContext.getResources().getDrawable(drawableResourceId);
        }
    }

    public void setOffset(MPPointF offset) {
        this.mOffset = offset;
        if (this.mOffset == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setOffset(float offsetX, float offsetY) {
        this.mOffset.x = offsetX;
        this.mOffset.y = offsetY;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return this.mOffset;
    }

    public void setSize(FSize size) {
        this.mSize = size;
        if (this.mSize == null) {
            this.mSize = new FSize();
        }
    }

    public FSize getSize() {
        return this.mSize;
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public Chart getChartView() {
        if (this.mWeakChart == null) {
            return null;
        }
        return this.mWeakChart.get();
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        MPPointF offset = getOffset();
        this.mOffset2.x = offset.x;
        this.mOffset2.y = offset.y;
        Chart chart = getChartView();
        float width = this.mSize.width;
        float height = this.mSize.height;
        if (width == 0.0f && this.mDrawable != null) {
            width = this.mDrawable.getIntrinsicWidth();
        }
        if (height == 0.0f && this.mDrawable != null) {
            height = this.mDrawable.getIntrinsicHeight();
        }
        if (this.mOffset2.x + posX < 0.0f) {
            this.mOffset2.x = -posX;
        } else if (chart != null && posX + width + this.mOffset2.x > chart.getWidth()) {
            this.mOffset2.x = (chart.getWidth() - posX) - width;
        }
        if (this.mOffset2.y + posY < 0.0f) {
            this.mOffset2.y = -posY;
        } else if (chart != null && posY + height + this.mOffset2.y > chart.getHeight()) {
            this.mOffset2.y = (chart.getHeight() - posY) - height;
        }
        return this.mOffset2;
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry e, Highlight highlight) {
    }

    @Override // com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float posX, float posY) {
        if (this.mDrawable == null) {
            return;
        }
        MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
        float width = this.mSize.width;
        float height = this.mSize.height;
        if (width == 0.0f && this.mDrawable != null) {
            width = this.mDrawable.getIntrinsicWidth();
        }
        if (height == 0.0f && this.mDrawable != null) {
            height = this.mDrawable.getIntrinsicHeight();
        }
        this.mDrawable.copyBounds(this.mDrawableBoundsCache);
        this.mDrawable.setBounds(this.mDrawableBoundsCache.left, this.mDrawableBoundsCache.top, this.mDrawableBoundsCache.left + ((int) width), this.mDrawableBoundsCache.top + ((int) height));
        int saveId = canvas.save();
        canvas.translate(offset.x + posX, offset.y + posY);
        this.mDrawable.draw(canvas);
        canvas.restoreToCount(saveId);
        this.mDrawable.setBounds(this.mDrawableBoundsCache);
    }
}
