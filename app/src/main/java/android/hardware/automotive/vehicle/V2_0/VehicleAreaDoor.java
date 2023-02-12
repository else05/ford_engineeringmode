package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAreaDoor {
    public static final int HOOD = 268435456;
    public static final int REAR = 536870912;
    public static final int ROW_1_LEFT = 1;
    public static final int ROW_1_RIGHT = 4;
    public static final int ROW_2_LEFT = 16;
    public static final int ROW_2_RIGHT = 64;
    public static final int ROW_3_LEFT = 256;
    public static final int ROW_3_RIGHT = 1024;

    public static final String toString(int o) {
        if (o == 1) {
            return "ROW_1_LEFT";
        }
        if (o == 4) {
            return "ROW_1_RIGHT";
        }
        if (o == 16) {
            return "ROW_2_LEFT";
        }
        if (o == 64) {
            return "ROW_2_RIGHT";
        }
        if (o == 256) {
            return "ROW_3_LEFT";
        }
        if (o == 1024) {
            return "ROW_3_RIGHT";
        }
        if (o == 268435456) {
            return "HOOD";
        }
        if (o == 536870912) {
            return "REAR";
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
        if ((o & 4) == 4) {
            list.add("ROW_1_RIGHT");
            flipped |= 4;
        }
        if ((o & 16) == 16) {
            list.add("ROW_2_LEFT");
            flipped |= 16;
        }
        if ((o & 64) == 64) {
            list.add("ROW_2_RIGHT");
            flipped |= 64;
        }
        if ((o & 256) == 256) {
            list.add("ROW_3_LEFT");
            flipped |= 256;
        }
        if ((o & 1024) == 1024) {
            list.add("ROW_3_RIGHT");
            flipped |= 1024;
        }
        if ((o & 268435456) == 268435456) {
            list.add("HOOD");
            flipped |= 268435456;
        }
        if ((o & 536870912) == 536870912) {
            list.add("REAR");
            flipped |= 536870912;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
