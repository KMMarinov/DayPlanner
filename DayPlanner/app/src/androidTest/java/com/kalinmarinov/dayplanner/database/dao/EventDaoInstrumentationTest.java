package com.kalinmarinov.dayplanner.database.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.kalinmarinov.dayplanner.database.roomdatabases.ApplicationDatabase;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@RunWith(AndroidJUnit4.class)
public class EventDaoInstrumentationTest {

    private EventDao eventDao;
    private ApplicationDatabase applicationDatabase;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDatabase() {
        final Context context = InstrumentationRegistry.getTargetContext();
        applicationDatabase = Room.inMemoryDatabaseBuilder(context, ApplicationDatabase.class).allowMainThreadQueries()
                .build();
        eventDao = applicationDatabase.getEventDao();
    }

    @After
    public void closeDatabase() {
        applicationDatabase.close();
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
        final List<Event> events = eventDao.findAll().blockingFirst();
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
        final Event persistedEvent = eventDao.findAll().blockingFirst().get(0);

        // when
        eventDao.delete(persistedEvent);

        // then
        final TestSubscriber<List<Event>> testSubscriber = eventDao.findAll().test();
        testSubscriber.assertNoErrors();
        final List<Event> events = testSubscriber.values().get(0);
        assertThat(events, is(empty()));
    }

    @Test
    public void shouldFetchAllEvents() {
        // given
        final String testName = "TestName";
        final Event event = new Event();
        event.setName(testName);
        eventDao.save(event);

        // when
        final Flowable<List<Event>> eventsFlowable = eventDao.findAll();

        // then
        final TestSubscriber<List<Event>> testSubscriber = eventsFlowable.test();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        final Event newEvent = testSubscriber.values().get(0).get(0);
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
        final Flowable<Event> eventSingle = eventDao.findById(1);
        final TestSubscriber<Event> testObserver = eventSingle.test();

        // then
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        final Event foundEvent = testObserver.values().get(0);
        assertThat(foundEvent, is(notNullValue()));
        assertThat(foundEvent.getId(), is(1));
        assertThat(foundEvent.getName(), equalTo(testName));
    }
}
