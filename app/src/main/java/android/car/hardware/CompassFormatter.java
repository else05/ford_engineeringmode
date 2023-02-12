package android.car.hardware;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes2.dex */
public class CompassFormatter {
    private static final int[] sides = {0, 45, 90, 135, 180, 225, 270, 315, 360};
    private static String[] names = null;
    private static final double[] north = {Utils.DOUBLE_EPSILON, 22.49d, 337.51d, 360.0d};
    private static final double[] northeast = {22.51d, 67.49d};
    private static final double[] east = {67.51d, 112.49d};
    private static final double[] southeast = {112.51d, 157.49d};
    private static final double[] south = {157.51d, 202.49d};
    private static final double[] southwest = {202.51d, 247.49d};
    private static final double[] west = {247.51d, 292.49d};
    private static final double[] northwest = {292.51d, 337.49d};

    public static int format(float azimuth) {
        if ((azimuth >= north[0] && azimuth <= north[1]) || (azimuth >= north[2] && azimuth <= north[3])) {
            return 0;
        }
        if (azimuth >= northeast[0] && azimuth <= northeast[1]) {
            return 1;
        }
        if (azimuth >= east[0] && azimuth <= east[1]) {
            return 2;
        }
        if (azimuth >= southeast[0] && azimuth <= southeast[1]) {
            return 3;
        }
        if (azimuth >= south[0] && azimuth <= south[1]) {
            return 4;
        }
        if (azimuth >= southwest[0] && azimuth <= southwest[1]) {
            return 5;
        }
        if (azimuth >= west[0] && azimuth <= west[1]) {
            return 6;
        }
        if (azimuth >= northwest[0] && azimuth <= northwest[1]) {
            return 7;
        }
        return -1;
    }
}
