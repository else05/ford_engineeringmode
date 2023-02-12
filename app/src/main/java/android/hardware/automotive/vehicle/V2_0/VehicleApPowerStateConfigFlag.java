package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleApPowerStateConfigFlag {
    public static final int CONFIG_SUPPORT_TIMER_POWER_ON_FLAG = 2;
    public static final int ENABLE_DEEP_SLEEP_FLAG = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "ENABLE_DEEP_SLEEP_FLAG";
        }
        if (o == 2) {
            return "CONFIG_SUPPORT_TIMER_POWER_ON_FLAG";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("ENABLE_DEEP_SLEEP_FLAG");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CONFIG_SUPPORT_TIMER_POWER_ON_FLAG");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
