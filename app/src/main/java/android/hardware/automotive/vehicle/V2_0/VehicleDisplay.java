package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleDisplay {
    public static final int INSTRUMENT_CLUSTER = 1;
    public static final int MAIN = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "MAIN";
        }
        if (o == 1) {
            return "INSTRUMENT_CLUSTER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("MAIN");
        if ((o & 1) == 1) {
            list.add("INSTRUMENT_CLUSTER");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
