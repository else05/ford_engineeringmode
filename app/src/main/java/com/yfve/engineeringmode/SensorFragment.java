package com.yfve.engineeringmode;

import android.car.Car;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yfve.tools.GpsUtils;
import java.util.Calendar;

/* loaded from: classes1.dex */
public class SensorFragment extends BaseFragment {
    private TextView mtvAccelerometerX;
    private TextView mtvAccelerometerY;
    private TextView mtvAccelerometerZ;
    private TextView mtvGpsSnr;
    private TextView mtvGpsTime;
    private TextView mtvLatitudeValue;
    private TextView mtvLongitudeValue;
    private SensorManager mSensorManager = null;
    private Sensor mSensorAccelerometer = null;
    private MyAccelerometerSensorEventListener mMyAccelerometerSensorEventListener = null;
    private GpsLocationChangedListener mGpsLocationChangedListener = null;
    private GpsStatusChangedListener mGpsStatusChangedListener = null;
    private long mlCurrentTimeAccelerometer = -1;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sensor, (ViewGroup) null);
        if (ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.ACCESS_FINE_LOCATION") != 0 || ActivityCompat.checkSelfPermission(this.mMainActivity, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this.mMainActivity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1);
        }
        this.mtvAccelerometerX = (TextView) view.findViewById(R.id.tv_accelerometer_x);
        this.mtvAccelerometerY = (TextView) view.findViewById(R.id.tv_accelerometer_y);
        this.mtvAccelerometerZ = (TextView) view.findViewById(R.id.tv_accelerometer_z);
        this.mtvLongitudeValue = (TextView) view.findViewById(R.id.tv_gps_longitude_value);
        this.mtvLatitudeValue = (TextView) view.findViewById(R.id.tv_gps_latitude_value);
        this.mtvGpsTime = (TextView) view.findViewById(R.id.tv_gps_time_value);
        this.mtvGpsSnr = (TextView) view.findViewById(R.id.tv_gps_snr_value);
        this.mSensorManager = (SensorManager) this.mMainActivity.getSystemService(Car.SENSOR_SERVICE);
        this.mSensorAccelerometer = this.mSensorManager.getDefaultSensor(1);
        this.mMyAccelerometerSensorEventListener = new MyAccelerometerSensorEventListener();
        this.mGpsLocationChangedListener = new GpsLocationChangedListener();
        this.mGpsStatusChangedListener = new GpsStatusChangedListener();
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_sensor));
        if (this.mSensorManager != null && this.mMyAccelerometerSensorEventListener != null && this.mSensorAccelerometer != null) {
            this.mSensorManager.registerListener(this.mMyAccelerometerSensorEventListener, this.mSensorAccelerometer, 3);
        }
        GpsUtils.getInstance(this.mMainActivity).initGpsLocationData();
        GpsUtils.getInstance(this.mMainActivity).initGpsStatusData();
        GpsUtils.getInstance(this.mMainActivity).setLocationChangedListener(this.mGpsLocationChangedListener);
        GpsUtils.getInstance(this.mMainActivity).setStatusListener(this.mGpsStatusChangedListener);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mSensorManager != null && this.mMyAccelerometerSensorEventListener != null) {
            this.mSensorManager.unregisterListener(this.mMyAccelerometerSensorEventListener);
        }
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        GpsUtils.getInstance(this.mMainActivity).removeLocationUpdates();
        GpsUtils.getInstance(this.mMainActivity).removeStatusUpdates();
        this.mtvGpsSnr.setText("");
        this.mtvLongitudeValue.setText("");
        this.mtvLatitudeValue.setText("");
        this.mtvGpsTime.setText("");
    }

    /* loaded from: classes1.dex */
    private class MyAccelerometerSensorEventListener implements SensorEventListener {
        private MyAccelerometerSensorEventListener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent event) {
            if (System.currentTimeMillis() - SensorFragment.this.mlCurrentTimeAccelerometer <= 500) {
                return;
            }
            SensorFragment.this.mlCurrentTimeAccelerometer = System.currentTimeMillis();
            if (event.sensor.getType() == 1) {
                float anglex = event.values[0];
                float angley = event.values[1];
                float anglez = event.values[2];
                TextView textView = SensorFragment.this.mtvAccelerometerX;
                textView.setText(anglex + "");
                TextView textView2 = SensorFragment.this.mtvAccelerometerY;
                textView2.setText(angley + "");
                TextView textView3 = SensorFragment.this.mtvAccelerometerZ;
                textView3.setText(anglez + "");
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    /* loaded from: classes1.dex */
    private class GpsStatusChangedListener implements GpsUtils.IGpsStatusChangedListener {
        private GpsStatusChangedListener() {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsStatusChangedListener
        public void onGetGpsLocationChangedSnr(String strSnr) {
            SensorFragment.this.mtvGpsSnr.setText(strSnr);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsStatusChangedListener
        public void onGetGpsStatusCount(int igps) {
        }
    }

    /* loaded from: classes1.dex */
    private class GpsLocationChangedListener implements GpsUtils.IGpsLocationChangedListener {
        private GpsLocationChangedListener() {
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedCalendar(Calendar calendar) {
            String date = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
            String time = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
            SensorFragment.this.mtvGpsTime.setText(date + " " + time);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedLongitude(double longitude) {
            TextView textView = SensorFragment.this.mtvLongitudeValue;
            textView.setText("" + longitude);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocationChangedLatitude(double latitude) {
            TextView textView = SensorFragment.this.mtvLatitudeValue;
            textView.setText("" + latitude);
        }

        @Override // com.yfve.tools.GpsUtils.IGpsLocationChangedListener
        public void onGetGpsLocation(Location location) {
        }
    }
}
