package com.kalinmarinov.dayplanner.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 29.12.2017.
 */
public final class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private DateUtils() {
    }

    public static Date parseDate(final String dateString) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
