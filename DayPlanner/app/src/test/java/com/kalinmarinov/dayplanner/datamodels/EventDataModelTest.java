package com.kalinmarinov.dayplanner.datamodels;

import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.models.Event;
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
public class EventDataModelTest {

    @Mock
    private EventDataSource eventDataSource;

    @InjectMocks
    private EventDataModel eventDataModel;

    @Test
    public void shouldReturnListOfEvents() {
        // given
        given(eventDataSource.findAll()).willReturn(Arrays.asList(new Event(), new Event()));

        // when
        final List<Event> events = eventDataModel.getEvents();

        // then
        assertThat(events, is(notNullValue()));
        assertThat(events.size(), is(2));
    }

    @Test
    public void shouldModifyEvent() {
        // given
        final Event event = new Event();

        // when
        eventDataModel.saveEvent(event);

        // then
        verify(eventDataSource, times(1)).save(event);
    }

    @Test
    public void shouldDeleteEvent() {
        // given
        final Event event = new Event();

        // when
        eventDataModel.deleteEvent(event);

        // then
        verify(eventDataSource, times(1)).delete(event);
    }
}
