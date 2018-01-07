package com.kalinmarinov.dayplanner.viewmodels.types;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public enum CalendarPeriodType {
    MONTH("Month Calendar"),
    WEEK("Week Calendar"),
    DAY("Day Calendar");

    private final String value;

    CalendarPeriodType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
