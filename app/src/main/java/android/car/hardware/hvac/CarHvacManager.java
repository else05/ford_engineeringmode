package android.car.hardware.hvac;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManagerBase;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarHvacManager implements CarManagerBase {
    private static final boolean DBG = true;
    public static final int FAN_BODY = 32;
    public static final int FAN_BODY_FLOOR = 96;
    public static final int FAN_FACE = 16;
    public static final int FAN_FACE_BODY = 48;
    public static final int FAN_FACE_BODY_FLOOR = 112;
    public static final int FAN_FACE_FLOOR = 80;
    public static final int FAN_FLOOR = 64;
    public static final int FAN_POSITION_DEFROST = 4;
    public static final int FAN_POSITION_DEFROST_AND_FLOOR = 5;
    public static final int FAN_POSITION_FACE = 1;
    public static final int FAN_POSITION_FACE_AND_FLOOR = 3;
    public static final int FAN_POSITION_FLOOR = 2;
    public static final int ID_HVAC_AAR = 20508;
    public static final int ID_HVAC_POPUP = 20513;
    public static final int ID_LOAD_SHEDDING_MODE = 20484;
    public static final int ID_LOCK_BACKROW = 20490;
    public static final int ID_MAX_GLOBAL_PROPERTY_ID = 16383;
    public static final int ID_MIRROR_DEFROSTER_ON = 1;
    public static final int ID_OUTSIDE_AIR_TEMP = 3;
    public static final int ID_SECOND_ROW_AUTO = 20492;
    public static final int ID_SECOND_ROW_BLOWER_DEC = 20494;
    public static final int ID_SECOND_ROW_BLOWER_INC = 20493;
    public static final int ID_SECOND_ROW_BLOWER_SET = 20511;
    public static final int ID_SECOND_ROW_FACE = 20498;
    public static final int ID_SECOND_ROW_FLOOR = 20495;
    public static final int ID_SECOND_ROW_POWER = 20491;
    public static final int ID_SECOND_ROW_TEMP_DEC = 20497;
    public static final int ID_SECOND_ROW_TEMP_INC = 20496;
    public static final int ID_SECOND_ROW_TEMP_SET = 20509;
    public static final int ID_STEERING_WHEEL_TEMP = 2;
    public static final int ID_STEERING_WHEEL_TEMP_LEVEL = 20488;
    public static final int ID_TEMPERATURE_UNITS = 4;
    public static final int ID_THIRD_ROW_AUTO = 20504;
    public static final int ID_THIRD_ROW_BLOWER_DEC = 20502;
    public static final int ID_THIRD_ROW_BLOWER_INC = 20501;
    public static final int ID_THIRD_ROW_BLOWER_SET = 20512;
    public static final int ID_THIRD_ROW_FACE = 20505;
    public static final int ID_THIRD_ROW_FLOOR = 20506;
    public static final int ID_THIRD_ROW_POWER = 20507;
    public static final int ID_THIRD_ROW_TEMP_DEC = 20500;
    public static final int ID_THIRD_ROW_TEMP_INC = 20499;
    public static final int ID_THIRD_ROW_TEMP_SET = 20510;
    public static final int ID_THIRD_SECOND_ROW_SYNC = 20503;
    public static final int ID_WINDOW_DEFROSTER_ON = 20481;
    public static final int ID_ZONED_AC_ON = 16393;
    public static final int ID_ZONED_AIR_RECIRCULATION_ON = 16395;
    public static final int ID_ZONED_AIR_REFRESH = 20485;
    public static final int ID_ZONED_AUTOMATIC_LABLE = 20487;
    public static final int ID_ZONED_AUTOMATIC_MODE_ON = 16394;
    public static final int ID_ZONED_DUAL_ZONE_ON = 16397;
    public static final int ID_ZONED_FAN_POSITION = 16391;
    public static final int ID_ZONED_FAN_POSITION_AVAILABLE = 16390;
    public static final int ID_ZONED_FAN_SPEED_PLUS = 20482;
    public static final int ID_ZONED_FAN_SPEED_RPM = 16389;
    public static final int ID_ZONED_FAN_SPEED_SETPOINT = 16388;
    public static final int ID_ZONED_HVAC_AUTO_RECIRC_ON = 16399;
    public static final int ID_ZONED_HVAC_LOCK = 20483;
    public static final int ID_ZONED_HVAC_POWER_ON = 16387;
    public static final int ID_ZONED_MAX_AC_ON = 16396;
    public static final int ID_ZONED_MAX_DEFROST_ON = 16398;
    public static final int ID_ZONED_MULTIPLE_AUTOMATIC_MODE_ON = 20486;
    public static final int ID_ZONED_SEAT_TEMP = 16392;
    public static final int ID_ZONED_TEMP_ACTUAL = 16386;
    public static final int ID_ZONED_TEMP_SETPOINT = 16385;
    public static final int ID_ZONE_FAN_FLOOR = 20489;
    private static final String TAG = "CarHvacManager";
    private final ArraySet<CarHvacEventCallback> mCallbacks = new ArraySet<>();
    private CarPropertyEventListenerToBase mListenerToBase = null;
    private final CarPropertyManagerBase mMgr;

    /* loaded from: classes2.dex */
    public interface CarHvacEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PropertyId {
    }

    /* loaded from: classes2.dex */
    private static class CarPropertyEventListenerToBase implements CarPropertyManagerBase.CarPropertyEventCallback {
        private final WeakReference<CarHvacManager> mManager;

        public CarPropertyEventListenerToBase(CarHvacManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            Log.i(CarHvacManager.TAG, "PropID,area,value:" + value.getPropertyId() + "_" + value.getAreaId() + "_" + value.getValue());
            CarHvacManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            CarHvacManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(propertyId, zone);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangeEvent(CarPropertyValue value) {
        Collection<CarHvacEventCallback> callbacks;
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
        }
        if (!callbacks.isEmpty()) {
            for (CarHvacEventCallback l : callbacks) {
                l.onChangeEvent(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnErrorEvent(int propertyId, int zone) {
        Collection<CarHvacEventCallback> callbacks;
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
        }
        if (!callbacks.isEmpty()) {
            for (CarHvacEventCallback l : callbacks) {
                l.onErrorEvent(propertyId, zone);
            }
        }
    }

    public CarHvacManager(IBinder service, Context context, Handler handler) {
        this.mMgr = new CarPropertyManagerBase(service, handler, true, TAG);
    }

    public static boolean isZonedProperty(int propertyId) {
        return propertyId > 16383;
    }

    public synchronized void registerCallback(CarHvacEventCallback callback) throws CarNotConnectedException {
        if (this.mCallbacks.isEmpty()) {
            this.mListenerToBase = new CarPropertyEventListenerToBase(this);
            this.mMgr.registerCallback(this.mListenerToBase);
        }
        this.mCallbacks.add(callback);
    }

    public synchronized void unregisterCallback(CarHvacEventCallback callback) {
        this.mCallbacks.remove(callback);
        if (this.mCallbacks.isEmpty()) {
            this.mMgr.unregisterCallback();
            this.mListenerToBase = null;
        }
    }

    public List<CarPropertyConfig> getPropertyList() throws CarNotConnectedException {
        return this.mMgr.getPropertyList();
    }

    public boolean getBooleanProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mMgr.getBooleanProperty(propertyId, area);
    }

    public float getFloatProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mMgr.getFloatProperty(propertyId, area);
    }

    public int getIntProperty(int propertyId, int area) throws CarNotConnectedException {
        return this.mMgr.getIntProperty(propertyId, area);
    }

    public void setBooleanProperty(int propertyId, int area, boolean val) throws CarNotConnectedException {
        this.mMgr.setBooleanProperty(propertyId, area, val);
    }

    public void setFloatProperty(int propertyId, int area, float val) throws CarNotConnectedException {
        this.mMgr.setFloatProperty(propertyId, area, val);
    }

    public void setIntProperty(int propertyId, int area, int val) throws CarNotConnectedException {
        Log.i(TAG, "PropID :0x" + Integer.toHexString(propertyId) + " area :" + Integer.toHexString(area) + "value :" + val);
        this.mMgr.setIntProperty(propertyId, area, val);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mMgr.onCarDisconnected();
    }
}
