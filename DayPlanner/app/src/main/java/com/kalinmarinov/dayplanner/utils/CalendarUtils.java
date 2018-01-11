package com.kalinmarinov.dayplanner.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public final class CalendarUtils {

    private static final String DAY_NAME_IN_WEEK = "E";

    private CalendarUtils() {
    }

    public static int currentMonthNumberWeeks() {
        final Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public static String getShortNameWeekday(final int weekday) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, weekday + 1);
        final SimpleDateFormat simpleDateformat = new SimpleDateFormat(DAY_NAME_IN_WEEK);
        return simpleDateformat.format(calendar.getTime());
    }

    public static int getMonthDate(final int weekday, final int week) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, weekday + 1);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        return calendar.get(Calendar.DATE);
    }

    public static int getCurrentMonthDayOfWeek(final int dayOfMonth) {
        final Calendar currentMonthCalendar = new GregorianCalendar(Locale.FRANCE);
        currentMonthCalendar.set(Calendar.DATE, dayOfMonth);
        currentMonthCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        return (Constants.DAYS_IN_WEEK + currentMonthCalendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY - 1)
                % Constants.DAYS_IN_WEEK + 1;
    }

    public static int getCurrentMonthWeekOfMonth(final int dayOfMonth) {
        final Calendar currentMonthCalendar = new GregorianCalendar(Locale.FRANCE);
        currentMonthCalendar.set(Calendar.DATE, dayOfMonth);
        currentMonthCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        return currentMonthCalendar.get(Calendar.WEEK_OF_MONTH);
    }

    public static Calendar calendarFromDate(final Date date) {
        final Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(date);
        return endDateCalendar;
    }
}
