package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleApPowerStateIndex {
    public static final int ADDITIONAL = 1;
    public static final int STATE = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "STATE";
        }
        if (o == 1) {
            return "ADDITIONAL";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("STATE");
        if ((o & 1) == 1) {
            list.add("ADDITIONAL");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
