package com.github.mikephil.charting.utils;

import android.car.hardware.LocationProtocal;
import android.content.res.Resources;
import android.graphics.Color;
import com.yfve.engineeringmode.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ColorTemplate {
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187), Color.rgb((int) R.styleable.AppCompatTheme_windowNoTitle, 174, 175), Color.rgb(42, (int) R.styleable.AppCompatTheme_windowActionBar, 130)};
    public static final int[] JOYFUL_COLORS = {Color.rgb(217, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120), Color.rgb((int) R.styleable.AppCompatTheme_toolbarStyle, 167, 134), Color.rgb(53, 194, 209)};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162), Color.rgb(191, 134, 134), Color.rgb((int) LocationProtocal.longitudeDIMax, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, (int) R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, 0), Color.rgb(245, 199, 0), Color.rgb((int) R.styleable.AppCompatTheme_toolbarStyle, 150, 31), Color.rgb((int) LocationProtocal.longitudeDIMax, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};

    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 255;
        int g = (color >> 8) & 255;
        int b = (color >> 0) & 255;
        return Color.rgb(r, g, b);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, 181, 229);
    }

    public static int colorWithAlpha(int color, int alpha) {
        return (16777215 & color) | ((alpha & 255) << 24);
    }

    public static List<Integer> createColors(Resources r, int[] colors) {
        List<Integer> result = new ArrayList<>();
        for (int i : colors) {
            result.add(Integer.valueOf(r.getColor(i)));
        }
        return result;
    }

    public static List<Integer> createColors(int[] colors) {
        List<Integer> result = new ArrayList<>();
        for (int i : colors) {
            result.add(Integer.valueOf(i));
        }
        return result;
    }
}
