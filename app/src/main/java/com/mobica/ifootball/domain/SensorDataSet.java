package com.mobica.ifootball.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmfi on 02/02/2016.
 */
public class SensorDataSet {

    private List<Float> roll;
    private List<Float> pitch;
    private List<Float> yaw;

    public SensorDataSet() {
        initialize();
    }

    private void initialize() {
        this.roll = new ArrayList<>();
        this.yaw = new ArrayList<>();
        this.pitch = new ArrayList<>();
    }

    public List<Float> getRoll() {
        return roll;
    }

    public void setRoll(List<Float> roll) {
        this.roll = roll;
    }

    public List<Float> getPitch() {
        return pitch;
    }

    public void setPitch(List<Float> pitch) {
        this.pitch = pitch;
    }

    public List<Float> getYaw() {
        return yaw;
    }

    public void setYaw(List<Float> yaw) {
        this.yaw = yaw;
    }

    public void addSensorValues(float[] values) {
        roll.add(values[0]);
        pitch.add(values[1]);
        yaw.add(values[2]);
    }

    public void clear() {
        roll.clear();
        pitch.clear();
        yaw.clear();
    }
}
