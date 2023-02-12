package com.yfve.engineeringmode;

import android.car.Car;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* loaded from: classes1.dex */
public class GyroscopeFragment extends BaseFragment {
    private TextView mtvGyroscopeX;
    private TextView mtvGyroscopeY;
    private TextView mtvGyroscopeZ;
    private SensorManager mSensorManager = null;
    private Sensor mSensorGyroscope = null;
    private MyGyroscopeSensorEventListener mMyGyroscopeSensorEventListener = null;
    private long mlCurrentTimeGyroscope = -1;

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gyroscope, (ViewGroup) null);
        this.mtvGyroscopeX = (TextView) view.findViewById(R.id.tv_gyroscope_x);
        this.mtvGyroscopeY = (TextView) view.findViewById(R.id.tv_gyroscope_y);
        this.mtvGyroscopeZ = (TextView) view.findViewById(R.id.tv_gyroscope_z);
        this.mSensorManager = (SensorManager) this.mMainActivity.getSystemService(Car.SENSOR_SERVICE);
        this.mSensorGyroscope = this.mSensorManager.getDefaultSensor(4);
        this.mMyGyroscopeSensorEventListener = new MyGyroscopeSensorEventListener();
        return view;
    }

    @Override // com.yfve.engineeringmode.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mMainActivity.setTitleContent(getString(R.string.title_gyroscope));
        if (this.mSensorManager != null && this.mSensorGyroscope != null && this.mMyGyroscopeSensorEventListener != null) {
            this.mSensorManager.registerListener(this.mMyGyroscopeSensorEventListener, this.mSensorGyroscope, 3);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mSensorManager != null && this.mMyGyroscopeSensorEventListener != null) {
            this.mSensorManager.unregisterListener(this.mMyGyroscopeSensorEventListener);
        }
    }

    /* loaded from: classes1.dex */
    private class MyGyroscopeSensorEventListener implements SensorEventListener {
        private MyGyroscopeSensorEventListener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent event) {
            if (System.currentTimeMillis() - GyroscopeFragment.this.mlCurrentTimeGyroscope <= 500) {
                return;
            }
            GyroscopeFragment.this.mlCurrentTimeGyroscope = System.currentTimeMillis();
            if (event.sensor.getType() == 4) {
                float anglex = event.values[0];
                float angley = event.values[1];
                float anglez = event.values[2];
                TextView textView = GyroscopeFragment.this.mtvGyroscopeX;
                textView.setText(anglex + "");
                TextView textView2 = GyroscopeFragment.this.mtvGyroscopeY;
                textView2.setText(angley + "");
                TextView textView3 = GyroscopeFragment.this.mtvGyroscopeZ;
                textView3.setText(anglez + "");
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
