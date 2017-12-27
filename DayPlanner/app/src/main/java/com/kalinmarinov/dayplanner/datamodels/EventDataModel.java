package com.kalinmarinov.dayplanner.datamodels;

import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public class EventDataModel {

    private final EventDataSource eventDataSource;

    public EventDataModel(final EventDataSource eventDataSource) {
        this.eventDataSource = eventDataSource;
    }

    public Flowable<List<Event>> getEvents() {
        return eventDataSource.findAll();
    }

    public void deleteEvent(final Event event) {
        eventDataSource.delete(event);
    }

    public void saveEvent(final Event event) {
        eventDataSource.save(event);
    }
}
