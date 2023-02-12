package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehicleWirlessChargeStatus {
    public static final int WIRELESS_CHARGE_ABNORNAL = 3;
    public static final int WIRELESS_CHARGE_ING_ = 2;
    public static final int WIRELESS_CHARGE_NOT_SUPPORT = 0;
    public static final int WIRELESS_CHARGE_START = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "WIRELESS_CHARGE_NOT_SUPPORT";
        }
        if (o == 1) {
            return "WIRELESS_CHARGE_START";
        }
        if (o == 2) {
            return "WIRELESS_CHARGE_ING_";
        }
        if (o == 3) {
            return "WIRELESS_CHARGE_ABNORNAL";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("WIRELESS_CHARGE_NOT_SUPPORT");
        if ((o & 1) == 1) {
            list.add("WIRELESS_CHARGE_START");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("WIRELESS_CHARGE_ING_");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("WIRELESS_CHARGE_ABNORNAL");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
