package com.kalinmarinov.dayplanner.views.containers.calendar;

import com.kalinmarinov.dayplanner.models.Event;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public interface GridPositionCalculator {

    GridPosition calculate(final Event event);
}
