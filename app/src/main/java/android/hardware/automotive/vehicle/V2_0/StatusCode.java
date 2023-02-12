package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class StatusCode {
    public static final int ACCESS_DENIED = 4;
    public static final int INTERNAL_ERROR = 5;
    public static final int INVALID_ARG = 2;
    public static final int NOT_AVAILABLE = 3;
    public static final int OK = 0;
    public static final int TRY_AGAIN = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "OK";
        }
        if (o == 1) {
            return "TRY_AGAIN";
        }
        if (o == 2) {
            return "INVALID_ARG";
        }
        if (o == 3) {
            return "NOT_AVAILABLE";
        }
        if (o == 4) {
            return "ACCESS_DENIED";
        }
        if (o == 5) {
            return "INTERNAL_ERROR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("OK");
        if ((o & 1) == 1) {
            list.add("TRY_AGAIN");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("INVALID_ARG");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("NOT_AVAILABLE");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("ACCESS_DENIED");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("INTERNAL_ERROR");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
