package com.kalinmarinov.dayplanner.scheduling.android.factories;

import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by Kalin.Marinov on 10.01.2018.
 */
public class JobComponentFactory {

    private final Context context;

    public JobComponentFactory(final Context context) {
        this.context = context;
    }

    public ComponentName create(final Class<? extends JobService> clazz) {
        return new ComponentName(context, clazz);
    }
}
