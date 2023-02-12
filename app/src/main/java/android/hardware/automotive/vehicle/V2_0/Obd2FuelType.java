package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2FuelType {
    public static final int BIFUEL_RUNNING_CNG = 13;
    public static final int BIFUEL_RUNNING_DIESEL = 23;
    public static final int BIFUEL_RUNNING_ELECTRIC = 15;
    public static final int BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION = 16;
    public static final int BIFUEL_RUNNING_ETHANOL = 11;
    public static final int BIFUEL_RUNNING_GASOLINE = 9;
    public static final int BIFUEL_RUNNING_LPG = 12;
    public static final int BIFUEL_RUNNING_METHANOL = 10;
    public static final int BIFUEL_RUNNING_PROPANE = 14;
    public static final int CNG = 6;
    public static final int DIESEL = 4;
    public static final int ELECTRIC = 8;
    public static final int ETHANOL = 3;
    public static final int GASOLINE = 1;
    public static final int HYBRID_DIESEL = 19;
    public static final int HYBRID_ELECTRIC = 20;
    public static final int HYBRID_ETHANOL = 18;
    public static final int HYBRID_GASOLINE = 17;
    public static final int HYBRID_REGENERATIVE = 22;
    public static final int HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION = 21;
    public static final int LPG = 5;
    public static final int METHANOL = 2;
    public static final int NOT_AVAILABLE = 0;
    public static final int PROPANE = 7;

    public static final String toString(int o) {
        if (o == 0) {
            return "NOT_AVAILABLE";
        }
        if (o == 1) {
            return "GASOLINE";
        }
        if (o == 2) {
            return "METHANOL";
        }
        if (o == 3) {
            return "ETHANOL";
        }
        if (o == 4) {
            return "DIESEL";
        }
        if (o == 5) {
            return "LPG";
        }
        if (o == 6) {
            return "CNG";
        }
        if (o == 7) {
            return "PROPANE";
        }
        if (o == 8) {
            return "ELECTRIC";
        }
        if (o == 9) {
            return "BIFUEL_RUNNING_GASOLINE";
        }
        if (o == 10) {
            return "BIFUEL_RUNNING_METHANOL";
        }
        if (o == 11) {
            return "BIFUEL_RUNNING_ETHANOL";
        }
        if (o == 12) {
            return "BIFUEL_RUNNING_LPG";
        }
        if (o == 13) {
            return "BIFUEL_RUNNING_CNG";
        }
        if (o == 14) {
            return "BIFUEL_RUNNING_PROPANE";
        }
        if (o == 15) {
            return "BIFUEL_RUNNING_ELECTRIC";
        }
        if (o == 16) {
            return "BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION";
        }
        if (o == 17) {
            return "HYBRID_GASOLINE";
        }
        if (o == 18) {
            return "HYBRID_ETHANOL";
        }
        if (o == 19) {
            return "HYBRID_DIESEL";
        }
        if (o == 20) {
            return "HYBRID_ELECTRIC";
        }
        if (o == 21) {
            return "HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION";
        }
        if (o == 22) {
            return "HYBRID_REGENERATIVE";
        }
        if (o == 23) {
            return "BIFUEL_RUNNING_DIESEL";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NOT_AVAILABLE");
        if ((o & 1) == 1) {
            list.add("GASOLINE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("METHANOL");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("ETHANOL");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("DIESEL");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("LPG");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("CNG");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("PROPANE");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("ELECTRIC");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("BIFUEL_RUNNING_GASOLINE");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("BIFUEL_RUNNING_METHANOL");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("BIFUEL_RUNNING_ETHANOL");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("BIFUEL_RUNNING_LPG");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("BIFUEL_RUNNING_CNG");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("BIFUEL_RUNNING_PROPANE");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("BIFUEL_RUNNING_ELECTRIC");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("HYBRID_GASOLINE");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("HYBRID_ETHANOL");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("HYBRID_DIESEL");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("HYBRID_ELECTRIC");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION");
            flipped |= 21;
        }
        if ((o & 22) == 22) {
            list.add("HYBRID_REGENERATIVE");
            flipped |= 22;
        }
        if ((o & 23) == 23) {
            list.add("BIFUEL_RUNNING_DIESEL");
            flipped |= 23;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
