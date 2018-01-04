package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.utils.DateUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Date;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelImpl extends ViewModel implements SingleEventViewModel {

    private final EventDataModel eventDataModel;
    private final SchedulerProvider schedulerProvider;

    private Event event;

    public SingleEventViewModelImpl(final EventDataModel eventDataModel, final SchedulerProvider schedulerProvider) {
        this.eventDataModel = eventDataModel;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Flowable<Event> getEvent(final int eventId) {
        //TODO: add object that will be convenient for the view. Facilitating date handling
        return eventDataModel.findById(eventId).map(e -> event = e)
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Single<Event> getEventSingle(final int eventId) {
        //TODO: add object that will be convenient for the view. Facilitating date handling
        return eventDataModel.findById(eventId).map(e -> event = e).firstOrError()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Completable saveEvent(final String name, final String description, final String startDate,
                                 final String endDate) {
        return Completable.fromAction(() -> {
            // TODO: create custom class that will be able to parse date
            final Date parsedStartDate = DateUtils.parseDate(startDate);
            final Date parsedEndDate = DateUtils.parseDate(endDate);
            getEvent().setDescription(description);
            getEvent().setName(name);
            getEvent().setStartDate(parsedStartDate);
            getEvent().setEndDate(parsedEndDate);
            eventDataModel.saveEvent(getEvent());
        }).subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Completable deleteEvent(final Event event) {
        return Completable.fromAction(() -> eventDataModel.deleteEvent(getEvent()))
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    private Event getEvent() {
        if (event == null) {
            event = new Event();
        }
        return event;
    }
}
