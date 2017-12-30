package com.kalinmarinov.dayplanner.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.SingleEventViewModelFactory;
import com.kalinmarinov.dayplanner.views.base.AppCompatMenuActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShowEventActivity extends AppCompatMenuActivity {

    private SingleEventViewModel singleEventViewModel;
    private SingleEventViewModelFactory singleEventViewModelFactory;
    private CompositeDisposable compositeDisposable;

    // UI components
    private Button editButton;
    private Button deleteButton;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView startDateTextView;
    private TextView endDateTextView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        singleEventViewModelFactory = new SingleEventViewModelFactory();
        singleEventViewModel = ViewModelProviders.of(this, singleEventViewModelFactory)
                .get(SingleEventViewModelImpl.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            // TODO: handle on error
            compositeDisposable.add(singleEventViewModel.getEvent(eventId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::setupEvent));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void initUI() {
        setContentView(R.layout.activity_view_event);

        editButton = findViewById(R.id.viewEventButtonEdit);
        deleteButton = findViewById(R.id.viewEventButtonDelete);
        nameTextView = findViewById(R.id.viewEventTextViewName);
        descriptionTextView = findViewById(R.id.viewEventTextViewDescription);
        startDateTextView = findViewById(R.id.viewEventTextViewStartDate);
        endDateTextView = findViewById(R.id.viewEventTextViewEndDate);
    }

    private void setupEvent(final Event event) {
        nameTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());
        startDateTextView.setText(String.valueOf(event.getStartDate()));
        endDateTextView.setText(String.valueOf(event.getEndDate()));

        editButton.setOnClickListener(v -> ActivityUtils
                .startActivityWithExtra(getApplicationContext(), CreateEditEventActivity.class,
                        Constants.EVENT_INTENT_ID_EXTRA_KEY, event.getId()));
        deleteButton.setOnClickListener(v -> onDeleteButtonClick(event));
    }

    private void onDeleteButtonClick(final Event event) {
        // TODO: Use on complete
        singleEventViewModel.deleteEvent(event);
        finish();
    }
}
