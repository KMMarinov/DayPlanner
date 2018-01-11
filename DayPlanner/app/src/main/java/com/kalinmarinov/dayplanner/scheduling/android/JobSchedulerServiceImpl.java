package com.kalinmarinov.dayplanner.scheduling.android;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.scheduling.android.factories.JobComponentFactory;
import com.kalinmarinov.dayplanner.scheduling.android.services.NotificationJobService;
import com.kalinmarinov.dayplanner.services.JobSchedulerService;

/**
 * Created by Kalin.Marinov on 10.01.2018.
 */
public class JobSchedulerServiceImpl implements JobSchedulerService {

    private final JobScheduler jobScheduler;
    private final JobComponentFactory jobComponentFactory;

    public JobSchedulerServiceImpl(final JobScheduler jobScheduler, final JobComponentFactory jobComponentFactory) {
        this.jobScheduler = jobScheduler;
        this.jobComponentFactory = jobComponentFactory;
    }

    @Override
    public void scheduleEventNotification(final Event event) {
        final int jobId = event.getId();
        final ComponentName componentName = jobComponentFactory.create(NotificationJobService.class);
        final JobInfo jobInfo = new JobInfo.Builder(jobId, componentName)
                .setPeriodic(100)
                .build();
        jobScheduler.schedule(jobInfo);
    }
}
