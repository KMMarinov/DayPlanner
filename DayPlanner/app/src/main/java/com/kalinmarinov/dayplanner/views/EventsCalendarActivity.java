package com.kalinmarinov.dayplanner.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.adapters.EventMonthItemGridAdapter;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventsCalendarActivity extends InjectableAppCompatActivity {

    @Inject
    EventsCalendarViewModel eventCalendarViewModel;

    private CompositeDisposable compositeDisposable;

    @BindView(R.id.eventsCalendarGridView)
    GridView eventsCalendarGridView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_calendar);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Disposable disposable = eventCalendarViewModel
                .getEvents(CalendarPeriodType.MONTH)
                .subscribe(this::showEventName);
        compositeDisposable = new CompositeDisposable(disposable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void showEventName(final List<Event> events) {
        final EventMonthItemGridAdapter arrayAdapter = new EventMonthItemGridAdapter(this,
                android.R.layout.simple_gallery_item,
                events);
        eventsCalendarGridView.setAdapter(arrayAdapter);
    }
}
