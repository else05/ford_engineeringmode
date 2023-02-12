package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2SparkIgnitionMonitors {
    public static final int AC_REFRIGERANT_AVAILABLE = 4096;
    public static final int AC_REFRIGERANT_INCOMPLETE = 8192;
    public static final int CATALYST_AVAILABLE = 1048576;
    public static final int CATALYST_INCOMPLETE = 2097152;
    public static final int COMPONENTS_AVAILABLE = 1;
    public static final int COMPONENTS_INCOMPLETE = 2;
    public static final int EGR_AVAILABLE = 64;
    public static final int EGR_INCOMPLETE = 128;
    public static final int EVAPORATIVE_SYSTEM_AVAILABLE = 65536;
    public static final int EVAPORATIVE_SYSTEM_INCOMPLETE = 131072;
    public static final int FUEL_SYSTEM_AVAILABLE = 4;
    public static final int FUEL_SYSTEM_INCOMPLETE = 8;
    public static final int HEATED_CATALYST_AVAILABLE = 262144;
    public static final int HEATED_CATALYST_INCOMPLETE = 524288;
    public static final int MISFIRE_AVAILABLE = 16;
    public static final int MISFIRE_INCOMPLETE = 32;
    public static final int OXYGEN_SENSOR_AVAILABLE = 1024;
    public static final int OXYGEN_SENSOR_HEATER_AVAILABLE = 256;
    public static final int OXYGEN_SENSOR_HEATER_INCOMPLETE = 512;
    public static final int OXYGEN_SENSOR_INCOMPLETE = 2048;
    public static final int SECONDARY_AIR_SYSTEM_AVAILABLE = 16384;
    public static final int SECONDARY_AIR_SYSTEM_INCOMPLETE = 32768;

    public static final String toString(int o) {
        if (o == 64) {
            return "EGR_AVAILABLE";
        }
        if (o == 128) {
            return "EGR_INCOMPLETE";
        }
        if (o == 256) {
            return "OXYGEN_SENSOR_HEATER_AVAILABLE";
        }
        if (o == 512) {
            return "OXYGEN_SENSOR_HEATER_INCOMPLETE";
        }
        if (o == 1024) {
            return "OXYGEN_SENSOR_AVAILABLE";
        }
        if (o == 2048) {
            return "OXYGEN_SENSOR_INCOMPLETE";
        }
        if (o == 4096) {
            return "AC_REFRIGERANT_AVAILABLE";
        }
        if (o == 8192) {
            return "AC_REFRIGERANT_INCOMPLETE";
        }
        if (o == 16384) {
            return "SECONDARY_AIR_SYSTEM_AVAILABLE";
        }
        if (o == 32768) {
            return "SECONDARY_AIR_SYSTEM_INCOMPLETE";
        }
        if (o == 65536) {
            return "EVAPORATIVE_SYSTEM_AVAILABLE";
        }
        if (o == 131072) {
            return "EVAPORATIVE_SYSTEM_INCOMPLETE";
        }
        if (o == 262144) {
            return "HEATED_CATALYST_AVAILABLE";
        }
        if (o == 524288) {
            return "HEATED_CATALYST_INCOMPLETE";
        }
        if (o == 1048576) {
            return "CATALYST_AVAILABLE";
        }
        if (o == 2097152) {
            return "CATALYST_INCOMPLETE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 64) == 64) {
            list.add("EGR_AVAILABLE");
            flipped = 0 | 64;
        }
        if ((o & 128) == 128) {
            list.add("EGR_INCOMPLETE");
            flipped |= 128;
        }
        if ((o & 256) == 256) {
            list.add("OXYGEN_SENSOR_HEATER_AVAILABLE");
            flipped |= 256;
        }
        if ((o & 512) == 512) {
            list.add("OXYGEN_SENSOR_HEATER_INCOMPLETE");
            flipped |= 512;
        }
        if ((o & 1024) == 1024) {
            list.add("OXYGEN_SENSOR_AVAILABLE");
            flipped |= 1024;
        }
        if ((o & 2048) == 2048) {
            list.add("OXYGEN_SENSOR_INCOMPLETE");
            flipped |= 2048;
        }
        if ((o & 4096) == 4096) {
            list.add("AC_REFRIGERANT_AVAILABLE");
            flipped |= 4096;
        }
        if ((o & 8192) == 8192) {
            list.add("AC_REFRIGERANT_INCOMPLETE");
            flipped |= 8192;
        }
        if ((o & 16384) == 16384) {
            list.add("SECONDARY_AIR_SYSTEM_AVAILABLE");
            flipped |= 16384;
        }
        if ((o & 32768) == 32768) {
            list.add("SECONDARY_AIR_SYSTEM_INCOMPLETE");
            flipped |= 32768;
        }
        if ((o & 65536) == 65536) {
            list.add("EVAPORATIVE_SYSTEM_AVAILABLE");
            flipped |= 65536;
        }
        if ((o & 131072) == 131072) {
            list.add("EVAPORATIVE_SYSTEM_INCOMPLETE");
            flipped |= 131072;
        }
        if ((o & 262144) == 262144) {
            list.add("HEATED_CATALYST_AVAILABLE");
            flipped |= 262144;
        }
        if ((o & 524288) == 524288) {
            list.add("HEATED_CATALYST_INCOMPLETE");
            flipped |= 524288;
        }
        if ((o & 1048576) == 1048576) {
            list.add("CATALYST_AVAILABLE");
            flipped |= 1048576;
        }
        if ((2097152 & o) == 2097152) {
            list.add("CATALYST_INCOMPLETE");
            flipped |= 2097152;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
