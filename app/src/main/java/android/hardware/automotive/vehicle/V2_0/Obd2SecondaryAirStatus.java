package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2SecondaryAirStatus {
    public static final int DOWNSTREAM_OF_CATALYCIC_CONVERTER = 2;
    public static final int FROM_OUTSIDE_OR_OFF = 4;
    public static final int PUMP_ON_FOR_DIAGNOSTICS = 8;
    public static final int UPSTREAM = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "UPSTREAM";
        }
        if (o == 2) {
            return "DOWNSTREAM_OF_CATALYCIC_CONVERTER";
        }
        if (o == 4) {
            return "FROM_OUTSIDE_OR_OFF";
        }
        if (o == 8) {
            return "PUMP_ON_FOR_DIAGNOSTICS";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("UPSTREAM");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("DOWNSTREAM_OF_CATALYCIC_CONVERTER");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("FROM_OUTSIDE_OR_OFF");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("PUMP_ON_FOR_DIAGNOSTICS");
            flipped |= 8;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
