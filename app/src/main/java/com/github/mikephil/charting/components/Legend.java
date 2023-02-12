package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    /* loaded from: classes2.dex */
    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    /* loaded from: classes2.dex */
    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    /* loaded from: classes2.dex */
    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    /* loaded from: classes2.dex */
    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    /* loaded from: classes2.dex */
    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] entries) {
        this();
        if (entries == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = entries;
    }

    @Deprecated
    public Legend(int[] colors, String[] labels) {
        this();
        if (colors == null || labels == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }
        if (colors.length != labels.length) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        List<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < Math.min(colors.length, labels.length); i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = labels[i];
            if (entry.formColor == 1122868) {
                entry.form = LegendForm.NONE;
            } else if (entry.formColor == 1122867 || entry.formColor == 0) {
                entry.form = LegendForm.EMPTY;
            }
            entries.add(entry);
        }
        int i2 = entries.size();
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[i2]);
    }

    @Deprecated
    public Legend(List<Integer> colors, List<String> labels) {
        this(Utils.convertIntegers(colors), Utils.convertStrings(labels));
    }

    public void setEntries(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint p) {
        LegendEntry[] legendEntryArr;
        float max = 0.0f;
        float maxFormSize = 0.0f;
        float formToTextSpace = Utils.convertDpToPixel(this.mFormToTextSpace);
        for (LegendEntry entry : this.mEntries) {
            float formSize = Utils.convertDpToPixel(Float.isNaN(entry.formSize) ? this.mFormSize : entry.formSize);
            if (formSize > maxFormSize) {
                maxFormSize = formSize;
            }
            String label = entry.label;
            if (label != null) {
                float length = Utils.calcTextWidth(p, label);
                if (length > max) {
                    max = length;
                }
            }
        }
        return max + maxFormSize + formToTextSpace;
    }

    public float getMaximumEntryHeight(Paint p) {
        LegendEntry[] legendEntryArr;
        float max = 0.0f;
        for (LegendEntry entry : this.mEntries) {
            String label = entry.label;
            if (label != null) {
                float length = Utils.calcTextHeight(p, label);
                if (length > max) {
                    max = length;
                }
            }
        }
        return max;
    }

    @Deprecated
    public int[] getColors() {
        int[] old = new int[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            old[i] = this.mEntries[i].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : this.mEntries[i].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : this.mEntries[i].formColor;
        }
        return old;
    }

    @Deprecated
    public String[] getLabels() {
        String[] old = new String[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            old[i] = this.mEntries[i].label;
        }
        return old;
    }

    @Deprecated
    public int[] getExtraColors() {
        int[] old = new int[this.mExtraEntries.length];
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            old[i] = this.mExtraEntries[i].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : this.mExtraEntries[i].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : this.mExtraEntries[i].formColor;
        }
        return old;
    }

    @Deprecated
    public String[] getExtraLabels() {
        String[] old = new String[this.mExtraEntries.length];
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            old[i] = this.mExtraEntries[i].label;
        }
        return old;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> entries) {
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
    }

    public void setExtra(LegendEntry[] entries) {
        if (entries == null) {
            entries = new LegendEntry[0];
        }
        this.mExtraEntries = entries;
    }

    @Deprecated
    public void setExtra(List<Integer> colors, List<String> labels) {
        setExtra(Utils.convertIntegers(colors), Utils.convertStrings(labels));
    }

    public void setExtra(int[] colors, String[] labels) {
        List<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < Math.min(colors.length, labels.length); i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = labels[i];
            if (entry.formColor == 1122868 || entry.formColor == 0) {
                entry.form = LegendForm.NONE;
            } else if (entry.formColor == 1122867) {
                entry.form = LegendForm.EMPTY;
            }
            entries.add(entry);
        }
        int i2 = entries.size();
        this.mExtraEntries = (LegendEntry[]) entries.toArray(new LegendEntry[i2]);
    }

    public void setCustom(LegendEntry[] entries) {
        this.mEntries = entries;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> entries) {
        this.mEntries = (LegendEntry[]) entries.toArray(new LegendEntry[entries.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    @Deprecated
    public LegendPosition getPosition() {
        if (this.mOrientation == LegendOrientation.VERTICAL && this.mHorizontalAlignment == LegendHorizontalAlignment.CENTER && this.mVerticalAlignment == LegendVerticalAlignment.CENTER) {
            return LegendPosition.PIECHART_CENTER;
        }
        return this.mOrientation == LegendOrientation.HORIZONTAL ? this.mVerticalAlignment == LegendVerticalAlignment.TOP ? this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? LegendPosition.ABOVE_CHART_LEFT : this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER : this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? LegendPosition.BELOW_CHART_LEFT : this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER : this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT ? (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.LEFT_OF_CHART_INSIDE : this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART : (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.RIGHT_OF_CHART_INSIDE : this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART;
    }

    @Deprecated
    public void setPosition(LegendPosition newValue) {
        switch (newValue) {
            case LEFT_OF_CHART:
            case LEFT_OF_CHART_INSIDE:
            case LEFT_OF_CHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
                this.mVerticalAlignment = newValue == LegendPosition.LEFT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case RIGHT_OF_CHART:
            case RIGHT_OF_CHART_INSIDE:
            case RIGHT_OF_CHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
                this.mVerticalAlignment = newValue == LegendPosition.RIGHT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case ABOVE_CHART_LEFT:
            case ABOVE_CHART_CENTER:
            case ABOVE_CHART_RIGHT:
                this.mHorizontalAlignment = newValue == LegendPosition.ABOVE_CHART_LEFT ? LegendHorizontalAlignment.LEFT : newValue == LegendPosition.ABOVE_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case BELOW_CHART_LEFT:
            case BELOW_CHART_CENTER:
            case BELOW_CHART_RIGHT:
                this.mHorizontalAlignment = newValue == LegendPosition.BELOW_CHART_LEFT ? LegendHorizontalAlignment.LEFT : newValue == LegendPosition.BELOW_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case PIECHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.CENTER;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
        }
        this.mDrawInside = newValue == LegendPosition.LEFT_OF_CHART_INSIDE || newValue == LegendPosition.RIGHT_OF_CHART_INSIDE;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment value) {
        this.mHorizontalAlignment = value;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment value) {
        this.mVerticalAlignment = value;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation value) {
        this.mOrientation = value;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean value) {
        this.mDrawInside = value;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection pos) {
        this.mDirection = pos;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm shape) {
        this.mShape = shape;
    }

    public void setFormSize(float size) {
        this.mFormSize = size;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float size) {
        this.mFormLineWidth = size;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float space) {
        this.mXEntrySpace = space;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float space) {
        this.mYEntrySpace = space;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float space) {
        this.mFormToTextSpace = space;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float space) {
        this.mStackSpace = space;
    }

    public void setWordWrapEnabled(boolean enabled) {
        this.mWordWrapEnabled = enabled;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float maxSize) {
        this.mMaxSizePercent = maxSize;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0165  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void calculateDimensions(android.graphics.Paint r33, com.github.mikephil.charting.utils.ViewPortHandler r34) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.components.Legend.calculateDimensions(android.graphics.Paint, com.github.mikephil.charting.utils.ViewPortHandler):void");
    }
}
