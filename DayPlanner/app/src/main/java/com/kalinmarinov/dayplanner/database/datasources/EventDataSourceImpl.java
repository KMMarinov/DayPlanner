package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.database.models.EventEntity;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.models.builders.EventBuilder;
import io.reactivex.Flowable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        return eventDao.findAll().map(EventDataSourceImpl::toEvents);
    }

    @Override
    public Flowable<Event> findById(final int eventId) {
        return eventDao.findById(eventId).map(EventDataSourceImpl::toEvent);
    }

    @Override
    public Flowable<List<Event>> findByStartDateBetween(final Date start, final Date end) {
        return eventDao.findByStartDateBetween(start, end).map(EventDataSourceImpl::toEvents);
    }

    @Override
    public long save(final Event event) {
        final EventEntity eventEntity = toEventEntity(event);
        return eventDao.save(eventEntity);
    }

    @Override
    public int delete(final Event event) {
        final EventEntity eventEntity = toEventEntity(event);
        return eventDao.delete(eventEntity);
    }

    private static List<Event> toEvents(final List<EventEntity> eventEntities) {
        return eventEntities.stream().map(EventDataSourceImpl::toEvent).collect(Collectors.toList());
    }

    private static Event toEvent(final EventEntity eventEntity) {
        return new EventBuilder()
                .setId(eventEntity.getId())
                .setName(eventEntity.getName())
                .setDescription(eventEntity.getDescription())
                .setStartDate(eventEntity.getStartDate())
                .setEndDate(eventEntity.getEndDate())
                .build();
    }

    private static EventEntity toEventEntity(final Event event) {
        return EventEntity.builder()
                .setId(event.getId())
                .setName(event.getName())
                .setDescription(event.getDescription())
                .setStartDate(event.getStartDate())
                .setEndDate(event.getEndDate())
                .build();
    }
}
