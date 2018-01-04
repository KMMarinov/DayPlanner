package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.DateUtils;
import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class EventViewModelServiceImpl implements EventViewModelService {

    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

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
        final Event event = new Event();
        event.setId(eventModelViewContainer.getId());
        event.setName(eventModelViewContainer.getName());
        event.setDescription(eventModelViewContainer.getDescription());
        final Date startDate = parseDate(eventModelViewContainer.getStartDate(), "Invalid Start Date");
        event.setStartDate(startDate);
        final Date endDate = parseDate(eventModelViewContainer.getEndDate(), "Invalid End Date");
        event.setEndDate(endDate);
        return event;
    }

    private EventModelViewContainer.DateContainer formatDate(final Date date) {
        final String dateString = DateUtils.formatDate(date, DEFAULT_DATE_FORMAT);
        final EventModelViewContainer.DateContainer dateContainer = new EventModelViewContainer.DateContainer(
                dateString);
        return dateContainer;
    }

    private Date parseDate(final EventModelViewContainer.DateContainer dateContainer, final String onErrorMessage) {
        try {
            return DateUtils.parseDate(dateContainer.getDate(), DEFAULT_DATE_FORMAT);
        } catch (final ParseException e) {
            throw new IllegalArgumentException(onErrorMessage);
        }
    }
}
