package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModel;
import com.kalinmarinov.dayplanner.viewmodels.EventCalendarViewModelImpl;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventCalendarViewModelFactory extends GenericViewModelFactory {

    EventCalendarViewModelFactory(final EventDataModel eventDataModel) {
        super(eventDataModel);
    }

    @Override
    public Class<?> getClassImpl() {
        return EventCalendarViewModel.class;
    }

    @Override
    public <T extends ViewModel> T createViewModel(final EventDataModel eventDataModel) {
        return (T) new EventCalendarViewModelImpl(eventDataModel);
    }
}
