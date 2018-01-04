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
    private final SchedulerProvider schedulerProvider;

    public EventsViewModelImpl(final EventDataModel eventDataModel, final SchedulerProvider schedulerProvider) {
        this.eventDataModel = eventDataModel;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Flowable<List<Event>> getEvents() {
        return eventDataModel.getEvents()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }
}
