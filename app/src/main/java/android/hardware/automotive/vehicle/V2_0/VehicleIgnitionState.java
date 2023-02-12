package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleIgnitionState {
    public static final int ACC = 3;
    public static final int LOCK = 1;
    public static final int OFF = 2;
    public static final int ON = 4;
    public static final int START = 5;
    public static final int UNDEFINED = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNDEFINED";
        }
        if (o == 1) {
            return "LOCK";
        }
        if (o == 2) {
            return "OFF";
        }
        if (o == 3) {
            return "ACC";
        }
        if (o == 4) {
            return "ON";
        }
        if (o == 5) {
            return "START";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNDEFINED");
        if ((o & 1) == 1) {
            list.add("LOCK");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("OFF");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("ACC");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("ON");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("START");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
