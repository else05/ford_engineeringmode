package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleDrivingStatus {
    public static final int LIMIT_MESSAGE_LEN = 16;
    public static final int NO_CONFIG = 8;
    public static final int NO_KEYBOARD_INPUT = 2;
    public static final int NO_VIDEO = 1;
    public static final int NO_VOICE_INPUT = 4;
    public static final int UNRESTRICTED = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "UNRESTRICTED";
        }
        if (o == 1) {
            return "NO_VIDEO";
        }
        if (o == 2) {
            return "NO_KEYBOARD_INPUT";
        }
        if (o == 4) {
            return "NO_VOICE_INPUT";
        }
        if (o == 8) {
            return "NO_CONFIG";
        }
        if (o == 16) {
            return "LIMIT_MESSAGE_LEN";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("UNRESTRICTED");
        if ((o & 1) == 1) {
            list.add("NO_VIDEO");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("NO_KEYBOARD_INPUT");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("NO_VOICE_INPUT");
            flipped |= 4;
        }
        if ((o & 8) == 8) {
            list.add("NO_CONFIG");
            flipped |= 8;
        }
        if ((o & 16) == 16) {
            list.add("LIMIT_MESSAGE_LEN");
            flipped |= 16;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
