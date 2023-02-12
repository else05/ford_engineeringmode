package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class SubscribeFlags {
    public static final int DEFAULT = 1;
    public static final int HAL_EVENT = 1;
    public static final int SET_CALL = 2;
    public static final int UNDEFINED = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNDEFINED";
        }
        if (o == 1) {
            return "HAL_EVENT";
        }
        if (o == 2) {
            return "SET_CALL";
        }
        if (o == 1) {
            return "DEFAULT";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNDEFINED");
        if ((o & 1) == 1) {
            list.add("HAL_EVENT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("SET_CALL");
            flipped |= 2;
        }
        if ((o & 1) == 1) {
            list.add("DEFAULT");
            flipped |= 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
