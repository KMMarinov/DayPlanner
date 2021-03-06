package com.kalinmarinov.dayplanner.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.di.qualifiers.ViewModelProvided;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.views.adapters.EventItemListAdapter;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatActivity;

import javax.inject.Inject;
import java.util.List;

public class EventActivity extends InjectableAppCompatActivity {

    @Inject
    @ViewModelProvided
    EventsViewModel eventViewModel;
   
    @BindView(R.id.eventsListView)
    ListView eventsListView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDisposable(eventViewModel.getEvents().subscribe(this::showEventName));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_events_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listEventsMenuAddButton:
                startActivity(CreateEditEventActivity.class);
                break;
            case R.id.listEventsMenuCalendarButton:
                startActivity(EventsCalendarActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEventName(final List<Event> events) {
        final EventItemListAdapter arrayAdapter = new EventItemListAdapter(this, events);
        eventsListView.setAdapter(arrayAdapter);
    }

    private void startActivity(final Class activityClass) {
        final Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
