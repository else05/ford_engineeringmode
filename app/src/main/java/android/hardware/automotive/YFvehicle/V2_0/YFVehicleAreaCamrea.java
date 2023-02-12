package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehicleAreaCamrea {
    public static final int CAMERA_BACK = 3;
    public static final int CAMERA_BACK_WIDE = 4;
    public static final int CAMERA_FRONT = 0;
    public static final int CAMERA_FRONT_WIDE = 1;
    public static final int CAMERA_PANORAMIC = 2;

    public static final String toString(int o) {
        if (o == 0) {
            return "CAMERA_FRONT";
        }
        if (o == 1) {
            return "CAMERA_FRONT_WIDE";
        }
        if (o == 2) {
            return "CAMERA_PANORAMIC";
        }
        if (o == 3) {
            return "CAMERA_BACK";
        }
        if (o == 4) {
            return "CAMERA_BACK_WIDE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("CAMERA_FRONT");
        if ((o & 1) == 1) {
            list.add("CAMERA_FRONT_WIDE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("CAMERA_PANORAMIC");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("CAMERA_BACK");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("CAMERA_BACK_WIDE");
            flipped |= 4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
