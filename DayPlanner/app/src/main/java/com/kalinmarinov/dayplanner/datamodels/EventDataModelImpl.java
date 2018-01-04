package com.kalinmarinov.dayplanner.datamodels;

import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public class EventDataModelImpl implements EventDataModel {

    private final EventDataSource eventDataSource;

    public EventDataModelImpl(final EventDataSource eventDataSource) {
        this.eventDataSource = eventDataSource;
    }

    @Override
    public Flowable<List<Event>> getEvents() {
        return eventDataSource.findAll();
    }

    @Override
    public Flowable<Event> findById(final int eventId) {
        return eventDataSource.findById(eventId);
    }

    @Override
    public int deleteEvent(final Event event) {
        return eventDataSource.delete(event);
    }

    @Override
    public long saveEvent(final Event event) {
        Validate.notEmpty(event.getName(), "Name cannot be empty.");
        Validate.notEmpty(event.getDescription(), "Description cannot be empty.");

        if (event.getStartDate().after(event.getEndDate())) {
            throw new IllegalArgumentException("Start date must be earlier than end date.");
        }

        return eventDataSource.save(event);
    }
}
