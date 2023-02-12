package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioVolumeCapabilityFlag {
    public static final int MASTER_VOLUME_ONLY = 2;
    public static final int PERSISTENT_STORAGE = 1;

    public static final String toString(int o) {
        if (o == 1) {
            return "PERSISTENT_STORAGE";
        }
        if (o == 2) {
            return "MASTER_VOLUME_ONLY";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("PERSISTENT_STORAGE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("MASTER_VOLUME_ONLY");
            flipped |= 2;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
