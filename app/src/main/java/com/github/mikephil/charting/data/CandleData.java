package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import java.util.List;

/* loaded from: classes2.dex */
public class CandleData extends BarLineScatterCandleBubbleData<ICandleDataSet> {
    public CandleData() {
    }

    public CandleData(List<ICandleDataSet> dataSets) {
        super(dataSets);
    }

    public CandleData(ICandleDataSet... dataSets) {
        super(dataSets);
    }
}
