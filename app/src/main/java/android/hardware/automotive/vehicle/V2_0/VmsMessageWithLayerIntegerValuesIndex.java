package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VmsMessageWithLayerIntegerValuesIndex {
    public static final int LAYER_SUBTYPE = 2;
    public static final int LAYER_TYPE = 1;
    public static final int LAYER_VERSION = 3;
    public static final int MESSAGE_TYPE = 0;

    public static final String toString(int o) {
        if (o == 1) {
            return "LAYER_TYPE";
        }
        if (o == 2) {
            return "LAYER_SUBTYPE";
        }
        if (o == 3) {
            return "LAYER_VERSION";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("LAYER_TYPE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("LAYER_SUBTYPE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("LAYER_VERSION");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
