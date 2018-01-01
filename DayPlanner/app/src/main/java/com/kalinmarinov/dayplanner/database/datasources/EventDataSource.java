package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public interface EventDataSource {

    Flowable<List<Event>> findAll();

    Flowable<Event> findById(int eventId);

    long save(final Event event);

    int delete(final Event event);
}
