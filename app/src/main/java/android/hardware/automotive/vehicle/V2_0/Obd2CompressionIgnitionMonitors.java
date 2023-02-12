package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class Obd2CompressionIgnitionMonitors {
    public static final int BOOST_PRESSURE_AVAILABLE = 4096;
    public static final int BOOST_PRESSURE_INCOMPLETE = 8192;
    public static final int COMPONENTS_AVAILABLE = 1;
    public static final int COMPONENTS_INCOMPLETE = 2;
    public static final int EGR_OR_VVT_AVAILABLE = 64;
    public static final int EGR_OR_VVT_INCOMPLETE = 128;
    public static final int EXHAUST_GAS_SENSOR_AVAILABLE = 1024;
    public static final int EXHAUST_GAS_SENSOR_INCOMPLETE = 2048;
    public static final int FUEL_SYSTEM_AVAILABLE = 4;
    public static final int FUEL_SYSTEM_INCOMPLETE = 8;
    public static final int MISFIRE_AVAILABLE = 16;
    public static final int MISFIRE_INCOMPLETE = 32;
    public static final int NMHC_CATALYST_AVAILABLE = 65536;
    public static final int NMHC_CATALYST_INCOMPLETE = 131072;
    public static final int NOx_SCR_AVAILABLE = 16384;
    public static final int NOx_SCR_INCOMPLETE = 32768;
    public static final int PM_FILTER_AVAILABLE = 256;
    public static final int PM_FILTER_INCOMPLETE = 512;

    public static final String toString(int o) {
        if (o == 64) {
            return "EGR_OR_VVT_AVAILABLE";
        }
        if (o == 128) {
            return "EGR_OR_VVT_INCOMPLETE";
        }
        if (o == 256) {
            return "PM_FILTER_AVAILABLE";
        }
        if (o == 512) {
            return "PM_FILTER_INCOMPLETE";
        }
        if (o == 1024) {
            return "EXHAUST_GAS_SENSOR_AVAILABLE";
        }
        if (o == 2048) {
            return "EXHAUST_GAS_SENSOR_INCOMPLETE";
        }
        if (o == 4096) {
            return "BOOST_PRESSURE_AVAILABLE";
        }
        if (o == 8192) {
            return "BOOST_PRESSURE_INCOMPLETE";
        }
        if (o == 16384) {
            return "NOx_SCR_AVAILABLE";
        }
        if (o == 32768) {
            return "NOx_SCR_INCOMPLETE";
        }
        if (o == 65536) {
            return "NMHC_CATALYST_AVAILABLE";
        }
        if (o == 131072) {
            return "NMHC_CATALYST_INCOMPLETE";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if ((o & 64) == 64) {
            list.add("EGR_OR_VVT_AVAILABLE");
            flipped = 0 | 64;
        }
        if ((o & 128) == 128) {
            list.add("EGR_OR_VVT_INCOMPLETE");
            flipped |= 128;
        }
        if ((o & 256) == 256) {
            list.add("PM_FILTER_AVAILABLE");
            flipped |= 256;
        }
        if ((o & 512) == 512) {
            list.add("PM_FILTER_INCOMPLETE");
            flipped |= 512;
        }
        if ((o & 1024) == 1024) {
            list.add("EXHAUST_GAS_SENSOR_AVAILABLE");
            flipped |= 1024;
        }
        if ((o & 2048) == 2048) {
            list.add("EXHAUST_GAS_SENSOR_INCOMPLETE");
            flipped |= 2048;
        }
        if ((o & 4096) == 4096) {
            list.add("BOOST_PRESSURE_AVAILABLE");
            flipped |= 4096;
        }
        if ((o & 8192) == 8192) {
            list.add("BOOST_PRESSURE_INCOMPLETE");
            flipped |= 8192;
        }
        if ((o & 16384) == 16384) {
            list.add("NOx_SCR_AVAILABLE");
            flipped |= 16384;
        }
        if ((o & 32768) == 32768) {
            list.add("NOx_SCR_INCOMPLETE");
            flipped |= 32768;
        }
        if ((o & 65536) == 65536) {
            list.add("NMHC_CATALYST_AVAILABLE");
            flipped |= 65536;
        }
        if ((o & 131072) == 131072) {
            list.add("NMHC_CATALYST_INCOMPLETE");
            flipped |= 131072;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
