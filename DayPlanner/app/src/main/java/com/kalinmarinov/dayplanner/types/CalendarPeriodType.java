package com.kalinmarinov.dayplanner.types;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public enum CalendarPeriodType {
    MONTH("Month Calendar"),
    WEEK("Week Calendar"),
    DAY("Day Calendar"),
    PERIOD("Period");

    private final String value;

    CalendarPeriodType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CalendarPeriodType getType(final String value) {
        for (final CalendarPeriodType calendarPeriodType : values()) {
            if (calendarPeriodType.getValue().equals(value)) {
                return calendarPeriodType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
