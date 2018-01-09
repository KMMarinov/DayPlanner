package com.kalinmarinov.dayplanner.views.containers;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.types.CalendarPeriodType;

import java.util.List;

/**
 * Created by Kalin.Marinov on 09.01.2018.
 */
public class EventCalendarContainer {

    private CalendarPeriodType calendarPeriodType;
    private List<Event> events;

    public EventCalendarContainer(final CalendarPeriodType calendarPeriodType, final List<Event> events) {
        this.calendarPeriodType = calendarPeriodType;
        this.events = events;
    }

    public CalendarPeriodType getCalendarPeriodType() {
        return calendarPeriodType;
    }

    public void setCalendarPeriodType(CalendarPeriodType calendarPeriodType) {
        this.calendarPeriodType = calendarPeriodType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
