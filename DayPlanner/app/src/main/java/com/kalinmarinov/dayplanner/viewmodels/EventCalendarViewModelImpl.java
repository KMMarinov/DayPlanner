package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public class EventCalendarViewModelImpl extends ViewModel implements EventCalendarViewModel {

    private final EventDataModel eventDataModel;

    public EventCalendarViewModelImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }
}
