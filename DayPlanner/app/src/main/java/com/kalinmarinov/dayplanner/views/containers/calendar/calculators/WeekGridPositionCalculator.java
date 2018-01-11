package com.kalinmarinov.dayplanner.views.containers.calendar.calculators;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.CalendarUtils;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class WeekGridPositionCalculator implements GridPositionCalculator {

    private static final int ROW_POSITION = 1;

    @Override
    public List<GridPosition> calculate(final Event event) {
        final Calendar dateCalendar = CalendarUtils.calendarFromDate(event.getStartDate());
        final Calendar endDateCalendar = CalendarUtils.calendarFromDate(event.getEndDate());
        dateCalendar.set(Calendar.HOUR_OF_DAY, Constants.DAY_STARTING_HOUR);
        final int startDateWeek = dateCalendar.get(Calendar.WEEK_OF_MONTH);

        // while current date is before end date and has same day of month as start day
        final List<GridPosition> gridPositions = new LinkedList<>();
        while (!dateCalendar.after(endDateCalendar) && dateCalendar.get(Calendar.WEEK_OF_MONTH) == startDateWeek) {
            final int dayOfMonth = dateCalendar.get(Calendar.DAY_OF_MONTH);
            final int dayOfWeek = CalendarUtils.getCurrentMonthDayOfWeek(dayOfMonth);
            final GridPosition gridPosition = GridPosition.of(dayOfWeek, ROW_POSITION);
            gridPositions.add(gridPosition);
            dateCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return gridPositions;
    }
}
