package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleHwKeyInputAction {
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "ACTION_DOWN";
        }
        if (o == 1) {
            return "ACTION_UP";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("ACTION_DOWN");
        if ((o & 1) == 1) {
            list.add("ACTION_UP");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
