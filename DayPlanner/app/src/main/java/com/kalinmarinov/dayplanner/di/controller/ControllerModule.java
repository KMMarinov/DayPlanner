package com.kalinmarinov.dayplanner.di.controller;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.EventsViewModelFactory;
import com.kalinmarinov.dayplanner.viewmodels.factories.SingleEventViewModelFactory;
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
    public SingleEventViewModelFactory getSingleEventViewModelFactory(final EventDataModel eventDataModel) {
        return new SingleEventViewModelFactory(eventDataModel);
    }

    @Provides
    public EventsViewModelFactory getEventsViewModelFactory(final EventDataModel eventDataModel) {
        return new EventsViewModelFactory(eventDataModel);
    }

    @Provides
    public SingleEventViewModel getSingleEventViewModel(final SingleEventViewModelFactory singleEventViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, singleEventViewModelFactory).get(SingleEventViewModelImpl.class);
    }

    @Provides
    public EventsViewModel getEventsViewModel(final EventsViewModelFactory eventsViewModelFactory) {
        return ViewModelProviders.of(fragmentActivity, eventsViewModelFactory).get(EventsViewModelImpl.class);
    }
}
