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
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        //TODO: check the modelClass and throw exception if it doesn't match
        final EventDaoProvider eventDaoProvider = new EventDaoProviderImpl();
        final EventDataSource eventDataSource = new EventDataSourceImpl(eventDaoProvider.getEventDao());
        final EventDataModel eventDataModel = new EventDataModelImpl(eventDataSource);
        return (T) new SingleEventViewModelImpl(eventDataModel);
    }
}
