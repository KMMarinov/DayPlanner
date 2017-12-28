package com.kalinmarinov.dayplanner;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kalin.Marinov on 28.12.2017.
 */
public class DayPlannerApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        DayPlannerApplication.context = getApplicationContext();
    }

    public static Context getContext() {
        return DayPlannerApplication.context;
    }
}
