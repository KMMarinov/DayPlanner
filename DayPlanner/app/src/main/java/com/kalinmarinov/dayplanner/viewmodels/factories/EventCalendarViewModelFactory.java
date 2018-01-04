package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventCalendarViewModelFactory implements ViewModelProvider.Factory {

    private EventDataModel eventDataModel;

    EventCalendarViewModelFactory(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!EventCalendarViewModel.class.isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, EventCalendarViewModel.class);
        }
        return (T) new EventCalendarViewModelImpl(eventDataModel);
    }
}
