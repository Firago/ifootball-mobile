package com.mobica.ifootball;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dmfi on 02/02/2016.
 */
public class ApplicationContext extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static ComponentName startService(Class<?> serviceClass) {
        return context.startService(new Intent(context, serviceClass));
    }

    public static boolean stopService(Class<?> serviceClass) {
        return context.stopService(new Intent(context, serviceClass));
    }

    public static boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
