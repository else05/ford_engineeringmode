package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleApPowerStateShutdownParam {
    public static final int CAN_SLEEP = 2;
    public static final int SHUTDOWN_IMMEDIATELY = 1;
    public static final int SHUTDOWN_ONLY = 3;

    public static final String toString(int o) {
        if (o == 1) {
            return "SHUTDOWN_IMMEDIATELY";
        }
        if (o == 2) {
            return "CAN_SLEEP";
        }
        if (o == 3) {
            return "SHUTDOWN_ONLY";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("SHUTDOWN_IMMEDIATELY");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CAN_SLEEP");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("SHUTDOWN_ONLY");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
