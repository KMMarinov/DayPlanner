package com.kalinmarinov.dayplanner.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatMenuActivity;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class CreateEditEventActivity extends InjectableAppCompatMenuActivity {

    @Inject
    SingleEventViewModel singleEventViewModel;

    private Disposable eventDisposable;

    @BindView(R.id.createEditEventSaveButton)
    Button saveButton;

    @BindView(R.id.createEditEventEditTextName)
    EditText nameEditText;

    @BindView(R.id.createEditEventEditTextDescription)
    EditText descriptionEditText;

    @BindView(R.id.createEditEventEditTextStartDate)
    EditText startDateEditText;

    @BindView(R.id.createEditEventEditTextEndDate)
    EditText endDateEditText;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_event);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
        fetchEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventDisposable != null) {
            eventDisposable.dispose();
        }
    }

    private void fetchEvent() {
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            eventDisposable = singleEventViewModel.getEventSingle(eventId).subscribe(this::showEvent, throwable -> Toast
                    .makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show());
        }
    }

    private void showEvent(final Event event) {
        nameEditText.setText(event.getName());
        descriptionEditText.setText(event.getDescription());
        startDateEditText.setText(String.valueOf(event.getStartDate()));
        endDateEditText.setText(String.valueOf(event.getEndDate()));
    }

    @OnClick(R.id.createEditEventSaveButton)
    void saveEvent(final View view) {
        final String name = nameEditText.getText().toString().trim();
        final String description = descriptionEditText.getText().toString().trim();
        final String startDate = startDateEditText.getText().toString().trim();
        final String endDate = endDateEditText.getText().toString().trim();
        singleEventViewModel.saveEvent(name, description, startDate, endDate).subscribe(this::finish, throwable -> Toast
                .makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show());
    }
}
