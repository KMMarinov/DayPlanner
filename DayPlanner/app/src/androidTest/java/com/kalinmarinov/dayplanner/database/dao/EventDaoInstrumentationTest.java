package com.kalinmarinov.dayplanner.database.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.kalinmarinov.dayplanner.database.roomdatabases.EventsDatabase;
import com.kalinmarinov.dayplanner.models.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@RunWith(AndroidJUnit4.class)
public class EventDaoInstrumentationTest {

    private EventDao eventDao;
    private EventsDatabase eventsDatabase;

    @Before
    public void createDatabase() {
        final Context context = InstrumentationRegistry.getTargetContext();
        eventsDatabase = Room.inMemoryDatabaseBuilder(context, EventsDatabase.class).build();
        eventDao = eventsDatabase.getEventDao();
    }

    @After
    public void closeDatabase() {
        eventsDatabase.close();
    }

    @Test
    public void shouldInsertNewEvent() {
        // given
        final String testName = "TestName";
        final Event event = new Event();
        event.setName(testName);

        // when
        eventDao.save(event);

        // then
        final List<Event> events = eventDao.findAll();
        assertThat(events.size(), is(1));
        final Event newEvent = events.get(0);
        assertThat(newEvent.getId(), is(1));
        assertThat(newEvent.getName(), equalTo(testName));
    }

    @Test
    public void shouldDeleteExistingEvent() {
        // given
        final Event event = new Event();
        eventDao.save(event);
        final Event persistedEvent = eventDao.findAll().get(0);

        // when
        eventDao.delete(persistedEvent);

        // then
        final List<Event> events = eventDao.findAll();
        assertThat(events.size(), is(0));
    }

    @Test
    public void shouldFetchAllEvents() {
        // given
        final String testName = "TestName";
        final Event event = new Event();
        event.setName(testName);
        eventDao.save(event);

        // when
        final List<Event> events = eventDao.findAll();

        // then
        assertThat(events.size(), is(1));
        final Event newEvent = events.get(0);
        assertThat(newEvent.getId(), is(1));
        assertThat(newEvent.getName(), equalTo(testName));
    }

    @Test
    public void shouldFetchEventWithName() {
        // given
        final String testName = "TestName";
        final Event event = new Event();
        event.setName(testName);
        eventDao.save(event);

        // when
        final Event foundEvent = eventDao.findByName(testName);

        // then
        assertThat(foundEvent, is(notNullValue()));
        assertThat(foundEvent.getId(), is(1));
        assertThat(foundEvent.getName(), equalTo(testName));
    }
}
