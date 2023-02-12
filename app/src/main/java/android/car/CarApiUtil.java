package android.car;

import android.car.settings.CarSettings;

/* loaded from: classes2.dex */
public final class CarApiUtil {
    public static final String CAR_NOT_CONNECTED_EXCEPTION_MSG = "CarNotConnected";

    public static void checkCarNotConnectedExceptionFromCarService(IllegalStateException e) throws CarNotConnectedException {
        if (e.getMessage().equals("CarNotConnected")) {
            throw new CarNotConnectedException();
        }
        throw e;
    }

    private CarApiUtil() {
    }

    public static int[] decodeGarageTimeSetting(String time) {
        int[] result = CarSettings.DEFAULT_GARAGE_MODE_WAKE_UP_TIME;
        if (time == null) {
            return result;
        }
        String[] tokens = time.split(":");
        if (tokens.length != 2) {
            return result;
        }
        try {
            result[0] = Integer.valueOf(tokens[0]).intValue();
            result[1] = Integer.valueOf(tokens[1]).intValue();
            if (result[0] >= 0 && result[0] <= 23 && result[1] >= 0 && result[1] <= 59) {
                return result;
            }
            return CarSettings.DEFAULT_GARAGE_MODE_WAKE_UP_TIME;
        } catch (NumberFormatException e) {
            return CarSettings.DEFAULT_GARAGE_MODE_WAKE_UP_TIME;
        }
    }

    public static String encodeGarageTimeSetting(int hour, int min) {
        return hour + ":" + min;
    }
}
