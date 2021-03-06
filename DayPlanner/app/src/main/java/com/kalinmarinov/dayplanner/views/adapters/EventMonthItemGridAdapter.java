package com.kalinmarinov.dayplanner.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.CalendarUtils;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridEventsCalendar;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.calculators.MonthGridPositionCalculator;

import java.util.List;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class EventMonthItemGridAdapter extends CalendarItemGridAdapter {

    private final GridEventsCalendar gridEventsCalendar;

    public EventMonthItemGridAdapter(@NonNull final Activity context, @NonNull final List<Event> events) {
        super(context, events, R.layout.gridview_event_month_calendar_item);
        final MonthGridPositionCalculator monthGridPositionCalculator = new MonthGridPositionCalculator();
        gridEventsCalendar = new GridEventsCalendar(events, monthGridPositionCalculator);
    }

    @Override
    public int getCount() {
        return CalendarUtils.currentMonthNumberWeeks() * Constants.DAYS_IN_WEEK;
    }

    @Override
    public List<Event> getItem(final int position) {
        final GridPosition gridPosition = getGridPosition(position);
        return gridEventsCalendar.getEvents(gridPosition);
    }

    @Override
    protected void setHeaders(final TextView titleTextView, final TextView subtitleTextView, final int position) {
        final String shortWeekdayName = getShortWeekdayName(position);
        final String dateNumber = getDateNumber(position);
        titleTextView.setText(shortWeekdayName);
        subtitleTextView.setText(dateNumber);
    }

    private static GridPosition getGridPosition(final int position) {
        final int monthWeek = getMonthWeek(position);
        final int weekday = getWeekday(position);
        return GridPosition.of(weekday, monthWeek);
    }

    private static int getMonthWeek(final int position) {
        return position / Constants.DAYS_IN_WEEK + 1;
    }

    private static int getWeekday(final int position) {
        return position % Constants.DAYS_IN_WEEK + 1;
    }

    private static String getDateNumber(final int position) {
        final int weekday = getWeekday(position);
        final int monthWeek = getMonthWeek(position);
        final int dateOfMonth = CalendarUtils.getMonthDate(weekday, monthWeek);
        return String.valueOf(dateOfMonth);
    }

    private static String getShortWeekdayName(final int position) {
        final int weekday = getWeekday(position);
        return CalendarUtils.getShortNameWeekday(weekday);
    }
}
