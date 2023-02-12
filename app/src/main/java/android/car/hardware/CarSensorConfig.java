package android.car.hardware;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class CarSensorConfig implements Parcelable {
    public static final Parcelable.Creator<CarSensorConfig> CREATOR = new Parcelable.Creator<CarSensorConfig>() { // from class: android.car.hardware.CarSensorConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorConfig createFromParcel(Parcel in) {
            return new CarSensorConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarSensorConfig[] newArray(int size) {
            return new CarSensorConfig[size];
        }
    };
    public static final String WHEEL_TICK_DISTANCE_FRONT_LEFT_UM_PER_TICK = "android.car.wheelTickDistanceFrontLeftUmPerTick";
    public static final String WHEEL_TICK_DISTANCE_FRONT_RIGHT_UM_PER_TICK = "android.car.wheelTickDistanceFrontRightUmPerTick";
    public static final String WHEEL_TICK_DISTANCE_REAR_LEFT_UM_PER_TICK = "android.car.wheelTickDistanceRearLeftUmPerTick";
    public static final String WHEEL_TICK_DISTANCE_REAR_RIGHT_UM_PER_TICK = "android.car.wheelTickDistanceRearRightUmPerTick";
    public static final String WHEEL_TICK_DISTANCE_SUPPORTED_WHEELS = "android.car.wheelTickDistanceSupportedWhheels";
    private final Bundle mConfig;
    private final int mType;

    public CarSensorConfig(Parcel in) {
        this.mType = in.readInt();
        this.mConfig = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType);
        dest.writeBundle(this.mConfig);
    }

    public CarSensorConfig(int type, Bundle b) {
        this.mType = type;
        this.mConfig = b.deepCopy();
    }

    private void checkType(int type) {
        if (this.mType == type) {
            return;
        }
        throw new UnsupportedOperationException(String.format("Invalid sensor type: expected %d, got %d", Integer.valueOf(type), Integer.valueOf(this.mType)));
    }

    public Bundle getBundle() {
        return this.mConfig;
    }

    public int getInt(String key) {
        if (this.mConfig.containsKey(key)) {
            return this.mConfig.getInt(key);
        }
        throw new IllegalArgumentException("SensorType " + this.mType + " does not contain key: " + key);
    }

    public int getType() {
        return this.mType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName() + "[");
        sb.append("mType: " + this.mType);
        sb.append("mConfig: " + this.mConfig.toString());
        sb.append("]");
        return sb.toString();
    }
}
