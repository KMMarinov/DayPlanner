package com.kalinmarinov.dayplanner.views.containers.calendar;

import com.kalinmarinov.dayplanner.models.Event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class GridEventsCalendar {

    private final Map<GridPosition, List<Event>> gridEvents;
    private final GridPositionCalculator gridPositionCalculator;

    public GridEventsCalendar(final List<Event> events, final GridPositionCalculator gridPositionCalculator) {
        gridEvents = new HashMap<>();
        this.gridPositionCalculator = gridPositionCalculator;
        events.forEach(this::addEvent);
    }

    public void addEvent(final Event event) {
        final List<GridPosition> gridPositions = gridPositionCalculator.calculate(event);
        gridPositions.forEach(gridPosition -> addEvent(gridPosition, event));
    }

    public List<Event> getEvents(final GridPosition gridPosition) {
        final List<Event> events = gridEvents.get(gridPosition);
        return events == null ? null : new LinkedList<>(events);
    }

    private void addEvent(final GridPosition gridPosition, final Event event) {
        final List<Event> events = gridEvents.computeIfAbsent(gridPosition, key -> new LinkedList<>());
        events.add(event);
    }
}
