package com.kalinmarinov.dayplanner.views.containers.calendar;

import com.kalinmarinov.dayplanner.models.Event;

import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public interface GridPositionCalculator {

    List<GridPosition> calculate(final Event event);
}
