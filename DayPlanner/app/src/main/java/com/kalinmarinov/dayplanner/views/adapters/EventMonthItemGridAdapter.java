package com.kalinmarinov.dayplanner.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.CalendarUtils;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridEventsCalendar;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridPosition;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class EventMonthItemGridAdapter extends BaseAdapter {

    private final GridEventsCalendar gridEventsCalendar;
    private final Activity context;

    public EventMonthItemGridAdapter(@NonNull final Activity context, @NonNull final List<Event> events) {
        gridEventsCalendar = new GridEventsCalendar(events);
        this.context = context;
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
    public long getItemId(final int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = createView();
        }
        fillWithEvents(position, view);
        return view;
    }

    private void fillWithEvents(final int position, final View view) {
        final ViewHolder viewHolder = (ViewHolder) view.getTag();

        // set weekday name and date number
        final String shortWeekdayName = getShortWeekdayName(position);
        final TextView dayOfWeekTextView = viewHolder.getDayOfWeekTextView();
        final TextView dateNumberView = viewHolder.getDateNumberView();
        final String dateNumber = getDateNumber(position);
        dateNumberView.setText(dateNumber);
        dayOfWeekTextView.setText(shortWeekdayName);

        // set events if any
        final List<Event> events = getItem(position);
        if (!CollectionUtils.isEmpty(events)) {
            final List<TextView> eventNameViews = viewHolder.getEventNameViews();
            for (int i = 0; i < eventNameViews.size() && i < events.size(); i++) {
                final Event event = events.get(i);
                if (event != null) {
                    final TextView eventNameTextView = eventNameViews.get(i);
                    final String eventName = event.getName();
                    eventNameTextView.setText(eventName);
                }
            }
        }
    }

    @NonNull
    private View createView() {
        final LayoutInflater layoutInflater = context.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.gridview_event_month_calendar_item, null);
        final TextView dayOfWeekTextView = view.findViewById(R.id.gridViewEventMonthCalendarItemDayWeekName);
        final TextView dateNumber = view.findViewById(R.id.gridViewEventMonthCalendarItemDateNumber);
        final TextView eventNameTextView1 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent1);
        final TextView eventNameTextView2 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent2);
        final TextView eventNameTextView3 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent3);
        final List<TextView> eventNameViews = Arrays.asList(eventNameTextView1, eventNameTextView2, eventNameTextView3);
        final ViewHolder viewHolder = new ViewHolder(dayOfWeekTextView, eventNameViews, dateNumber);
        view.setTag(viewHolder);
        return view;
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

    private static class ViewHolder {

        private final TextView dayOfWeekTextView;
        private final List<TextView> eventNameViews;
        private final TextView dateNumberView;

        ViewHolder(final TextView dayOfWeekTextView, final List<TextView> eventNameViews,
                   final TextView dateNumberView) {
            this.dayOfWeekTextView = dayOfWeekTextView;
            this.eventNameViews = eventNameViews;
            this.dateNumberView = dateNumberView;
        }

        TextView getDayOfWeekTextView() {
            return dayOfWeekTextView;
        }

        List<TextView> getEventNameViews() {
            return eventNameViews;
        }

        TextView getDateNumberView() {
            return dateNumberView;
        }
    }
}
