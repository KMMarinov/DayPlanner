package com.kalinmarinov.dayplanner.viewmodels;

import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public interface SingleEventViewModel {

    Single<Event> getEventSingle(final int eventId);

    Flowable<Event> getEvent(final int eventId);

    void saveEvent(final String name, final String description, final String startDate, final String endDate);

    void deleteEvent(final Event event);
}
