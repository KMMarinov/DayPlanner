package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.utils.models.Period;
import com.kalinmarinov.dayplanner.views.containers.EventCalendarContainer;
import io.reactivex.Flowable;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public interface EventCalendarService {

    Flowable<EventCalendarContainer> currentMonthEvents();

    Flowable<EventCalendarContainer> weekOfCurrentMonthEvents(final int week);

    Flowable<EventCalendarContainer> dayOfCurrentMonthEvents(final int day);

    Flowable<EventCalendarContainer> daysInPeriodMonthEvents(final Period period);
}
