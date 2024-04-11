package com.melody.merlin.jet.utils;

public class JetUtils {

    public static final float PI = 3.1415927f;

    public static final float radiansToDegrees = 180f / PI;

    public static final float radDeg = radiansToDegrees;

    public static final float degreesToRadians = PI / 180;

    public static final float degRad = degreesToRadians;

    public static float cosDeg(float angle) {
        return (float) Math.cos(angle * degRad);
    }

    public static float sinDeg(float angle) {
        return (float) Math.sin(angle * degRad);
    }

    /** @param target After the first and before the last value.
     * @return index of first value greater than the target. */
    public static int binarySearch (float[] values, float target, int step) {
        int low = 0;
        int high = values.length / step - 2;
        if (high == 0) return step;
        int current = high >>> 1;
        while (true) {
            if (values[(current + 1) * step] <= target)
                low = current + 1;
            else
                high = current;
            if (low == high) return (low + 1) * step;
            current = (low + high) >>> 1;
        }
    }

    /** @param target After the first and before the last value.
     * @return index of first value greater than the target. */
    public static int binarySearch (float[] values, float target) {
        int low = 0;
        int high = values.length - 2;
        if (high == 0) return 1;
        int current = high >>> 1;
        while (true) {
            if (values[current + 1] <= target)
                low = current + 1;
            else
                high = current;
            if (low == high) return low + 1;
            current = (low + high) >>> 1;
        }
    }

    public static int linearSearch (float[] values, float target, int step) {
        for (int i = 0, last = values.length - step; i <= last; i += step)
            if (values[i] > target) return i;
        return -1;
    }

}
