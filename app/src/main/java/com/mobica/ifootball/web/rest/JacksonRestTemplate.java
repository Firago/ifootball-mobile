package com.mobica.ifootball.web.rest;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmfi on 03/02/2016.
 */
public class JacksonRestTemplate extends RestTemplate {

    private static RestTemplate instance;

    public static synchronized RestTemplate getInstance() {
        if (instance == null) {
            instance = new RestTemplate();
            instance.getMessageConverters()
                    .add(new MappingJackson2HttpMessageConverter());
        }
        return instance;
    }
}
