package com.kalinmarinov.dayplanner.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.views.containers.calendar.GridEventsCalendar;

import java.util.List;

/**
 * Created by Kalin.Marinov on 06.01.2018.
 */
public class EventMonthItemGridAdapter extends ArrayAdapter<Event> {

    private final GridEventsCalendar gridEventsCalendar;
    private final Activity context;

    public EventMonthItemGridAdapter(@NonNull final Activity context, final int resource,
                                     @NonNull final List<Event> events) {
        super(context, resource, events);
        gridEventsCalendar = new GridEventsCalendar(events);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 31;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            final LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.gridview_event_month_calendar_item, null);
            final TextView dayOfWeekTextView = view.findViewById(R.id.gridViewEventMonthCalendarItemDayWeekName);
            dayOfWeekTextView.setText("test");
            final EventMonthItemGridAdapter.ViewHolder viewHolder = new EventMonthItemGridAdapter.ViewHolder(
                    dayOfWeekTextView);
            view.setTag(viewHolder);
        }

        final EventMonthItemGridAdapter.ViewHolder viewHolder = (EventMonthItemGridAdapter.ViewHolder) view.getTag();
        final String extraKey = Constants.EVENT_INTENT_ID_EXTRA_KEY;
        //viewHolder.getTitleTextView().setText(event.getName());
        //viewHolder.getTitleTextView().setOnClickListener(v -> ActivityUtils
        //.startActivityWithExtra(getContext(), ShowEventActivity.class, extraKey, event.getId()));

        return view;
    }

    private static class ViewHolder {

        private final TextView titleTextView;

        public ViewHolder(final TextView titleTextView) {
            this.titleTextView = titleTextView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }
    }
}
