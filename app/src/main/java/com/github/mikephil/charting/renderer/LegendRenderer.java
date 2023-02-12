package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries;
    protected Paint.FontMetrics legendFontMetrics;
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.computedEntries = new ArrayList(16);
        this.legendFontMetrics = new Paint.FontMetrics();
        this.mLineFormPath = new Path();
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Paint.Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX WARN: Type inference failed for: r4v6, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    public void computeLegend(ChartData<?> data) {
        ChartData<?> chartData;
        ChartData<?> chartData2 = data;
        if (!this.mLegend.isLegendCustom()) {
            this.computedEntries.clear();
            int i = 0;
            while (i < data.getDataSetCount()) {
                ?? dataSetByIndex = chartData2.getDataSetByIndex(i);
                List<Integer> clrs = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                if ((dataSetByIndex instanceof IBarDataSet) && ((IBarDataSet) dataSetByIndex).isStacked()) {
                    IBarDataSet bds = (IBarDataSet) dataSetByIndex;
                    String[] sLabels = bds.getStackLabels();
                    for (int j = 0; j < clrs.size() && j < bds.getStackSize(); j++) {
                        this.computedEntries.add(new LegendEntry(sLabels[j % sLabels.length], dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), clrs.get(j).intValue()));
                    }
                    if (bds.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                    chartData = chartData2;
                } else {
                    if (dataSetByIndex instanceof IPieDataSet) {
                        IPieDataSet pds = (IPieDataSet) dataSetByIndex;
                        for (int j2 = 0; j2 < clrs.size() && j2 < entryCount; j2++) {
                            this.computedEntries.add(new LegendEntry(pds.getEntryForIndex(j2).getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), clrs.get(j2).intValue()));
                        }
                        if (pds.getLabel() != null) {
                            this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                        }
                    } else if (!(dataSetByIndex instanceof ICandleDataSet) || ((ICandleDataSet) dataSetByIndex).getDecreasingColor() == 1122867) {
                        int j3 = 0;
                        while (j3 < clrs.size() && j3 < entryCount) {
                            String label = (j3 >= clrs.size() + (-1) || j3 >= entryCount + (-1)) ? data.getDataSetByIndex(i).getLabel() : null;
                            this.computedEntries.add(new LegendEntry(label, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), clrs.get(j3).intValue()));
                            j3++;
                        }
                    } else {
                        int decreasingColor = ((ICandleDataSet) dataSetByIndex).getDecreasingColor();
                        int increasingColor = ((ICandleDataSet) dataSetByIndex).getIncreasingColor();
                        this.computedEntries.add(new LegendEntry(null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), decreasingColor));
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), increasingColor));
                    }
                    chartData = data;
                }
                i++;
                chartData2 = chartData;
            }
            if (this.mLegend.getExtraEntries() != null) {
                Collections.addAll(this.computedEntries, this.mLegend.getExtraEntries());
            }
            this.mLegend.setEntries(this.computedEntries);
        }
        Typeface tf = this.mLegend.getTypeface();
        if (tf != null) {
            this.mLegendLabelPaint.setTypeface(tf);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    public void renderLegend(Canvas c) {
        float labelLineSpacing;
        float formToTextSpace;
        float xEntrySpace;
        float originPosX;
        float originPosX2;
        float originPosX3;
        double d;
        float posY;
        float posX;
        Legend.LegendHorizontalAlignment horizontalAlignment;
        LegendEntry e;
        Legend.LegendHorizontalAlignment horizontalAlignment2;
        List<FSize> calculatedLineSizes;
        List<Boolean> calculatedLabelBreakPoints;
        Canvas canvas;
        float formToTextSpace2;
        float xEntrySpace2;
        float f;
        float f2;
        float xoffset;
        Legend.LegendHorizontalAlignment horizontalAlignment3;
        Legend.LegendOrientation orientation;
        float formYOffset;
        LegendEntry[] entries;
        float formYOffset2;
        Legend.LegendDirection direction;
        float formToTextSpace3;
        float f3;
        float posX2;
        float posY2;
        if (!this.mLegend.isEnabled()) {
            return;
        }
        Typeface tf = this.mLegend.getTypeface();
        if (tf != null) {
            this.mLegendLabelPaint.setTypeface(tf);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        float labelLineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
        float labelLineSpacing2 = Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics) + Utils.convertDpToPixel(this.mLegend.getYEntrySpace());
        float stackSpace = labelLineHeight - (Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0f);
        LegendEntry[] entries2 = this.mLegend.getEntries();
        float formToTextSpace4 = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
        float xEntrySpace3 = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
        Legend.LegendOrientation orientation2 = this.mLegend.getOrientation();
        Legend.LegendHorizontalAlignment horizontalAlignment4 = this.mLegend.getHorizontalAlignment();
        Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
        Legend.LegendDirection direction2 = this.mLegend.getDirection();
        float defaultFormSize = Utils.convertDpToPixel(this.mLegend.getFormSize());
        float stackSpace2 = Utils.convertDpToPixel(this.mLegend.getStackSpace());
        float yoffset = this.mLegend.getYOffset();
        float xoffset2 = this.mLegend.getXOffset();
        switch (horizontalAlignment4) {
            case LEFT:
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX = xoffset2;
                } else {
                    originPosX = this.mViewPortHandler.contentLeft() + xoffset2;
                }
                if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                    originPosX += this.mLegend.mNeededWidth;
                }
                originPosX2 = originPosX;
                break;
            case RIGHT:
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX = this.mViewPortHandler.getChartWidth() - xoffset2;
                } else {
                    originPosX = this.mViewPortHandler.contentRight() - xoffset2;
                }
                if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                    originPosX -= this.mLegend.mNeededWidth;
                }
                originPosX2 = originPosX;
                break;
            case CENTER:
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    originPosX3 = this.mViewPortHandler.getChartWidth() / 2.0f;
                } else {
                    originPosX3 = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                }
                float originPosX4 = (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT ? xoffset2 : -xoffset2) + originPosX3;
                if (orientation2 == Legend.LegendOrientation.VERTICAL) {
                    labelLineSpacing = labelLineSpacing2;
                    double d2 = originPosX4;
                    if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        formToTextSpace = formToTextSpace4;
                        xEntrySpace = xEntrySpace3;
                        d = ((-this.mLegend.mNeededWidth) / 2.0d) + xoffset2;
                    } else {
                        formToTextSpace = formToTextSpace4;
                        xEntrySpace = xEntrySpace3;
                        d = (this.mLegend.mNeededWidth / 2.0d) - xoffset2;
                    }
                    originPosX = (float) (d2 + d);
                    originPosX2 = originPosX;
                    break;
                } else {
                    labelLineSpacing = labelLineSpacing2;
                    formToTextSpace = formToTextSpace4;
                    xEntrySpace = xEntrySpace3;
                    originPosX2 = originPosX4;
                    break;
                }
            default:
                originPosX2 = 0.0f;
                labelLineSpacing = labelLineSpacing2;
                formToTextSpace = formToTextSpace4;
                xEntrySpace = xEntrySpace3;
                break;
        }
        switch (orientation2) {
            case HORIZONTAL:
                Legend.LegendHorizontalAlignment horizontalAlignment5 = horizontalAlignment4;
                float formToTextSpace5 = formToTextSpace;
                Canvas canvas2 = c;
                List<FSize> calculatedLineSizes2 = this.mLegend.getCalculatedLineSizes();
                List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
                List<Boolean> calculatedLabelBreakPoints2 = this.mLegend.getCalculatedLabelBreakPoints();
                float posX3 = originPosX2;
                float posY3 = 0.0f;
                switch (verticalAlignment) {
                    case TOP:
                        posY3 = yoffset;
                        break;
                    case BOTTOM:
                        posY3 = (this.mViewPortHandler.getChartHeight() - yoffset) - this.mLegend.mNeededHeight;
                        break;
                    case CENTER:
                        posY3 = ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f) + yoffset;
                        break;
                }
                int lineIndex = 0;
                int count = entries2.length;
                int i = 0;
                while (i < count) {
                    LegendEntry e2 = entries2[i];
                    float posX4 = posX3;
                    boolean drawingForm = e2.form != Legend.LegendForm.NONE;
                    float formSize = Float.isNaN(e2.formSize) ? defaultFormSize : Utils.convertDpToPixel(e2.formSize);
                    if (i >= calculatedLabelBreakPoints2.size() || !calculatedLabelBreakPoints2.get(i).booleanValue()) {
                        posY = posY3;
                        posX = posX4;
                    } else {
                        posX = originPosX2;
                        posY = posY3 + labelLineHeight + labelLineSpacing;
                    }
                    if (posX == originPosX2) {
                        horizontalAlignment = horizontalAlignment5;
                        if (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && lineIndex < calculatedLineSizes2.size()) {
                            if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f2 = calculatedLineSizes2.get(lineIndex).width;
                            } else {
                                f2 = -calculatedLineSizes2.get(lineIndex).width;
                            }
                            posX += f2 / 2.0f;
                            lineIndex++;
                        }
                    } else {
                        horizontalAlignment = horizontalAlignment5;
                    }
                    int lineIndex2 = lineIndex;
                    boolean isStacked = e2.label == null;
                    if (drawingForm) {
                        if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            posX -= formSize;
                        }
                        float posX5 = posX;
                        e = e2;
                        horizontalAlignment2 = horizontalAlignment;
                        calculatedLineSizes = calculatedLineSizes2;
                        canvas = c;
                        calculatedLabelBreakPoints = calculatedLabelBreakPoints2;
                        formToTextSpace2 = formToTextSpace5;
                        drawForm(c, posX5, posY + stackSpace, e, this.mLegend);
                        if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            posX = posX5 + formSize;
                        } else {
                            posX = posX5;
                        }
                    } else {
                        e = e2;
                        horizontalAlignment2 = horizontalAlignment;
                        calculatedLineSizes = calculatedLineSizes2;
                        calculatedLabelBreakPoints = calculatedLabelBreakPoints2;
                        canvas = c;
                        formToTextSpace2 = formToTextSpace5;
                    }
                    if (!isStacked) {
                        if (drawingForm) {
                            posX += direction2 == Legend.LegendDirection.RIGHT_TO_LEFT ? -formToTextSpace2 : formToTextSpace2;
                        }
                        if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            posX -= calculatedLabelSizes.get(i).width;
                        }
                        drawLabel(canvas, posX, posY + labelLineHeight, e.label);
                        if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            posX += calculatedLabelSizes.get(i).width;
                        }
                        if (direction2 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            xEntrySpace2 = xEntrySpace;
                            f = -xEntrySpace2;
                        } else {
                            xEntrySpace2 = xEntrySpace;
                            f = xEntrySpace2;
                        }
                    } else {
                        xEntrySpace2 = xEntrySpace;
                        f = direction2 == Legend.LegendDirection.RIGHT_TO_LEFT ? -stackSpace2 : stackSpace2;
                    }
                    posX3 = posX + f;
                    i++;
                    xEntrySpace = xEntrySpace2;
                    canvas2 = canvas;
                    formToTextSpace5 = formToTextSpace2;
                    posY3 = posY;
                    lineIndex = lineIndex2;
                    calculatedLineSizes2 = calculatedLineSizes;
                    horizontalAlignment5 = horizontalAlignment2;
                    calculatedLabelBreakPoints2 = calculatedLabelBreakPoints;
                }
                return;
            case VERTICAL:
                boolean wasStacked = false;
                float posY4 = 0.0f;
                switch (verticalAlignment) {
                    case TOP:
                        float posY5 = horizontalAlignment4 == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop();
                        posY4 = posY5 + yoffset;
                        break;
                    case BOTTOM:
                        if (horizontalAlignment4 == Legend.LegendHorizontalAlignment.CENTER) {
                            posY2 = this.mViewPortHandler.getChartHeight();
                        } else {
                            posY2 = this.mViewPortHandler.contentBottom();
                        }
                        posY4 = posY2 - (this.mLegend.mNeededHeight + yoffset);
                        break;
                    case CENTER:
                        posY4 = ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                        break;
                }
                float stack = 0.0f;
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    int i4 = entries2.length;
                    if (i3 < i4) {
                        LegendEntry e3 = entries2[i3];
                        boolean drawingForm2 = e3.form != Legend.LegendForm.NONE;
                        float formSize2 = Float.isNaN(e3.formSize) ? defaultFormSize : Utils.convertDpToPixel(e3.formSize);
                        float posX6 = originPosX2;
                        if (drawingForm2) {
                            float xoffset3 = xoffset2;
                            if (direction2 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                posX2 = posX6 + stack;
                            } else {
                                posX2 = posX6 - (formSize2 - stack);
                            }
                            float posX7 = posX2;
                            xoffset = xoffset3;
                            formYOffset = stackSpace;
                            formYOffset2 = stackSpace2;
                            entries = entries2;
                            direction = direction2;
                            horizontalAlignment3 = horizontalAlignment4;
                            orientation = orientation2;
                            drawForm(c, posX7, posY4 + stackSpace, e3, this.mLegend);
                            if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                posX6 = posX7 + formSize2;
                            } else {
                                posX6 = posX7;
                            }
                        } else {
                            xoffset = xoffset2;
                            horizontalAlignment3 = horizontalAlignment4;
                            orientation = orientation2;
                            formYOffset = stackSpace;
                            entries = entries2;
                            formYOffset2 = stackSpace2;
                            direction = direction2;
                        }
                        if (e3.label != null) {
                            if (drawingForm2 && !wasStacked) {
                                if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                    f3 = formToTextSpace;
                                    formToTextSpace3 = f3;
                                } else {
                                    formToTextSpace3 = formToTextSpace;
                                    f3 = -formToTextSpace3;
                                }
                                posX6 += f3;
                            } else {
                                formToTextSpace3 = formToTextSpace;
                                if (wasStacked) {
                                    posX6 = originPosX2;
                                }
                            }
                            if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                posX6 -= Utils.calcTextWidth(this.mLegendLabelPaint, e3.label);
                            }
                            if (!wasStacked) {
                                drawLabel(c, posX6, posY4 + labelLineHeight, e3.label);
                            } else {
                                posY4 += labelLineHeight + labelLineSpacing;
                                drawLabel(c, posX6, posY4 + labelLineHeight, e3.label);
                            }
                            posY4 += labelLineHeight + labelLineSpacing;
                            stack = 0.0f;
                        } else {
                            formToTextSpace3 = formToTextSpace;
                            stack += formSize2 + formYOffset2;
                            wasStacked = true;
                        }
                        i2 = i3 + 1;
                        formToTextSpace = formToTextSpace3;
                        stackSpace2 = formYOffset2;
                        direction2 = direction;
                        xoffset2 = xoffset;
                        stackSpace = formYOffset;
                        entries2 = entries;
                        horizontalAlignment4 = horizontalAlignment3;
                        orientation2 = orientation;
                    } else {
                        return;
                    }
                }
            default:
                return;
        }
    }

    protected void drawForm(Canvas c, float x, float y, LegendEntry entry, Legend legend) {
        if (entry.formColor == 1122868 || entry.formColor == 1122867 || entry.formColor == 0) {
            return;
        }
        int restoreCount = c.save();
        Legend.LegendForm form = entry.form;
        if (form == Legend.LegendForm.DEFAULT) {
            form = legend.getForm();
        }
        Legend.LegendForm form2 = form;
        this.mLegendFormPaint.setColor(entry.formColor);
        float formSize = Utils.convertDpToPixel(Float.isNaN(entry.formSize) ? legend.getFormSize() : entry.formSize);
        float half = formSize / 2.0f;
        switch (form2) {
            case DEFAULT:
            case CIRCLE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                c.drawCircle(x + half, y, half, this.mLegendFormPaint);
                break;
            case SQUARE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                c.drawRect(x, y - half, x + formSize, y + half, this.mLegendFormPaint);
                break;
            case LINE:
                float formLineWidth = Utils.convertDpToPixel(Float.isNaN(entry.formLineWidth) ? legend.getFormLineWidth() : entry.formLineWidth);
                DashPathEffect formLineDashEffect = entry.formLineDashEffect == null ? legend.getFormLineDashEffect() : entry.formLineDashEffect;
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                this.mLegendFormPaint.setStrokeWidth(formLineWidth);
                this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                this.mLineFormPath.reset();
                this.mLineFormPath.moveTo(x, y);
                this.mLineFormPath.lineTo(x + formSize, y);
                c.drawPath(this.mLineFormPath, this.mLegendFormPaint);
                break;
        }
        c.restoreToCount(restoreCount);
    }

    protected void drawLabel(Canvas c, float x, float y, String label) {
        c.drawText(label, x, y, this.mLegendLabelPaint);
    }
}
