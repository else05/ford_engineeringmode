package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehicleAreaTire {
    public static final int BACK_LEFT = 4;
    public static final int BACK_RIGHT = 8;
    public static final int FRONT_LEFT = 1;
    public static final int FRONT_RIGHT = 2;

    public static final String toString(int o) {
        if (o == 1) {
            return "FRONT_LEFT";
        }
        if (o == 2) {
            return "FRONT_RIGHT";
        }
        if (o == 4) {
            return "BACK_LEFT";
        }
        if (o == 8) {
            return "BACK_RIGHT";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("FRONT_LEFT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("FRONT_RIGHT");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("BACK_LEFT");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("BACK_RIGHT");
            flipped |= 8;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
