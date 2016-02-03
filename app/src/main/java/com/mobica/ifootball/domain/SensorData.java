package com.mobica.ifootball.domain;

import java.util.Date;

/**
 * Created by dmfi on 02/02/2016.
 */
public class SensorData {

    private Date time;
    private Float value;

    public SensorData(Date time, Float value) {
        this.time = time;
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
