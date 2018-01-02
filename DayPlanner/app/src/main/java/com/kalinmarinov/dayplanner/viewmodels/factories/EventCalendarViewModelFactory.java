package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.database.datasources.EventDataSourceImpl;
import com.kalinmarinov.dayplanner.database.providers.EventDaoProvider;
import com.kalinmarinov.dayplanner.database.providers.EventDaoProviderImpl;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModelImpl;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.factories.exceptions.ViewModelFactoryMismatchException;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventCalendarViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (!EventCalendarViewModel.class.isAssignableFrom(modelClass)) {
            throw new ViewModelFactoryMismatchException(modelClass, SingleEventViewModel.class);
        }
        final EventDaoProvider eventDaoProvider = new EventDaoProviderImpl();
        final EventDataSource eventDataSource = new EventDataSourceImpl(eventDaoProvider.getEventDao());
        final EventDataModel eventDataModel = new EventDataModelImpl(eventDataSource);
        return (T) new EventCalendarViewModelImpl(eventDataModel);
    }
}
