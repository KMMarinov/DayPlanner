package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.viewmodels.EventsCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;
import dagger.Lazy;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventCalendarViewModelFactory implements ViewModelProvider.Factory {

    private Lazy<EventsCalendarViewModel> eventCalendarViewModel;

    public EventCalendarViewModelFactory(final Lazy<EventsCalendarViewModel> eventCalendarViewModel) {
        this.eventCalendarViewModel = eventCalendarViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!EventsCalendarViewModel.class.isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, EventsCalendarViewModel.class);
        }
        return (T) eventCalendarViewModel.get();
    }
}
