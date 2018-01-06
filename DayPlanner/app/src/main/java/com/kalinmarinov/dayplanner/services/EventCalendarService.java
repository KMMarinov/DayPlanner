package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public interface EventCalendarService {

    Flowable<List<Event>> getEvents(final CalendarPeriodType calendarPeriodType);
}
