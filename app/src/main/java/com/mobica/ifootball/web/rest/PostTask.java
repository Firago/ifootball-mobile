package com.mobica.ifootball.web.rest;

import android.util.Log;

import org.springframework.web.client.RestClientException;

/**
 * Created by dmfi on 02/02/2016.
 */
public class PostTask<T> implements Runnable {

    private final String TAG = getClass().getSimpleName();

    private String url;
    private T object;

    public PostTask(T object, String url) {
        this.object = object;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            JacksonRestTemplate.getInstance().postForLocation(url, object);
        } catch (RestClientException e) {
            Log.e(TAG, "Failed to access remote server"); //TODO reconnect
        }
    }
}
