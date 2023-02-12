package com.yfve.tools;

/* loaded from: classes1.dex */
public class BeanTestSetting {
    private boolean mbIsChecked;
    private int miResult;
    private String mstrContent;
    private String mstrTitle;

    public BeanTestSetting(String mstrTitle, String mstrContent, int miResult, boolean mbIsChecked) {
        this.mstrTitle = "";
        this.mstrContent = "";
        this.mstrTitle = mstrTitle;
        this.mstrContent = mstrContent;
        this.miResult = miResult;
        this.mbIsChecked = mbIsChecked;
    }

    public boolean isMbIsChecked() {
        return this.mbIsChecked;
    }

    public void setMbIsChecked(boolean mbIsChecked) {
        this.mbIsChecked = mbIsChecked;
    }

    public String getMstrTitle() {
        return this.mstrTitle;
    }

    public void setMstrTitle(String mstrTitle) {
        this.mstrTitle = mstrTitle;
    }

    public String getMstrContent() {
        return this.mstrContent;
    }

    public void setMstrContent(String mstrContent) {
        this.mstrContent = mstrContent;
    }

    public int getMiResult() {
        return this.miResult;
    }

    public void setMiResult(int miResult) {
        this.miResult = miResult;
    }

    public String toString() {
        return "BeanTestSetting{mstrTitle='" + this.mstrTitle + "', mstrContent='" + this.mstrContent + "', miResult=" + this.miResult + ", mbIsChecked=" + this.mbIsChecked + '}';
    }
}
