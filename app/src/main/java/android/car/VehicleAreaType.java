package android.car;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class VehicleAreaType {
    public static final int VEHICLE_AREA_TYPE_DOOR = 4;
    public static final int VEHICLE_AREA_TYPE_MIRROR = 5;
    public static final int VEHICLE_AREA_TYPE_NONE = 0;
    public static final int VEHICLE_AREA_TYPE_SEAT = 3;
    public static final int VEHICLE_AREA_TYPE_TIRE = 6;
    public static final int VEHICLE_AREA_TYPE_WINDOW = 2;
    public static final int VEHICLE_AREA_TYPE_ZONE = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VehicleAreaTypeValue {
    }

    private VehicleAreaType() {
    }
}
