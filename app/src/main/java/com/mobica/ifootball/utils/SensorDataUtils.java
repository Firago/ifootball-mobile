package com.mobica.ifootball.utils;

import com.mobica.ifootball.domain.SensorDataSet;
import com.mobica.ifootball.domain.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dmfi on 02/02/2016.
 */
public class SensorDataUtils {

    public static float getRootMeanSquare(SensorDataSet dataSet) {
        List<Float> vectorLength = new ArrayList<>();
        // convert plain sensor data to difference
        List<Float> x = convertToDiff(dataSet.getRoll());
        List<Float> y = convertToDiff(dataSet.getPitch());
        List<Float> z = convertToDiff(dataSet.getYaw());
        // calculate vectors for each measurement
        List<Vector> vectors = convertPointsToVectors(x, y, z);
        for (Vector vector : vectors) {
            vectorLength.add(vector.getLength());
        }
        // return root mean square for vectors length dataset
        return getRootMeanSquare(vectorLength);
    }

    public static float getRootMeanSquare(List<Float> vectorLength) {
        float meanSquare = 0;
        for (Float data : vectorLength)
            meanSquare += data * data;
        meanSquare /= vectorLength.size();
        return (float) Math.sqrt(meanSquare);
    }

    public static List<Float> convertToDiff(List<Float> data) {
        List<Float> result = new ArrayList<>();
        if (data.size() > 1) {
            for (int i = 1; i < data.size(); i++) {
                result.add(data.get(i) - data.get(i - 1));
            }
        }
        return result;
    }

    public static List<Vector> convertPointsToVectors(List<Float> x, List<Float> y, List<Float> z) {
        List<Vector> result = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            result.add(new Vector(x.get(i), y.get(i), z.get(i)));
        }
        return result;
    }

    public static long convertNanosToMillis(long nano) {
        return TimeUnit.MILLISECONDS.convert(nano, TimeUnit.NANOSECONDS);
    }
}
