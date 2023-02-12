package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehiclePropertyID {
    public static final int MASK = 65535;

    public static final String toString(int o) {
        if (o == 65535) {
            return "MASK";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 65535) == 65535) {
            list.add("MASK");
            flipped = 0 | 65535;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
