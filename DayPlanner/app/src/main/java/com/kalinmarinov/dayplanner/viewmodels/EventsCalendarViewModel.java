package com.kalinmarinov.dayplanner.viewmodels;

import com.kalinmarinov.dayplanner.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.containers.EventCalendarContainer;
import io.reactivex.Flowable;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public interface EventsCalendarViewModel {

    Flowable<EventCalendarContainer> getEvents(final CalendarPeriodType calendarPeriodType, final String input);
}
