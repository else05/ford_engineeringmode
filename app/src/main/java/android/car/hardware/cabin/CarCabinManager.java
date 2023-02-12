package android.car.hardware.cabin;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyConfig;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManagerBase;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarCabinManager implements CarManagerBase {
    private static final boolean DBG = false;
    public static final int ID_ATMOSPHERE_LAMP_BRIGHTNESS = 12296;
    public static final int ID_ATMOSPHERE_LAMP_BRIGHTNESS_ST = 12321;
    public static final int ID_ATMOSPHERE_LAMP_COLOR = 12297;
    public static final int ID_ATMOSPHERE_LAMP_COLOR_ST = 12322;
    public static final int ID_ATMOSPHERE_LAMP_ON = 12295;
    public static final int ID_DOOR_LOCK = 3;
    public static final int ID_DOOR_MOVE = 2;
    public static final int ID_DOOR_POS = 1;
    public static final int ID_DRIVER_SEAT_ADJUSTMENT = 12298;
    public static final int ID_DRIVER_SEAT_MASSAGE = 12300;
    public static final int ID_MIRROR_FOLD = 4102;
    public static final int ID_MIRROR_LOCK = 4101;
    public static final int ID_MIRROR_Y_MOVE = 4100;
    public static final int ID_MIRROR_Y_POS = 4099;
    public static final int ID_MIRROR_Z_MOVE = 4098;
    public static final int ID_MIRROR_Z_POS = 4097;
    public static final int ID_PASSANGER_SEAT_MASSAGE = 12301;
    public static final int ID_PASSENGER_SEAT_ADJUSTMENT = 12299;
    public static final int ID_SEATLUMBAR_RQ = 12309;
    public static final int ID_SEATMASG_INTNS_D_STAT = 12313;
    public static final int ID_SEATMASG_PATTERN_RQ = 12311;
    public static final int ID_SEATMASG_PATTERN_STAT = 12312;
    public static final int ID_SEATSCRNDRVON_B_STAT = 12310;
    public static final int ID_SEATSWTCH_B_STAT = 12320;
    public static final int ID_SEAT_AREA_SET = 12307;
    public static final int ID_SEAT_BACKREST_ANGLE_1_MOVE = 8201;
    public static final int ID_SEAT_BACKREST_ANGLE_1_POS = 8200;
    public static final int ID_SEAT_BACKREST_ANGLE_2_MOVE = 8203;
    public static final int ID_SEAT_BACKREST_ANGLE_2_POS = 8202;
    public static final int ID_SEAT_BELT_BUCKLED = 8195;
    public static final int ID_SEAT_BELT_HEIGHT_MOVE = 8197;
    public static final int ID_SEAT_BELT_HEIGHT_POS = 8196;
    public static final int ID_SEAT_DEPTH_MOVE = 8207;
    public static final int ID_SEAT_DEPTH_POS = 8206;
    public static final int ID_SEAT_FORE_AFT_MOVE = 8199;
    public static final int ID_SEAT_FORE_AFT_POS = 8198;
    public static final int ID_SEAT_HEADREST_ANGLE_MOVE = 8217;
    public static final int ID_SEAT_HEADREST_ANGLE_POS = 8216;
    public static final int ID_SEAT_HEADREST_FORE_AFT_MOVE = 8219;
    public static final int ID_SEAT_HEADREST_FORE_AFT_POS = 8218;
    public static final int ID_SEAT_HEADREST_HEIGHT_MOVE = 8215;
    public static final int ID_SEAT_HEADREST_HEIGHT_POS = 8214;
    public static final int ID_SEAT_HEIGHT_MOVE = 8205;
    public static final int ID_SEAT_HEIGHT_POS = 8204;
    public static final int ID_SEAT_INTENSITY_SET = 12308;
    public static final int ID_SEAT_LOWERBOLSTER_ADJUST = 12305;
    public static final int ID_SEAT_LOWERLUMBAR_ADJUST = 12302;
    public static final int ID_SEAT_LUMBAR_FORE_AFT_MOVE = 8211;
    public static final int ID_SEAT_LUMBAR_FORE_AFT_POS = 8210;
    public static final int ID_SEAT_LUMBAR_SIDE_SUPPORT_MOVE = 8213;
    public static final int ID_SEAT_LUMBAR_SIDE_SUPPORT_POS = 8212;
    public static final int ID_SEAT_MEMORY_SELECT = 8193;
    public static final int ID_SEAT_MEMORY_SET = 8194;
    public static final int ID_SEAT_MIDDLELUMBAR_ADJUST = 12303;
    public static final int ID_SEAT_TILT_MOVE = 8209;
    public static final int ID_SEAT_TILT_POS = 8208;
    public static final int ID_SEAT_UPPERBLOSTER_ADJUST = 12306;
    public static final int ID_SEAT_UPPERLUMBAR_ADJUST = 12304;
    public static final int ID_TRUNK_ON = 12294;
    public static final int ID_WINDOW_LOCK = 12293;
    public static final int ID_WINDOW_MOVE = 12290;
    public static final int ID_WINDOW_POS = 12289;
    public static final int ID_WINDOW_VENT_MOVE = 12292;
    public static final int ID_WINDOW_VENT_POS = 12291;
    private static final String TAG = "CarCabinManager";
    private final ArraySet<CarCabinEventCallback> mCallbacks = new ArraySet<>();
    private CarPropertyEventListenerToBase mListenerToBase = null;
    private final CarPropertyManagerBase mMgr;

    /* loaded from: classes2.dex */
    public interface CarCabinEventCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PropertyId {
    }

    /* loaded from: classes2.dex */
    private static class CarPropertyEventListenerToBase implements CarPropertyManagerBase.CarPropertyEventCallback {
        private final WeakReference<CarCabinManager> mManager;

        public CarPropertyEventListenerToBase(CarCabinManager manager) {
            this.mManager = new WeakReference<>(manager);
        }

        @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            CarCabinManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnChangeEvent(value);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            CarCabinManager manager = this.mManager.get();
            if (manager != null) {
                manager.handleOnErrorEvent(propertyId, zone);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangeEvent(CarPropertyValue value) {
        Collection<CarCabinEventCallback> callbacks;
        synchronized (this) {
            callbacks = new ArraySet<>(this.mCallbacks);
        }
        for (CarCabinEventCallback l : callbacks) {
            l.onChangeEvent(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnErrorEvent(int propertyId, int zone) {
        Collection<CarCabinEventCallback> listeners;
        synchronized (this) {
            listeners = new ArraySet<>(this.mCallbacks);
        }
        if (!listeners.isEmpty()) {
            for (CarCabinEventCallback l : listeners) {
                l.onErrorEvent(propertyId, zone);
            }
        }
    }

    public CarCabinManager(IBinder service, Context context, Handler handler) {
        this.mMgr = new CarPropertyManagerBase(service, handler, false, TAG);
    }

    public static boolean isZonedProperty(int propertyId) {
        return true;
    }

    public synchronized void registerCallback(CarCabinEventCallback callback) throws CarNotConnectedException {
        if (this.mCallbacks.isEmpty()) {
            this.mListenerToBase = new CarPropertyEventListenerToBase(this);
            this.mMgr.registerCallback(this.mListenerToBase);
        }
        this.mCallbacks.add(callback);
    }

    public synchronized void unregisterCallback(CarCabinEventCallback callback) {
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
        this.mMgr.setIntProperty(propertyId, area, val);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mMgr.onCarDisconnected();
    }
}
