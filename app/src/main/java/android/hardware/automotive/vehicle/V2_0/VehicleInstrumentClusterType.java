package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleInstrumentClusterType {
    public static final int EXTERNAL_DISPLAY = 2;
    public static final int HAL_INTERFACE = 1;
    public static final int NONE = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "NONE";
        }
        if (o == 1) {
            return "HAL_INTERFACE";
        }
        if (o == 2) {
            return "EXTERNAL_DISPLAY";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NONE");
        if ((o & 1) == 1) {
            list.add("HAL_INTERFACE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("EXTERNAL_DISPLAY");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
