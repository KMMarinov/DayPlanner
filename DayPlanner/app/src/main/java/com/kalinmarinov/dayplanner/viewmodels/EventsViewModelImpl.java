package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
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

    public Flowable<List<Event>> getEvents() {
        return eventDataModel.getEvents();
    }

    public void deleteEvent(final Event event) {
        eventDataModel.deleteEvent(event);
    }
}
