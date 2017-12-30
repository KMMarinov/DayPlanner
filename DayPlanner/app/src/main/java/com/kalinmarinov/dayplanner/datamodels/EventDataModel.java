package com.kalinmarinov.dayplanner.datamodels;

import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public interface EventDataModel {

    Flowable<List<Event>> getEvents();

    Flowable<Event> findById(final int eventId);

    void deleteEvent(final Event event);

    void saveEvent(final Event event);
}
