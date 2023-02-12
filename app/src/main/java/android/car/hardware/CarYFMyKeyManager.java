package android.car.hardware;

import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.hardware.property.CarPropertyManagerBase;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import java.util.List;

/* loaded from: classes2.dex */
public final class CarYFMyKeyManager implements CarManagerBase {
    public static final byte AUDIOLIMITCONFIG_RQ_Inactive = 0;
    public static final byte AUDIOLIMITCONFIG_RQ_off = 1;
    public static final byte AUDIOLIMITCONFIG_RQ_on = 1;
    public static final int BeltminderAudioMute = 292573973;
    public static final byte CREATMYKEY_RQ_Create = 1;
    public static final byte CREATMYKEY_RQ_Inactive = 0;
    private static final boolean DBG = true;
    public static final int DoNotDisturbOveride_St = 292573975;
    public static final byte ESCRESTRICTCONF_RQ_Inactive = 0;
    public static final byte ESCRESTRICTCONF_RQ_off = 1;
    public static final byte ESCRESTRICTCONF_RQ_on = 1;
    public static final int ID_CREATEMYKEY_RSP = 292573960;
    public static final int ID_CREATMYKEY_RQ = 292573959;
    public static final int ID_IGNKEYTYPE_D_ACTl = 292573952;
    public static final int ID_KEYMYKEYTOT_NO_CNT = 292573972;
    public static final int ID_MK_ADMINMYKEYCOUNT_RSP = 292573955;
    public static final int ID_MK_ADMINMYKEYCOUNT_ST = 292573957;
    public static final int ID_MK_AUDIOLIMITCONFIG_RQ = 292573966;
    public static final int ID_MK_AUDIOLIMITCONFIG_ST = 292573967;
    public static final int ID_MK_ESCRESTRICTCONF_RQ = 292573968;
    public static final int ID_MK_ESCRESTRICTCONF_ST = 292573969;
    public static final int ID_MK_MYKEYDRIVERKM_RSP = 292573956;
    public static final int ID_MK_MYKEYSYSTEMINFO_RQ = 292573953;
    public static final int ID_MK_RESETALLUSERKEYS_RQ = 292573961;
    public static final int ID_MK_SPEEDLIMITCONF_ST = 292573964;
    public static final int ID_MK_SPEEDMINDERCONF_ST = 292573965;
    public static final int ID_MK_SPEEDSETTING_RQ = 292573962;
    public static final int ID_MK_SPEEDVALUE_RQ = 292573963;
    public static final int ID_MK_USERMYKEYCOUNT_RSP = 292573954;
    public static final int ID_MK_USERMYKEYCOUNT_ST = 292573958;
    public static final int ID_MyKey_e911Override_St = 289428248;
    public static final int ID_PAAKMYKEYSEARCH_D_STAT = 292573971;
    public static final int ID_PAAKMYKEY_D_RQ = 292573970;
    public static final int IPC_MyKeyVoLimit_St = 292573974;
    public static final byte MYKEYSYSTEMINFO_RQ_GetData = 1;
    public static final byte MYKEYSYSTEMINFO_RQ_Inactive = 0;
    public static final byte RESETALLUSERKEYS_RQ_Inactive = 0;
    public static final byte RESETALLUSERKEYS_RQ_ResetAllKeys = 1;
    public static final byte SPEEDSETTING_RQ_Inactive = 0;
    public static final byte SPEEDSETTING_RQ_Speedlimit = 1;
    public static final byte SPEEDSETTING_RQ_Speedminder = 1;
    private static final String TAG = CarYFMyKeyManager.class.getSimpleName();
    @GuardedBy("mLock")
    private ArraySet<CarYFMyKeyCallback> mCallbacks;
    private final Object mLock = new Object();
    private final CarPropertyManagerBase mPropertyManager;

    /* loaded from: classes2.dex */
    public interface CarYFMyKeyCallback {
        void onChangeEvent(CarPropertyValue carPropertyValue);

        void onErrorEvent(int i, int i2);
    }

    public CarYFMyKeyManager(IBinder service, Handler handler) {
        this.mPropertyManager = new CarPropertyManagerBase(service, handler, true, TAG);
        Log.i(TAG, "CarYFMyKeyManager");
    }

    public void registerCallback(CarYFMyKeyCallback callback) throws CarNotConnectedException {
        synchronized (this.mLock) {
            if (this.mCallbacks == null) {
                this.mPropertyManager.registerCallback(new CarPropertyManagerBase.CarPropertyEventCallback() { // from class: android.car.hardware.CarYFMyKeyManager.1
                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onChangeEvent(CarPropertyValue value) {
                        CarYFMyKeyCallback[] callbacks;
                        Log.i(CarYFMyKeyManager.TAG, "CarYFMyKeyCallBack");
                        for (CarYFMyKeyCallback listener : CarYFMyKeyManager.this.getCallbacks()) {
                            listener.onChangeEvent(value);
                        }
                    }

                    @Override // android.car.hardware.property.CarPropertyManagerBase.CarPropertyEventCallback
                    public void onErrorEvent(int propertyId, int zone) {
                        CarYFMyKeyCallback[] callbacks;
                        for (CarYFMyKeyCallback listener : CarYFMyKeyManager.this.getCallbacks()) {
                            listener.onErrorEvent(propertyId, zone);
                        }
                    }
                });
                this.mCallbacks = new ArraySet<>(1);
            }
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(CarYFMyKeyCallback callback) {
        synchronized (this.mLock) {
            this.mCallbacks.remove(callback);
            if (this.mCallbacks.isEmpty()) {
                this.mPropertyManager.unregisterCallback();
                this.mCallbacks = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CarYFMyKeyCallback[] getCallbacks() {
        CarYFMyKeyCallback[] carYFMyKeyCallbackArr;
        synchronized (this.mLock) {
            carYFMyKeyCallbackArr = (CarYFMyKeyCallback[]) this.mCallbacks.toArray(new CarYFMyKeyCallback[this.mCallbacks.size()]);
        }
        return carYFMyKeyCallbackArr;
    }

    public List<CarPropertyConfig> getProperties() throws CarNotConnectedException {
        return this.mPropertyManager.getPropertyList();
    }

    public <E> E getGlobalProperty(Class<E> propertyClass, int propId) throws CarNotConnectedException {
        return (E) getProperty(propertyClass, propId, 0);
    }

    public <E> E getProperty(Class<E> propertyClass, int propId, int area) throws CarNotConnectedException {
        return this.mPropertyManager.getProperty(propertyClass, propId, area).getValue();
    }

    public <E> void setGlobalProperty(Class<E> propertyClass, int propId, E value) throws CarNotConnectedException {
        Log.i(TAG, "set MyKey prop");
        this.mPropertyManager.setProperty(propertyClass, propId, 0, value);
    }

    public <E> void setProperty(Class<E> propertyClass, int propId, int area, E value) throws CarNotConnectedException {
        this.mPropertyManager.setProperty(propertyClass, propId, area, value);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        this.mPropertyManager.onCarDisconnected();
    }
}
