package com.yfve.tools;

/* loaded from: classes1.dex */
public class BeanTitleContent {
    private String mstrContent;
    private String mstrTitle;

    public BeanTitleContent() {
        this.mstrTitle = "";
        this.mstrContent = "";
    }

    public BeanTitleContent(String strTitle, String strContent) {
        this.mstrTitle = "";
        this.mstrContent = "";
        this.mstrTitle = strTitle;
        this.mstrContent = strContent;
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

    public String toString() {
        return "BeanTitleContent{mstrTitle='" + this.mstrTitle + "', mstrContent='" + this.mstrContent + "'}";
    }
}
