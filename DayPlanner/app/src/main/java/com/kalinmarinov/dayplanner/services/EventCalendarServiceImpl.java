package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
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
        final int startDay = period.getStart();
        final int endDay = period.getEnd();

        if (startDay > endDay) {
            throw new IllegalArgumentException("Start date must be earlier than end date.");
        }

        // Check if events are on a same day - use day calendar
        if (startDay == endDay) {
            return dayOfCurrentMonthEvents(startDay);
        }

        // Check if events are on a same week - use week calendar
        final Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_MONTH, startDay);
        final int startDayWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, endDay);
        final int endDayWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        if (startDayWeek == endDayWeek) {
            return weekOfCurrentMonthEvents(startDayWeek);
        }

        // If selected days are contained in more than one week, show month calendar
        return currentMonthEvents();
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
