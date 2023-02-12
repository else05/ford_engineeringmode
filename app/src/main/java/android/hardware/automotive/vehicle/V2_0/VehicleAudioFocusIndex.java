package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioFocusIndex {
    public static final int AUDIO_CONTEXTS = 3;
    public static final int EXTERNAL_FOCUS_STATE = 2;
    public static final int FOCUS = 0;
    public static final int STREAMS = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "FOCUS";
        }
        if (o == 1) {
            return "STREAMS";
        }
        if (o == 2) {
            return "EXTERNAL_FOCUS_STATE";
        }
        if (o == 3) {
            return "AUDIO_CONTEXTS";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("FOCUS");
        if ((o & 1) == 1) {
            list.add("STREAMS");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("EXTERNAL_FOCUS_STATE");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("AUDIO_CONTEXTS");
            flipped |= 3;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
