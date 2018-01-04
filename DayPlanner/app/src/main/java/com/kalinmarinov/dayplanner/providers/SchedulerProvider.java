package com.kalinmarinov.dayplanner.providers;

import io.reactivex.Scheduler;

/**
 * Created by Kalin.Marinov on 01.01.2018.
 */
public interface SchedulerProvider {

    Scheduler getIOScheduler();

    Scheduler getMainScheduler();

    Scheduler getComputationScheduler();
}
