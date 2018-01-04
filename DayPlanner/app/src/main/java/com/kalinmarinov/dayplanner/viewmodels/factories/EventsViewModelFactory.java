package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;
import dagger.Lazy;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class EventsViewModelFactory implements ViewModelProvider.Factory {

    private Lazy<EventsViewModel> eventsViewModel;

    public EventsViewModelFactory(final Lazy<EventsViewModel> eventsViewModel) {
        this.eventsViewModel = eventsViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!EventsViewModel.class.isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, EventsViewModel.class);
        }
        return (T) eventsViewModel.get();
    }
}
