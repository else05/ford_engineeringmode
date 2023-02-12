package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleApPowerBootupReason {
    public static final int TIMER = 2;
    public static final int USER_POWER_ON = 0;
    public static final int USER_UNLOCK = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "USER_POWER_ON";
        }
        if (o == 1) {
            return "USER_UNLOCK";
        }
        if (o == 2) {
            return "TIMER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("USER_POWER_ON");
        if ((o & 1) == 1) {
            list.add("USER_UNLOCK");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("TIMER");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
