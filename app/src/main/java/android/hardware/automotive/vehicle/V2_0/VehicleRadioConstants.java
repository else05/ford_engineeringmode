package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleRadioConstants {
    public static final int VEHICLE_RADIO_PRESET_MIN_VALUE = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "VEHICLE_RADIO_PRESET_MIN_VALUE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("VEHICLE_RADIO_PRESET_MIN_VALUE");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
