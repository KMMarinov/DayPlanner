package com.kalinmarinov.dayplanner.providers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class SchedulerProviderImpl implements SchedulerProvider {

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler getMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }
}
