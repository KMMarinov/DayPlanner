package com.kalinmarinov.dayplanner.services;

import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public interface EventViewModelService {

    EventModelViewContainer convertToContainer(final Event event);

    Event convertToModel(final EventModelViewContainer eventModelViewContainer);
}
