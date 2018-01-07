package com.kalinmarinov.dayplanner.views.containers.calendar.calculators;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class DayGridPositionCalculator implements GridPositionCalculator {

    @Override
    public GridPosition calculate(final Event event) {
        final Date startDate = event.getStartDate();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        final int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        return GridPosition.of(1, hourOfDay);
    }
}
