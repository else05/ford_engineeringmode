package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Wheel {
    public static final int LEFT_FRONT = 1;
    public static final int LEFT_REAR = 4;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_REAR = 8;
    public static final int UNKNOWN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNKNOWN";
        }
        if (o == 1) {
            return "LEFT_FRONT";
        }
        if (o == 2) {
            return "RIGHT_FRONT";
        }
        if (o == 4) {
            return "LEFT_REAR";
        }
        if (o == 8) {
            return "RIGHT_REAR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNKNOWN");
        if ((o & 1) == 1) {
            list.add("LEFT_FRONT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("RIGHT_FRONT");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("LEFT_REAR");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("RIGHT_REAR");
            flipped |= 8;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
