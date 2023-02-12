package com.yfve.tools;

import android.annotation.SuppressLint;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import com.yfve.engineeringmode.MainActivity;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: classes1.dex */
public class GpsUtils {
    private MainActivity mContext;
    private LocationListener mGpsLocationListener;
    private GpsNmeaListener mGpsNmeaListener;
    private GpsStatusListener mGpsStatusListener;
    private IGpsLocationChangedListener mIGpsLocationChangedListener;
    private IGpsNmeaListenerListener mIGpsNmeaListenerListener;
    private IGpsStatusChangedListener mIGpsStatusChangedListener;
    private Calendar mcalendar;
    private final String tag = GpsUtils.class.getSimpleName() + "zyx";
    private static GpsUtils mSingleInstance = null;
    private static LocationManager mLocationManager = null;

    /* loaded from: classes1.dex */
    public interface IGpsGpsSatelliteInfoCListener {
        void onGetGpsGpsSatellites(Iterator<GpsSatellite> it);
    }

    /* loaded from: classes1.dex */
    public interface IGpsLocationChangedListener {
        void onGetGpsLocation(Location location);

        void onGetGpsLocationChangedCalendar(Calendar calendar);

        void onGetGpsLocationChangedLatitude(double d);

        void onGetGpsLocationChangedLongitude(double d);
    }

    /* loaded from: classes1.dex */
    public interface IGpsNmeaListenerListener {
        void onGetNmeaReceived(long j, String str);
    }

    /* loaded from: classes1.dex */
    public interface IGpsStatusChangedListener {
        void onGetGpsLocationChangedSnr(String str);

        void onGetGpsStatusCount(int i);
    }

    public static synchronized GpsUtils getInstance(MainActivity context) {
        GpsUtils gpsUtils;
        synchronized (GpsUtils.class) {
            if (mSingleInstance == null) {
                mSingleInstance = new GpsUtils(context);
            }
            gpsUtils = mSingleInstance;
        }
        return gpsUtils;
    }

    private GpsUtils(MainActivity context) {
        this.mcalendar = null;
        this.mContext = context;
        this.mcalendar = Calendar.getInstance();
        mLocationManager = (LocationManager) this.mContext.getSystemService("location");
        if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1);
        }
    }

    public void setLocationChangedListener(IGpsLocationChangedListener gpsLocationChangedListener) {
        this.mIGpsLocationChangedListener = gpsLocationChangedListener;
    }

    public void setStatusListener(IGpsStatusChangedListener iGpsStatusChangedListener) {
        this.mIGpsStatusChangedListener = iGpsStatusChangedListener;
    }

    public void setGpsNmeaListener(IGpsNmeaListenerListener iGpsNmeaListenerListener) {
        this.mIGpsNmeaListenerListener = iGpsNmeaListenerListener;
    }

    public void initGpsLocationData() {
        if (this.mGpsLocationListener == null) {
            this.mGpsLocationListener = new GpsLocationListener();
        }
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return;
        }
        mLocationManager.requestLocationUpdates("gps", 1000L, 0.0f, this.mGpsLocationListener);
    }

    public void initGpsStatusData() {
        if (this.mGpsStatusListener == null) {
            this.mGpsStatusListener = new GpsStatusListener();
        }
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        mLocationManager.addGpsStatusListener(this.mGpsStatusListener);
    }

    public void initGpsNmeaData() {
        if (this.mGpsNmeaListener == null) {
            this.mGpsNmeaListener = new GpsNmeaListener();
        }
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        mLocationManager.addNmeaListener(this.mGpsNmeaListener);
    }

    public Calendar getLocalDatetimeCalendar(long iTime) {
        this.mcalendar.setTimeInMillis(iTime);
        return this.mcalendar;
    }

    public void removeLocationUpdates() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this.mGpsLocationListener);
        }
    }

    public void removeStatusUpdates() {
        if (mLocationManager != null) {
            mLocationManager.removeGpsStatusListener(this.mGpsStatusListener);
        }
    }

    public void removeNmeaUpdates() {
        if (mLocationManager != null) {
            mLocationManager.removeNmeaListener(this.mGpsNmeaListener);
        }
    }

    /* loaded from: classes1.dex */
    public class GpsLocationListener implements LocationListener {
        public GpsLocationListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            double dLongitude = location.getLongitude();
            double dLatitude = location.getLatitude();
            Calendar calendar = GpsUtils.this.getLocalDatetimeCalendar(location.getTime());
            if (GpsUtils.this.mIGpsLocationChangedListener != null) {
                GpsUtils.this.mIGpsLocationChangedListener.onGetGpsLocationChangedCalendar(calendar);
                GpsUtils.this.mIGpsLocationChangedListener.onGetGpsLocationChangedLongitude(dLongitude);
                GpsUtils.this.mIGpsLocationChangedListener.onGetGpsLocationChangedLatitude(dLatitude);
                GpsUtils.this.mIGpsLocationChangedListener.onGetGpsLocation(location);
                return;
            }
            LU.w(GpsUtils.this.tag, "the gps listener is null.");
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
        }
    }

    /* loaded from: classes1.dex */
    public class GpsStatusListener implements GpsStatus.Listener {
        public GpsStatusListener() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int event) {
            String str = GpsUtils.this.tag;
            LU.i(str, "onGpsStatusChanged()  event==" + event);
            if (event != 1) {
                switch (event) {
                    case 3:
                    default:
                        return;
                    case 4:
                        if (ActivityCompat.checkSelfPermission(GpsUtils.this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                            GpsStatus gpsStatus = GpsUtils.mLocationManager.getGpsStatus(null);
                            int maxSatellites = gpsStatus.getMaxSatellites();
                            Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                            int count = 0;
                            StringBuilder sb = new StringBuilder();
                            while (iters.hasNext() && count <= maxSatellites) {
                                GpsSatellite s = iters.next();
                                float snr = s.getSnr();
                                if (0.0f != snr) {
                                    count++;
                                    sb.append("Satellite ");
                                    sb.append(count);
                                    sb.append(" ：");
                                    sb.append(snr);
                                    sb.append("\n");
                                }
                            }
                            String str2 = GpsUtils.this.tag;
                            LU.e(str2, "onGpsStatusChanged()   snr==" + sb.toString().length());
                            if (sb.toString().length() == 0) {
                                if (GpsUtils.this.mIGpsStatusChangedListener != null) {
                                    GpsUtils.this.mIGpsStatusChangedListener.onGetGpsLocationChangedSnr("No satellite detected");
                                    GpsUtils.this.mIGpsStatusChangedListener.onGetGpsStatusCount(count);
                                    return;
                                }
                                return;
                            } else if (GpsUtils.this.mIGpsStatusChangedListener != null) {
                                GpsUtils.this.mIGpsStatusChangedListener.onGetGpsLocationChangedSnr(sb.toString());
                                GpsUtils.this.mIGpsStatusChangedListener.onGetGpsStatusCount(count);
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                }
            }
        }
    }

    /* loaded from: classes1.dex */
    private class GpsNmeaListener implements GpsStatus.NmeaListener {
        private GpsNmeaListener() {
        }

        @Override // android.location.GpsStatus.NmeaListener
        public void onNmeaReceived(long timestamp, String nmea) {
            if (GpsUtils.this.mIGpsNmeaListenerListener != null) {
                GpsUtils.this.mIGpsNmeaListenerListener.onGetNmeaReceived(timestamp, nmea);
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public GpsStatus getGpsStatus() {
        return mLocationManager.getGpsStatus(null);
    }
}
