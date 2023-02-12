package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class DiagnosticIntegerSensorIndex {
    public static final int ABSOLUTE_BAROMETRIC_PRESSURE = 11;
    public static final int AMBIENT_AIR_TEMPERATURE = 13;
    public static final int COMMANDED_SECONDARY_AIR_STATUS = 5;
    public static final int CONTROL_MODULE_VOLTAGE = 12;
    public static final int DISTANCE_TRAVELED_SINCE_CODES_CLEARED = 10;
    public static final int DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON = 8;
    public static final int DRIVER_DEMAND_PERCENT_TORQUE = 24;
    public static final int ENGINE_ACTUAL_PERCENT_TORQUE = 25;
    public static final int ENGINE_OIL_TEMPERATURE = 23;
    public static final int ENGINE_PERCENT_TORQUE_DATA_IDLE = 27;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT1 = 28;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT2 = 29;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT3 = 30;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT4 = 31;
    public static final int ENGINE_REFERENCE_PERCENT_TORQUE = 26;
    public static final int FUEL_RAIL_ABSOLUTE_PRESSURE = 22;
    public static final int FUEL_SYSTEM_STATUS = 0;
    public static final int FUEL_TYPE = 21;
    public static final int IGNITION_MONITORS_SUPPORTED = 2;
    public static final int IGNITION_SPECIFIC_MONITORS = 3;
    public static final int INTAKE_AIR_TEMPERATURE = 4;
    public static final int LAST_SYSTEM_INDEX = 31;
    public static final int MALFUNCTION_INDICATOR_LIGHT_ON = 1;
    public static final int MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR = 20;
    public static final int MAX_FUEL_AIR_EQUIVALENCE_RATIO = 16;
    public static final int MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 19;
    public static final int MAX_OXYGEN_SENSOR_CURRENT = 18;
    public static final int MAX_OXYGEN_SENSOR_VOLTAGE = 17;
    public static final int NUM_OXYGEN_SENSORS_PRESENT = 6;
    public static final int RUNTIME_SINCE_ENGINE_START = 7;
    public static final int TIME_SINCE_TROUBLE_CODES_CLEARED = 15;
    public static final int TIME_WITH_MALFUNCTION_LIGHT_ON = 14;
    public static final int WARMUPS_SINCE_CODES_CLEARED = 9;

    public static final String toString(int o) {
        if (o == 0) {
            return "FUEL_SYSTEM_STATUS";
        }
        if (o == 1) {
            return "MALFUNCTION_INDICATOR_LIGHT_ON";
        }
        if (o == 2) {
            return "IGNITION_MONITORS_SUPPORTED";
        }
        if (o == 3) {
            return "IGNITION_SPECIFIC_MONITORS";
        }
        if (o == 4) {
            return "INTAKE_AIR_TEMPERATURE";
        }
        if (o == 5) {
            return "COMMANDED_SECONDARY_AIR_STATUS";
        }
        if (o == 6) {
            return "NUM_OXYGEN_SENSORS_PRESENT";
        }
        if (o == 7) {
            return "RUNTIME_SINCE_ENGINE_START";
        }
        if (o == 8) {
            return "DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON";
        }
        if (o == 9) {
            return "WARMUPS_SINCE_CODES_CLEARED";
        }
        if (o == 10) {
            return "DISTANCE_TRAVELED_SINCE_CODES_CLEARED";
        }
        if (o == 11) {
            return "ABSOLUTE_BAROMETRIC_PRESSURE";
        }
        if (o == 12) {
            return "CONTROL_MODULE_VOLTAGE";
        }
        if (o == 13) {
            return "AMBIENT_AIR_TEMPERATURE";
        }
        if (o == 14) {
            return "TIME_WITH_MALFUNCTION_LIGHT_ON";
        }
        if (o == 15) {
            return "TIME_SINCE_TROUBLE_CODES_CLEARED";
        }
        if (o == 16) {
            return "MAX_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 17) {
            return "MAX_OXYGEN_SENSOR_VOLTAGE";
        }
        if (o == 18) {
            return "MAX_OXYGEN_SENSOR_CURRENT";
        }
        if (o == 19) {
            return "MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE";
        }
        if (o == 20) {
            return "MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR";
        }
        if (o == 21) {
            return "FUEL_TYPE";
        }
        if (o == 22) {
            return "FUEL_RAIL_ABSOLUTE_PRESSURE";
        }
        if (o == 23) {
            return "ENGINE_OIL_TEMPERATURE";
        }
        if (o == 24) {
            return "DRIVER_DEMAND_PERCENT_TORQUE";
        }
        if (o == 25) {
            return "ENGINE_ACTUAL_PERCENT_TORQUE";
        }
        if (o == 26) {
            return "ENGINE_REFERENCE_PERCENT_TORQUE";
        }
        if (o == 27) {
            return "ENGINE_PERCENT_TORQUE_DATA_IDLE";
        }
        if (o == 28) {
            return "ENGINE_PERCENT_TORQUE_DATA_POINT1";
        }
        if (o == 29) {
            return "ENGINE_PERCENT_TORQUE_DATA_POINT2";
        }
        if (o == 30) {
            return "ENGINE_PERCENT_TORQUE_DATA_POINT3";
        }
        if (o == 31) {
            return "ENGINE_PERCENT_TORQUE_DATA_POINT4";
        }
        if (o == 31) {
            return "LAST_SYSTEM_INDEX";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("FUEL_SYSTEM_STATUS");
        if ((o & 1) == 1) {
            list.add("MALFUNCTION_INDICATOR_LIGHT_ON");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("IGNITION_MONITORS_SUPPORTED");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("IGNITION_SPECIFIC_MONITORS");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("INTAKE_AIR_TEMPERATURE");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("COMMANDED_SECONDARY_AIR_STATUS");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("NUM_OXYGEN_SENSORS_PRESENT");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("RUNTIME_SINCE_ENGINE_START");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("WARMUPS_SINCE_CODES_CLEARED");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("DISTANCE_TRAVELED_SINCE_CODES_CLEARED");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("ABSOLUTE_BAROMETRIC_PRESSURE");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("CONTROL_MODULE_VOLTAGE");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("AMBIENT_AIR_TEMPERATURE");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("TIME_WITH_MALFUNCTION_LIGHT_ON");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("TIME_SINCE_TROUBLE_CODES_CLEARED");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("MAX_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("MAX_OXYGEN_SENSOR_VOLTAGE");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("MAX_OXYGEN_SENSOR_CURRENT");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("FUEL_TYPE");
            flipped |= 21;
        }
        if ((o & 22) == 22) {
            list.add("FUEL_RAIL_ABSOLUTE_PRESSURE");
            flipped |= 22;
        }
        if ((o & 23) == 23) {
            list.add("ENGINE_OIL_TEMPERATURE");
            flipped |= 23;
        }
        if ((o & 24) == 24) {
            list.add("DRIVER_DEMAND_PERCENT_TORQUE");
            flipped |= 24;
        }
        if ((o & 25) == 25) {
            list.add("ENGINE_ACTUAL_PERCENT_TORQUE");
            flipped |= 25;
        }
        if ((o & 26) == 26) {
            list.add("ENGINE_REFERENCE_PERCENT_TORQUE");
            flipped |= 26;
        }
        if ((o & 27) == 27) {
            list.add("ENGINE_PERCENT_TORQUE_DATA_IDLE");
            flipped |= 27;
        }
        if ((o & 28) == 28) {
            list.add("ENGINE_PERCENT_TORQUE_DATA_POINT1");
            flipped |= 28;
        }
        if ((o & 29) == 29) {
            list.add("ENGINE_PERCENT_TORQUE_DATA_POINT2");
            flipped |= 29;
        }
        if ((o & 30) == 30) {
            list.add("ENGINE_PERCENT_TORQUE_DATA_POINT3");
            flipped |= 30;
        }
        if ((o & 31) == 31) {
            list.add("ENGINE_PERCENT_TORQUE_DATA_POINT4");
            flipped |= 31;
        }
        if ((o & 31) == 31) {
            list.add("LAST_SYSTEM_INDEX");
            flipped |= 31;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
