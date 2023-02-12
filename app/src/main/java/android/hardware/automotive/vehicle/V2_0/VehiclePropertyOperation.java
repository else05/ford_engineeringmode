package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehiclePropertyOperation {
    public static final int GENERIC = 0;
    public static final int GET = 2;
    public static final int SET = 1;
    public static final int SUBSCRIBE = 3;

    public static final String toString(int o) {
        if (o == 0) {
            return "GENERIC";
        }
        if (o == 1) {
            return "SET";
        }
        if (o == 2) {
            return "GET";
        }
        if (o == 3) {
            return "SUBSCRIBE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("GENERIC");
        if ((o & 1) == 1) {
            list.add("SET");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("GET");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("SUBSCRIBE");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
