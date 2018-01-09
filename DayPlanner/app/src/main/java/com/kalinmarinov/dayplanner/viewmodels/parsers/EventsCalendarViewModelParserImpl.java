package com.kalinmarinov.dayplanner.viewmodels.parsers;

import com.kalinmarinov.dayplanner.utils.models.Period;

import java.util.Calendar;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public class EventsCalendarViewModelParserImpl implements EventsCalendarViewModelParser {

    private static final String PERIOD_PATTERN = "^\\d{1,2}-\\d{1,2}$";
    private static final String SINGLE_DATE_PATTERN = "^\\d{1,2}$";
    private static final String DATE_DELIMITER = "-";

    @Override
    public Period calculateDayPeriod(final String input) {
        if (!input.matches(SINGLE_DATE_PATTERN)) {
            throw new IllegalArgumentException("Invalid input - " + input);
        }
        final int dayOfMonth = Integer.parseInt(input);
        final int actualMaximum = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        if (dayOfMonth <= 0 || dayOfMonth > actualMaximum) {
            throw new IllegalArgumentException("Invalid input - " + input);
        }
        return new Period(dayOfMonth, dayOfMonth);
    }

    @Override
    public Period calculateWeekPeriod(final String input) {
        if (!input.matches(SINGLE_DATE_PATTERN)) {
            throw new IllegalArgumentException("Invalid input - " + input);
        }
        final int weekOfMonth = Integer.parseInt(input);
        final int actualMaximum = Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_MONTH);
        if (weekOfMonth <= 0 || weekOfMonth > actualMaximum) {
            throw new IllegalArgumentException("Invalid input - " + input);
        }
        return new Period(weekOfMonth, weekOfMonth);
    }

    @Override
    public Period calculatePeriodFromPeriod(final String input) {
        if (!input.matches(PERIOD_PATTERN)) {
            throw new IllegalArgumentException("Invalid input - " + input);
        }
        final String[] dates = input.split(DATE_DELIMITER);
        final int start = calculateDayPeriod(dates[0]).getStart();
        final int end = calculateDayPeriod(dates[1]).getEnd();
        return new Period(start, end);
    }
}
