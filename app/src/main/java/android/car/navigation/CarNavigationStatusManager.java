package android.car.navigation;

import android.car.CarApiUtil;
import android.car.CarManagerBase;
import android.car.CarNotConnectedException;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class CarNavigationStatusManager implements CarManagerBase {
    public static final int DISTANCE_FEET = 4;
    public static final int DISTANCE_KILOMETERS = 2;
    public static final int DISTANCE_METERS = 1;
    public static final int DISTANCE_MILES = 3;
    public static final int DISTANCE_YARDS = 5;
    public static final int EVENT_TYPE_NEXT_MANEUVER_COUNTDOWN = 2;
    public static final int EVENT_TYPE_NEXT_MANEUVER_INFO = 1;
    public static final int EVENT_TYPE_VENDOR_FIRST = 1024;
    private static final int START = 1;
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 2;
    public static final int STATUS_UNAVAILABLE = 0;
    private static final int STOP = 2;
    private static final String TAG = "CAR.L.NAV";
    public static final int TURN_DEPART = 1;
    public static final int TURN_DESTINATION = 19;
    public static final int TURN_FERRY_BOAT = 16;
    public static final int TURN_FERRY_TRAIN = 17;
    public static final int TURN_FORK = 9;
    public static final int TURN_MERGE = 10;
    public static final int TURN_NAME_CHANGE = 2;
    public static final int TURN_OFF_RAMP = 8;
    public static final int TURN_ON_RAMP = 7;
    public static final int TURN_ROUNDABOUT_ENTER = 11;
    public static final int TURN_ROUNDABOUT_ENTER_AND_EXIT = 13;
    public static final int TURN_ROUNDABOUT_EXIT = 12;
    public static final int TURN_SHARP_TURN = 5;
    public static final int TURN_SIDE_LEFT = 1;
    public static final int TURN_SIDE_RIGHT = 2;
    public static final int TURN_SIDE_UNSPECIFIED = 3;
    public static final int TURN_SLIGHT_TURN = 3;
    public static final int TURN_STRAIGHT = 14;
    public static final int TURN_TURN = 4;
    public static final int TURN_UNKNOWN = 0;
    public static final int TURN_U_TURN = 6;
    private final IInstrumentClusterNavigation mService;

    /* loaded from: classes2.dex */
    public @interface DistanceUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Status {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TurnEvent {
    }

    /* loaded from: classes2.dex */
    public @interface TurnSide {
    }

    public CarNavigationStatusManager(IBinder service) {
        this.mService = IInstrumentClusterNavigation.Stub.asInterface(service);
    }

    public void sendNavigationStatus(int status) throws CarNotConnectedException {
        try {
            if (status == 1) {
                this.mService.onStartNavigation();
            } else {
                this.mService.onStopNavigation();
            }
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public void sendNavigationTurnEvent(int turnEvent, CharSequence eventName, int turnAngle, int turnNumber, Bitmap image, @TurnSide int turnSide) throws CarNotConnectedException {
        try {
            this.mService.onNextManeuverChanged(turnEvent, eventName, turnAngle, turnNumber, image, turnSide);
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public void sendNavigationTurnDistanceEvent(int distanceMeters, int timeSeconds, int displayDistanceMillis, @DistanceUnit int displayDistanceUnit) throws CarNotConnectedException {
        try {
            this.mService.onNextManeuverDistanceChanged(distanceMeters, timeSeconds, displayDistanceMillis, displayDistanceUnit);
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    public void sendEvent(int eventType, Bundle bundle) throws CarNotConnectedException {
        try {
            this.mService.onEvent(eventType, bundle);
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
        } catch (IllegalStateException e2) {
            CarApiUtil.checkCarNotConnectedExceptionFromCarService(e2);
        }
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        Log.d("CAR.L.NAV", "onCarDisconnected");
    }

    public CarNavigationInstrumentCluster getInstrumentClusterInfo() throws CarNotConnectedException {
        try {
            return this.mService.getInstrumentClusterInfo();
        } catch (RemoteException e) {
            handleCarServiceRemoteExceptionAndThrow(e);
            return null;
        }
    }

    private void handleCarServiceRemoteExceptionAndThrow(RemoteException e) throws CarNotConnectedException {
        handleCarServiceRemoteException(e);
        throw new CarNotConnectedException();
    }

    private void handleCarServiceRemoteException(RemoteException e) {
        Log.w("CAR.L.NAV", "RemoteException from car service:" + e.getMessage());
    }
}
