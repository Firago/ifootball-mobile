package com.mobica.ifootball.web.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.mobica.ifootball.domain.SensorData;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmfi on 02/02/2016.
 */
public class PostSensorDataTask extends AsyncTask<SensorData, Void, SensorData> {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected SensorData doInBackground(SensorData... params) {
        final String url = "https://ifootball-mobica.herokuapp.com/api/sensorData";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            restTemplate.postForLocation(url, params[0]);
        } catch (ResourceAccessException e) {
            //TODO reconnect
            Log.e(TAG, "Failed to access remote server");
        }
        return null;
    }
}
