package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioVolumeIndex {
    public static final int STATE = 2;
    public static final int STREAM = 0;
    public static final int VOLUME = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "STREAM";
        }
        if (o == 1) {
            return "VOLUME";
        }
        if (o == 2) {
            return "STATE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("STREAM");
        if ((o & 1) == 1) {
            list.add("VOLUME");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("STATE");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
