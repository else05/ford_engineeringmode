package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class Approximator {
    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] points, float tolerance) {
        float greatestDistance = 0.0f;
        Line line = new Line(points[0], points[1], points[points.length - 2], points[points.length - 1]);
        int greatestIndex = 0;
        for (int greatestIndex2 = 2; greatestIndex2 < points.length - 2; greatestIndex2 += 2) {
            float distance = line.distance(points[greatestIndex2], points[greatestIndex2 + 1]);
            if (distance > greatestDistance) {
                greatestDistance = distance;
                greatestIndex = greatestIndex2;
            }
        }
        int i = (greatestDistance > tolerance ? 1 : (greatestDistance == tolerance ? 0 : -1));
        if (i > 0) {
            float[] reduced1 = reduceWithDouglasPeucker(Arrays.copyOfRange(points, 0, greatestIndex + 2), tolerance);
            float[] reduced2 = reduceWithDouglasPeucker(Arrays.copyOfRange(points, greatestIndex, points.length), tolerance);
            float[] result2 = Arrays.copyOfRange(reduced2, 2, reduced2.length);
            return concat(reduced1, result2);
        }
        float[] reduced12 = line.getPoints();
        return reduced12;
    }

    float[] concat(float[]... arrays) {
        int length = 0;
        for (float[] array : arrays) {
            length += array.length;
        }
        float[] result = new float[length];
        int length2 = arrays.length;
        int pos = 0;
        int pos2 = 0;
        while (pos2 < length2) {
            float[] array2 = arrays[pos2];
            int pos3 = pos;
            for (float element : array2) {
                result[pos3] = element;
                pos3++;
            }
            pos2++;
            pos = pos3;
        }
        return result;
    }

    /* loaded from: classes2.dex */
    private class Line {
        private float dx;
        private float dy;
        private float exsy;
        private float length;
        private float[] points;
        private float sxey;

        public Line(float x1, float y1, float x2, float y2) {
            this.dx = x1 - x2;
            this.dy = y1 - y2;
            this.sxey = x1 * y2;
            this.exsy = x2 * y1;
            this.length = (float) Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
            this.points = new float[]{x1, y1, x2, y2};
        }

        public float distance(float x, float y) {
            return Math.abs((((this.dy * x) - (this.dx * y)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }
}
