package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.services.EventCalendarService;
import com.kalinmarinov.dayplanner.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.utils.models.Period;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParser;
import com.kalinmarinov.dayplanner.views.containers.EventCalendarContainer;
import io.reactivex.Flowable;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventsCalendarViewModelImpl extends ViewModel implements EventsCalendarViewModel {

    private final SchedulerProvider schedulerProvider;
    private final EventCalendarService eventCalendarService;
    private final EventsCalendarViewModelParser eventsCalendarViewModelParser;

    public EventsCalendarViewModelImpl(final SchedulerProvider schedulerProvider,
                                       final EventCalendarService eventCalendarService,
                                       final EventsCalendarViewModelParser eventsCalendarViewModelParser) {
        this.schedulerProvider = schedulerProvider;
        this.eventCalendarService = eventCalendarService;
        this.eventsCalendarViewModelParser = eventsCalendarViewModelParser;
    }

    @Override
    public Flowable<EventCalendarContainer> getEvents(final CalendarPeriodType calendarPeriodType, final String input) {
        return Flowable.fromCallable(() -> getListFlowable(calendarPeriodType, input))
                .flatMap(eventCalendarContainerFlowable -> eventCalendarContainerFlowable)
                .observeOn(schedulerProvider.getMainScheduler())
                .subscribeOn(schedulerProvider.getIOScheduler());
    }

    private Flowable<EventCalendarContainer> getListFlowable(final CalendarPeriodType calendarPeriodType,
                                                             final String input) {
        final Period period;
        switch (calendarPeriodType) {
            case DAY:
                period = eventsCalendarViewModelParser.calculateDayPeriod(input);
                return eventCalendarService.dayOfCurrentMonthEvents(period.getStart());
            case WEEK:
                period = eventsCalendarViewModelParser.calculateWeekPeriod(input);
                return eventCalendarService.weekOfCurrentMonthEvents(period.getStart());
            case PERIOD:
                period = eventsCalendarViewModelParser.calculatePeriodFromPeriod(input);
                return eventCalendarService.daysInPeriodMonthEvents(period);
        }
        return eventCalendarService.currentMonthEvents();
    }
}
