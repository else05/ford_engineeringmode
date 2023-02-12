package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAreaZone {
    public static final int ROW_1 = 8;
    public static final int ROW_1_CENTER = 2;
    public static final int ROW_1_LEFT = 1;
    public static final int ROW_1_RIGHT = 4;
    public static final int ROW_2 = 128;
    public static final int ROW_2_CENTER = 32;
    public static final int ROW_2_LEFT = 16;
    public static final int ROW_2_RIGHT = 64;
    public static final int ROW_3 = 2048;
    public static final int ROW_3_CENTER = 512;
    public static final int ROW_3_LEFT = 256;
    public static final int ROW_3_RIGHT = 1024;
    public static final int ROW_4 = 32768;
    public static final int ROW_4_CENTER = 8192;
    public static final int ROW_4_LEFT = 4096;
    public static final int ROW_4_RIGHT = 16384;
    public static final int WHOLE_CABIN = Integer.MIN_VALUE;

    public static final String toString(int o) {
        if (o == 1) {
            return "ROW_1_LEFT";
        }
        if (o == 2) {
            return "ROW_1_CENTER";
        }
        if (o == 4) {
            return "ROW_1_RIGHT";
        }
        if (o == 8) {
            return "ROW_1";
        }
        if (o == 16) {
            return "ROW_2_LEFT";
        }
        if (o == 32) {
            return "ROW_2_CENTER";
        }
        if (o == 64) {
            return "ROW_2_RIGHT";
        }
        if (o == 128) {
            return "ROW_2";
        }
        if (o == 256) {
            return "ROW_3_LEFT";
        }
        if (o == 512) {
            return "ROW_3_CENTER";
        }
        if (o == 1024) {
            return "ROW_3_RIGHT";
        }
        if (o == 2048) {
            return "ROW_3";
        }
        if (o == 4096) {
            return "ROW_4_LEFT";
        }
        if (o == 8192) {
            return "ROW_4_CENTER";
        }
        if (o == 16384) {
            return "ROW_4_RIGHT";
        }
        if (o == 32768) {
            return "ROW_4";
        }
        if (o == Integer.MIN_VALUE) {
            return "WHOLE_CABIN";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("ROW_1_LEFT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("ROW_1_CENTER");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("ROW_1_RIGHT");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("ROW_1");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("ROW_2_LEFT");
            flipped |= 16;
        }
        if ((o & 32) == 32) {
            list.add("ROW_2_CENTER");
            flipped |= 32;
        }
        if ((o & 64) == 64) {
            list.add("ROW_2_RIGHT");
            flipped |= 64;
        }
        if ((o & 128) == 128) {
            list.add("ROW_2");
            flipped |= 128;
        }
        if ((o & 256) == 256) {
            list.add("ROW_3_LEFT");
            flipped |= 256;
        }
        if ((o & 512) == 512) {
            list.add("ROW_3_CENTER");
            flipped |= 512;
        }
        if ((o & 1024) == 1024) {
            list.add("ROW_3_RIGHT");
            flipped |= 1024;
        }
        if ((o & 2048) == 2048) {
            list.add("ROW_3");
            flipped |= 2048;
        }
        if ((o & 4096) == 4096) {
            list.add("ROW_4_LEFT");
            flipped |= 4096;
        }
        if ((o & 8192) == 8192) {
            list.add("ROW_4_CENTER");
            flipped |= 8192;
        }
        if ((o & 16384) == 16384) {
            list.add("ROW_4_RIGHT");
            flipped |= 16384;
        }
        if ((32768 & o) == 32768) {
            list.add("ROW_4");
            flipped |= 32768;
        }
        if ((Integer.MIN_VALUE & o) == Integer.MIN_VALUE) {
            list.add("WHOLE_CABIN");
            flipped |= Integer.MIN_VALUE;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
