package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.utils.CalendarUtils;
import com.kalinmarinov.dayplanner.utils.models.Period;
import com.kalinmarinov.dayplanner.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.containers.EventCalendarContainer;
import io.reactivex.Flowable;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class EventCalendarServiceImpl implements EventCalendarService {

    private final EventDataModel eventDataModel;

    public EventCalendarServiceImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    @Override
    public Flowable<EventCalendarContainer> currentMonthEvents() {
        final LocalDate monthBeginLocalDate = LocalDate.MIN.withDayOfMonth(1);
        final LocalDate monthEndLocalDate = LocalDate.MIN.plusMonths(1).withDayOfMonth(1);
        return getListEventsBetween(monthBeginLocalDate, monthEndLocalDate, CalendarPeriodType.MONTH);
    }

    @Override
    public Flowable<EventCalendarContainer> weekOfCurrentMonthEvents(final int week) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        final LocalDate weekBeginLocalDate = LocalDate.MIN.withDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
        final LocalDate weekEndLocalDate = weekBeginLocalDate.plusWeeks(1);
        return getListEventsBetween(weekBeginLocalDate, weekEndLocalDate, CalendarPeriodType.WEEK);
    }

    @Override
    public Flowable<EventCalendarContainer> dayOfCurrentMonthEvents(final int day) {
        final LocalDate beginLocalDate = LocalDate.MIN.withDayOfMonth(day);
        final LocalDate endLocalDate = beginLocalDate.plusDays(1);
        return getListEventsBetween(beginLocalDate, endLocalDate, CalendarPeriodType.DAY);
    }

    @Override
    public Flowable<EventCalendarContainer> daysInPeriodMonthEvents(final Period period) {
        final LocalDate startDate = LocalDate.MIN.withDayOfMonth(period.getStart());
        final LocalDate endDate = LocalDate.MIN.withDayOfMonth(period.getEnd());

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be earlier than end date.");
        }

        // Check if events are on a same day - use day calendar
        if (startDate.isEqual(endDate)) {
            return getListEventsBetween(startDate, endDate.plusDays(1), CalendarPeriodType.DAY);
        }

        // Check if events are on a same week - use week calendar
        final int startDayWeek = CalendarUtils.getCurrentMonthWeekOfMonth(startDate.getDayOfMonth());
        final int endDayWeek = CalendarUtils.getCurrentMonthWeekOfMonth(endDate.getDayOfMonth());
        if (startDayWeek == endDayWeek) {
            return getListEventsBetween(startDate, endDate.plusDays(1), CalendarPeriodType.WEEK);
        }

        // If selected days are contained in more than one week, show month calendar
        return getListEventsBetween(startDate, endDate.plusDays(1), CalendarPeriodType.MONTH);
    }

    private Flowable<EventCalendarContainer> getListEventsBetween(final LocalDate start, final LocalDate end,
                                                                  final CalendarPeriodType calendarPeriodType) {
        final Date beginDate = convertToDate(start);
        final Date endDate = convertToDate(end);
        return eventDataModel.findByStartDateBetween(beginDate, endDate)
                .map(events -> new EventCalendarContainer(calendarPeriodType, events));
    }

    private static Date convertToDate(final LocalDate localDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, localDate.getMonthValue() - 1);
        calendar.set(Calendar.DATE, localDate.getDayOfMonth());
        return calendar.getTime();
    }
}
