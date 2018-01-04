package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventsViewModelImpl;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class EventsViewModelFactory extends GenericViewModelFactory {

    public EventsViewModelFactory(final EventDataModel eventDataModel) {
        super(eventDataModel);
    }

    @Override
    public Class<?> getClassImpl() {
        return EventsViewModel.class;
    }

    @Override
    public <T extends ViewModel> T createViewModel(final EventDataModel eventDataModel) {
        return (T) new EventsViewModelImpl(eventDataModel);
    }
}
