package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.DateUtils;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Date;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelImpl extends ViewModel implements SingleEventViewModel {

    private final EventDataModel eventDataModel;
    private Event event;

    public SingleEventViewModelImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    @Override
    public Flowable<Event> getEvent(final int eventId) {
        //TODO: add object that will be convenient for the view. Facilitating date handling
        return eventDataModel.findById(eventId).map(e -> event = e);
    }

    @Override
    public Single<Event> getEventSingle(final int eventId) {
        //TODO: add object that will be convenient for the view. Facilitating date handling
        return eventDataModel.findById(eventId).map(e -> event = e).firstOrError();
    }

    @Override
    public void saveEvent(final String name, final String description, final String startDate, final String endDate) {
        final Date parsedStartDate = DateUtils.parseDate(startDate);
        final Date parsedEndDate = DateUtils.parseDate(endDate);
        getEvent().setDescription(description);
        getEvent().setName(name);
        getEvent().setStartDate(parsedStartDate);
        getEvent().setEndDate(parsedEndDate);
        //TODO: on complete - save or error has occurred
        eventDataModel.saveEvent(getEvent());
    }

    @Override
    public void deleteEvent(final Event event) {
        // TODO: use on complete method
        eventDataModel.deleteEvent(event);
    }

    private Event getEvent() {
        if (event == null) {
            event = new Event();
        }
        return event;
    }
}
