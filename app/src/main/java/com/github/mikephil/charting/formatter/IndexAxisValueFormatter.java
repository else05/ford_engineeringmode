package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import java.util.Collection;

/* loaded from: classes2.dex */
public class IndexAxisValueFormatter implements IAxisValueFormatter {
    private int mValueCount;
    private String[] mValues;

    public IndexAxisValueFormatter() {
        this.mValues = new String[0];
        this.mValueCount = 0;
    }

    public IndexAxisValueFormatter(String[] values) {
        this.mValues = new String[0];
        this.mValueCount = 0;
        if (values != null) {
            setValues(values);
        }
    }

    public IndexAxisValueFormatter(Collection<String> values) {
        this.mValues = new String[0];
        this.mValueCount = 0;
        if (values != null) {
            setValues((String[]) values.toArray(new String[values.size()]));
        }
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float value, AxisBase axis) {
        int index = Math.round(value);
        if (index < 0 || index >= this.mValueCount || index != ((int) value)) {
            return "";
        }
        return this.mValues[index];
    }

    public String[] getValues() {
        return this.mValues;
    }

    public void setValues(String[] values) {
        if (values == null) {
            values = new String[0];
        }
        this.mValues = values;
        this.mValueCount = values.length;
    }
}
