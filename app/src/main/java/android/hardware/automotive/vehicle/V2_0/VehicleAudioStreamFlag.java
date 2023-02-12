package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleAudioStreamFlag {
    public static final int STREAM0_FLAG = 1;
    public static final int STREAM1_FLAG = 2;
    public static final int STREAM2_FLAG = 4;

    public static final String toString(int o) {
        if (o == 1) {
            return "STREAM0_FLAG";
        }
        if (o == 2) {
            return "STREAM1_FLAG";
        }
        if (o == 4) {
            return "STREAM2_FLAG";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1) == 1) {
            list.add("STREAM0_FLAG");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("STREAM1_FLAG");
            flipped |= 2;
        }
        if ((o & 4) == 4) {
            list.add("STREAM2_FLAG");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
