package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.models.builders.EventBuilder;
import com.kalinmarinov.dayplanner.utils.DateUtils;
import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class EventViewModelServiceImpl implements EventViewModelService {

    private static final String DEFAULT_DATE_FORMAT = "dd'D-'HH'H'";

    @Override
    public EventModelViewContainer convertToContainer(final Event event) {
        final EventModelViewContainer eventModelViewContainer = new EventModelViewContainer();
        eventModelViewContainer.setId(event.getId());
        eventModelViewContainer.setName(event.getName());
        eventModelViewContainer.setDescription(event.getDescription());
        final EventModelViewContainer.DateContainer startDate = formatDate(event.getStartDate());
        eventModelViewContainer.setStartDate(startDate);
        final EventModelViewContainer.DateContainer endDate = formatDate(event.getEndDate());
        eventModelViewContainer.setEndDate(endDate);
        return eventModelViewContainer;
    }

    @Override
    public Event convertToModel(final EventModelViewContainer eventModelViewContainer) {
        final Date startDate = parseDate(eventModelViewContainer.getStartDate(), "Invalid Start Date");
        final Date endDate = parseDate(eventModelViewContainer.getEndDate(), "Invalid End Date");
        final Event event = new EventBuilder()
                .setId(eventModelViewContainer.getId())
                .setName(eventModelViewContainer.getName())
                .setDescription(eventModelViewContainer.getDescription())
                .setStartDate(startDate)
                .setEndDate(endDate)
                .build();
        return event;
    }

    private static EventModelViewContainer.DateContainer formatDate(final Date date) {
        final String dateString = DateUtils.formatDate(date, DEFAULT_DATE_FORMAT);
        return new EventModelViewContainer.DateContainer(dateString);
    }

    private static Date parseDate(final EventModelViewContainer.DateContainer dateContainer,
                                  final String onErrorMessage) {
        try {
            return DateUtils.parseDate(dateContainer.getDate(), DEFAULT_DATE_FORMAT);
        } catch (final ParseException e) {
            throw new IllegalArgumentException(onErrorMessage);
        }
    }
}
