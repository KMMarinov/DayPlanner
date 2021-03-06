package com.kalinmarinov.dayplanner.di.controller;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.di.qualifiers.ViewModelProvided;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.services.EventCalendarService;
import com.kalinmarinov.dayplanner.services.EventViewModelService;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventCalendarViewModelFactory;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventsViewModelFactory;
import com.kalinmarinov.dayplanner.viewmodels.factories.SingleEventViewModelFactory;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParser;
import com.kalinmarinov.dayplanner.views.presenters.EventsCalendarViewPresenter;
import com.kalinmarinov.dayplanner.views.presenters.EventsCalendarViewPresenterImpl;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Module
public class ControllerModule {

    private final FragmentActivity fragmentActivity;

    public ControllerModule(final FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Provides
    SingleEventViewModel getSingleEventViewModel(final EventDataModel eventDataModel,
                                                 final SchedulerProvider schedulerProvider,
                                                 final EventViewModelService eventViewModelService) {
        return new SingleEventViewModelImpl(eventDataModel, schedulerProvider, eventViewModelService);
    }

    @Provides
    EventsViewModel getEventsViewModel(final EventDataModel eventDataModel,
                                       final SchedulerProvider schedulerProvider) {
        return new EventsViewModelImpl(eventDataModel, schedulerProvider);
    }

    @Provides
    EventsCalendarViewModel getEventsCalendarViewModel(final SchedulerProvider schedulerProvider,
                                                       final EventCalendarService eventCalendarService,
                                                       final EventsCalendarViewModelParser eventsCalendarViewModelParser) {
        return new EventsCalendarViewModelImpl(schedulerProvider, eventCalendarService, eventsCalendarViewModelParser);
    }

    @Provides
    SingleEventViewModelFactory getSingleEventViewModelFactory(final Lazy<SingleEventViewModel> singleEventViewModel) {
        return new SingleEventViewModelFactory(singleEventViewModel);
    }

    @Provides
    EventsViewModelFactory getEventsViewModelFactory(final Lazy<EventsViewModel> eventsViewModel) {
        return new EventsViewModelFactory(eventsViewModel);
    }

    @Provides
    EventCalendarViewModelFactory getEventCalendarViewModelFactory(
            final Lazy<EventsCalendarViewModel> eventsCalendarViewModel) {
        return new EventCalendarViewModelFactory(eventsCalendarViewModel);
    }

    @Provides
    @ViewModelProvided
    SingleEventViewModel getSingleEventViewModelProvided(
            final SingleEventViewModelFactory singleEventViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, singleEventViewModelFactory).get(SingleEventViewModelImpl.class);
    }

    @Provides
    @ViewModelProvided
    EventsViewModel getEventsViewModelProvided(final EventsViewModelFactory eventsViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, eventsViewModelFactory).get(EventsViewModelImpl.class);
    }

    @Provides
    @ViewModelProvided
    EventsCalendarViewModel getEventsCalendarViewModelProvided(
            final EventCalendarViewModelFactory eventCalendarViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, eventCalendarViewModelFactory)
                .get(EventsCalendarViewModelImpl.class);
    }

    @Provides
    EventsCalendarViewPresenter getEventCalendarViewPresenter() {
        return new EventsCalendarViewPresenterImpl(fragmentActivity);
    }
}
