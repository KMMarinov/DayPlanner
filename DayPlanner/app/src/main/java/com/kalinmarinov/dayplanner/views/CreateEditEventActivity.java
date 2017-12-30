package com.kalinmarinov.dayplanner.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.SingleEventViewModelFactory;
import com.kalinmarinov.dayplanner.views.base.AppCompatMenuActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateEditEventActivity extends AppCompatMenuActivity {

    private SingleEventViewModel singleEventViewModel;
    private SingleEventViewModelFactory singleEventViewModelFactory;
    private Disposable eventDisposable;

    // UI components
    private Button saveButton;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        singleEventViewModelFactory = new SingleEventViewModelFactory();
        singleEventViewModel = ViewModelProviders.of(this, singleEventViewModelFactory)
                .get(SingleEventViewModelImpl.class);
        fetchEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventDisposable != null) {
            eventDisposable.dispose();
        }
    }

    private void initUI() {
        setContentView(R.layout.activity_create_edit_event);

        saveButton = findViewById(R.id.createEditEventSaveButton);
        saveButton.setOnClickListener(this::saveEvent);
        nameEditText = findViewById(R.id.createEditEventEditTextName);
        descriptionEditText = findViewById(R.id.createEditEventEditTextDescription);
        startDateEditText = findViewById(R.id.createEditEventEditTextStartDate);
        endDateEditText = findViewById(R.id.createEditEventEditTextEndDate);
    }

    private void fetchEvent() {
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            // TODO: handle on error
            eventDisposable = singleEventViewModel.getEventSingle(eventId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showEvent);
        }
    }

    private void showEvent(final Event event) {
        nameEditText.setText(event.getName());
        descriptionEditText.setText(event.getDescription());
        startDateEditText.setText(String.valueOf(event.getStartDate()));
        endDateEditText.setText(String.valueOf(event.getEndDate()));
    }

    private void saveEvent(final View view) {
        final String name = nameEditText.getText().toString().trim();
        final String description = descriptionEditText.getText().toString().trim();
        final String startDate = startDateEditText.getText().toString().trim();
        final String endDate = endDateEditText.getText().toString().trim();
        // TODO: Should check if the operation was successful, e.g using RxJava Completable
        singleEventViewModel.saveEvent(name, description, startDate, endDate);
        finish();
    }
}
