package com.kalinmarinov.dayplanner.views.containers.calendar;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
        final List<Event> events = gridEvents.computeIfAbsent(gridPosition, key -> new LinkedList<>());
        events.add(event);
    }

    public List<Event> getEvents(final GridPosition gridPosition) {
        final List<Event> events = gridEvents.get(gridPosition);
        return events == null ? null : new LinkedList<>(events);
    }

    private static GridPosition calculateGridPosition(final Event event) {
        final Date startDate = event.getStartDate();
        final Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        final Calendar currentMonthCalendar = new GregorianCalendar(Locale.FRANCE);
        currentMonthCalendar.set(Calendar.DATE, calendarStartDate.get(Calendar.DATE));
        currentMonthCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        final int weekOfMonth = currentMonthCalendar.get(Calendar.WEEK_OF_MONTH);
        final int dayOfWeek = calculateDayOfWeek(currentMonthCalendar);
        return GridPosition.of(dayOfWeek, weekOfMonth);
    }

    private static int calculateDayOfWeek(final Calendar currentMonthCalendar) {
        return (Constants.DAYS_IN_WEEK + currentMonthCalendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY - 1)
                % Constants.DAYS_IN_WEEK + 1;
    }
}
