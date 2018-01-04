package com.kalinmarinov.dayplanner.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.kalinmarinov.dayplanner.R;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.utils.android.ActivityUtils;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.views.base.InjectableAppCompatMenuActivity;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class ShowEventActivity extends InjectableAppCompatMenuActivity {

    @Inject
    SingleEventViewModel singleEventViewModel;

    private CompositeDisposable compositeDisposable;

    @BindView(R.id.viewEventButtonEdit)
    Button editButton;

    @BindView(R.id.viewEventButtonDelete)
    Button deleteButton;

    @BindView(R.id.viewEventTextViewName)
    TextView nameTextView;

    @BindView(R.id.viewEventTextViewDescription)
    TextView descriptionTextView;

    @BindView(R.id.viewEventTextViewStartDate)
    TextView startDateTextView;

    @BindView(R.id.viewEventTextViewEndDate)
    TextView endDateTextView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            compositeDisposable.add(singleEventViewModel.getEvent(eventId)
                    .subscribe(this::setupEvent, throwable -> Toast
                            .makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
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
        compositeDisposable.add(singleEventViewModel.deleteEvent(event)
                .subscribe(this::finish, throwable -> Toast
                        .makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }
}
