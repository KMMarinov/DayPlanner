package com.kalinmarinov.dayplanner.datamodels;

import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public class EventDataModelImpl implements EventDataModel {

    private final EventDataSource eventDataSource;

    public EventDataModelImpl(final EventDataSource eventDataSource) {
        this.eventDataSource = eventDataSource;
    }

    public Flowable<List<Event>> getEvents() {
        return eventDataSource.findAll();
    }

    @Override
    public Flowable<Event> findById(final int eventId) {
        return eventDataSource.findById(eventId);
    }

    public int deleteEvent(final Event event) {
        return eventDataSource.delete(event);
    }

    public long saveEvent(final Event event) {
        return eventDataSource.save(event);
    }
}
