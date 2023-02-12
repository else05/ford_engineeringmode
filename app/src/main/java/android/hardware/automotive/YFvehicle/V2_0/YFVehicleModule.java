package android.hardware.automotive.YFvehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class YFVehicleModule {
    public static final int ATMPOSPHERE_LAMP = 1;
    public static final int CARNETWORK = 24576;
    public static final int CARYFMYKEY = 16384;
    public static final int CARYFPAAK = 20480;
    public static final int CARYFSETTING = 12288;
    public static final int DIAGEOL = 20;
    public static final int DIAGNOSTIC = 19;
    public static final int DOOR = 5;
    public static final int EVS_CAMERA = 32768;
    public static final int HAVC = 4;
    public static final int LOCATIONSERVICE = 17;
    public static final int MULTI_CLUSTER = 28672;
    public static final int MULTI_SCREEN_INTERACTION = 12;
    public static final int NONE = 0;
    public static final int PHEV = 8192;
    public static final int POWER_MANAGERMENT = 18;
    public static final int SEAT = 2;
    public static final int SEAT_MASSAGE = 3;
    public static final int TCS = 14;
    public static final int TRANSPORT_PROTOCOL = 16;
    public static final int TRUNK = 6;
    public static final int VEHICLE_AUXILIARY = 8;
    public static final int VEHICLE_CAR_PARK_ASSIST = 13;
    public static final int VEHICLE_CLUSTER = 15;
    public static final int VEHICLE_CONDITION = 7;
    public static final int VEHICLE_CONDITION_PHASE2 = 21;
    public static final int VEHICLE_INFO = 11;
    public static final int WIRELESS_CHARGE = 9;
    public static final int WIRELESS_HOTSPOT = 10;

    public static final String toString(int o) {
        if (o == 0) {
            return "NONE";
        }
        if (o == 1) {
            return "ATMPOSPHERE_LAMP";
        }
        if (o == 2) {
            return "SEAT";
        }
        if (o == 3) {
            return "SEAT_MASSAGE";
        }
        if (o == 4) {
            return "HAVC";
        }
        if (o == 5) {
            return "DOOR";
        }
        if (o == 6) {
            return "TRUNK";
        }
        if (o == 7) {
            return "VEHICLE_CONDITION";
        }
        if (o == 8) {
            return "VEHICLE_AUXILIARY";
        }
        if (o == 9) {
            return "WIRELESS_CHARGE";
        }
        if (o == 10) {
            return "WIRELESS_HOTSPOT";
        }
        if (o == 11) {
            return "VEHICLE_INFO";
        }
        if (o == 12) {
            return "MULTI_SCREEN_INTERACTION";
        }
        if (o == 13) {
            return "VEHICLE_CAR_PARK_ASSIST";
        }
        if (o == 14) {
            return "TCS";
        }
        if (o == 15) {
            return "VEHICLE_CLUSTER";
        }
        if (o == 16) {
            return "TRANSPORT_PROTOCOL";
        }
        if (o == 17) {
            return "LOCATIONSERVICE";
        }
        if (o == 18) {
            return "POWER_MANAGERMENT";
        }
        if (o == 19) {
            return "DIAGNOSTIC";
        }
        if (o == 20) {
            return "DIAGEOL";
        }
        if (o == 21) {
            return "VEHICLE_CONDITION_PHASE2";
        }
        if (o == 8192) {
            return "PHEV";
        }
        if (o == 12288) {
            return "CARYFSETTING";
        }
        if (o == 16384) {
            return "CARYFMYKEY";
        }
        if (o == 20480) {
            return "CARYFPAAK";
        }
        if (o == 24576) {
            return "CARNETWORK";
        }
        if (o == 28672) {
            return "MULTI_CLUSTER";
        }
        if (o == 32768) {
            return "EVS_CAMERA";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("NONE");
        if ((o & 1) == 1) {
            list.add("ATMPOSPHERE_LAMP");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("SEAT");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("SEAT_MASSAGE");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("HAVC");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("DOOR");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("TRUNK");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("VEHICLE_CONDITION");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("VEHICLE_AUXILIARY");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("WIRELESS_CHARGE");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("WIRELESS_HOTSPOT");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("VEHICLE_INFO");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("MULTI_SCREEN_INTERACTION");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("VEHICLE_CAR_PARK_ASSIST");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("TCS");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("VEHICLE_CLUSTER");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("TRANSPORT_PROTOCOL");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("LOCATIONSERVICE");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("POWER_MANAGERMENT");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("DIAGNOSTIC");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("DIAGEOL");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("VEHICLE_CONDITION_PHASE2");
            flipped |= 21;
        }
        if ((o & 8192) == 8192) {
            list.add("PHEV");
            flipped |= 8192;
        }
        if ((o & CARYFSETTING) == 12288) {
            list.add("CARYFSETTING");
            flipped |= CARYFSETTING;
        }
        if ((o & 16384) == 16384) {
            list.add("CARYFMYKEY");
            flipped |= 16384;
        }
        if ((o & CARYFPAAK) == 20480) {
            list.add("CARYFPAAK");
            flipped |= CARYFPAAK;
        }
        if ((o & CARNETWORK) == 24576) {
            list.add("CARNETWORK");
            flipped |= CARNETWORK;
        }
        if ((o & MULTI_CLUSTER) == 28672) {
            list.add("MULTI_CLUSTER");
            flipped |= MULTI_CLUSTER;
        }
        if ((32768 & o) == 32768) {
            list.add("EVS_CAMERA");
            flipped |= 32768;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
