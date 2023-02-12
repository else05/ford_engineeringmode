package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2CommonIgnitionMonitors {
    public static final int COMPONENTS_AVAILABLE = 1;
    public static final int COMPONENTS_INCOMPLETE = 2;
    public static final int FUEL_SYSTEM_AVAILABLE = 4;
    public static final int FUEL_SYSTEM_INCOMPLETE = 8;
    public static final int MISFIRE_AVAILABLE = 16;
    public static final int MISFIRE_INCOMPLETE = 32;

    public static final String toString(int o) {
        if (o == 1) {
            return "COMPONENTS_AVAILABLE";
        }
        if (o == 2) {
            return "COMPONENTS_INCOMPLETE";
        }
        if (o == 4) {
            return "FUEL_SYSTEM_AVAILABLE";
        }
        if (o == 8) {
            return "FUEL_SYSTEM_INCOMPLETE";
        }
        if (o == 16) {
            return "MISFIRE_AVAILABLE";
        }
        if (o == 32) {
            return "MISFIRE_INCOMPLETE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("COMPONENTS_AVAILABLE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("COMPONENTS_INCOMPLETE");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("FUEL_SYSTEM_AVAILABLE");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("FUEL_SYSTEM_INCOMPLETE");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("MISFIRE_AVAILABLE");
            flipped |= 16;
        }
        if ((o & 32) == 32) {
            list.add("MISFIRE_INCOMPLETE");
            flipped |= 32;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
