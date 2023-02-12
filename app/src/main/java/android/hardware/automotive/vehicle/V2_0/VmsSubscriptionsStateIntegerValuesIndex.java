package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VmsSubscriptionsStateIntegerValuesIndex {
    public static final int MESSAGE_TYPE = 0;
    public static final int NUMBER_OF_ASSOCIATED_LAYERS = 3;
    public static final int NUMBER_OF_LAYERS = 2;
    public static final int SEQUENCE_NUMBER = 1;
    public static final int SUBSCRIPTIONS_START = 4;

    public static final String toString(int o) {
        if (o == 1) {
            return "SEQUENCE_NUMBER";
        }
        if (o == 2) {
            return "NUMBER_OF_LAYERS";
        }
        if (o == 3) {
            return "NUMBER_OF_ASSOCIATED_LAYERS";
        }
        if (o == 4) {
            return "SUBSCRIPTIONS_START";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("SEQUENCE_NUMBER");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("NUMBER_OF_LAYERS");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("NUMBER_OF_ASSOCIATED_LAYERS");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("SUBSCRIPTIONS_START");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
