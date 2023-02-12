package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioFocusRequest {
    public static final int REQUEST_GAIN = 1;
    public static final int REQUEST_GAIN_TRANSIENT = 2;
    public static final int REQUEST_GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int REQUEST_GAIN_TRANSIENT_NO_DUCK = 4;
    public static final int REQUEST_RELEASE = 5;

    public static final String toString(int o) {
        if (o == 1) {
            return "REQUEST_GAIN";
        }
        if (o == 2) {
            return "REQUEST_GAIN_TRANSIENT";
        }
        if (o == 3) {
            return "REQUEST_GAIN_TRANSIENT_MAY_DUCK";
        }
        if (o == 4) {
            return "REQUEST_GAIN_TRANSIENT_NO_DUCK";
        }
        if (o == 5) {
            return "REQUEST_RELEASE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("REQUEST_GAIN");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("REQUEST_GAIN_TRANSIENT");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("REQUEST_GAIN_TRANSIENT_MAY_DUCK");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("REQUEST_GAIN_TRANSIENT_NO_DUCK");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("REQUEST_RELEASE");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
