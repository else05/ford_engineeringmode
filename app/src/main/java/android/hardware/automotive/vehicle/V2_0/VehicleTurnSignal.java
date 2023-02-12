package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleTurnSignal {
    public static final int EMERGENCY = 4;
    public static final int LEFT = 2;
    public static final int NONE = 0;
    public static final int RIGHT = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "NONE";
        }
        if (o == 1) {
            return "RIGHT";
        }
        if (o == 2) {
            return "LEFT";
        }
        if (o == 4) {
            return "EMERGENCY";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NONE");
        if ((o & 1) == 1) {
            list.add("RIGHT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("LEFT");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("EMERGENCY");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
