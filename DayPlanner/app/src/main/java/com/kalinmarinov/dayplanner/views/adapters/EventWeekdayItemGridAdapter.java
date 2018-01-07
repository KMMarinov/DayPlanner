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
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;
import com.kalinmarinov.dayplanner.views.containers.calendar.calculators.WeekGridPositionCalculator;

import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class EventWeekdayItemGridAdapter extends CalendarItemGridAdapter {

    private final GridEventsCalendar gridEventsCalendar;

    public EventWeekdayItemGridAdapter(@NonNull final Activity context, @NonNull final List<Event> events) {
        super(context, events, R.layout.gridview_event_month_calendar_item);
        final GridPositionCalculator gridPositionCalculator = new WeekGridPositionCalculator();
        gridEventsCalendar = new GridEventsCalendar(events, gridPositionCalculator);
    }

    @Override
    public int getCount() {
        return Constants.DAYS_IN_WEEK;
    }

    @Override
    public List<Event> getItem(final int position) {
        final int weekday = getWeekday(position);
        final GridPosition gridPosition = GridPosition.of(weekday, 1);
        return gridEventsCalendar.getEvents(gridPosition);
    }

    @Override
    protected void setHeaders(final TextView titleTextView, final TextView subtitleTextView, final int position) {
        final String shortWeekdayName = getShortWeekdayName(position);
        titleTextView.setText(shortWeekdayName);
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }

    private static int getWeekday(final int position) {
        return position % Constants.DAYS_IN_WEEK + 1;
    }

    private static String getShortWeekdayName(final int position) {
        final int weekday = getWeekday(position);
        return CalendarUtils.getShortNameWeekday(weekday);
    }
}
