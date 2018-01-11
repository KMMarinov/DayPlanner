package com.kalinmarinov.dayplanner.scheduling.android.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by Kalin.Marinov on 10.01.2018.
 */
public class NotificationJobService extends JobService {

    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.e("debug", "Testing");
        return false;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        return false;
    }
}
