package com.kalinmarinov.dayplanner.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridEventsCalendar;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPositionCalculator;
import com.kalinmarinov.dayplanner.views.containers.calendar.calculators.DayGridPositionCalculator;

import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public class EventDayItemGridAdapter extends CalendarItemGridAdapter {

    private final GridEventsCalendar gridEventsCalendar;

    public EventDayItemGridAdapter(@NonNull final Activity context, @NonNull final List<Event> events) {
        super(context, events, R.layout.gridview_event_month_calendar_item);
        final GridPositionCalculator gridPositionCalculator = new DayGridPositionCalculator();
        gridEventsCalendar = new GridEventsCalendar(events, gridPositionCalculator);
    }

    @Override
    public int getCount() {
        return Constants.HOURS_IN_DAY;
    }

    @Override
    public List<Event> getItem(final int position) {
        final GridPosition gridPosition = GridPosition.of(1, position);
        return gridEventsCalendar.getEvents(gridPosition);
    }

    @Override
    protected void setHeaders(final TextView titleTextView, final TextView subtitleTextView, final int position) {
        titleTextView.setText(String.valueOf(position));
    }

    @Override
    public long getItemId(final int position) {
        return 0;
    }
}
