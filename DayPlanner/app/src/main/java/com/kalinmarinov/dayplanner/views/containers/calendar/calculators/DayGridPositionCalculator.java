package com.kalinmarinov.dayplanner.views.containers.calendar.calculators;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.CalendarUtils;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class DayGridPositionCalculator implements GridPositionCalculator {

    private static final int COLUMN_POSITION = 1;

    @Override
    public List<GridPosition> calculate(final Event event) {
        final Calendar dateCalendar = CalendarUtils.calendarFromDate(event.getStartDate());
        final Calendar endDateCalendar = CalendarUtils.calendarFromDate(event.getEndDate());
        final int startingDayOfMonth = dateCalendar.get(Calendar.DAY_OF_MONTH);

        final List<GridPosition> gridPositions = new LinkedList<>();
        while (!dateCalendar.after(endDateCalendar) && dateCalendar.get(Calendar.DAY_OF_MONTH) == startingDayOfMonth) {
            final int hourOfDay = dateCalendar.get(Calendar.HOUR_OF_DAY);
            final GridPosition gridPosition = GridPosition.of(COLUMN_POSITION, hourOfDay);
            gridPositions.add(gridPosition);
            dateCalendar.add(Calendar.HOUR, 1);
        }
        return gridPositions;
    }
}
