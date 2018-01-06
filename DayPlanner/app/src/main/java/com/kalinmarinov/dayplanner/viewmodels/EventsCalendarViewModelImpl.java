package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.services.EventCalendarService;
import com.kalinmarinov.dayplanner.viewmodels.types.CalendarPeriodType;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventsCalendarViewModelImpl extends ViewModel implements EventsCalendarViewModel {

    private final SchedulerProvider schedulerProvider;
    private final EventCalendarService eventCalendarService;

    public EventsCalendarViewModelImpl(final SchedulerProvider schedulerProvider,
                                       final EventCalendarService eventCalendarService) {
        this.schedulerProvider = schedulerProvider;
        this.eventCalendarService = eventCalendarService;
    }

    @Override
    public Flowable<List<Event>> getEvents(final CalendarPeriodType calendarPeriodType) {
        return eventCalendarService.getEvents(calendarPeriodType)
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getMainScheduler());
    }
}
