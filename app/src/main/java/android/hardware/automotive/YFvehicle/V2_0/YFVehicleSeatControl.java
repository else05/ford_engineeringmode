package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehicleSeatControl {
    public static final int CUSHION = 7;
    public static final int LOWERBOLSTER = 4;
    public static final int LOWERLUMBAR = 1;
    public static final int LUMBAR = 6;
    public static final int MIDDLELUMBAR = 2;
    public static final int UPPERBOLSTER = 5;
    public static final int UPPERLUMBAR = 3;

    public static final String toString(int o) {
        if (o == 1) {
            return "LOWERLUMBAR";
        }
        if (o == 2) {
            return "MIDDLELUMBAR";
        }
        if (o == 3) {
            return "UPPERLUMBAR";
        }
        if (o == 4) {
            return "LOWERBOLSTER";
        }
        if (o == 5) {
            return "UPPERBOLSTER";
        }
        if (o == 6) {
            return "LUMBAR";
        }
        if (o == 7) {
            return "CUSHION";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("LOWERLUMBAR");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("MIDDLELUMBAR");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("UPPERLUMBAR");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("LOWERBOLSTER");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("UPPERBOLSTER");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("LUMBAR");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("CUSHION");
            flipped |= 7;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
