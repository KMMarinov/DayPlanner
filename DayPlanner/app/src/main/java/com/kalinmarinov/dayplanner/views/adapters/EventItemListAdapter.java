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
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.views.ShowEventActivity;

import java.util.List;

import static com.kalinmarinov.dayplanner.DayPlannerApplication.getContext;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventItemListAdapter extends BaseAdapter {

    private final List<Event> events;
    private final Activity context;

    public EventItemListAdapter(@NonNull final Activity context, @NonNull final List<Event> events) {
        this.events = events;
        this.context = context;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(final int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return events.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            final LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.listview_event_item, null);
            final TextView titleTextView = view.findViewById(R.id.listview_event_item_title);
            final ViewHolder viewHolder = new ViewHolder(titleTextView);
            view.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        final Event event = getItem(position);
        final String extraKey = Constants.EVENT_INTENT_ID_EXTRA_KEY;
        viewHolder.getTitleTextView().setText(event.getName());
        viewHolder.getTitleTextView().setOnClickListener(v -> ActivityUtils
                .startActivityWithExtra(getContext(), ShowEventActivity.class, extraKey, (int) getItemId(position)));

        return view;
    }

    private static class ViewHolder {

        private final TextView titleTextView;

        ViewHolder(final TextView titleTextView) {
            this.titleTextView = titleTextView;
        }

        TextView getTitleTextView() {
            return titleTextView;
        }
    }
}
