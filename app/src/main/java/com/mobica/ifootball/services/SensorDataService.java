package com.mobica.ifootball.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mobica.ifootball.domain.SensorData;
import com.mobica.ifootball.domain.SensorDataSet;
import com.mobica.ifootball.utils.SensorDataUtils;
import com.mobica.ifootball.web.rest.PostTask;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dmfi on 02/02/2016.
 */
public class SensorDataService extends Service implements SensorEventListener {

    private final String TAG = getClass().getSimpleName();

    private static final String URL = "https://ifootball-mobica.herokuapp.com/api/sensorData";

    private static final String INTERVAL_PREF_KEY = "interval";
    private static final String INTERVAL_DEF_VALUE = "100";

    private SharedPreferences preferences;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private SensorDataSet dataSet;
    private Long startMeasurementTime;

    private ExecutorService executorService;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        initialize();
    }

    private void initialize() {
        Log.d(TAG, "initialize");
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        dataSet = new SensorDataSet();
        startMeasurementTime = new Date().getTime();
        executorService = Executors.newCachedThreadPool();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (Sensor.TYPE_ACCELEROMETER == sensor.getType()) {
            dataSet.addSensorValues(event.values);
            long currentTime = System.currentTimeMillis();
            // if measurement process runs longer than provided interval, close the interval and process its data
            if (currentTime - startMeasurementTime > Integer.valueOf(preferences.getString(INTERVAL_PREF_KEY, INTERVAL_DEF_VALUE))) {
                // calculate root mean square
                float rootMeanSquare = SensorDataUtils.getRootMeanSquare(dataSet);
                Log.d(TAG, String.format("RootMeanSquare: %.3f", rootMeanSquare));
                postSensorData(new Date(), rootMeanSquare);
                dataSet.clear();
                startMeasurementTime = currentTime;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void postSensorData(Date time, Float value) {
        SensorData sensorData = new SensorData(time, value);
        PostTask<SensorData> task = new PostTask<>(sensorData, URL);
        executorService.execute(task);
    }
}
