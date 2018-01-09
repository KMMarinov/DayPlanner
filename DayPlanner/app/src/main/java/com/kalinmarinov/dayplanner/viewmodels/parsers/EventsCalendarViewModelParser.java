package com.kalinmarinov.dayplanner.viewmodels.parsers;

import com.kalinmarinov.dayplanner.utils.models.Period;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public interface EventsCalendarViewModelParser {

    Period calculateDayPeriod(final String input);

    Period calculateWeekPeriod(final String input);

    Period calculatePeriodFromPeriod(final String input);
}
