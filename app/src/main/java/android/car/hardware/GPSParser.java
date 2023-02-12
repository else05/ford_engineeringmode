package android.car.hardware;

import android.os.Bundle;
import android.util.Log;
import com.github.mikephil.charting.utils.Utils;
import java.math.BigDecimal;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class GPSParser {
    private static final int GpsHsphLattSth_D_ACTLNorthern = 2;
    private static final int GpsHsphLattSth_D_ACTLSouthern = 1;
    private static final int GpsHsphLongEast_D_ACTLEastern = 1;
    private static final int GpsHsphLongEast_D_ACTLWestern = 2;
    private static final String TAG = GPSParser.class.getSimpleName();

    public byte[] parse462Data(Bundle bundle) {
        byte[] b = new byte[11];
        double Latitude = bundle.getDouble("latitude", Utils.DOUBLE_EPSILON);
        double Longitude = bundle.getDouble("longitude", Utils.DOUBLE_EPSILON);
        Log.i(TAG, "latitude: " + Latitude + "  longitude: " + Longitude);
        if (Longitude < Utils.DOUBLE_EPSILON) {
            b[0] = 2;
        } else {
            b[0] = 1;
        }
        if (Latitude < Utils.DOUBLE_EPSILON) {
            b[1] = 1;
        } else {
            b[1] = 2;
        }
        int Gps_Longitute_Degrees = (int) Longitude;
        Log.d(TAG, "Gps_Longitute_Degrees b[5][6]: " + Gps_Longitute_Degrees);
        if ((Gps_Longitute_Degrees < -179) | (Gps_Longitute_Degrees > 179)) {
            b[5] = 1;
            b[6] = -2;
        } else {
            b[5] = (byte) (((Gps_Longitute_Degrees + LocationProtocal.longitudeDIMax) >> 8) & 255);
            b[6] = (byte) ((Gps_Longitute_Degrees + LocationProtocal.longitudeDIMax) & 255);
        }
        double LongituteMinutes = Math.abs(mul(sub(Longitude, Gps_Longitute_Degrees), 60.0d));
        Log.d(TAG, "LongituteMinutes b[4]: " + LongituteMinutes);
        int Gps_Longitute_Minutes = (int) LongituteMinutes;
        Log.d(TAG, "Gps_Longitute_Minutes b[4]: " + Gps_Longitute_Minutes);
        if ((Gps_Longitute_Minutes < 0) | (Gps_Longitute_Minutes > 59)) {
            b[4] = 62;
        } else {
            b[4] = (byte) (Gps_Longitute_Minutes & 255);
        }
        double Gps_Longitute_Min_dec = sub(LongituteMinutes, Gps_Longitute_Minutes);
        Log.d(TAG, "Gps_Longitute_Min_dec b[2]b[3]: " + Gps_Longitute_Min_dec);
        if (!((Gps_Longitute_Min_dec < 1.0E-4d) | (Gps_Longitute_Min_dec > 0.9999d))) {
            int Gps_Longitute_Min_decInt = (int) mul(Gps_Longitute_Min_dec, 10000.0d);
            b[2] = (byte) ((Gps_Longitute_Min_decInt >> 8) & 255);
            b[3] = (byte) (Gps_Longitute_Min_decInt & 255);
        } else {
            b[2] = 63;
            b[3] = -2;
        }
        int Gps_Longitute_Min_decInt2 = (int) Latitude;
        Log.d(TAG, "Gps_Latitute_Degreesb[10]: " + Gps_Longitute_Min_decInt2);
        if ((Gps_Longitute_Min_decInt2 < -89) | (Gps_Longitute_Min_decInt2 > 89)) {
            b[10] = -2;
        } else {
            b[10] = (byte) ((Gps_Longitute_Min_decInt2 + 89) & 255);
        }
        double LatituteMinutes = Math.abs(mul(sub(Latitude, Gps_Longitute_Min_decInt2), 60.0d));
        int Gps_Latitute_Minutes = (int) LatituteMinutes;
        Log.d(TAG, "Gps_Latitute_Minutes[9]: " + Gps_Latitute_Minutes);
        if ((Gps_Latitute_Minutes < 0) | (Gps_Latitute_Minutes > 59)) {
            b[9] = 62;
        } else {
            b[9] = (byte) (Gps_Latitute_Minutes & 255);
        }
        double Gps_Latitude_Min_dec = sub(LatituteMinutes, Gps_Latitute_Minutes);
        Log.i(TAG, "Gps_Latitude_Min_dec [7][8]: " + Gps_Latitude_Min_dec);
        if (!((Gps_Latitude_Min_dec < 1.0E-4d) | (Gps_Latitude_Min_dec > 0.9999d))) {
            int Gps_Latitude_Min_decInt = (int) mul(Gps_Latitude_Min_dec, 10000.0d);
            b[7] = (byte) ((Gps_Latitude_Min_decInt >> 8) & 255);
            b[8] = (byte) (Gps_Latitude_Min_decInt & 255);
        } else {
            b[7] = 63;
            b[8] = -2;
        }
        String s = "";
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < b.length) {
                s = s + Integer.toHexString(b[i2]) + ",";
                i = i2 + 1;
            } else {
                Log.d(TAG, s);
                return b;
            }
        }
    }

    public byte[] parse463Data(Bundle bundle) {
        byte[] b = new byte[10];
        b[0] = 0;
        b[1] = (byte) ((bundle.getInt("UTCYear", 1) - 2010) & 255);
        b[2] = (byte) (bundle.getInt("UTCMonth", 1) & 255);
        b[3] = (byte) (bundle.getInt("UTCTD", 1) & 255);
        b[4] = 1;
        b[5] = (byte) (bundle.getInt("GPS_Compass_direction", 0) & 255);
        String str = TAG;
        Log.d(str, "GPS_Compass_direction " + bundle.getInt("GPS_Compass_direction", 0));
        float GPS_Pdop = bundle.getFloat("accuracy", 5.9f);
        String str2 = TAG;
        Log.d(str2, "GPS_Pdop=accuracy" + GPS_Pdop);
        if ((((double) GPS_Pdop) > 5.8d) | (GPS_Pdop < 0.0f)) {
            b[6] = 30;
        } else {
            b[6] = (byte) (((int) mul(GPS_Pdop, 5.0d)) & 255);
        }
        b[7] = (byte) (bundle.getInt("UTCTS", 1) & 255);
        b[8] = (byte) (bundle.getInt("UTCTM", 1) & 255);
        b[9] = (byte) (bundle.getInt("UTCTH", 1) & 255);
        String str3 = TAG;
        Log.d(str3, "463: " + Arrays.toString(b));
        return b;
    }

    public byte[] parse464Data(Bundle bundle) {
        byte[] b = new byte[9];
        b[0] = 30;
        b[1] = 30;
        int GPS_Speed = (int) bundle.getFloat("velocity", 254.0f);
        String str = TAG;
        Log.d(str, "GPS_Speed=GPS_Speed" + bundle.getFloat("velocity", 254.0f));
        if ((GPS_Speed < 0) | (GPS_Speed > 253)) {
            b[2] = -2;
        } else {
            b[2] = (byte) (GPS_Speed & 255);
        }
        float GPS_Heading = bundle.getFloat("heading", 655.34f);
        String str2 = TAG;
        Log.d(str2, "GPS_Speed=GPS_Speed" + bundle.getFloat("heading", 655.34f));
        if ((GPS_Heading < 0.0f) | (((double) GPS_Heading) > 655.33d)) {
            b[3] = -1;
            b[4] = -2;
        } else {
            int GPS_HeadingInt = (int) mul(GPS_Heading, 100.0d);
            b[3] = (byte) ((GPS_HeadingInt >> 8) & 255);
            b[4] = (byte) (GPS_HeadingInt & 255);
        }
        int GPS_MSL_altitude = (int) bundle.getDouble("altitude", 20460.0d);
        String str3 = TAG;
        Log.d(str3, "GPS_MSL_altitude=altitude" + bundle.getDouble("altitude", 20460.0d));
        if ((GPS_MSL_altitude < -20460) & (GPS_MSL_altitude > 20460)) {
            b[5] = 15;
            b[6] = -2;
        } else {
            b[5] = (byte) ((((GPS_MSL_altitude + 20460) / 10) >> 8) & 255);
            b[6] = (byte) (((GPS_MSL_altitude + 20460) / 10) & 255);
        }
        b[7] = 0;
        int GPS_Sat_num_in_view = bundle.getInt("GPSSIS", 30);
        String str4 = TAG;
        Log.d(str4, "GPS_Sat_num_in_view" + bundle.getInt("GPSSIS", 30));
        if ((GPS_Sat_num_in_view > 29) | (GPS_Sat_num_in_view < 0)) {
            b[8] = 30;
        } else {
            b[8] = (byte) (GPS_Sat_num_in_view & 255);
        }
        String str5 = TAG;
        Log.d(str5, "464: " + Arrays.toString(b));
        return b;
    }

    private double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

    private double mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
}
