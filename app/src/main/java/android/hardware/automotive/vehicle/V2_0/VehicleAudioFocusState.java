package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioFocusState {
    public static final int STATE_GAIN = 1;
    public static final int STATE_GAIN_TRANSIENT = 2;
    public static final int STATE_LOSS = 5;
    public static final int STATE_LOSS_TRANSIENT = 4;
    public static final int STATE_LOSS_TRANSIENT_CAN_DUCK = 3;
    public static final int STATE_LOSS_TRANSIENT_EXLCUSIVE = 6;

    public static final String toString(int o) {
        if (o == 1) {
            return "STATE_GAIN";
        }
        if (o == 2) {
            return "STATE_GAIN_TRANSIENT";
        }
        if (o == 3) {
            return "STATE_LOSS_TRANSIENT_CAN_DUCK";
        }
        if (o == 4) {
            return "STATE_LOSS_TRANSIENT";
        }
        if (o == 5) {
            return "STATE_LOSS";
        }
        if (o == 6) {
            return "STATE_LOSS_TRANSIENT_EXLCUSIVE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("STATE_GAIN");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("STATE_GAIN_TRANSIENT");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("STATE_LOSS_TRANSIENT_CAN_DUCK");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("STATE_LOSS_TRANSIENT");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("STATE_LOSS");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("STATE_LOSS_TRANSIENT_EXLCUSIVE");
            flipped |= 6;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
