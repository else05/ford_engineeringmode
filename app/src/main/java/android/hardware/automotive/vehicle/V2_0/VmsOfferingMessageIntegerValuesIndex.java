package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VmsOfferingMessageIntegerValuesIndex {
    public static final int MESSAGE_TYPE = 0;
    public static final int NUMBER_OF_OFFERS = 2;
    public static final int OFFERING_START = 3;
    public static final int PUBLISHER_ID = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "PUBLISHER_ID";
        }
        if (o == 2) {
            return "NUMBER_OF_OFFERS";
        }
        if (o == 3) {
            return "OFFERING_START";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("PUBLISHER_ID");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("NUMBER_OF_OFFERS");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("OFFERING_START");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
