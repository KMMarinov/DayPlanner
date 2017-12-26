package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.models.Event;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public interface EventDataSource {

    List<Event> findAll();

    void save(final Event event);

    void delete(final Event event);
}
