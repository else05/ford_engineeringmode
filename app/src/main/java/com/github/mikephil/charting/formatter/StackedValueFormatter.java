package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class StackedValueFormatter implements IValueFormatter {
    private String mAppendix;
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;

    public StackedValueFormatter(boolean drawWholeStack, String appendix, int decimals) {
        this.mDrawWholeStack = drawWholeStack;
        this.mAppendix = appendix;
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < decimals; i++) {
            if (i == 0) {
                b.append(".");
            }
            b.append("0");
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        BarEntry barEntry;
        float[] vals;
        if (!this.mDrawWholeStack && (entry instanceof BarEntry) && (vals = (barEntry = (BarEntry) entry).getYVals()) != null) {
            if (vals[vals.length - 1] == value) {
                return this.mFormat.format(barEntry.getY()) + this.mAppendix;
            }
            return "";
        }
        return this.mFormat.format(value) + this.mAppendix;
    }
}
