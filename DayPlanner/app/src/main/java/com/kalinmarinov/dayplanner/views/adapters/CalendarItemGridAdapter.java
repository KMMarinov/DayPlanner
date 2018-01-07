package com.kalinmarinov.dayplanner.views.adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kalin.Marinov on 07.01.2018.
 */
public abstract class CalendarItemGridAdapter extends BaseAdapter {

    private final Activity context;
    private final int resource;

    public CalendarItemGridAdapter(@NonNull final Activity context, @NonNull final List<Event> events,
                                   @LayoutRes final int resource) {
        this.resource = resource;
        this.context = context;
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

    @Override
    public abstract List<Event> getItem(int position);

    protected abstract void setHeaders(final TextView titleTextView, final TextView subtitleTextView,
                                       final int position);

    private void fillWithEvents(final int position, final View view) {
        final ViewHolder viewHolder = (ViewHolder) view.getTag();

        // set headers
        final TextView titleTextView = viewHolder.getTitleTextView();
        final TextView subtitleTextView = viewHolder.getSubtitleTextView();
        setHeaders(titleTextView, subtitleTextView, position);

        // set events
        final List<Event> events = getItem(position);
        final List<TextView> eventNameViews = viewHolder.getEventNameViews();
        eventNameViews.forEach(textView -> textView.setText(Constants.EMPTY_STRING));
        if (!CollectionUtils.isEmpty(events)) {
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
        final View view = layoutInflater.inflate(resource, null);
        final TextView titleTextView = view.findViewById(R.id.gridViewEventMonthCalendarItemDayWeekName);
        final TextView subtitleTextView = view.findViewById(R.id.gridViewEventMonthCalendarItemDateNumber);
        final TextView eventNameTextView1 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent1);
        final TextView eventNameTextView2 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent2);
        final TextView eventNameTextView3 = view.findViewById(R.id.gridViewEventMonthCalendarItemEvent3);
        final List<TextView> eventNameViews = Arrays.asList(eventNameTextView1, eventNameTextView2, eventNameTextView3);
        final ViewHolder viewHolder = new ViewHolder(titleTextView, subtitleTextView, eventNameViews);
        view.setTag(viewHolder);
        return view;
    }

    private static class ViewHolder {

        private final TextView titleTextView;
        private final TextView subtitleTextView;
        private final List<TextView> eventNameViews;

        ViewHolder(final TextView titleTextView, final TextView subtitleTextView,
                   final List<TextView> eventNameViews) {
            this.titleTextView = titleTextView;
            this.subtitleTextView = subtitleTextView;
            this.eventNameViews = eventNameViews;
        }

        TextView getTitleTextView() {
            return titleTextView;
        }

        TextView getSubtitleTextView() {
            return subtitleTextView;
        }

        List<TextView> getEventNameViews() {
            return eventNameViews;
        }
    }
}
