package com.mobica.ifootball;

import android.app.Application;
import android.content.Context;

/**
 * Created by dmfi on 02/02/2016.
 */
public class ContextApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ContextApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContextApplication.context;
    }

}
