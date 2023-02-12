package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehiclePropertyType {
    public static final int BOOLEAN = 2097152;
    public static final int BYTES = 7340032;
    public static final int COMPLEX = 14680064;
    public static final int FLOAT = 6291456;
    public static final int FLOAT_VEC = 6356992;
    public static final int INT32 = 4194304;
    public static final int INT32_VEC = 4259840;
    public static final int INT64 = 5242880;
    public static final int MASK = 16711680;
    public static final int STRING = 1048576;

    public static final String toString(int o) {
        if (o == 1048576) {
            return "STRING";
        }
        if (o == 2097152) {
            return "BOOLEAN";
        }
        if (o == 4194304) {
            return "INT32";
        }
        if (o == 4259840) {
            return "INT32_VEC";
        }
        if (o == 5242880) {
            return "INT64";
        }
        if (o == 6291456) {
            return "FLOAT";
        }
        if (o == 6356992) {
            return "FLOAT_VEC";
        }
        if (o == 7340032) {
            return "BYTES";
        }
        if (o == 14680064) {
            return "COMPLEX";
        }
        if (o == 16711680) {
            return "MASK";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 1048576) == 1048576) {
            list.add("STRING");
            flipped = 0 | 1048576;
        }
        if ((o & 2097152) == 2097152) {
            list.add("BOOLEAN");
            flipped |= 2097152;
        }
        if ((o & INT32) == 4194304) {
            list.add("INT32");
            flipped |= INT32;
        }
        if ((o & INT32_VEC) == 4259840) {
            list.add("INT32_VEC");
            flipped |= INT32_VEC;
        }
        if ((o & INT64) == 5242880) {
            list.add("INT64");
            flipped |= INT64;
        }
        if ((o & FLOAT) == 6291456) {
            list.add("FLOAT");
            flipped |= FLOAT;
        }
        if ((o & FLOAT_VEC) == 6356992) {
            list.add("FLOAT_VEC");
            flipped |= FLOAT_VEC;
        }
        if ((o & BYTES) == 7340032) {
            list.add("BYTES");
            flipped |= BYTES;
        }
        if ((o & COMPLEX) == 14680064) {
            list.add("COMPLEX");
            flipped |= COMPLEX;
        }
        if ((o & MASK) == 16711680) {
            list.add("MASK");
            flipped |= MASK;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
