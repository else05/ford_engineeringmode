package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioExtFocusFlag {
    public static final int MUTE_MEDIA_FLAG = 8;
    public static final int NONE_FLAG = 0;
    public static final int PERMANENT_FLAG = 1;
    public static final int PLAY_ONLY_FLAG = 4;
    public static final int TRANSIENT_FLAG = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "NONE_FLAG";
        }
        if (o == 1) {
            return "PERMANENT_FLAG";
        }
        if (o == 2) {
            return "TRANSIENT_FLAG";
        }
        if (o == 4) {
            return "PLAY_ONLY_FLAG";
        }
        if (o == 8) {
            return "MUTE_MEDIA_FLAG";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NONE_FLAG");
        if ((o & 1) == 1) {
            list.add("PERMANENT_FLAG");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("TRANSIENT_FLAG");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("PLAY_ONLY_FLAG");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("MUTE_MEDIA_FLAG");
            flipped |= 8;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
