package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleHvacFanDirection {
    public static final int DEFROST = 4;
    public static final int DEFROST_AND_FLOOR = 5;
    public static final int FACE = 1;
    public static final int FACE_AND_FLOOR = 3;
    public static final int FLOOR = 2;

    public static final String toString(int o) {
        if (o == 1) {
            return "FACE";
        }
        if (o == 2) {
            return "FLOOR";
        }
        if (o == 3) {
            return "FACE_AND_FLOOR";
        }
        if (o == 4) {
            return "DEFROST";
        }
        if (o == 5) {
            return "DEFROST_AND_FLOOR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("FACE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("FLOOR");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("FACE_AND_FLOOR");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("DEFROST");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("DEFROST_AND_FLOOR");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
