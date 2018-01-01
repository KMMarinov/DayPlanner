package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

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
    public Flowable<List<Event>> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Flowable<Event> findById(final int eventId) {
        return eventDao.findById(eventId);
    }

    @Override
    public long save(final Event event) {
        return eventDao.save(event);
    }

    @Override
    public int delete(final Event event) {
        return eventDao.delete(event);
    }
}
