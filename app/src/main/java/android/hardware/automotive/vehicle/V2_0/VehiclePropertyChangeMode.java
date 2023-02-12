package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehiclePropertyChangeMode {
    public static final int CONTINUOUS = 2;
    public static final int ON_CHANGE = 1;
    public static final int ON_SET = 4;
    public static final int POLL = 3;
    public static final int STATIC = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "STATIC";
        }
        if (o == 1) {
            return "ON_CHANGE";
        }
        if (o == 2) {
            return "CONTINUOUS";
        }
        if (o == 3) {
            return "POLL";
        }
        if (o == 4) {
            return "ON_SET";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("STATIC");
        if ((o & 1) == 1) {
            list.add("ON_CHANGE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CONTINUOUS");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("POLL");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("ON_SET");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
