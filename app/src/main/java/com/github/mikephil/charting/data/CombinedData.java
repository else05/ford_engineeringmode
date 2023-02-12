package com.github.mikephil.charting.data;

import android.util.Log;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CombinedData extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends Entry>> {
    private BarData mBarData;
    private BubbleData mBubbleData;
    private CandleData mCandleData;
    private LineData mLineData;
    private ScatterData mScatterData;

    public void setData(LineData data) {
        this.mLineData = data;
        notifyDataChanged();
    }

    public void setData(BarData data) {
        this.mBarData = data;
        notifyDataChanged();
    }

    public void setData(ScatterData data) {
        this.mScatterData = data;
        notifyDataChanged();
    }

    public void setData(CandleData data) {
        this.mCandleData = data;
        notifyDataChanged();
    }

    public void setData(BubbleData data) {
        this.mBubbleData = data;
        notifyDataChanged();
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public void calcMinMax() {
        if (this.mDataSets == null) {
            this.mDataSets = new ArrayList();
        }
        this.mDataSets.clear();
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        List<BarLineScatterCandleBubbleData> allData = getAllData();
        for (ChartData data : allData) {
            data.calcMinMax();
            List<IBarLineScatterCandleBubbleDataSet<? extends Entry>> sets = data.getDataSets();
            this.mDataSets.addAll(sets);
            if (data.getYMax() > this.mYMax) {
                this.mYMax = data.getYMax();
            }
            if (data.getYMin() < this.mYMin) {
                this.mYMin = data.getYMin();
            }
            if (data.getXMax() > this.mXMax) {
                this.mXMax = data.getXMax();
            }
            if (data.getXMin() < this.mXMin) {
                this.mXMin = data.getXMin();
            }
            if (data.mLeftAxisMax > this.mLeftAxisMax) {
                this.mLeftAxisMax = data.mLeftAxisMax;
            }
            if (data.mLeftAxisMin < this.mLeftAxisMin) {
                this.mLeftAxisMin = data.mLeftAxisMin;
            }
            if (data.mRightAxisMax > this.mRightAxisMax) {
                this.mRightAxisMax = data.mRightAxisMax;
            }
            if (data.mRightAxisMin < this.mRightAxisMin) {
                this.mRightAxisMin = data.mRightAxisMin;
            }
        }
    }

    public BubbleData getBubbleData() {
        return this.mBubbleData;
    }

    public LineData getLineData() {
        return this.mLineData;
    }

    public BarData getBarData() {
        return this.mBarData;
    }

    public ScatterData getScatterData() {
        return this.mScatterData;
    }

    public CandleData getCandleData() {
        return this.mCandleData;
    }

    public List<BarLineScatterCandleBubbleData> getAllData() {
        List<BarLineScatterCandleBubbleData> data = new ArrayList<>();
        if (this.mLineData != null) {
            data.add(this.mLineData);
        }
        if (this.mBarData != null) {
            data.add(this.mBarData);
        }
        if (this.mScatterData != null) {
            data.add(this.mScatterData);
        }
        if (this.mCandleData != null) {
            data.add(this.mCandleData);
        }
        if (this.mBubbleData != null) {
            data.add(this.mBubbleData);
        }
        return data;
    }

    public BarLineScatterCandleBubbleData getDataByIndex(int index) {
        return getAllData().get(index);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public void notifyDataChanged() {
        if (this.mLineData != null) {
            this.mLineData.notifyDataChanged();
        }
        if (this.mBarData != null) {
            this.mBarData.notifyDataChanged();
        }
        if (this.mCandleData != null) {
            this.mCandleData.notifyDataChanged();
        }
        if (this.mScatterData != null) {
            this.mScatterData.notifyDataChanged();
        }
        if (this.mBubbleData != null) {
            this.mBubbleData.notifyDataChanged();
        }
        calcMinMax();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    @Override // com.github.mikephil.charting.data.ChartData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.github.mikephil.charting.data.Entry getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight r9) {
        /*
            r8 = this;
            java.util.List r0 = r8.getAllData()
            int r1 = r9.getDataIndex()
            int r2 = r0.size()
            r3 = 0
            if (r1 < r2) goto L10
            return r3
        L10:
            int r1 = r9.getDataIndex()
            java.lang.Object r1 = r0.get(r1)
            com.github.mikephil.charting.data.ChartData r1 = (com.github.mikephil.charting.data.ChartData) r1
            int r2 = r9.getDataSetIndex()
            int r4 = r1.getDataSetCount()
            if (r2 < r4) goto L25
            return r3
        L25:
            int r2 = r9.getDataSetIndex()
            com.github.mikephil.charting.interfaces.datasets.IDataSet r2 = r1.getDataSetByIndex(r2)
            float r4 = r9.getX()
            java.util.List r2 = r2.getEntriesForXValue(r4)
            java.util.Iterator r4 = r2.iterator()
        L39:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L5e
            java.lang.Object r5 = r4.next()
            com.github.mikephil.charting.data.Entry r5 = (com.github.mikephil.charting.data.Entry) r5
            float r6 = r5.getY()
            float r7 = r9.getY()
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 == 0) goto L5d
            float r6 = r9.getY()
            boolean r6 = java.lang.Float.isNaN(r6)
            if (r6 == 0) goto L5c
            goto L5d
        L5c:
            goto L39
        L5d:
            return r5
        L5e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.data.CombinedData.getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight):com.github.mikephil.charting.data.Entry");
    }

    public int getDataIndex(ChartData data) {
        return getAllData().indexOf(data);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public boolean removeDataSet(IBarLineScatterCandleBubbleDataSet<? extends Entry> d) {
        List<BarLineScatterCandleBubbleData> datas = getAllData();
        boolean success = false;
        for (ChartData data : datas) {
            success = data.removeDataSet((ChartData) d);
            if (success) {
                break;
            }
        }
        return success;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeDataSet(int index) {
        Log.e(Chart.LOG_TAG, "removeDataSet(int index) not supported for CombinedData");
        return false;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeEntry(Entry e, int dataSetIndex) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeEntry(float xValue, int dataSetIndex) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }
}
