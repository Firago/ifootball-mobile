package com.mobica.ifootball.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmfi on 02/02/2016.
 */
public class Vector {

    private Float x;
    private Float y;
    private Float z;

    public Vector(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }


}
