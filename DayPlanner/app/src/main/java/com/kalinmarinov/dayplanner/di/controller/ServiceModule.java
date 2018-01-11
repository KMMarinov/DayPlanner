package com.kalinmarinov.dayplanner.di.controller;

import android.app.job.JobScheduler;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.scheduling.android.JobSchedulerServiceImpl;
import com.kalinmarinov.dayplanner.scheduling.android.factories.JobComponentFactory;
import com.kalinmarinov.dayplanner.services.EventCalendarService;
import com.kalinmarinov.dayplanner.services.EventCalendarServiceImpl;
import com.kalinmarinov.dayplanner.services.EventViewModelService;
import com.kalinmarinov.dayplanner.services.EventViewModelServiceImpl;
import com.kalinmarinov.dayplanner.services.JobSchedulerService;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParser;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParserImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Module
public class ServiceModule {

    private final FragmentActivity fragmentActivity;

    public ServiceModule(final FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Provides
    EventViewModelService getEventViewModelService() {
        return new EventViewModelServiceImpl();
    }

    @Provides
    EventCalendarService getEventCalendarService(final EventDataModel eventDataModel) {
        return new EventCalendarServiceImpl(eventDataModel);
    }

    @Provides
    EventsCalendarViewModelParser getEventsCalendarViewModelParser() {
        return new EventsCalendarViewModelParserImpl();
    }

    @Provides
    JobScheduler getJobScheduler() {
        return (JobScheduler) fragmentActivity.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    @Provides
    JobSchedulerService getJobSchedulerService(final JobScheduler jobScheduler,
                                               final JobComponentFactory jobComponentFactory) {
        return new JobSchedulerServiceImpl(jobScheduler, jobComponentFactory);
    }
}
