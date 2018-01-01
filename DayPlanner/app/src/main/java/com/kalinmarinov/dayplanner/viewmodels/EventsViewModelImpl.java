package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class EventsViewModelImpl extends ViewModel implements EventsViewModel {

    private final EventDataModel eventDataModel;

    public EventsViewModelImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    @Override
    public Flowable<List<Event>> getEvents() {
        return eventDataModel.getEvents()
                .subscribeOn(SchedulerProvider.getInstance().getIOScheduler())
                .observeOn(SchedulerProvider.getInstance().getMainScheduler());
    }
}
