package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class VehicleProperty {
    public static final int ABS_ACTIVE = 287310858;
    public static final int AP_POWER_BOOTUP_REASON = 289409538;
    public static final int AP_POWER_STATE = 289475072;
    public static final int AUDIO_ATTENUATION_CONTROL = 289409288;
    public static final int AUDIO_ATTENUATION_EVENT = 289409289;
    public static final int AUDIO_EXT_ROUTING_HINT = 289474821;
    public static final int AUDIO_FOCUS = 289474816;
    public static final int AUDIO_FOCUS_EXT_SYNC = 289474832;
    public static final int AUDIO_HW_VARIANT = 289409284;
    public static final int AUDIO_PARAMETERS = 286263559;
    public static final int AUDIO_ROUTING_POLICY = 289474819;
    public static final int AUDIO_STREAM_STATE = 289474822;
    public static final int AUDIO_VOLUME = 289474817;
    public static final int AUDIO_VOLUME_EXT_SYNC = 289474833;
    public static final int AUDIO_VOLUME_LIMIT = 289474818;
    public static final int CURRENT_GEAR = 289408001;
    public static final int CURRENT_TIME_IN_SECONDS = 289409585;
    public static final int DISPLAY_BRIGHTNESS = 289409537;
    public static final int DOOR_LOCK = 371198722;
    public static final int DOOR_MOVE = 373295873;
    public static final int DOOR_POS = 373295872;
    public static final int DRIVING_STATUS = 289408004;
    public static final int ENGINE_COOLANT_TEMP = 291504897;
    public static final int ENGINE_OIL_TEMP = 291504900;
    public static final int ENGINE_RPM = 291504901;
    public static final int ENV_CABIN_TEMPERATURE = 291505924;
    public static final int ENV_OUTSIDE_TEMPERATURE = 291505923;
    public static final int FUEL_LEVEL_IN_DISTENCE = 291505427;
    public static final int FUEL_LEVEL_IN_PERCENT = 291505428;
    public static final int FUEL_LEVEL_LOW = 289408005;
    public static final int FUEL_LEVEL_STATE = 291505158;
    public static final int GEAR_SELECTION = 289408000;
    public static final int HVAC_ACTUAL_FAN_SPEED_RPM = 306185487;
    public static final int HVAC_AC_ON = 306185477;
    public static final int HVAC_AUTO_ON = 306185482;
    public static final int HVAC_AUTO_RECIRC_ON = 304088338;
    public static final int HVAC_DEFROSTER = 322962692;
    public static final int HVAC_DUAL_ON = 306185481;
    public static final int HVAC_FAN_DIRECTION = 306185473;
    public static final int HVAC_FAN_DIRECTION_AVAILABLE = 306185489;
    public static final int HVAC_FAN_SPEED = 306185472;
    public static final int HVAC_MAX_AC_ON = 306185478;
    public static final int HVAC_MAX_DEFROST_ON = 322962695;
    public static final int HVAC_POWER_ON = 306185488;
    public static final int HVAC_RECIRC_ON = 306185480;
    public static final int HVAC_SEAT_TEMPERATURE = 356517131;
    public static final int HVAC_SIDE_MIRROR_HEAT = 339739916;
    public static final int HVAC_STEERING_WHEEL_TEMP = 289408269;
    public static final int HVAC_TEMPERATURE_CURRENT = 308282626;
    public static final int HVAC_TEMPERATURE_SET = 308282627;
    public static final int HVAC_TEMPERATURE_UNITS = 289408270;
    public static final int HW_KEY_INPUT = 289475088;
    public static final int IGNITION_STATE = 289408009;
    public static final int INFO_FUEL_CAPACITY = 291504388;
    public static final int INFO_MAKE = 286261505;
    public static final int INFO_MODEL = 286261506;
    public static final int INFO_MODEL_YEAR = 289407235;
    public static final int INFO_VIN = 286261504;
    public static final int INSTRUMENT_CLUSTER_INFO = 289475104;
    public static final int INVALID = 0;
    public static final int MIRROR_FOLD = 287312709;
    public static final int MIRROR_LOCK = 287312708;
    public static final int MIRROR_Y_MOVE = 339741507;
    public static final int MIRROR_Y_POS = 339741506;
    public static final int MIRROR_Z_MOVE = 339741505;
    public static final int MIRROR_Z_POS = 339741504;
    public static final int NIGHT_MODE = 287310855;
    public static final int OBD2_FREEZE_FRAME = 299896065;
    public static final int OBD2_FREEZE_FRAME_CLEAR = 299896067;
    public static final int OBD2_FREEZE_FRAME_INFO = 299896066;
    public static final int OBD2_LIVE_FRAME = 299896064;
    public static final int PARKING_BRAKE_ON = 287310850;
    public static final int PERF_ODOMETER = 291504644;
    public static final int PERF_VEHICLE_SPEED = 291504647;
    public static final int RADIO_PRESET = 289474561;
    public static final int SEAT_BACKREST_ANGLE_1_MOVE = 356518792;
    public static final int SEAT_BACKREST_ANGLE_1_POS = 356518791;
    public static final int SEAT_BACKREST_ANGLE_2_MOVE = 356518794;
    public static final int SEAT_BACKREST_ANGLE_2_POS = 356518793;
    public static final int SEAT_BELT_BUCKLED = 354421634;
    public static final int SEAT_BELT_HEIGHT_MOVE = 356518788;
    public static final int SEAT_BELT_HEIGHT_POS = 356518787;
    public static final int SEAT_DEPTH_MOVE = 356518798;
    public static final int SEAT_DEPTH_POS = 356518797;
    public static final int SEAT_FORE_AFT_MOVE = 356518790;
    public static final int SEAT_FORE_AFT_POS = 356518789;
    public static final int SEAT_HEADREST_ANGLE_MOVE = 356518808;
    public static final int SEAT_HEADREST_ANGLE_POS = 356518807;
    public static final int SEAT_HEADREST_FORE_AFT_MOVE = 356518810;
    public static final int SEAT_HEADREST_FORE_AFT_POS = 356518809;
    public static final int SEAT_HEADREST_HEIGHT_MOVE = 356518806;
    public static final int SEAT_HEADREST_HEIGHT_POS = 289409941;
    public static final int SEAT_HEIGHT_MOVE = 356518796;
    public static final int SEAT_HEIGHT_POS = 356518795;
    public static final int SEAT_LUMBAR_FORE_AFT_MOVE = 356518802;
    public static final int SEAT_LUMBAR_FORE_AFT_POS = 356518801;
    public static final int SEAT_LUMBAR_SIDE_SUPPORT_MOVE = 356518804;
    public static final int SEAT_LUMBAR_SIDE_SUPPORT_POS = 356518803;
    public static final int SEAT_MEMORY_SELECT = 356518784;
    public static final int SEAT_MEMORY_SET = 356518785;
    public static final int SEAT_TILT_MOVE = 356518800;
    public static final int SEAT_TILT_POS = 356518799;
    public static final int TRACTION_CONTROL_ACTIVE = 287310859;
    public static final int TURN_SIGNAL_STATE = 289408008;
    public static final int UNIX_TIME = 290458160;
    public static final int VEHICLE_MAP_SERVICE = 299895808;
    public static final int WHEEL_TICK = 299893510;
    public static final int WINDOW_LOCK = 287312836;
    public static final int WINDOW_MOVE = 289409985;
    public static final int WINDOW_POS = 289409984;
    public static final int WINDOW_VENT_MOVE = 289409987;
    public static final int WINDOW_VENT_POS = 289409986;

    public static final String toString(int o) {
        if (o == 0) {
            return "INVALID";
        }
        if (o == 286261504) {
            return "INFO_VIN";
        }
        if (o == 286261505) {
            return "INFO_MAKE";
        }
        if (o == 286261506) {
            return "INFO_MODEL";
        }
        if (o == 289407235) {
            return "INFO_MODEL_YEAR";
        }
        if (o == 291504388) {
            return "INFO_FUEL_CAPACITY";
        }
        if (o == 291504644) {
            return "PERF_ODOMETER";
        }
        if (o == 291504647) {
            return "PERF_VEHICLE_SPEED";
        }
        if (o == 291504897) {
            return "ENGINE_COOLANT_TEMP";
        }
        if (o == 291504900) {
            return "ENGINE_OIL_TEMP";
        }
        if (o == 291504901) {
            return "ENGINE_RPM";
        }
        if (o == 299893510) {
            return "WHEEL_TICK";
        }
        if (o == 289408000) {
            return "GEAR_SELECTION";
        }
        if (o == 289408001) {
            return "CURRENT_GEAR";
        }
        if (o == 287310850) {
            return "PARKING_BRAKE_ON";
        }
        if (o == 289408004) {
            return "DRIVING_STATUS";
        }
        if (o == 289408005) {
            return "FUEL_LEVEL_LOW";
        }
        if (o == 291505158) {
            return "FUEL_LEVEL_STATE";
        }
        if (o == 291505427) {
            return "FUEL_LEVEL_IN_DISTENCE";
        }
        if (o == 291505428) {
            return "FUEL_LEVEL_IN_PERCENT";
        }
        if (o == 287310855) {
            return "NIGHT_MODE";
        }
        if (o == 289408008) {
            return "TURN_SIGNAL_STATE";
        }
        if (o == 289408009) {
            return "IGNITION_STATE";
        }
        if (o == 287310858) {
            return "ABS_ACTIVE";
        }
        if (o == 287310859) {
            return "TRACTION_CONTROL_ACTIVE";
        }
        if (o == 306185472) {
            return "HVAC_FAN_SPEED";
        }
        if (o == 306185473) {
            return "HVAC_FAN_DIRECTION";
        }
        if (o == 308282626) {
            return "HVAC_TEMPERATURE_CURRENT";
        }
        if (o == 308282627) {
            return "HVAC_TEMPERATURE_SET";
        }
        if (o == 322962692) {
            return "HVAC_DEFROSTER";
        }
        if (o == 306185477) {
            return "HVAC_AC_ON";
        }
        if (o == 306185478) {
            return "HVAC_MAX_AC_ON";
        }
        if (o == 322962695) {
            return "HVAC_MAX_DEFROST_ON";
        }
        if (o == 306185480) {
            return "HVAC_RECIRC_ON";
        }
        if (o == 306185481) {
            return "HVAC_DUAL_ON";
        }
        if (o == 306185482) {
            return "HVAC_AUTO_ON";
        }
        if (o == 356517131) {
            return "HVAC_SEAT_TEMPERATURE";
        }
        if (o == 339739916) {
            return "HVAC_SIDE_MIRROR_HEAT";
        }
        if (o == 289408269) {
            return "HVAC_STEERING_WHEEL_TEMP";
        }
        if (o == 289408270) {
            return "HVAC_TEMPERATURE_UNITS";
        }
        if (o == 306185487) {
            return "HVAC_ACTUAL_FAN_SPEED_RPM";
        }
        if (o == 306185488) {
            return "HVAC_POWER_ON";
        }
        if (o == 306185489) {
            return "HVAC_FAN_DIRECTION_AVAILABLE";
        }
        if (o == 304088338) {
            return "HVAC_AUTO_RECIRC_ON";
        }
        if (o == 291505923) {
            return "ENV_OUTSIDE_TEMPERATURE";
        }
        if (o == 291505924) {
            return "ENV_CABIN_TEMPERATURE";
        }
        if (o == 289474561) {
            return "RADIO_PRESET";
        }
        if (o == 289474816) {
            return "AUDIO_FOCUS";
        }
        if (o == 289474832) {
            return "AUDIO_FOCUS_EXT_SYNC";
        }
        if (o == 289474817) {
            return "AUDIO_VOLUME";
        }
        if (o == 289474833) {
            return "AUDIO_VOLUME_EXT_SYNC";
        }
        if (o == 289474818) {
            return "AUDIO_VOLUME_LIMIT";
        }
        if (o == 289474819) {
            return "AUDIO_ROUTING_POLICY";
        }
        if (o == 289409284) {
            return "AUDIO_HW_VARIANT";
        }
        if (o == 289474821) {
            return "AUDIO_EXT_ROUTING_HINT";
        }
        if (o == 289474822) {
            return "AUDIO_STREAM_STATE";
        }
        if (o == 286263559) {
            return "AUDIO_PARAMETERS";
        }
        if (o == 289409288) {
            return "AUDIO_ATTENUATION_CONTROL";
        }
        if (o == 289409289) {
            return "AUDIO_ATTENUATION_EVENT";
        }
        if (o == 289475072) {
            return "AP_POWER_STATE";
        }
        if (o == 289409537) {
            return "DISPLAY_BRIGHTNESS";
        }
        if (o == 289409538) {
            return "AP_POWER_BOOTUP_REASON";
        }
        if (o == 289475088) {
            return "HW_KEY_INPUT";
        }
        if (o == 289475104) {
            return "INSTRUMENT_CLUSTER_INFO";
        }
        if (o == 290458160) {
            return "UNIX_TIME";
        }
        if (o == 289409585) {
            return "CURRENT_TIME_IN_SECONDS";
        }
        if (o == 373295872) {
            return "DOOR_POS";
        }
        if (o == 373295873) {
            return "DOOR_MOVE";
        }
        if (o == 371198722) {
            return "DOOR_LOCK";
        }
        if (o == 339741504) {
            return "MIRROR_Z_POS";
        }
        if (o == 339741505) {
            return "MIRROR_Z_MOVE";
        }
        if (o == 339741506) {
            return "MIRROR_Y_POS";
        }
        if (o == 339741507) {
            return "MIRROR_Y_MOVE";
        }
        if (o == 287312708) {
            return "MIRROR_LOCK";
        }
        if (o == 287312709) {
            return "MIRROR_FOLD";
        }
        if (o == 356518784) {
            return "SEAT_MEMORY_SELECT";
        }
        if (o == 356518785) {
            return "SEAT_MEMORY_SET";
        }
        if (o == 354421634) {
            return "SEAT_BELT_BUCKLED";
        }
        if (o == 356518787) {
            return "SEAT_BELT_HEIGHT_POS";
        }
        if (o == 356518788) {
            return "SEAT_BELT_HEIGHT_MOVE";
        }
        if (o == 356518789) {
            return "SEAT_FORE_AFT_POS";
        }
        if (o == 356518790) {
            return "SEAT_FORE_AFT_MOVE";
        }
        if (o == 356518791) {
            return "SEAT_BACKREST_ANGLE_1_POS";
        }
        if (o == 356518792) {
            return "SEAT_BACKREST_ANGLE_1_MOVE";
        }
        if (o == 356518793) {
            return "SEAT_BACKREST_ANGLE_2_POS";
        }
        if (o == 356518794) {
            return "SEAT_BACKREST_ANGLE_2_MOVE";
        }
        if (o == 356518795) {
            return "SEAT_HEIGHT_POS";
        }
        if (o == 356518796) {
            return "SEAT_HEIGHT_MOVE";
        }
        if (o == 356518797) {
            return "SEAT_DEPTH_POS";
        }
        if (o == 356518798) {
            return "SEAT_DEPTH_MOVE";
        }
        if (o == 356518799) {
            return "SEAT_TILT_POS";
        }
        if (o == 356518800) {
            return "SEAT_TILT_MOVE";
        }
        if (o == 356518801) {
            return "SEAT_LUMBAR_FORE_AFT_POS";
        }
        if (o == 356518802) {
            return "SEAT_LUMBAR_FORE_AFT_MOVE";
        }
        if (o == 356518803) {
            return "SEAT_LUMBAR_SIDE_SUPPORT_POS";
        }
        if (o == 356518804) {
            return "SEAT_LUMBAR_SIDE_SUPPORT_MOVE";
        }
        if (o == 289409941) {
            return "SEAT_HEADREST_HEIGHT_POS";
        }
        if (o == 356518806) {
            return "SEAT_HEADREST_HEIGHT_MOVE";
        }
        if (o == 356518807) {
            return "SEAT_HEADREST_ANGLE_POS";
        }
        if (o == 356518808) {
            return "SEAT_HEADREST_ANGLE_MOVE";
        }
        if (o == 356518809) {
            return "SEAT_HEADREST_FORE_AFT_POS";
        }
        if (o == 356518810) {
            return "SEAT_HEADREST_FORE_AFT_MOVE";
        }
        if (o == 289409984) {
            return "WINDOW_POS";
        }
        if (o == 289409985) {
            return "WINDOW_MOVE";
        }
        if (o == 289409986) {
            return "WINDOW_VENT_POS";
        }
        if (o == 289409987) {
            return "WINDOW_VENT_MOVE";
        }
        if (o == 287312836) {
            return "WINDOW_LOCK";
        }
        if (o == 299895808) {
            return "VEHICLE_MAP_SERVICE";
        }
        if (o == 299896064) {
            return "OBD2_LIVE_FRAME";
        }
        if (o == 299896065) {
            return "OBD2_FREEZE_FRAME";
        }
        if (o == 299896066) {
            return "OBD2_FREEZE_FRAME_INFO";
        }
        if (o == 299896067) {
            return "OBD2_FREEZE_FRAME_CLEAR";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("INVALID");
        if ((o & 286261504) == 286261504) {
            list.add("INFO_VIN");
            flipped = 0 | 286261504;
        }
        if ((o & 286261505) == 286261505) {
            list.add("INFO_MAKE");
            flipped |= 286261505;
        }
        if ((o & 286261506) == 286261506) {
            list.add("INFO_MODEL");
            flipped |= 286261506;
        }
        if ((o & 289407235) == 289407235) {
            list.add("INFO_MODEL_YEAR");
            flipped |= 289407235;
        }
        if ((o & 291504388) == 291504388) {
            list.add("INFO_FUEL_CAPACITY");
            flipped |= 291504388;
        }
        if ((o & 291504644) == 291504644) {
            list.add("PERF_ODOMETER");
            flipped |= 291504644;
        }
        if ((o & 291504647) == 291504647) {
            list.add("PERF_VEHICLE_SPEED");
            flipped |= 291504647;
        }
        if ((o & 291504897) == 291504897) {
            list.add("ENGINE_COOLANT_TEMP");
            flipped |= 291504897;
        }
        if ((o & 291504900) == 291504900) {
            list.add("ENGINE_OIL_TEMP");
            flipped |= 291504900;
        }
        if ((o & 291504901) == 291504901) {
            list.add("ENGINE_RPM");
            flipped |= 291504901;
        }
        if ((o & 299893510) == 299893510) {
            list.add("WHEEL_TICK");
            flipped |= 299893510;
        }
        if ((o & 289408000) == 289408000) {
            list.add("GEAR_SELECTION");
            flipped |= 289408000;
        }
        if ((o & 289408001) == 289408001) {
            list.add("CURRENT_GEAR");
            flipped |= 289408001;
        }
        if ((o & 287310850) == 287310850) {
            list.add("PARKING_BRAKE_ON");
            flipped |= 287310850;
        }
        if ((o & 289408004) == 289408004) {
            list.add("DRIVING_STATUS");
            flipped |= 289408004;
        }
        if ((289408005 & o) == 289408005) {
            list.add("FUEL_LEVEL_LOW");
            flipped |= 289408005;
        }
        if ((291505158 & o) == 291505158) {
            list.add("FUEL_LEVEL_STATE");
            flipped |= 291505158;
        }
        if ((291505427 & o) == 291505427) {
            list.add("FUEL_LEVEL_IN_DISTENCE");
            flipped |= 291505427;
        }
        if ((291505428 & o) == 291505428) {
            list.add("FUEL_LEVEL_IN_PERCENT");
            flipped |= 291505428;
        }
        if ((287310855 & o) == 287310855) {
            list.add("NIGHT_MODE");
            flipped |= 287310855;
        }
        if ((289408008 & o) == 289408008) {
            list.add("TURN_SIGNAL_STATE");
            flipped |= 289408008;
        }
        if ((289408009 & o) == 289408009) {
            list.add("IGNITION_STATE");
            flipped |= 289408009;
        }
        if ((287310858 & o) == 287310858) {
            list.add("ABS_ACTIVE");
            flipped |= 287310858;
        }
        if ((287310859 & o) == 287310859) {
            list.add("TRACTION_CONTROL_ACTIVE");
            flipped |= 287310859;
        }
        if ((306185472 & o) == 306185472) {
            list.add("HVAC_FAN_SPEED");
            flipped |= 306185472;
        }
        if ((306185473 & o) == 306185473) {
            list.add("HVAC_FAN_DIRECTION");
            flipped |= 306185473;
        }
        if ((308282626 & o) == 308282626) {
            list.add("HVAC_TEMPERATURE_CURRENT");
            flipped |= 308282626;
        }
        if ((308282627 & o) == 308282627) {
            list.add("HVAC_TEMPERATURE_SET");
            flipped |= 308282627;
        }
        if ((322962692 & o) == 322962692) {
            list.add("HVAC_DEFROSTER");
            flipped |= 322962692;
        }
        if ((306185477 & o) == 306185477) {
            list.add("HVAC_AC_ON");
            flipped |= 306185477;
        }
        if ((306185478 & o) == 306185478) {
            list.add("HVAC_MAX_AC_ON");
            flipped |= 306185478;
        }
        if ((322962695 & o) == 322962695) {
            list.add("HVAC_MAX_DEFROST_ON");
            flipped |= 322962695;
        }
        if ((306185480 & o) == 306185480) {
            list.add("HVAC_RECIRC_ON");
            flipped |= 306185480;
        }
        if ((306185481 & o) == 306185481) {
            list.add("HVAC_DUAL_ON");
            flipped |= 306185481;
        }
        if ((306185482 & o) == 306185482) {
            list.add("HVAC_AUTO_ON");
            flipped |= 306185482;
        }
        if ((356517131 & o) == 356517131) {
            list.add("HVAC_SEAT_TEMPERATURE");
            flipped |= 356517131;
        }
        if ((339739916 & o) == 339739916) {
            list.add("HVAC_SIDE_MIRROR_HEAT");
            flipped |= 339739916;
        }
        if ((289408269 & o) == 289408269) {
            list.add("HVAC_STEERING_WHEEL_TEMP");
            flipped |= 289408269;
        }
        if ((289408270 & o) == 289408270) {
            list.add("HVAC_TEMPERATURE_UNITS");
            flipped |= 289408270;
        }
        if ((306185487 & o) == 306185487) {
            list.add("HVAC_ACTUAL_FAN_SPEED_RPM");
            flipped |= 306185487;
        }
        if ((306185488 & o) == 306185488) {
            list.add("HVAC_POWER_ON");
            flipped |= 306185488;
        }
        if ((306185489 & o) == 306185489) {
            list.add("HVAC_FAN_DIRECTION_AVAILABLE");
            flipped |= 306185489;
        }
        if ((304088338 & o) == 304088338) {
            list.add("HVAC_AUTO_RECIRC_ON");
            flipped |= 304088338;
        }
        if ((291505923 & o) == 291505923) {
            list.add("ENV_OUTSIDE_TEMPERATURE");
            flipped |= 291505923;
        }
        if ((291505924 & o) == 291505924) {
            list.add("ENV_CABIN_TEMPERATURE");
            flipped |= 291505924;
        }
        if ((289474561 & o) == 289474561) {
            list.add("RADIO_PRESET");
            flipped |= 289474561;
        }
        if ((289474816 & o) == 289474816) {
            list.add("AUDIO_FOCUS");
            flipped |= 289474816;
        }
        if ((289474832 & o) == 289474832) {
            list.add("AUDIO_FOCUS_EXT_SYNC");
            flipped |= 289474832;
        }
        if ((289474817 & o) == 289474817) {
            list.add("AUDIO_VOLUME");
            flipped |= 289474817;
        }
        if ((289474833 & o) == 289474833) {
            list.add("AUDIO_VOLUME_EXT_SYNC");
            flipped |= 289474833;
        }
        if ((289474818 & o) == 289474818) {
            list.add("AUDIO_VOLUME_LIMIT");
            flipped |= 289474818;
        }
        if ((289474819 & o) == 289474819) {
            list.add("AUDIO_ROUTING_POLICY");
            flipped |= 289474819;
        }
        if ((289409284 & o) == 289409284) {
            list.add("AUDIO_HW_VARIANT");
            flipped |= 289409284;
        }
        if ((289474821 & o) == 289474821) {
            list.add("AUDIO_EXT_ROUTING_HINT");
            flipped |= 289474821;
        }
        if ((289474822 & o) == 289474822) {
            list.add("AUDIO_STREAM_STATE");
            flipped |= 289474822;
        }
        if ((286263559 & o) == 286263559) {
            list.add("AUDIO_PARAMETERS");
            flipped |= 286263559;
        }
        if ((289409288 & o) == 289409288) {
            list.add("AUDIO_ATTENUATION_CONTROL");
            flipped |= 289409288;
        }
        if ((289409289 & o) == 289409289) {
            list.add("AUDIO_ATTENUATION_EVENT");
            flipped |= 289409289;
        }
        if ((289475072 & o) == 289475072) {
            list.add("AP_POWER_STATE");
            flipped |= 289475072;
        }
        if ((289409537 & o) == 289409537) {
            list.add("DISPLAY_BRIGHTNESS");
            flipped |= 289409537;
        }
        if ((289409538 & o) == 289409538) {
            list.add("AP_POWER_BOOTUP_REASON");
            flipped |= 289409538;
        }
        if ((289475088 & o) == 289475088) {
            list.add("HW_KEY_INPUT");
            flipped |= 289475088;
        }
        if ((289475104 & o) == 289475104) {
            list.add("INSTRUMENT_CLUSTER_INFO");
            flipped |= 289475104;
        }
        if ((290458160 & o) == 290458160) {
            list.add("UNIX_TIME");
            flipped |= 290458160;
        }
        if ((289409585 & o) == 289409585) {
            list.add("CURRENT_TIME_IN_SECONDS");
            flipped |= 289409585;
        }
        if ((373295872 & o) == 373295872) {
            list.add("DOOR_POS");
            flipped |= 373295872;
        }
        if ((373295873 & o) == 373295873) {
            list.add("DOOR_MOVE");
            flipped |= 373295873;
        }
        if ((371198722 & o) == 371198722) {
            list.add("DOOR_LOCK");
            flipped |= 371198722;
        }
        if ((339741504 & o) == 339741504) {
            list.add("MIRROR_Z_POS");
            flipped |= 339741504;
        }
        if ((339741505 & o) == 339741505) {
            list.add("MIRROR_Z_MOVE");
            flipped |= 339741505;
        }
        if ((339741506 & o) == 339741506) {
            list.add("MIRROR_Y_POS");
            flipped |= 339741506;
        }
        if ((339741507 & o) == 339741507) {
            list.add("MIRROR_Y_MOVE");
            flipped |= 339741507;
        }
        if ((287312708 & o) == 287312708) {
            list.add("MIRROR_LOCK");
            flipped |= 287312708;
        }
        if ((287312709 & o) == 287312709) {
            list.add("MIRROR_FOLD");
            flipped |= 287312709;
        }
        if ((356518784 & o) == 356518784) {
            list.add("SEAT_MEMORY_SELECT");
            flipped |= 356518784;
        }
        if ((356518785 & o) == 356518785) {
            list.add("SEAT_MEMORY_SET");
            flipped |= 356518785;
        }
        if ((354421634 & o) == 354421634) {
            list.add("SEAT_BELT_BUCKLED");
            flipped |= 354421634;
        }
        if ((356518787 & o) == 356518787) {
            list.add("SEAT_BELT_HEIGHT_POS");
            flipped |= 356518787;
        }
        if ((356518788 & o) == 356518788) {
            list.add("SEAT_BELT_HEIGHT_MOVE");
            flipped |= 356518788;
        }
        if ((356518789 & o) == 356518789) {
            list.add("SEAT_FORE_AFT_POS");
            flipped |= 356518789;
        }
        if ((356518790 & o) == 356518790) {
            list.add("SEAT_FORE_AFT_MOVE");
            flipped |= 356518790;
        }
        if ((356518791 & o) == 356518791) {
            list.add("SEAT_BACKREST_ANGLE_1_POS");
            flipped |= 356518791;
        }
        if ((356518792 & o) == 356518792) {
            list.add("SEAT_BACKREST_ANGLE_1_MOVE");
            flipped |= 356518792;
        }
        if ((356518793 & o) == 356518793) {
            list.add("SEAT_BACKREST_ANGLE_2_POS");
            flipped |= 356518793;
        }
        if ((356518794 & o) == 356518794) {
            list.add("SEAT_BACKREST_ANGLE_2_MOVE");
            flipped |= 356518794;
        }
        if ((356518795 & o) == 356518795) {
            list.add("SEAT_HEIGHT_POS");
            flipped |= 356518795;
        }
        if ((356518796 & o) == 356518796) {
            list.add("SEAT_HEIGHT_MOVE");
            flipped |= 356518796;
        }
        if ((356518797 & o) == 356518797) {
            list.add("SEAT_DEPTH_POS");
            flipped |= 356518797;
        }
        if ((356518798 & o) == 356518798) {
            list.add("SEAT_DEPTH_MOVE");
            flipped |= 356518798;
        }
        if ((356518799 & o) == 356518799) {
            list.add("SEAT_TILT_POS");
            flipped |= 356518799;
        }
        if ((356518800 & o) == 356518800) {
            list.add("SEAT_TILT_MOVE");
            flipped |= 356518800;
        }
        if ((356518801 & o) == 356518801) {
            list.add("SEAT_LUMBAR_FORE_AFT_POS");
            flipped |= 356518801;
        }
        if ((356518802 & o) == 356518802) {
            list.add("SEAT_LUMBAR_FORE_AFT_MOVE");
            flipped |= 356518802;
        }
        if ((356518803 & o) == 356518803) {
            list.add("SEAT_LUMBAR_SIDE_SUPPORT_POS");
            flipped |= 356518803;
        }
        if ((356518804 & o) == 356518804) {
            list.add("SEAT_LUMBAR_SIDE_SUPPORT_MOVE");
            flipped |= 356518804;
        }
        if ((289409941 & o) == 289409941) {
            list.add("SEAT_HEADREST_HEIGHT_POS");
            flipped |= 289409941;
        }
        if ((356518806 & o) == 356518806) {
            list.add("SEAT_HEADREST_HEIGHT_MOVE");
            flipped |= 356518806;
        }
        if ((356518807 & o) == 356518807) {
            list.add("SEAT_HEADREST_ANGLE_POS");
            flipped |= 356518807;
        }
        if ((356518808 & o) == 356518808) {
            list.add("SEAT_HEADREST_ANGLE_MOVE");
            flipped |= 356518808;
        }
        if ((356518809 & o) == 356518809) {
            list.add("SEAT_HEADREST_FORE_AFT_POS");
            flipped |= 356518809;
        }
        if ((356518810 & o) == 356518810) {
            list.add("SEAT_HEADREST_FORE_AFT_MOVE");
            flipped |= 356518810;
        }
        if ((289409984 & o) == 289409984) {
            list.add("WINDOW_POS");
            flipped |= 289409984;
        }
        if ((289409985 & o) == 289409985) {
            list.add("WINDOW_MOVE");
            flipped |= 289409985;
        }
        if ((289409986 & o) == 289409986) {
            list.add("WINDOW_VENT_POS");
            flipped |= 289409986;
        }
        if ((289409987 & o) == 289409987) {
            list.add("WINDOW_VENT_MOVE");
            flipped |= 289409987;
        }
        if ((287312836 & o) == 287312836) {
            list.add("WINDOW_LOCK");
            flipped |= 287312836;
        }
        if ((299895808 & o) == 299895808) {
            list.add("VEHICLE_MAP_SERVICE");
            flipped |= 299895808;
        }
        if ((299896064 & o) == 299896064) {
            list.add("OBD2_LIVE_FRAME");
            flipped |= 299896064;
        }
        if ((299896065 & o) == 299896065) {
            list.add("OBD2_FREEZE_FRAME");
            flipped |= 299896065;
        }
        if ((299896066 & o) == 299896066) {
            list.add("OBD2_FREEZE_FRAME_INFO");
            flipped |= 299896066;
        }
        if ((299896067 & o) == 299896067) {
            list.add("OBD2_FREEZE_FRAME_CLEAR");
            flipped |= 299896067;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
