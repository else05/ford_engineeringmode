package android.car.diagnostic;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* loaded from: classes2.dex */
public class CarDiagnosticEvent implements Parcelable {
    public static final Parcelable.Creator<CarDiagnosticEvent> CREATOR = new Parcelable.Creator<CarDiagnosticEvent>() { // from class: android.car.diagnostic.CarDiagnosticEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarDiagnosticEvent createFromParcel(Parcel in) {
            return new CarDiagnosticEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarDiagnosticEvent[] newArray(int size) {
            return new CarDiagnosticEvent[size];
        }
    };
    public final String dtc;
    private final SparseArray<Float> floatValues;
    public final int frameType;
    private final SparseIntArray intValues;
    public final long timestamp;

    public CarDiagnosticEvent(Parcel in) {
        this.frameType = in.readInt();
        this.timestamp = in.readLong();
        int len = in.readInt();
        this.floatValues = new SparseArray<>(len);
        for (int i = 0; i < len; i++) {
            int key = in.readInt();
            float value = in.readFloat();
            this.floatValues.put(key, Float.valueOf(value));
        }
        int len2 = in.readInt();
        this.intValues = new SparseIntArray(len2);
        for (int i2 = 0; i2 < len2; i2++) {
            int key2 = in.readInt();
            int value2 = in.readInt();
            this.intValues.put(key2, value2);
        }
        this.dtc = (String) in.readValue(String.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.frameType);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.floatValues.size());
        for (int i = 0; i < this.floatValues.size(); i++) {
            int key = this.floatValues.keyAt(i);
            dest.writeInt(key);
            dest.writeFloat(this.floatValues.get(key).floatValue());
        }
        dest.writeInt(this.intValues.size());
        for (int i2 = 0; i2 < this.intValues.size(); i2++) {
            int key2 = this.intValues.keyAt(i2);
            dest.writeInt(key2);
            dest.writeInt(this.intValues.get(key2));
        }
        dest.writeValue(this.dtc);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("type");
        switch (this.frameType) {
            case 0:
                jsonWriter.value("live");
                break;
            case 1:
                jsonWriter.value("freeze");
                break;
            default:
                throw new IllegalStateException("unknown frameType " + this.frameType);
        }
        jsonWriter.name("timestamp").value(this.timestamp);
        jsonWriter.name("intValues").beginArray();
        for (int i = 0; i < this.intValues.size(); i++) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(this.intValues.keyAt(i));
            jsonWriter.name("value").value(this.intValues.valueAt(i));
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.name("floatValues").beginArray();
        for (int i2 = 0; i2 < this.floatValues.size(); i2++) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(this.floatValues.keyAt(i2));
            jsonWriter.name("value").value(this.floatValues.valueAt(i2));
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        if (this.dtc != null) {
            jsonWriter.name("stringValue").value(this.dtc);
        }
        jsonWriter.endObject();
    }

    private CarDiagnosticEvent(int frameType, long timestamp, SparseArray<Float> floatValues, SparseIntArray intValues, String dtc) {
        this.frameType = frameType;
        this.timestamp = timestamp;
        this.floatValues = floatValues;
        this.intValues = intValues;
        this.dtc = dtc;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mType;
        private long mTimestamp = 0;
        private SparseArray<Float> mFloatValues = new SparseArray<>();
        private SparseIntArray mIntValues = new SparseIntArray();
        private String mDtc = null;

        private Builder(int type) {
            this.mType = 0;
            this.mType = type;
        }

        public static Builder newLiveFrameBuilder() {
            return new Builder(0);
        }

        public static Builder newFreezeFrameBuilder() {
            return new Builder(1);
        }

        public Builder atTimestamp(long timestamp) {
            this.mTimestamp = timestamp;
            return this;
        }

        public Builder withIntValue(int key, int value) {
            this.mIntValues.put(key, value);
            return this;
        }

        public Builder withFloatValue(int key, float value) {
            this.mFloatValues.put(key, Float.valueOf(value));
            return this;
        }

        public Builder withDtc(String dtc) {
            this.mDtc = dtc;
            return this;
        }

        public CarDiagnosticEvent build() {
            return new CarDiagnosticEvent(this.mType, this.mTimestamp, this.mFloatValues, this.mIntValues, this.mDtc);
        }
    }

    public CarDiagnosticEvent withVendorSensorsRemoved() {
        SparseIntArray newIntValues = this.intValues.clone();
        SparseArray<Float> newFloatValues = this.floatValues.clone();
        for (int i = 0; i < this.intValues.size(); i++) {
            int key = this.intValues.keyAt(i);
            if (key >= 31) {
                newIntValues.delete(key);
            }
        }
        for (int i2 = 0; i2 < this.floatValues.size(); i2++) {
            int key2 = this.floatValues.keyAt(i2);
            if (key2 >= 70) {
                newFloatValues.delete(key2);
            }
        }
        return new CarDiagnosticEvent(this.frameType, this.timestamp, newFloatValues, newIntValues, this.dtc);
    }

    public boolean isLiveFrame() {
        return this.frameType == 0;
    }

    public boolean isFreezeFrame() {
        return 1 == this.frameType;
    }

    public boolean isEmptyFrame() {
        boolean empty = (this.intValues.size() == 0) & (this.floatValues.size() == 0);
        return isFreezeFrame() ? empty & this.dtc.isEmpty() : empty;
    }

    public CarDiagnosticEvent checkLiveFrame() {
        if (!isLiveFrame()) {
            throw new IllegalStateException("frame is not a live frame");
        }
        return this;
    }

    public CarDiagnosticEvent checkFreezeFrame() {
        if (!isFreezeFrame()) {
            throw new IllegalStateException("frame is not a freeze frame");
        }
        return this;
    }

    public boolean isEarlierThan(CarDiagnosticEvent otherEvent) {
        return this.timestamp < ((CarDiagnosticEvent) Objects.requireNonNull(otherEvent)).timestamp;
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || !(otherObject instanceof CarDiagnosticEvent)) {
            return false;
        }
        CarDiagnosticEvent otherEvent = (CarDiagnosticEvent) otherObject;
        if (otherEvent.frameType != this.frameType || otherEvent.timestamp != this.timestamp || otherEvent.intValues.size() != this.intValues.size() || otherEvent.floatValues.size() != this.floatValues.size() || !Objects.equals(this.dtc, otherEvent.dtc)) {
            return false;
        }
        for (int i = 0; i < this.intValues.size(); i++) {
            int key = this.intValues.keyAt(i);
            int otherKey = otherEvent.intValues.keyAt(i);
            if (key != otherKey) {
                return false;
            }
            int value = this.intValues.valueAt(i);
            int otherValue = otherEvent.intValues.valueAt(i);
            if (value != otherValue) {
                return false;
            }
        }
        for (int i2 = 0; i2 < this.floatValues.size(); i2++) {
            int key2 = this.floatValues.keyAt(i2);
            int otherKey2 = otherEvent.floatValues.keyAt(i2);
            if (key2 != otherKey2) {
                return false;
            }
            float value2 = this.floatValues.valueAt(i2).floatValue();
            float otherValue2 = otherEvent.floatValues.valueAt(i2).floatValue();
            if (value2 != otherValue2) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Integer[] intKeys = new Integer[this.intValues.size()];
        Integer[] floatKeys = new Integer[this.floatValues.size()];
        Integer[] intValues = new Integer[intKeys.length];
        Float[] floatValues = new Float[floatKeys.length];
        for (int i = 0; i < intKeys.length; i++) {
            intKeys[i] = Integer.valueOf(this.intValues.keyAt(i));
            intValues[i] = Integer.valueOf(this.intValues.valueAt(i));
        }
        for (int i2 = 0; i2 < floatKeys.length; i2++) {
            floatKeys[i2] = Integer.valueOf(this.floatValues.keyAt(i2));
            floatValues[i2] = this.floatValues.valueAt(i2);
        }
        int intKeysHash = Objects.hash(intKeys);
        int intValuesHash = Objects.hash(intValues);
        int floatKeysHash = Objects.hash(floatKeys);
        int floatValuesHash = Objects.hash(floatValues);
        return Objects.hash(Integer.valueOf(this.frameType), Long.valueOf(this.timestamp), this.dtc, Integer.valueOf(intKeysHash), Integer.valueOf(intValuesHash), Integer.valueOf(floatKeysHash), Integer.valueOf(floatValuesHash));
    }

    public String toString() {
        Object[] objArr = new Object[5];
        objArr[0] = isLiveFrame() ? "live" : "freeze";
        objArr[1] = Long.valueOf(this.timestamp);
        objArr[2] = this.dtc;
        objArr[3] = this.intValues.toString();
        objArr[4] = this.floatValues.toString();
        return String.format("%s diagnostic frame {\n\ttimestamp: %d, DTC: %s\n\tintValues: %s\n\tfloatValues: %s\n}", objArr);
    }

    public int getSystemIntegerSensor(int sensor, int defaultValue) {
        return this.intValues.get(sensor, defaultValue);
    }

    public float getSystemFloatSensor(int sensor, float defaultValue) {
        return this.floatValues.get(sensor, Float.valueOf(defaultValue)).floatValue();
    }

    public int getVendorIntegerSensor(int sensor, int defaultValue) {
        return this.intValues.get(sensor, defaultValue);
    }

    public float getVendorFloatSensor(int sensor, float defaultValue) {
        return this.floatValues.get(sensor, Float.valueOf(defaultValue)).floatValue();
    }

    public Integer getSystemIntegerSensor(int sensor) {
        int index = this.intValues.indexOfKey(sensor);
        if (index < 0) {
            return null;
        }
        return Integer.valueOf(this.intValues.valueAt(index));
    }

    public Float getSystemFloatSensor(int sensor) {
        int index = this.floatValues.indexOfKey(sensor);
        if (index < 0) {
            return null;
        }
        return this.floatValues.valueAt(index);
    }

    public Integer getVendorIntegerSensor(int sensor) {
        int index = this.intValues.indexOfKey(sensor);
        if (index < 0) {
            return null;
        }
        return Integer.valueOf(this.intValues.valueAt(index));
    }

    public Float getVendorFloatSensor(int sensor) {
        int index = this.floatValues.indexOfKey(sensor);
        if (index < 0) {
            return null;
        }
        return this.floatValues.valueAt(index);
    }

    /* loaded from: classes2.dex */
    public static final class FuelSystemStatus {
        public static final int CLOSED_LOOP = 2;
        public static final int CLOSED_LOOP_BUT_FEEDBACK_FAULT = 16;
        public static final int OPEN_ENGINE_LOAD_OR_DECELERATION = 4;
        public static final int OPEN_INSUFFICIENT_ENGINE_TEMPERATURE = 1;
        public static final int OPEN_SYSTEM_FAILURE = 8;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Status {
        }

        private FuelSystemStatus() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class SecondaryAirStatus {
        public static final int DOWNSTREAM_OF_CATALYCIC_CONVERTER = 2;
        public static final int FROM_OUTSIDE_OR_OFF = 4;
        public static final int PUMP_ON_FOR_DIAGNOSTICS = 8;
        public static final int UPSTREAM = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Status {
        }

        private SecondaryAirStatus() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class FuelType {
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

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Type {
        }

        private FuelType() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class IgnitionMonitor {
        public final boolean available;
        public final boolean incomplete;

        IgnitionMonitor(boolean available, boolean incomplete) {
            this.available = available;
            this.incomplete = incomplete;
        }

        /* loaded from: classes2.dex */
        public static final class Decoder {
            private final int mAvailableBitmask;
            private final int mIncompleteBitmask;

            Decoder(int availableBitmask, int incompleteBitmask) {
                this.mAvailableBitmask = availableBitmask;
                this.mIncompleteBitmask = incompleteBitmask;
            }

            public IgnitionMonitor fromValue(int value) {
                boolean available = (this.mAvailableBitmask & value) != 0;
                boolean incomplete = (this.mIncompleteBitmask & value) != 0;
                return new IgnitionMonitor(available, incomplete);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class CommonIgnitionMonitors {
        public static final int COMPONENTS_AVAILABLE = 1;
        public static final int COMPONENTS_INCOMPLETE = 2;
        public static final int FUEL_SYSTEM_AVAILABLE = 4;
        public static final int FUEL_SYSTEM_INCOMPLETE = 8;
        public static final int MISFIRE_AVAILABLE = 16;
        public static final int MISFIRE_INCOMPLETE = 32;
        public final IgnitionMonitor components;
        public final IgnitionMonitor fuelSystem;
        public final IgnitionMonitor misfire;
        static final IgnitionMonitor.Decoder COMPONENTS_DECODER = new IgnitionMonitor.Decoder(1, 2);
        static final IgnitionMonitor.Decoder FUEL_SYSTEM_DECODER = new IgnitionMonitor.Decoder(4, 8);
        static final IgnitionMonitor.Decoder MISFIRE_DECODER = new IgnitionMonitor.Decoder(16, 32);

        CommonIgnitionMonitors(int bitmask) {
            this.components = COMPONENTS_DECODER.fromValue(bitmask);
            this.fuelSystem = FUEL_SYSTEM_DECODER.fromValue(bitmask);
            this.misfire = MISFIRE_DECODER.fromValue(bitmask);
        }

        public SparkIgnitionMonitors asSparkIgnitionMonitors() {
            if (this instanceof SparkIgnitionMonitors) {
                return (SparkIgnitionMonitors) this;
            }
            return null;
        }

        public CompressionIgnitionMonitors asCompressionIgnitionMonitors() {
            if (this instanceof CompressionIgnitionMonitors) {
                return (CompressionIgnitionMonitors) this;
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static final class SparkIgnitionMonitors extends CommonIgnitionMonitors {
        public static final int AC_REFRIGERANT_AVAILABLE = 4096;
        public static final int AC_REFRIGERANT_INCOMPLETE = 8192;
        public static final int CATALYST_AVAILABLE = 1048576;
        public static final int CATALYST_INCOMPLETE = 2097152;
        public static final int EGR_AVAILABLE = 64;
        public static final int EGR_INCOMPLETE = 128;
        public static final int EVAPORATIVE_SYSTEM_AVAILABLE = 65536;
        public static final int EVAPORATIVE_SYSTEM_INCOMPLETE = 131072;
        public static final int HEATED_CATALYST_AVAILABLE = 262144;
        public static final int HEATED_CATALYST_INCOMPLETE = 524288;
        public static final int OXYGEN_SENSOR_AVAILABLE = 1024;
        public static final int OXYGEN_SENSOR_HEATER_AVAILABLE = 256;
        public static final int OXYGEN_SENSOR_HEATER_INCOMPLETE = 512;
        public static final int OXYGEN_SENSOR_INCOMPLETE = 2048;
        public static final int SECONDARY_AIR_SYSTEM_AVAILABLE = 16384;
        public static final int SECONDARY_AIR_SYSTEM_INCOMPLETE = 32768;
        public final IgnitionMonitor ACRefrigerant;
        public final IgnitionMonitor EGR;
        public final IgnitionMonitor catalyst;
        public final IgnitionMonitor evaporativeSystem;
        public final IgnitionMonitor heatedCatalyst;
        public final IgnitionMonitor oxygenSensor;
        public final IgnitionMonitor oxygenSensorHeater;
        public final IgnitionMonitor secondaryAirSystem;
        static final IgnitionMonitor.Decoder EGR_DECODER = new IgnitionMonitor.Decoder(64, 128);
        static final IgnitionMonitor.Decoder OXYGEN_SENSOR_HEATER_DECODER = new IgnitionMonitor.Decoder(256, 512);
        static final IgnitionMonitor.Decoder OXYGEN_SENSOR_DECODER = new IgnitionMonitor.Decoder(1024, 2048);
        static final IgnitionMonitor.Decoder AC_REFRIGERANT_DECODER = new IgnitionMonitor.Decoder(4096, 8192);
        static final IgnitionMonitor.Decoder SECONDARY_AIR_SYSTEM_DECODER = new IgnitionMonitor.Decoder(16384, 32768);
        static final IgnitionMonitor.Decoder EVAPORATIVE_SYSTEM_DECODER = new IgnitionMonitor.Decoder(65536, 131072);
        static final IgnitionMonitor.Decoder HEATED_CATALYST_DECODER = new IgnitionMonitor.Decoder(262144, 524288);
        static final IgnitionMonitor.Decoder CATALYST_DECODER = new IgnitionMonitor.Decoder(1048576, 2097152);

        SparkIgnitionMonitors(int bitmask) {
            super(bitmask);
            this.EGR = EGR_DECODER.fromValue(bitmask);
            this.oxygenSensorHeater = OXYGEN_SENSOR_HEATER_DECODER.fromValue(bitmask);
            this.oxygenSensor = OXYGEN_SENSOR_DECODER.fromValue(bitmask);
            this.ACRefrigerant = AC_REFRIGERANT_DECODER.fromValue(bitmask);
            this.secondaryAirSystem = SECONDARY_AIR_SYSTEM_DECODER.fromValue(bitmask);
            this.evaporativeSystem = EVAPORATIVE_SYSTEM_DECODER.fromValue(bitmask);
            this.heatedCatalyst = HEATED_CATALYST_DECODER.fromValue(bitmask);
            this.catalyst = CATALYST_DECODER.fromValue(bitmask);
        }
    }

    /* loaded from: classes2.dex */
    public static final class CompressionIgnitionMonitors extends CommonIgnitionMonitors {
        public static final int BOOST_PRESSURE_AVAILABLE = 4096;
        public static final int BOOST_PRESSURE_INCOMPLETE = 8192;
        public static final int EGR_OR_VVT_AVAILABLE = 64;
        public static final int EGR_OR_VVT_INCOMPLETE = 128;
        public static final int EXHAUST_GAS_SENSOR_AVAILABLE = 1024;
        public static final int EXHAUST_GAS_SENSOR_INCOMPLETE = 2048;
        public static final int NMHC_CATALYST_AVAILABLE = 65536;
        public static final int NMHC_CATALYST_INCOMPLETE = 131072;
        public static final int NOx_SCR_AVAILABLE = 16384;
        public static final int NOx_SCR_INCOMPLETE = 32768;
        public static final int PM_FILTER_AVAILABLE = 256;
        public static final int PM_FILTER_INCOMPLETE = 512;
        public final IgnitionMonitor EGROrVVT;
        public final IgnitionMonitor NMHCCatalyst;
        public final IgnitionMonitor NOxSCR;
        public final IgnitionMonitor PMFilter;
        public final IgnitionMonitor boostPressure;
        public final IgnitionMonitor exhaustGasSensor;
        static final IgnitionMonitor.Decoder EGR_OR_VVT_DECODER = new IgnitionMonitor.Decoder(64, 128);
        static final IgnitionMonitor.Decoder PM_FILTER_DECODER = new IgnitionMonitor.Decoder(256, 512);
        static final IgnitionMonitor.Decoder EXHAUST_GAS_SENSOR_DECODER = new IgnitionMonitor.Decoder(1024, 2048);
        static final IgnitionMonitor.Decoder BOOST_PRESSURE_DECODER = new IgnitionMonitor.Decoder(4096, 8192);
        static final IgnitionMonitor.Decoder NOx_SCR_DECODER = new IgnitionMonitor.Decoder(16384, 32768);
        static final IgnitionMonitor.Decoder NMHC_CATALYST_DECODER = new IgnitionMonitor.Decoder(65536, 131072);

        CompressionIgnitionMonitors(int bitmask) {
            super(bitmask);
            this.EGROrVVT = EGR_OR_VVT_DECODER.fromValue(bitmask);
            this.PMFilter = PM_FILTER_DECODER.fromValue(bitmask);
            this.exhaustGasSensor = EXHAUST_GAS_SENSOR_DECODER.fromValue(bitmask);
            this.boostPressure = BOOST_PRESSURE_DECODER.fromValue(bitmask);
            this.NOxSCR = NOx_SCR_DECODER.fromValue(bitmask);
            this.NMHCCatalyst = NMHC_CATALYST_DECODER.fromValue(bitmask);
        }
    }

    public Integer getFuelSystemStatus() {
        return getSystemIntegerSensor(0);
    }

    public Integer getSecondaryAirStatus() {
        return getSystemIntegerSensor(5);
    }

    public CommonIgnitionMonitors getIgnitionMonitors() {
        Integer ignitionMonitorsType = getSystemIntegerSensor(2);
        Integer ignitionMonitorsBitmask = getSystemIntegerSensor(3);
        if (ignitionMonitorsType == null || ignitionMonitorsBitmask == null) {
            return null;
        }
        switch (ignitionMonitorsType.intValue()) {
            case 0:
                return new SparkIgnitionMonitors(ignitionMonitorsBitmask.intValue());
            case 1:
                return new CompressionIgnitionMonitors(ignitionMonitorsBitmask.intValue());
            default:
                return null;
        }
    }

    public Integer getFuelType() {
        return getSystemIntegerSensor(21);
    }
}
