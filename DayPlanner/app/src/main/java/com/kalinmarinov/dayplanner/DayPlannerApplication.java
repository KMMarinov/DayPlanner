package com.kalinmarinov.dayplanner;

import android.app.Application;
import android.content.Context;
import com.kalinmarinov.dayplanner.di.ApplicationComponent;
import com.kalinmarinov.dayplanner.di.DaggerApplicationComponent;
import com.kalinmarinov.dayplanner.di.application.ApplicationModule;

/**
 * Created by Kalin.Marinov on 28.12.2017.
 */
public class DayPlannerApplication extends Application {

    private static Context context;
    private static DayPlannerApplication dayPlannerApplication;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        DayPlannerApplication.context = getApplicationContext();
        dayPlannerApplication = this;

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static DayPlannerApplication getDayPlannerApplication() {
        return dayPlannerApplication;
    }

    public static Context getContext() {
        return DayPlannerApplication.context;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
