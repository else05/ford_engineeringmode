package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehiclePropertyAccess {
    public static final int NONE = 0;
    public static final int READ = 1;
    public static final int READ_WRITE = 3;
    public static final int WRITE = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "NONE";
        }
        if (o == 1) {
            return "READ";
        }
        if (o == 2) {
            return "WRITE";
        }
        if (o == 3) {
            return "READ_WRITE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NONE");
        if ((o & 1) == 1) {
            list.add("READ");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("WRITE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("READ_WRITE");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
