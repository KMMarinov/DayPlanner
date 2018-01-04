package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.services.EventViewModelService;
import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelImpl extends ViewModel implements SingleEventViewModel {

    private final EventDataModel eventDataModel;
    private final SchedulerProvider schedulerProvider;
    private final EventViewModelService eventViewModelService;

    private Event event;

    public SingleEventViewModelImpl(final EventDataModel eventDataModel, final SchedulerProvider schedulerProvider,
                                    final EventViewModelService eventViewModelService) {
        this.eventDataModel = eventDataModel;
        this.schedulerProvider = schedulerProvider;
        this.eventViewModelService = eventViewModelService;
    }

    @Override
    public Flowable<EventModelViewContainer> getEvent(final int eventId) {
        return eventDataModel.findById(eventId).map(e -> {
            event = e;
            return eventViewModelService.convertToContainer(event);
        }).subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Single<EventModelViewContainer> getEventSingle(final int eventId) {
        return eventDataModel.findById(eventId).map(e -> {
            event = e;
            return eventViewModelService.convertToContainer(event);
        }).firstOrError()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Completable saveEvent(final EventModelViewContainer eventModelViewContainer) {
        return Completable.fromAction(() -> {
            final Event event = eventViewModelService.convertToModel(eventModelViewContainer);
            updateEvent(event);
            eventDataModel.saveEvent(getEvent());
        }).subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    @Override
    public Completable deleteEvent() {
        return Completable.fromAction(() -> eventDataModel.deleteEvent(getEvent()))
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }

    private void updateEvent(final Event event) {
        getEvent().setName(event.getName());
        getEvent().setDescription(event.getDescription());
        getEvent().setStartDate(event.getStartDate());
        getEvent().setEndDate(event.getEndDate());
    }

    private Event getEvent() {
        if (event == null) {
            event = new Event();
        }
        return event;
    }
}
