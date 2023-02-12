package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleApPowerState {
    public static final int DEEP_SLEEP = 1;
    public static final int OFF = 0;
    public static final int ON_DISP_OFF = 2;
    public static final int ON_FULL = 3;
    public static final int SHUTDOWN_PREPARE = 4;

    public static final String toString(int o) {
        if (o == 0) {
            return "OFF";
        }
        if (o == 1) {
            return "DEEP_SLEEP";
        }
        if (o == 2) {
            return "ON_DISP_OFF";
        }
        if (o == 3) {
            return "ON_FULL";
        }
        if (o == 4) {
            return "SHUTDOWN_PREPARE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("OFF");
        if ((o & 1) == 1) {
            list.add("DEEP_SLEEP");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("ON_DISP_OFF");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("ON_FULL");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("SHUTDOWN_PREPARE");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
