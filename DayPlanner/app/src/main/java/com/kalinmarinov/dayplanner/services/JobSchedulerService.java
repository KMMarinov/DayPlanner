package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.models.Event;

/**
 * Created by Kalin.Marinov on 10.01.2018.
 */
public interface JobSchedulerService {

    void scheduleEventNotification(final Event event);
}
