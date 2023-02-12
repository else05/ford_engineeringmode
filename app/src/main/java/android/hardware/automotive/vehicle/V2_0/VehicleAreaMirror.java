package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAreaMirror {
    public static final int DRIVER_CENTER = 4;
    public static final int DRIVER_LEFT = 1;
    public static final int DRIVER_RIGHT = 2;

    public static final String toString(int o) {
        if (o == 1) {
            return "DRIVER_LEFT";
        }
        if (o == 2) {
            return "DRIVER_RIGHT";
        }
        if (o == 4) {
            return "DRIVER_CENTER";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("DRIVER_LEFT");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("DRIVER_RIGHT");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("DRIVER_CENTER");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
