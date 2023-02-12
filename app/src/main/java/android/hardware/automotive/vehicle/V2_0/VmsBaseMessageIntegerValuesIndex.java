package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VmsBaseMessageIntegerValuesIndex {
    public static final int MESSAGE_TYPE = 0;

    public static final String toString(int o) {
        if (o == 0) {
            return "MESSAGE_TYPE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        list.add("MESSAGE_TYPE");
        if (o != 0) {
            list.add("0x" + Integer.toHexString((~0) & o));
        }
        return String.join(" | ", list);
    }
}
