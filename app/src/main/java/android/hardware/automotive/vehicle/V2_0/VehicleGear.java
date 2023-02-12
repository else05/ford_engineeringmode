package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleGear {
    public static final int GEAR_1 = 16;
    public static final int GEAR_2 = 32;
    public static final int GEAR_3 = 64;
    public static final int GEAR_4 = 128;
    public static final int GEAR_5 = 256;
    public static final int GEAR_6 = 512;
    public static final int GEAR_7 = 1024;
    public static final int GEAR_8 = 2048;
    public static final int GEAR_9 = 4096;
    public static final int GEAR_DRIVE = 8;
    public static final int GEAR_LOW = 16;
    public static final int GEAR_NEUTRAL = 1;
    public static final int GEAR_PARK = 4;
    public static final int GEAR_REVERSE = 2;

    public static final String toString(int o) {
        if (o == 1) {
            return "GEAR_NEUTRAL";
        }
        if (o == 2) {
            return "GEAR_REVERSE";
        }
        if (o == 4) {
            return "GEAR_PARK";
        }
        if (o == 8) {
            return "GEAR_DRIVE";
        }
        if (o == 16) {
            return "GEAR_LOW";
        }
        if (o == 16) {
            return "GEAR_1";
        }
        if (o == 32) {
            return "GEAR_2";
        }
        if (o == 64) {
            return "GEAR_3";
        }
        if (o == 128) {
            return "GEAR_4";
        }
        if (o == 256) {
            return "GEAR_5";
        }
        if (o == 512) {
            return "GEAR_6";
        }
        if (o == 1024) {
            return "GEAR_7";
        }
        if (o == 2048) {
            return "GEAR_8";
        }
        if (o == 4096) {
            return "GEAR_9";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("GEAR_NEUTRAL");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("GEAR_REVERSE");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("GEAR_PARK");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("GEAR_DRIVE");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("GEAR_LOW");
            flipped |= 16;
        }
        if ((o & 16) == 16) {
            list.add("GEAR_1");
            flipped |= 16;
        }
        if ((o & 32) == 32) {
            list.add("GEAR_2");
            flipped |= 32;
        }
        if ((o & 64) == 64) {
            list.add("GEAR_3");
            flipped |= 64;
        }
        if ((o & 128) == 128) {
            list.add("GEAR_4");
            flipped |= 128;
        }
        if ((o & 256) == 256) {
            list.add("GEAR_5");
            flipped |= 256;
        }
        if ((o & 512) == 512) {
            list.add("GEAR_6");
            flipped |= 512;
        }
        if ((o & 1024) == 1024) {
            list.add("GEAR_7");
            flipped |= 1024;
        }
        if ((o & 2048) == 2048) {
            list.add("GEAR_8");
            flipped |= 2048;
        }
        if ((o & 4096) == 4096) {
            list.add("GEAR_9");
            flipped |= 4096;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
