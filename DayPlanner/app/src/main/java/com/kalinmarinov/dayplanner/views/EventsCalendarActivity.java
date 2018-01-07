package com.kalinmarinov.dayplanner.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.adapters.EventItemListAdapter;
import com.kalinmarinov.dayplanner.views.adapters.EventMonthItemGridAdapter;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatMenuActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventsCalendarActivity extends InjectableAppCompatMenuActivity {

    private CompositeDisposable compositeDisposable;
    private Map<CalendarPeriodType, Consumer<List<Event>>> eventHandlers;

    @Inject
    EventsCalendarViewModel eventCalendarViewModel;

    @BindView(R.id.eventsCalendarGridView)
    GridView eventsCalendarGridView;

    @BindView(R.id.eventsCalendarSpinner)
    Spinner eventsCalendarSpinner;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_calendar);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
        initEventHandlers();
        eventsCalendarSpinner.setAdapter(
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, CalendarPeriodType.values()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        createEventObserver(CalendarPeriodType.MONTH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    @OnItemSelected(R.id.eventsCalendarSpinner)
    void changeCalendarType(final Spinner spinner, final int position) {
        final CalendarPeriodType selectedCalendarPeriodType = CalendarPeriodType.values()[position];
        createEventObserver(selectedCalendarPeriodType);
    }

    private void createEventObserver(final CalendarPeriodType calendarPeriodType) {
        final Consumer<List<Event>> eventHandler = eventHandlers.get(calendarPeriodType);
        final Disposable disposable = eventCalendarViewModel
                .getEvents(calendarPeriodType)
                .subscribe(eventHandler);
        emptyAndAddDisposable(disposable);
    }

    private void emptyAndAddDisposable(final Disposable... disposables) {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        } else {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.addAll(disposables);
    }

    private void initEventHandlers() {
        eventHandlers = new HashMap<>();
        eventHandlers.put(CalendarPeriodType.MONTH, this::monthEventHandler);
        eventHandlers.put(CalendarPeriodType.WEEK, this::weekEventHandler);
        eventHandlers.put(CalendarPeriodType.DAY, this::dayEventHandler);
    }

    private void monthEventHandler(final List<Event> events) {
        final EventMonthItemGridAdapter arrayAdapter = new EventMonthItemGridAdapter(this, events);
        eventsCalendarGridView.setAdapter(arrayAdapter);
    }

    private void weekEventHandler(final List<Event> events) {
        final EventItemListAdapter arrayAdapter = new EventItemListAdapter(this, events);
        eventsCalendarGridView.setAdapter(arrayAdapter);
    }

    private void dayEventHandler(final List<Event> events) {
        final EventMonthItemGridAdapter arrayAdapter = new EventMonthItemGridAdapter(this, events);
        eventsCalendarGridView.setAdapter(arrayAdapter);
    }
}
