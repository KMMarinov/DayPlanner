package com.kalinmarinov.dayplanner.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventsViewModelFactory;
import com.kalinmarinov.dayplanner.views.adapters.EventItemListAdapter;
import io.reactivex.disposables.CompositeDisposable;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;
    private EventsViewModel eventViewModel;
    private EventsViewModelFactory eventViewModelFactory;

    // UI components
    private ListView eventsListView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        eventViewModelFactory = new EventsViewModelFactory();
        eventViewModel = ViewModelProviders.of(this, eventViewModelFactory).get(EventsViewModelImpl.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(eventViewModel.getEvents()

                .subscribe(this::showEventName));
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        setContentView(R.layout.activity_list_events);
        eventsListView = findViewById(R.id.eventsListView);
    }

    private void showEventName(final List<Event> events) {
        final EventItemListAdapter arrayAdapter = new EventItemListAdapter(this, android.R.layout.simple_list_item_1,
                events);
        eventsListView.setAdapter(arrayAdapter);
    }

    private void startActivity(final Class activityClass) {
        final Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
