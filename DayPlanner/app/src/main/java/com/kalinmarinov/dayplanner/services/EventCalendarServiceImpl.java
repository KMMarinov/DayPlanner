package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import io.reactivex.Flowable;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class EventCalendarServiceImpl implements EventCalendarService {

    private final EventDataModel eventDataModel;

    public EventCalendarServiceImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    @Override
    public Flowable<List<Event>> getEvents(final CalendarPeriodType calendarPeriodType) {
        switch (calendarPeriodType) {
            case MONTH:
                return currentMonthEvents();
            case WEEK:
                return currentWeekEvents();
            case DAY:
                return currentDayEvents();
        }
        throw new IllegalArgumentException("Unsupported calendar type");
    }

    private Flowable<List<Event>> currentMonthEvents() {
        final LocalDate monthBeginLocalDate = LocalDate.now().withDayOfMonth(1);
        final LocalDate monthEndLocalDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);
        final Date monthBeginDate = convertToDate(monthBeginLocalDate);
        final Date monthEndDate = convertToDate(monthEndLocalDate);
        return eventDataModel.findByStartDateBetween(monthBeginDate, monthEndDate);
    }

    private static Flowable<List<Event>> currentWeekEvents() {
        return null;
    }

    private static Flowable<List<Event>> currentDayEvents() {
        return null;
    }

    private static Date convertToDate(final LocalDate localDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        calendar.set(Calendar.DATE, localDate.getDayOfMonth());
        return calendar.getTime();
    }
}
