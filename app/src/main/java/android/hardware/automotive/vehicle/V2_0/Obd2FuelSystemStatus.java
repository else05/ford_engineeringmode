package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2FuelSystemStatus {
    public static final int CLOSED_LOOP = 2;
    public static final int CLOSED_LOOP_BUT_FEEDBACK_FAULT = 16;
    public static final int OPEN_ENGINE_LOAD_OR_DECELERATION = 4;
    public static final int OPEN_INSUFFICIENT_ENGINE_TEMPERATURE = 1;
    public static final int OPEN_SYSTEM_FAILURE = 8;

    public static final String toString(int o) {
        if (o == 1) {
            return "OPEN_INSUFFICIENT_ENGINE_TEMPERATURE";
        }
        if (o == 2) {
            return "CLOSED_LOOP";
        }
        if (o == 4) {
            return "OPEN_ENGINE_LOAD_OR_DECELERATION";
        }
        if (o == 8) {
            return "OPEN_SYSTEM_FAILURE";
        }
        if (o == 16) {
            return "CLOSED_LOOP_BUT_FEEDBACK_FAULT";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("OPEN_INSUFFICIENT_ENGINE_TEMPERATURE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CLOSED_LOOP");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("OPEN_ENGINE_LOAD_OR_DECELERATION");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("OPEN_SYSTEM_FAILURE");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("CLOSED_LOOP_BUT_FEEDBACK_FAULT");
            flipped |= 16;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
