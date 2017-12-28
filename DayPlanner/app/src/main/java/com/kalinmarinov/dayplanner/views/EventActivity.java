package com.kalinmarinov.dayplanner.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.viewmodels.EventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventViewModelFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;
    private EventViewModel eventViewModel;
    private EventViewModelFactory eventViewModelFactory;

    // UI components
    private ListView eventsListView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        eventViewModelFactory = new EventViewModelFactory();
        eventViewModel = ViewModelProviders.of(this, eventViewModelFactory).get(EventViewModelImpl.class);
    }

    private void initUI() {
        setContentView(R.layout.activity_list_events);
        eventsListView = findViewById(R.id.eventsListView);
        eventsListView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getBaseContext(), ((TextView) view).getText().toString(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(eventViewModel.getEventNames()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showEventName));
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void showEventName(final List<String> events) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        eventsListView.setAdapter(arrayAdapter);
    }
}
