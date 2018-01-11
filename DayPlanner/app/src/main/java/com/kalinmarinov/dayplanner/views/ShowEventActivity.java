package com.kalinmarinov.dayplanner.views;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.TextView;
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

import javax.inject.Inject;

public class ShowEventActivity extends InjectableAppCompatMenuActivity {

    @Inject
    @ViewModelProvided
    SingleEventViewModel singleEventViewModel;

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
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_audiotrack_dark)
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        final int eventId = ActivityUtils.getExtra(getIntent(), Constants.EVENT_INTENT_ID_EXTRA_KEY);
        if (eventId != 0) {
            addDisposable(singleEventViewModel
                    .getEvent(eventId)
                    .subscribe(this::setupEvent, this::showError));
        }
    }

    @OnClick(R.id.viewEventButtonDelete)
    void onDeleteButtonClick() {
        addDisposable(singleEventViewModel
                .deleteEvent()
                .subscribe(this::finish, this::showError));
    }

    private void setupEvent(final EventModelViewContainer event) {
        nameTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());
        startDateTextView.setText(event.getStartDate().getDate());
        endDateTextView.setText(event.getEndDate().getDate());

        editButton.setOnClickListener(v -> ActivityUtils
                .startActivityWithExtra(getApplicationContext(), CreateEditEventActivity.class,
                        Constants.EVENT_INTENT_ID_EXTRA_KEY, event.getId()));
    }
}
