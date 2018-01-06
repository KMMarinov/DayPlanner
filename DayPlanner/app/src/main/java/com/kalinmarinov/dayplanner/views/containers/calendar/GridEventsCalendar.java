package com.kalinmarinov.dayplanner.views.containers.calendar;

import com.kalinmarinov.dayplanner.models.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class GridEventsCalendar {

    private final Map<GridPosition, List<Event>> gridEvents;

    public GridEventsCalendar(final List<Event> events) {
        gridEvents = new HashMap<>();
        events.forEach(this::addEvent);
    }

    public void addEvent(final Event event) {
        final GridPosition gridPosition = calculateGridPosition(event);
        final List<Event> events = gridEvents.computeIfAbsent(gridPosition, k -> new LinkedList<>());
        events.add(event);
    }

    public List<Event> getEvents(final GridPosition gridPosition) {
        final List<Event> events = gridEvents.get(gridPosition);
        return new LinkedList<>(events);
    }

    private static GridPosition calculateGridPosition(final Event event) {
        final Date startDate = event.getStartDate();
        final Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        final int weekOfMonth = calendarStartDate.get(Calendar.WEEK_OF_MONTH);
        final int dayOfWeek = calendarStartDate.get(Calendar.DAY_OF_WEEK);
        return GridPosition.of(weekOfMonth, dayOfWeek);
    }
}
