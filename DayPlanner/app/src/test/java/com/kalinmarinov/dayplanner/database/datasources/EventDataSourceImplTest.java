package com.kalinmarinov.dayplanner.database.datasources;

import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventDataSourceImplTest {

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventDataSourceImpl eventDataSource;

    @Test
    public void shouldReturnListOfEvents() {
        // given
        final Flowable<List<Event>> eventFlowableData = Flowable.just(Arrays.asList(new Event(), new Event()));
        given(eventDao.findAll()).willReturn(eventFlowableData);

        // when
        final Flowable<List<Event>> eventsFlowable = eventDataSource.findAll();

        // then
        final TestSubscriber<List<Event>> testSubscriber = eventsFlowable.test();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        final List<Event> events = eventsFlowable.blockingFirst();
        assertThat(events, is(notNullValue()));
        assertThat(events.size(), is(2));
    }

    @Test
    public void shouldModifyEvent() {
        // given
        final Event event = new Event();

        // when
        eventDataSource.save(event);

        // then
        verify(eventDao, times(1)).save(event);
    }

    @Test
    public void shouldDeleteEvent() {
        // given
        final Event event = new Event();

        // when
        eventDataSource.delete(event);

        // then
        verify(eventDao, times(1)).delete(event);
    }
}