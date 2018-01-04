package com.kalinmarinov.dayplanner.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 29.12.2017.
 */
public final class DateUtils {

    private DateUtils() {
    }

    public static Date parseDate(final String dateString, final String dateFormat) throws ParseException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.parse(dateString);
    }

    public static String formatDate(final Date date, final String dateFormat) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }
}
