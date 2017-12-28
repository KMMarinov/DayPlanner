package com.kalinmarinov.dayplanner.viewmodels;

import android.arch.lifecycle.ViewModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.models.builders.EventBuilder;
import com.kalinmarinov.dayplanner.utils.DateUtils;
import io.reactivex.Flowable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public class EventViewModelImpl extends ViewModel implements EventViewModel {

    private final EventDataModel eventDataModel;

    public EventViewModelImpl(final EventDataModel eventDataModel) {
        this.eventDataModel = eventDataModel;
    }

    public Flowable<List<String>> getEventNames() {
        return eventDataModel.getEvents()
                .map(events -> events.stream()
                        .map(Event::getName)
                        .collect(Collectors.toList())
                );
    }

    @Override
    public void save(final Event event) {
        eventDataModel.saveEvent(event);
    }

    @Override
    public void saveEvent(final String name, final String description, final String startDate, final String endDate) {
        final Date parsedStartDate = DateUtils.parseDate(startDate);
        final Date parsedEndDate = DateUtils.parseDate(endDate);
        final Event newEvent = new EventBuilder()
                .setDescription(description)
                .setName(name)
                .setStartDate(parsedStartDate)
                .setEndDate(parsedEndDate)
                .build();
        //TODO: on complete - save or error has occurred
        eventDataModel.saveEvent(newEvent);
    }

    public void deleteEvent(final Event event) {
        eventDataModel.deleteEvent(event);
    }
}
