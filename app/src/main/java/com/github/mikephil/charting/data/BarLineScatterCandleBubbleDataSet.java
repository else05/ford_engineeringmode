package com.github.mikephil.charting.data;

import android.graphics.Color;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.yfve.engineeringmode.R;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BarLineScatterCandleBubbleDataSet<T extends Entry> extends DataSet<T> implements IBarLineScatterCandleBubbleDataSet<T> {
    protected int mHighLightColor;

    public BarLineScatterCandleBubbleDataSet(List<T> yVals, String label) {
        super(yVals, label);
        this.mHighLightColor = Color.rgb(255, 187, (int) R.styleable.AppCompatTheme_windowFixedWidthMinor);
    }

    public void setHighLightColor(int color) {
        this.mHighLightColor = color;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet
    public int getHighLightColor() {
        return this.mHighLightColor;
    }
}
