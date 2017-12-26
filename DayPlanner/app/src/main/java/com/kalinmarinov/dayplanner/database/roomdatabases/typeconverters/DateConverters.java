package com.kalinmarinov.dayplanner.database.roomdatabases.typeconverters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public final class DateConverters {

    private DateConverters() {
    }

    @TypeConverter
    public static Date fromTimestamp(final Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(final Date date) {
        return date == null ? null : date.getTime();
    }
}
