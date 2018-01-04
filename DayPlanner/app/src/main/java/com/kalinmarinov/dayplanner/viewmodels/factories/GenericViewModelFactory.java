package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public abstract class GenericViewModelFactory implements ViewModelProvider.Factory {

    private EventDataModel eventDataModel;

    GenericViewModelFactory(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    public abstract Class<?> getClassImpl();

    public abstract <T extends ViewModel> T createViewModel(final EventDataModel eventDataModel);

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!getClassImpl().isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, getClassImpl());
        }
        return createViewModel(eventDataModel);
    }
}
