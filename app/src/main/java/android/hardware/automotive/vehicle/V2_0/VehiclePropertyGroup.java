package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehiclePropertyGroup {
    public static final int MASK = -268435456;
    public static final int SYSTEM = 268435456;
    public static final int VENDOR = 536870912;

    public static final String toString(int o) {
        if (o == 268435456) {
            return "SYSTEM";
        }
        if (o == 536870912) {
            return "VENDOR";
        }
        if (o == -268435456) {
            return "MASK";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 268435456) == 268435456) {
            list.add("SYSTEM");
            flipped = 0 | 268435456;
        }
        if ((o & 536870912) == 536870912) {
            list.add("VENDOR");
            flipped |= 536870912;
        }
        if ((o & MASK) == -268435456) {
            list.add("MASK");
            flipped |= MASK;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
