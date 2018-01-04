package com.kalinmarinov.dayplanner.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModel;
import com.kalinmarinov.dayplanner.viewmodels.SingleEventViewModelImpl;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class SingleEventViewModelFactory extends GenericViewModelFactory {

    public SingleEventViewModelFactory(final EventDataModel eventDataModel) {
        super(eventDataModel);
    }

    @Override
    public Class<?> getClassImpl() {
        return SingleEventViewModel.class;
    }

    @Override
    public <T extends ViewModel> T createViewModel(final EventDataModel eventDataModel) {
        return (T) new SingleEventViewModelImpl(eventDataModel);
    }
}
