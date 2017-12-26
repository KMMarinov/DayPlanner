package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.models.Event;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public class EventDataSourceImpl implements EventDataSource {

    private final EventDao eventDao;

    public EventDataSourceImpl(final EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public void save(final Event event) {
        eventDao.save(event);
    }

    @Override
    public void delete(final Event event) {
        eventDao.delete(event);
    }
}