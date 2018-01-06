package com.kalinmarinov.dayplanner.viewmodels;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public interface EventsCalendarViewModel {

    Flowable<List<Event>> getEvents(final CalendarPeriodType calendarPeriodType);
}
