package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;
import dagger.Lazy;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelFactory implements ViewModelProvider.Factory {

    private Lazy<SingleEventViewModel> singleEventViewModel;

    public SingleEventViewModelFactory(final Lazy<SingleEventViewModel> singleEventViewModel) {
        this.singleEventViewModel = singleEventViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!SingleEventViewModel.class.isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, SingleEventViewModel.class);
        }
        return (T) singleEventViewModel.get();
    }
}
