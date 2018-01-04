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
import com.kalinmarinov.dayplanner.di.qualifiers.ViewModelProvided;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatMenuActivity;
import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class CreateEditEventActivity extends InjectableAppCompatMenuActivity {

    @Inject
    @ViewModelProvided
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

    @OnClick(R.id.createEditEventSaveButton)
    void saveEvent(final View view) {
        singleEventViewModel.saveEvent(getFormInput())
                .subscribe(this::finish, this::showError);
    }

    private void fetchEvent() {
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            eventDisposable = singleEventViewModel.getEventSingle(eventId)
                    .subscribe(this::showEvent, this::showError);
        }
    }

    private void showEvent(final EventModelViewContainer event) {
        nameEditText.setText(event.getName());
        descriptionEditText.setText(event.getDescription());
        startDateEditText.setText(event.getStartDate().getDate());
        endDateEditText.setText(event.getEndDate().getDate());
    }

    private void showError(final Throwable throwable) {
        Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    private EventModelViewContainer getFormInput() {
        final String name = nameEditText.getText().toString().trim();
        final String description = descriptionEditText.getText().toString().trim();
        final String startDate = startDateEditText.getText().toString().trim();
        final String endDate = endDateEditText.getText().toString().trim();

        final EventModelViewContainer eventModelViewContainer = new EventModelViewContainer();
        eventModelViewContainer.setName(name);
        eventModelViewContainer.setDescription(description);
        eventModelViewContainer.setStartDate(new EventModelViewContainer.DateContainer(startDate));
        eventModelViewContainer.setEndDate(new EventModelViewContainer.DateContainer(endDate));

        return eventModelViewContainer;
    }
}
