package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioVolumeState {
    public static final int STATE_LIMIT_REACHED = 1;
    public static final int STATE_OK = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "STATE_OK";
        }
        if (o == 1) {
            return "STATE_LIMIT_REACHED";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("STATE_OK");
        if ((o & 1) == 1) {
            list.add("STATE_LIMIT_REACHED");
            flipped = 0 | 1;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
