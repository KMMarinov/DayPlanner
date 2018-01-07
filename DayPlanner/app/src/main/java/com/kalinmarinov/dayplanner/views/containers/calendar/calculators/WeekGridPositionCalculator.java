package com.kalinmarinov.dayplanner.views.containers.calendar.calculators;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class WeekGridPositionCalculator implements GridPositionCalculator {

    @Override
    public GridPosition calculate(final Event event) {
        final Date startDate = event.getStartDate();
        final Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        final Calendar currentMonthCalendar = new GregorianCalendar(Locale.FRANCE);
        currentMonthCalendar.set(Calendar.DATE, calendarStartDate.get(Calendar.DATE));
        currentMonthCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        final int dayOfWeek = calculateDayOfWeek(currentMonthCalendar);
        return GridPosition.of(dayOfWeek, 1);
    }

    private static int calculateDayOfWeek(final Calendar currentMonthCalendar) {
        return (Constants.DAYS_IN_WEEK + currentMonthCalendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY - 1)
                % Constants.DAYS_IN_WEEK + 1;
    }
}
