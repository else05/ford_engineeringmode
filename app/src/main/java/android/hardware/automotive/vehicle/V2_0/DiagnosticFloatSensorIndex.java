package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class DiagnosticFloatSensorIndex {
    public static final int ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE = 58;
    public static final int ABSOLUTE_LOAD_VALUE = 48;
    public static final int ABSOLUTE_THROTTLE_POSITION_B = 51;
    public static final int ABSOLUTE_THROTTLE_POSITION_C = 52;
    public static final int ACCELERATOR_PEDAL_POSITION_D = 53;
    public static final int ACCELERATOR_PEDAL_POSITION_E = 54;
    public static final int ACCELERATOR_PEDAL_POSITION_F = 55;
    public static final int CALCULATED_ENGINE_LOAD = 0;
    public static final int CATALYST_TEMPERATURE_BANK1_SENSOR1 = 44;
    public static final int CATALYST_TEMPERATURE_BANK1_SENSOR2 = 46;
    public static final int CATALYST_TEMPERATURE_BANK2_SENSOR1 = 45;
    public static final int CATALYST_TEMPERATURE_BANK2_SENSOR2 = 47;
    public static final int COMMANDED_EVAPORATIVE_PURGE = 41;
    public static final int COMMANDED_EXHAUST_GAS_RECIRCULATION = 39;
    public static final int COMMANDED_THROTTLE_ACTUATOR = 56;
    public static final int ENGINE_COOLANT_TEMPERATURE = 1;
    public static final int ENGINE_FUEL_RATE = 70;
    public static final int ENGINE_RPM = 8;
    public static final int ETHANOL_FUEL_PERCENTAGE = 57;
    public static final int EVAPORATION_SYSTEM_VAPOR_PRESSURE = 43;
    public static final int EXHAUST_GAS_RECIRCULATION_ERROR = 40;
    public static final int FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO = 49;
    public static final int FUEL_INJECTION_TIMING = 69;
    public static final int FUEL_PRESSURE = 6;
    public static final int FUEL_RAIL_GAUGE_PRESSURE = 38;
    public static final int FUEL_RAIL_PRESSURE = 37;
    public static final int FUEL_TANK_LEVEL_INPUT = 42;
    public static final int HYBRID_BATTERY_PACK_REMAINING_LIFE = 68;
    public static final int INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 7;
    public static final int LAST_SYSTEM_INDEX = 70;
    public static final int LONG_TERM_FUEL_TRIM_BANK1 = 3;
    public static final int LONG_TERM_FUEL_TRIM_BANK2 = 5;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 63;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 64;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 65;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 66;
    public static final int MAF_AIR_FLOW_RATE = 11;
    public static final int OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO = 15;
    public static final int OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM = 14;
    public static final int OXYGEN_SENSOR1_VOLTAGE = 13;
    public static final int OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO = 18;
    public static final int OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM = 17;
    public static final int OXYGEN_SENSOR2_VOLTAGE = 16;
    public static final int OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO = 21;
    public static final int OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM = 20;
    public static final int OXYGEN_SENSOR3_VOLTAGE = 19;
    public static final int OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO = 24;
    public static final int OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM = 23;
    public static final int OXYGEN_SENSOR4_VOLTAGE = 22;
    public static final int OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO = 27;
    public static final int OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM = 26;
    public static final int OXYGEN_SENSOR5_VOLTAGE = 25;
    public static final int OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO = 30;
    public static final int OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM = 29;
    public static final int OXYGEN_SENSOR6_VOLTAGE = 28;
    public static final int OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO = 33;
    public static final int OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM = 32;
    public static final int OXYGEN_SENSOR7_VOLTAGE = 31;
    public static final int OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO = 36;
    public static final int OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM = 35;
    public static final int OXYGEN_SENSOR8_VOLTAGE = 34;
    public static final int RELATIVE_ACCELERATOR_PEDAL_POSITION = 67;
    public static final int RELATIVE_THROTTLE_POSITION = 50;
    public static final int SHORT_TERM_FUEL_TRIM_BANK1 = 2;
    public static final int SHORT_TERM_FUEL_TRIM_BANK2 = 4;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 59;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 60;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 61;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 62;
    public static final int THROTTLE_POSITION = 12;
    public static final int TIMING_ADVANCE = 10;
    public static final int VEHICLE_SPEED = 9;

    public static final String toString(int o) {
        if (o == 0) {
            return "CALCULATED_ENGINE_LOAD";
        }
        if (o == 1) {
            return "ENGINE_COOLANT_TEMPERATURE";
        }
        if (o == 2) {
            return "SHORT_TERM_FUEL_TRIM_BANK1";
        }
        if (o == 3) {
            return "LONG_TERM_FUEL_TRIM_BANK1";
        }
        if (o == 4) {
            return "SHORT_TERM_FUEL_TRIM_BANK2";
        }
        if (o == 5) {
            return "LONG_TERM_FUEL_TRIM_BANK2";
        }
        if (o == 6) {
            return "FUEL_PRESSURE";
        }
        if (o == 7) {
            return "INTAKE_MANIFOLD_ABSOLUTE_PRESSURE";
        }
        if (o == 8) {
            return "ENGINE_RPM";
        }
        if (o == 9) {
            return "VEHICLE_SPEED";
        }
        if (o == 10) {
            return "TIMING_ADVANCE";
        }
        if (o == 11) {
            return "MAF_AIR_FLOW_RATE";
        }
        if (o == 12) {
            return "THROTTLE_POSITION";
        }
        if (o == 13) {
            return "OXYGEN_SENSOR1_VOLTAGE";
        }
        if (o == 14) {
            return "OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 15) {
            return "OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 16) {
            return "OXYGEN_SENSOR2_VOLTAGE";
        }
        if (o == 17) {
            return "OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 18) {
            return "OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 19) {
            return "OXYGEN_SENSOR3_VOLTAGE";
        }
        if (o == 20) {
            return "OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 21) {
            return "OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 22) {
            return "OXYGEN_SENSOR4_VOLTAGE";
        }
        if (o == 23) {
            return "OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 24) {
            return "OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 25) {
            return "OXYGEN_SENSOR5_VOLTAGE";
        }
        if (o == 26) {
            return "OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 27) {
            return "OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 28) {
            return "OXYGEN_SENSOR6_VOLTAGE";
        }
        if (o == 29) {
            return "OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 30) {
            return "OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 31) {
            return "OXYGEN_SENSOR7_VOLTAGE";
        }
        if (o == 32) {
            return "OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 33) {
            return "OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 34) {
            return "OXYGEN_SENSOR8_VOLTAGE";
        }
        if (o == 35) {
            return "OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM";
        }
        if (o == 36) {
            return "OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO";
        }
        if (o == 37) {
            return "FUEL_RAIL_PRESSURE";
        }
        if (o == 38) {
            return "FUEL_RAIL_GAUGE_PRESSURE";
        }
        if (o == 39) {
            return "COMMANDED_EXHAUST_GAS_RECIRCULATION";
        }
        if (o == 40) {
            return "EXHAUST_GAS_RECIRCULATION_ERROR";
        }
        if (o == 41) {
            return "COMMANDED_EVAPORATIVE_PURGE";
        }
        if (o == 42) {
            return "FUEL_TANK_LEVEL_INPUT";
        }
        if (o == 43) {
            return "EVAPORATION_SYSTEM_VAPOR_PRESSURE";
        }
        if (o == 44) {
            return "CATALYST_TEMPERATURE_BANK1_SENSOR1";
        }
        if (o == 45) {
            return "CATALYST_TEMPERATURE_BANK2_SENSOR1";
        }
        if (o == 46) {
            return "CATALYST_TEMPERATURE_BANK1_SENSOR2";
        }
        if (o == 47) {
            return "CATALYST_TEMPERATURE_BANK2_SENSOR2";
        }
        if (o == 48) {
            return "ABSOLUTE_LOAD_VALUE";
        }
        if (o == 49) {
            return "FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO";
        }
        if (o == 50) {
            return "RELATIVE_THROTTLE_POSITION";
        }
        if (o == 51) {
            return "ABSOLUTE_THROTTLE_POSITION_B";
        }
        if (o == 52) {
            return "ABSOLUTE_THROTTLE_POSITION_C";
        }
        if (o == 53) {
            return "ACCELERATOR_PEDAL_POSITION_D";
        }
        if (o == 54) {
            return "ACCELERATOR_PEDAL_POSITION_E";
        }
        if (o == 55) {
            return "ACCELERATOR_PEDAL_POSITION_F";
        }
        if (o == 56) {
            return "COMMANDED_THROTTLE_ACTUATOR";
        }
        if (o == 57) {
            return "ETHANOL_FUEL_PERCENTAGE";
        }
        if (o == 58) {
            return "ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE";
        }
        if (o == 59) {
            return "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1";
        }
        if (o == 60) {
            return "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2";
        }
        if (o == 61) {
            return "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3";
        }
        if (o == 62) {
            return "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4";
        }
        if (o == 63) {
            return "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1";
        }
        if (o == 64) {
            return "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2";
        }
        if (o == 65) {
            return "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3";
        }
        if (o == 66) {
            return "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4";
        }
        if (o == 67) {
            return "RELATIVE_ACCELERATOR_PEDAL_POSITION";
        }
        if (o == 68) {
            return "HYBRID_BATTERY_PACK_REMAINING_LIFE";
        }
        if (o == 69) {
            return "FUEL_INJECTION_TIMING";
        }
        if (o == 70) {
            return "ENGINE_FUEL_RATE";
        }
        if (o == 70) {
            return "LAST_SYSTEM_INDEX";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("CALCULATED_ENGINE_LOAD");
        if ((o & 1) == 1) {
            list.add("ENGINE_COOLANT_TEMPERATURE");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("SHORT_TERM_FUEL_TRIM_BANK1");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("LONG_TERM_FUEL_TRIM_BANK1");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("SHORT_TERM_FUEL_TRIM_BANK2");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("LONG_TERM_FUEL_TRIM_BANK2");
            flipped |= 5;
        }
        if ((o & 6) == 6) {
            list.add("FUEL_PRESSURE");
            flipped |= 6;
        }
        if ((o & 7) == 7) {
            list.add("INTAKE_MANIFOLD_ABSOLUTE_PRESSURE");
            flipped |= 7;
        }
        if ((o & 8) == 8) {
            list.add("ENGINE_RPM");
            flipped |= 8;
        }
        if ((o & 9) == 9) {
            list.add("VEHICLE_SPEED");
            flipped |= 9;
        }
        if ((o & 10) == 10) {
            list.add("TIMING_ADVANCE");
            flipped |= 10;
        }
        if ((o & 11) == 11) {
            list.add("MAF_AIR_FLOW_RATE");
            flipped |= 11;
        }
        if ((o & 12) == 12) {
            list.add("THROTTLE_POSITION");
            flipped |= 12;
        }
        if ((o & 13) == 13) {
            list.add("OXYGEN_SENSOR1_VOLTAGE");
            flipped |= 13;
        }
        if ((o & 14) == 14) {
            list.add("OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM");
            flipped |= 14;
        }
        if ((o & 15) == 15) {
            list.add("OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 15;
        }
        if ((o & 16) == 16) {
            list.add("OXYGEN_SENSOR2_VOLTAGE");
            flipped |= 16;
        }
        if ((o & 17) == 17) {
            list.add("OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM");
            flipped |= 17;
        }
        if ((o & 18) == 18) {
            list.add("OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 18;
        }
        if ((o & 19) == 19) {
            list.add("OXYGEN_SENSOR3_VOLTAGE");
            flipped |= 19;
        }
        if ((o & 20) == 20) {
            list.add("OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM");
            flipped |= 20;
        }
        if ((o & 21) == 21) {
            list.add("OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 21;
        }
        if ((o & 22) == 22) {
            list.add("OXYGEN_SENSOR4_VOLTAGE");
            flipped |= 22;
        }
        if ((o & 23) == 23) {
            list.add("OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM");
            flipped |= 23;
        }
        if ((o & 24) == 24) {
            list.add("OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 24;
        }
        if ((o & 25) == 25) {
            list.add("OXYGEN_SENSOR5_VOLTAGE");
            flipped |= 25;
        }
        if ((o & 26) == 26) {
            list.add("OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM");
            flipped |= 26;
        }
        if ((o & 27) == 27) {
            list.add("OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 27;
        }
        if ((o & 28) == 28) {
            list.add("OXYGEN_SENSOR6_VOLTAGE");
            flipped |= 28;
        }
        if ((o & 29) == 29) {
            list.add("OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM");
            flipped |= 29;
        }
        if ((o & 30) == 30) {
            list.add("OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 30;
        }
        if ((o & 31) == 31) {
            list.add("OXYGEN_SENSOR7_VOLTAGE");
            flipped |= 31;
        }
        if ((o & 32) == 32) {
            list.add("OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM");
            flipped |= 32;
        }
        if ((o & 33) == 33) {
            list.add("OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 33;
        }
        if ((o & 34) == 34) {
            list.add("OXYGEN_SENSOR8_VOLTAGE");
            flipped |= 34;
        }
        if ((o & 35) == 35) {
            list.add("OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM");
            flipped |= 35;
        }
        if ((o & 36) == 36) {
            list.add("OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO");
            flipped |= 36;
        }
        if ((o & 37) == 37) {
            list.add("FUEL_RAIL_PRESSURE");
            flipped |= 37;
        }
        if ((o & 38) == 38) {
            list.add("FUEL_RAIL_GAUGE_PRESSURE");
            flipped |= 38;
        }
        if ((o & 39) == 39) {
            list.add("COMMANDED_EXHAUST_GAS_RECIRCULATION");
            flipped |= 39;
        }
        if ((o & 40) == 40) {
            list.add("EXHAUST_GAS_RECIRCULATION_ERROR");
            flipped |= 40;
        }
        if ((o & 41) == 41) {
            list.add("COMMANDED_EVAPORATIVE_PURGE");
            flipped |= 41;
        }
        if ((o & 42) == 42) {
            list.add("FUEL_TANK_LEVEL_INPUT");
            flipped |= 42;
        }
        if ((o & 43) == 43) {
            list.add("EVAPORATION_SYSTEM_VAPOR_PRESSURE");
            flipped |= 43;
        }
        if ((o & 44) == 44) {
            list.add("CATALYST_TEMPERATURE_BANK1_SENSOR1");
            flipped |= 44;
        }
        if ((o & 45) == 45) {
            list.add("CATALYST_TEMPERATURE_BANK2_SENSOR1");
            flipped |= 45;
        }
        if ((o & 46) == 46) {
            list.add("CATALYST_TEMPERATURE_BANK1_SENSOR2");
            flipped |= 46;
        }
        if ((o & 47) == 47) {
            list.add("CATALYST_TEMPERATURE_BANK2_SENSOR2");
            flipped |= 47;
        }
        if ((o & 48) == 48) {
            list.add("ABSOLUTE_LOAD_VALUE");
            flipped |= 48;
        }
        if ((o & 49) == 49) {
            list.add("FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO");
            flipped |= 49;
        }
        if ((o & 50) == 50) {
            list.add("RELATIVE_THROTTLE_POSITION");
            flipped |= 50;
        }
        if ((o & 51) == 51) {
            list.add("ABSOLUTE_THROTTLE_POSITION_B");
            flipped |= 51;
        }
        if ((o & 52) == 52) {
            list.add("ABSOLUTE_THROTTLE_POSITION_C");
            flipped |= 52;
        }
        if ((o & 53) == 53) {
            list.add("ACCELERATOR_PEDAL_POSITION_D");
            flipped |= 53;
        }
        if ((o & 54) == 54) {
            list.add("ACCELERATOR_PEDAL_POSITION_E");
            flipped |= 54;
        }
        if ((o & 55) == 55) {
            list.add("ACCELERATOR_PEDAL_POSITION_F");
            flipped |= 55;
        }
        if ((o & 56) == 56) {
            list.add("COMMANDED_THROTTLE_ACTUATOR");
            flipped |= 56;
        }
        if ((o & 57) == 57) {
            list.add("ETHANOL_FUEL_PERCENTAGE");
            flipped |= 57;
        }
        if ((o & 58) == 58) {
            list.add("ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE");
            flipped |= 58;
        }
        if ((o & 59) == 59) {
            list.add("SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1");
            flipped |= 59;
        }
        if ((o & 60) == 60) {
            list.add("SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2");
            flipped |= 60;
        }
        if ((o & 61) == 61) {
            list.add("SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3");
            flipped |= 61;
        }
        if ((o & 62) == 62) {
            list.add("SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4");
            flipped |= 62;
        }
        if ((o & 63) == 63) {
            list.add("LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1");
            flipped |= 63;
        }
        if ((o & 64) == 64) {
            list.add("LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2");
            flipped |= 64;
        }
        if ((o & 65) == 65) {
            list.add("LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3");
            flipped |= 65;
        }
        if ((o & 66) == 66) {
            list.add("LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4");
            flipped |= 66;
        }
        if ((o & 67) == 67) {
            list.add("RELATIVE_ACCELERATOR_PEDAL_POSITION");
            flipped |= 67;
        }
        if ((o & 68) == 68) {
            list.add("HYBRID_BATTERY_PACK_REMAINING_LIFE");
            flipped |= 68;
        }
        if ((o & 69) == 69) {
            list.add("FUEL_INJECTION_TIMING");
            flipped |= 69;
        }
        if ((o & 70) == 70) {
            list.add("ENGINE_FUEL_RATE");
            flipped |= 70;
        }
        if ((o & 70) == 70) {
            list.add("LAST_SYSTEM_INDEX");
            flipped |= 70;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
