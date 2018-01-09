package com.kalinmarinov.dayplanner.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModel;
import com.kalinmarinov.dayplanner.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatMenuActivity;
import com.kalinmarinov.dayplanner.views.presenters.EventsCalendarViewPresenter;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventsCalendarActivity extends InjectableAppCompatMenuActivity {

    @Inject
    EventsCalendarViewModel eventCalendarViewModel;

    @Inject
    EventsCalendarViewPresenter eventsCalendarViewPresenter;

    @BindView(R.id.eventsCalendarGridView)
    GridView eventsCalendarGridView;

    @BindView(R.id.eventsCalendarSpinner)
    Spinner eventsCalendarSpinner;

    @BindView(R.id.eventsCalendarInputEditText)
    EditText inputEditText;

    public GridView getEventsCalendarGridView() {
        return eventsCalendarGridView;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_calendar);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
        eventsCalendarSpinner.setAdapter(
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, CalendarPeriodType.values()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        createEventObserver(CalendarPeriodType.MONTH, Constants.EMPTY_STRING);
    }

    @OnItemSelected(R.id.eventsCalendarSpinner)
    void onItemSpinnerSelected() {
        final String calendarTypeString = eventsCalendarSpinner.getSelectedItem().toString();
        final CalendarPeriodType calendarPeriodType = CalendarPeriodType.getType(calendarTypeString);
        inputEditText.setVisibility(calendarPeriodType == CalendarPeriodType.MONTH ? View.INVISIBLE : View.VISIBLE);
    }

    @OnClick(R.id.eventsCalendarSetButton)
    void onSetCalendarClick() {
        final String calendarTypeString = eventsCalendarSpinner.getSelectedItem().toString();
        final CalendarPeriodType calendarPeriodType = CalendarPeriodType.getType(calendarTypeString);
        final String inputString = inputEditText.getText().toString();
        createEventObserver(calendarPeriodType, inputString);
    }

    private void createEventObserver(final CalendarPeriodType calendarPeriodType, final String input) {
        final Disposable disposable = eventCalendarViewModel
                .getEvents(calendarPeriodType, input)
                .subscribe(eventsCalendarViewPresenter::handleCalendar, this::showError);
        addDisposable(disposable);
    }
}
