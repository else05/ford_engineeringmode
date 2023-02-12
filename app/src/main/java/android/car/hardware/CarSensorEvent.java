package android.car.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes2.dex */
public class CarSensorEvent implements Parcelable {
    public static final Parcelable.Creator<CarSensorEvent> CREATOR = new Parcelable.Creator<CarSensorEvent>() { // from class: android.car.hardware.CarSensorEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorEvent createFromParcel(Parcel in) {
            return new CarSensorEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorEvent[] newArray(int size) {
            return new CarSensorEvent[size];
        }
    };
    public static final int DRIVE_STATUS_FULLY_RESTRICTED = 31;
    public static final int DRIVE_STATUS_LIMIT_MESSAGE_LEN = 16;
    public static final int DRIVE_STATUS_NO_CONFIG = 8;
    public static final int DRIVE_STATUS_NO_KEYBOARD_INPUT = 2;
    public static final int DRIVE_STATUS_NO_VIDEO = 1;
    public static final int DRIVE_STATUS_NO_VOICE_INPUT = 4;
    public static final int DRIVE_STATUS_UNRESTRICTED = 0;
    public static final int GEAR_DRIVE = 3;
    public static final int GEAR_FAULT = 15;
    public static final int GEAR_FIFTH = 10;
    public static final int GEAR_FIRST = 6;
    public static final int GEAR_FOURTH = 9;
    public static final int GEAR_LOW = 5;
    public static final int GEAR_NEUTRAL = 2;
    public static final int GEAR_PARK = 0;
    public static final int GEAR_REVERSE = 1;
    public static final int GEAR_SECOND = 7;
    public static final int GEAR_SIXTH = 11;
    public static final int GEAR_SPORT = 4;
    public static final int GEAR_THIRD = 8;
    public static final int GEAR_UNDEFINED_FAULT1 = 12;
    public static final int GEAR_UNDEFINED_FAULT2 = 13;
    public static final int GEAR_UNKOWN = 14;
    public static final int IGNITION_STATE_ACC = 2;
    public static final int IGNITION_STATE_INVAILD = 15;
    public static final int IGNITION_STATE_OFF = 1;
    public static final int IGNITION_STATE_ON = 4;
    public static final int IGNITION_STATE_START = 8;
    public static final int IGNITION_STATE_UNKOWN = 0;
    public static final int INDEX_ENVIRONMENT_PRESSURE = 1;
    public static final int INDEX_ENVIRONMENT_TEMPERATURE = 0;
    public static final int INDEX_FUEL_LEVEL_IN_DISTANCE = 1;
    public static final int INDEX_FUEL_LEVEL_IN_PERCENTILE = 0;
    public static final int INDEX_FUEL_LEVEL_TOTAL_DISTANCE = 2;
    public static final int INDEX_FUEL_LOW_WARNING = 0;
    public static final int INDEX_WHEEL_DISTANCE_FRONT_LEFT = 1;
    public static final int INDEX_WHEEL_DISTANCE_FRONT_RIGHT = 2;
    public static final int INDEX_WHEEL_DISTANCE_REAR_LEFT = 4;
    public static final int INDEX_WHEEL_DISTANCE_REAR_RIGHT = 3;
    public static final int INDEX_WHEEL_DISTANCE_RESET_COUNT = 0;
    public static final int LOW_LEVEL_LOW = 1;
    public static final int LOW_LEVEL_NOT_USED = 3;
    public static final int LOW_LEVEL_OK = 0;
    public static final int LOW_LEVEL_VERY_LOW = 2;
    private static final long MILLI_IN_NANOS = 1000000;
    public static final int PRESSURE_STATUS_ALERT = 4;
    public static final int PRESSURE_STATUS_FAULT = 3;
    public static final int PRESSURE_STATUS_LOW = 2;
    public static final int PRESSURE_STATUS_NORMAL = 1;
    public static final int PRESSURE_STATUS_NOT_SUPPROTED = 15;
    public static final int PRESSURE_STATUS_UNKOWN = 0;
    private static final String TAG = "CarSensorEvent";
    public final float[] floatValues;
    public final int[] intValues;
    public final long[] longValues;
    public int sensorType;
    public long timestamp;

    public CarSensorEvent(Parcel in) {
        this.sensorType = in.readInt();
        this.timestamp = in.readLong();
        int len = in.readInt();
        this.floatValues = new float[len];
        in.readFloatArray(this.floatValues);
        int len2 = in.readInt();
        this.intValues = new int[len2];
        in.readIntArray(this.intValues);
        int len3 = in.readInt();
        this.longValues = new long[len3];
        in.readLongArray(this.longValues);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sensorType);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.floatValues.length);
        dest.writeFloatArray(this.floatValues);
        dest.writeInt(this.intValues.length);
        dest.writeIntArray(this.intValues);
        dest.writeInt(this.longValues.length);
        dest.writeLongArray(this.longValues);
    }

    public CarSensorEvent(int sensorType, long timestamp, int floatValueSize, int intValueSize, int longValueSize) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.floatValues = new float[floatValueSize];
        this.intValues = new int[intValueSize];
        this.longValues = new long[longValueSize];
    }

    CarSensorEvent(int sensorType, long timestamp, float[] floatValues, int[] intValues, long[] longValues) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
        this.floatValues = floatValues;
        this.intValues = intValues;
        this.longValues = longValues;
    }

    private void checkType(int type) {
        if (this.sensorType == type) {
            return;
        }
        throw new UnsupportedOperationException(String.format("Invalid sensor type: expected %d, got %d", Integer.valueOf(type), Integer.valueOf(this.sensorType)));
    }

    /* loaded from: classes2.dex */
    public static class EnvironmentData {
        public float pressure;
        public float temperature;
        public long timestamp;

        private EnvironmentData() {
        }
    }

    public EnvironmentData getEnvironmentData(EnvironmentData data) {
        checkType(12);
        if (data == null) {
            data = new EnvironmentData();
        }
        data.timestamp = this.timestamp;
        data.temperature = this.floatValues[0];
        data.pressure = this.floatValues[1];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class NightData {
        public boolean isNightMode;
        public long timestamp;

        private NightData() {
        }
    }

    public NightData getNightData(NightData data) {
        checkType(9);
        if (data == null) {
            data = new NightData();
        }
        data.timestamp = this.timestamp;
        data.isNightMode = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes2.dex */
    public static class GearData {
        public int gear;
        public long timestamp;

        private GearData() {
        }
    }

    public GearData getGearData(GearData data) {
        checkType(7);
        if (data == null) {
            data = new GearData();
        }
        data.timestamp = this.timestamp;
        data.gear = this.intValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class ParkingBrakeData {
        public boolean isEngaged;
        public long timestamp;

        private ParkingBrakeData() {
        }
    }

    public ParkingBrakeData getParkingBrakeData(ParkingBrakeData data) {
        checkType(6);
        if (data == null) {
            data = new ParkingBrakeData();
        }
        data.timestamp = this.timestamp;
        data.isEngaged = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes2.dex */
    public static class FuelLevelData {
        public int level;
        public float range;
        public long timestamp;

        private FuelLevelData() {
        }
    }

    public FuelLevelData getFuelLevelData(FuelLevelData data) {
        checkType(5);
        if (data == null) {
            data = new FuelLevelData();
        }
        data.timestamp = this.timestamp;
        if (this.floatValues == null) {
            data.level = -1;
            data.range = -1.0f;
        } else {
            if (this.floatValues[0] < 0.0f) {
                data.level = -1;
            } else {
                data.level = (int) this.floatValues[0];
            }
            if (this.floatValues[1] < 0.0f) {
                data.range = -1.0f;
            } else {
                data.range = this.floatValues[1];
            }
        }
        return data;
    }

    /* loaded from: classes2.dex */
    public static class OdometerData {
        public float kms;
        public long timestamp;

        private OdometerData() {
        }
    }

    public OdometerData getOdometerData(OdometerData data) {
        checkType(4);
        if (data == null) {
            data = new OdometerData();
        }
        data.timestamp = this.timestamp;
        data.kms = this.floatValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class RpmData {
        public float rpm;
        public long timestamp;

        private RpmData() {
        }
    }

    public RpmData getRpmData(RpmData data) {
        checkType(3);
        if (data == null) {
            data = new RpmData();
        }
        data.timestamp = this.timestamp;
        data.rpm = this.floatValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class CarSpeedData {
        public float carSpeed;
        public long timestamp;

        private CarSpeedData() {
        }
    }

    public CarSpeedData getCarSpeedData(CarSpeedData data) {
        checkType(2);
        if (data == null) {
            data = new CarSpeedData();
        }
        data.timestamp = this.timestamp;
        data.carSpeed = this.floatValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class DrivingStatusData {
        public int status;
        public long timestamp;

        private DrivingStatusData() {
        }
    }

    public DrivingStatusData getDrivingStatusData(DrivingStatusData data) {
        checkType(11);
        if (data == null) {
            data = new DrivingStatusData();
        }
        data.status = this.intValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class CarWheelTickDistanceData {
        public long frontLeftWheelDistanceMm;
        public long frontRightWheelDistanceMm;
        public long rearLeftWheelDistanceMm;
        public long rearRightWheelDistanceMm;
        public long sensorResetCount;
        public long timestamp;

        private CarWheelTickDistanceData() {
        }
    }

    public CarWheelTickDistanceData getCarWheelTickDistanceData(CarWheelTickDistanceData data) {
        checkType(23);
        if (data == null) {
            data = new CarWheelTickDistanceData();
        }
        data.timestamp = this.timestamp;
        data.sensorResetCount = this.longValues[0];
        data.frontLeftWheelDistanceMm = this.longValues[1];
        data.frontRightWheelDistanceMm = this.longValues[2];
        data.rearRightWheelDistanceMm = this.longValues[3];
        data.rearLeftWheelDistanceMm = this.longValues[4];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class CarAbsActiveData {
        public boolean absIsActive;
        public long timestamp;

        private CarAbsActiveData() {
        }
    }

    public CarAbsActiveData getCarAbsActiveData(CarAbsActiveData data) {
        checkType(24);
        if (data == null) {
            data = new CarAbsActiveData();
        }
        data.timestamp = this.timestamp;
        data.absIsActive = this.intValues[0] == 1;
        return data;
    }

    /* loaded from: classes2.dex */
    public static class CarTractionControlActiveData {
        public long timestamp;
        public boolean tractionControlIsActive;

        private CarTractionControlActiveData() {
        }
    }

    public CarTractionControlActiveData getCarTractionControlActiveData(CarTractionControlActiveData data) {
        checkType(72);
        if (data == null) {
            data = new CarTractionControlActiveData();
        }
        data.timestamp = this.timestamp;
        data.tractionControlIsActive = this.intValues[0] == 1;
        return data;
    }

    public String toString() {
        long[] jArr;
        int[] iArr;
        float[] fArr;
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName() + "[");
        sb.append("type:" + Integer.toHexString(this.sensorType));
        if (this.floatValues != null && this.floatValues.length > 0) {
            sb.append(" float values:");
            for (float v : this.floatValues) {
                sb.append(" " + v);
            }
        }
        if (this.intValues != null && this.intValues.length > 0) {
            sb.append(" int values:");
            for (int v2 : this.intValues) {
                sb.append(" " + v2);
            }
        }
        if (this.longValues != null && this.longValues.length > 0) {
            sb.append(" long values:");
            for (long v3 : this.longValues) {
                sb.append(" " + v3);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public static class EngineStateData {
        public int state;
        public long timestamp;

        private EngineStateData() {
        }
    }

    public EngineStateData getEngineStateData(EngineStateData data) {
        checkType(22);
        if (data == null) {
            data = new EngineStateData();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getEngineStateData_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class OilLifeData {
        public int state;
        public long timestamp;

        private OilLifeData() {
        }
    }

    public OilLifeData getOilLifeData(OilLifeData data) {
        checkType(51);
        if (data == null) {
            data = new OilLifeData();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getOilLifeData_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class DoorStateLB {
        public int state;

        private DoorStateLB() {
        }
    }

    public DoorStateLB getDoorStateLB(DoorStateLB data) {
        checkType(56);
        if (data == null) {
            data = new DoorStateLB();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoorStateLB_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class DoorStateRB {
        public int state;

        private DoorStateRB() {
        }
    }

    public DoorStateRB getDoorStateRB(DoorStateRB data) {
        checkType(57);
        if (data == null) {
            data = new DoorStateRB();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoorStateRB_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class DoorStateLR {
        public int state;

        private DoorStateLR() {
        }
    }

    public DoorStateLR getDoorStateLR(DoorStateLR data) {
        checkType(58);
        if (data == null) {
            data = new DoorStateLR();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoorStateLR_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class DoorStateRR {
        public int state;

        private DoorStateRR() {
        }
    }

    public DoorStateRR getDoorStateRR(DoorStateRR data) {
        checkType(59);
        if (data == null) {
            data = new DoorStateRR();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoorStateRR_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Door_State_Trunk_In {
        public int state;

        private Door_State_Trunk_In() {
        }
    }

    public Door_State_Trunk_In getDoor_State_Trunk_In(Door_State_Trunk_In data) {
        checkType(60);
        if (data == null) {
            data = new Door_State_Trunk_In();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoor_State_Trunk_In_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Trailer_Stat {
        public int state;

        private Trailer_Stat() {
        }
    }

    public Trailer_Stat getTrailer_Stat(Trailer_Stat data) {
        checkType(71);
        if (data == null) {
            data = new Trailer_Stat();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getTrailer_Stat:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Door_State_Trunk_Out {
        public int state;

        private Door_State_Trunk_Out() {
        }
    }

    public Door_State_Trunk_Out getDoor_State_Trunk_Out(Door_State_Trunk_Out data) {
        checkType(61);
        if (data == null) {
            data = new Door_State_Trunk_Out();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoor_State_Trunk_Out_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Door_State_Hood {
        public int state;

        private Door_State_Hood() {
        }
    }

    public Door_State_Hood getDoor_State_Hood(Door_State_Hood data) {
        checkType(62);
        if (data == null) {
            data = new Door_State_Hood();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getDoor_State_Hood_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Fault_DTC {
        public long[] getDTC;

        private Fault_DTC() {
        }
    }

    public Fault_DTC getFault_DTC(Fault_DTC data) {
        checkType(63);
        if (data == null) {
            data = new Fault_DTC();
        }
        data.getDTC = new long[this.longValues.length];
        for (int i = 0; i < this.longValues.length; i++) {
            data.getDTC[i] = this.longValues[i];
            Log.w(TAG, "getVehicle_Fault:" + data.getDTC[i]);
        }
        return data;
    }

    /* loaded from: classes2.dex */
    public static class Fault_DID {
        public long[] getDID;

        private Fault_DID() {
        }
    }

    public Fault_DID getFault_DID(Fault_DID data) {
        checkType(64);
        if (data == null) {
            data = new Fault_DID();
        }
        data.getDID = new long[2];
        data.getDID[0] = this.longValues[0];
        data.getDID[1] = this.longValues[1];
        Log.w(TAG, "getFault_DID[0]:" + this.longValues[0] + "_getFault_DID[1]:" + this.longValues[1]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class AVGFuelConsumptionData {
        public int avgFuelConsumption;
        public long timestamp;

        private AVGFuelConsumptionData() {
        }
    }

    public AVGFuelConsumptionData getAVGFuelConsumptionData(AVGFuelConsumptionData data) {
        checkType(65);
        if (data == null) {
            data = new AVGFuelConsumptionData();
        }
        data.timestamp = this.timestamp;
        data.avgFuelConsumption = this.intValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class AVGFuelUnit {
        public int avgFuelUnit;
        public long timestamp;

        private AVGFuelUnit() {
        }
    }

    public AVGFuelUnit getAVGFuelUnit(AVGFuelUnit data) {
        checkType(66);
        if (data == null) {
            data = new AVGFuelUnit();
        }
        data.timestamp = this.timestamp;
        data.avgFuelUnit = this.intValues[0];
        return data;
    }

    /* loaded from: classes2.dex */
    public static class ParkingBrakeSate {
        public int state;
        public long timestamp;

        private ParkingBrakeSate() {
        }
    }

    public ParkingBrakeSate getParkingBrakeSate(ParkingBrakeSate data) {
        checkType(52);
        if (data == null) {
            data = new ParkingBrakeSate();
        }
        data.timestamp = this.timestamp;
        data.state = this.intValues[0];
        Log.w(TAG, "getParkingBrakeSate_state:" + this.intValues[0]);
        return data;
    }

    /* loaded from: classes2.dex */
    public static class EngStateData {
        public int state;
        public long timestamp;

        private EngStateData() {
        }
    }

    public EngStateData getEngState(EngStateData data) {
        checkType(72);
        if (data == null) {
            data = new EngStateData();
        }
        data.state = this.intValues[0];
        Log.w(TAG, "getEngState_state:" + this.intValues[0]);
        return data;
    }
}
