package com.yfve.tools;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: classes1.dex */
public class YfSensorManager {
    private static final String TAG = "sensor";
    private static YfSensorManager manager;
    private Sensor aSensor;
    private DirectionListener mDirectionListener;
    private Sensor mSensor;
    private SensorManager sm;
    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];
    final SensorEventListener listener = new SensorEventListener() { // from class: com.yfve.tools.YfSensorManager.1
        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 2) {
                YfSensorManager.this.magneticFieldValues = sensorEvent.values;
            }
            if (sensorEvent.sensor.getType() == 1) {
                YfSensorManager.this.accelerometerValues = sensorEvent.values;
            }
            YfSensorManager.this.calculateOrientation();
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    /* loaded from: classes1.dex */
    public interface DirectionListener {
        void onDirectionChanged(String str);
    }

    public static YfSensorManager getInstance(Context ctx) {
        if (manager == null) {
            synchronized (YfSensorManager.class) {
                if (manager == null) {
                    manager = new YfSensorManager(ctx);
                }
            }
        }
        return manager;
    }

    private YfSensorManager(Context context) {
        this.sm = (SensorManager) context.getSystemService("sensor");
        this.aSensor = this.sm.getDefaultSensor(1);
        this.mSensor = this.sm.getDefaultSensor(2);
        this.sm.registerListener(this.listener, this.aSensor, 3);
        this.sm.registerListener(this.listener, this.mSensor, 3);
    }

    public void setDirectionListener(DirectionListener listener) {
        this.mDirectionListener = listener;
    }

    public void removeDirectionListener(DirectionListener listener) {
        this.mDirectionListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateOrientation() {
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null, this.accelerometerValues, this.magneticFieldValues);
        SensorManager.getOrientation(R, values);
        float[] values = {(float) Math.toDegrees(values[0])};
        String direction_tmp = "";
        if (values[0] >= -5.0f && values[0] < 5.0f) {
            direction_tmp = "North";
        } else if (values[0] >= 5.0f && values[0] < 85.0f) {
            direction_tmp = "EastNorth";
        } else if (values[0] >= 85.0f && values[0] <= 95.0f) {
            direction_tmp = "East";
        } else if (values[0] >= 95.0f && values[0] < 175.0f) {
            direction_tmp = "EastSouth";
        } else if ((values[0] >= 175.0f && values[0] <= 180.0f) || (values[0] >= -180.0f && values[0] < -175.0f)) {
            direction_tmp = "South";
        } else if (values[0] >= -175.0f && values[0] < -95.0f) {
            direction_tmp = "WestSouth";
        } else if (values[0] >= -95.0f && values[0] < -85.0f) {
            direction_tmp = "West";
        } else if (values[0] >= -85.0f && values[0] < -5.0f) {
            direction_tmp = "WestNorth";
        }
        if (this.mDirectionListener != null && !direction_tmp.equals("")) {
            this.mDirectionListener.onDirectionChanged(direction_tmp);
        }
    }
}
