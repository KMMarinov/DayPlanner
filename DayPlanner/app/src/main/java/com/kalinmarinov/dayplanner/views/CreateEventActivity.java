package com.kalinmarinov.dayplanner.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.viewmodels.EventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventViewModelFactory;

public class CreateEventActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;
    private EventViewModelFactory eventViewModelFactory;

    // UI components
    private Button saveButton;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        eventViewModelFactory = new EventViewModelFactory();
        eventViewModel = ViewModelProviders.of(this, eventViewModelFactory).get(EventViewModelImpl.class);
    }

    private void initUI() {
        setContentView(R.layout.activity_create_event);

        saveButton = findViewById(R.id.createEventSaveButton);
        saveButton.setOnClickListener(this::saveEvent);
        nameEditText = findViewById(R.id.createEventEditTextName);
        descriptionEditText = findViewById(R.id.createEventEditTextDescription);
        startDateEditText = findViewById(R.id.createEventEditTextStartDate);
        endDateEditText = findViewById(R.id.createEventEditTextEndDate);
    }

    private void saveEvent(final View view) {
        final String name = nameEditText.getText().toString().trim();
        final String description = descriptionEditText.getText().toString().trim();
        final String startDate = startDateEditText.getText().toString().trim();
        final String endDate = endDateEditText.getText().toString().trim();
        // TODO: Should check if the operation was successful, e.g using RxJava Completable
        eventViewModel.saveEvent(name, description, startDate, endDate);
        finish();
    }
}
