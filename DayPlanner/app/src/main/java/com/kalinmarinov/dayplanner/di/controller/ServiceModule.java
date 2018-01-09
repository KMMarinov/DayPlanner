package com.kalinmarinov.dayplanner.di.controller;

import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.services.EventCalendarService;
import com.kalinmarinov.dayplanner.services.EventCalendarServiceImpl;
import com.kalinmarinov.dayplanner.services.EventViewModelService;
import com.kalinmarinov.dayplanner.services.EventViewModelServiceImpl;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParser;
import com.kalinmarinov.dayplanner.viewmodels.parsers.EventsCalendarViewModelParserImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Module
public class ServiceModule {

    @Provides
    EventViewModelService getEventViewModelService() {
        return new EventViewModelServiceImpl();
    }

    @Provides
    EventCalendarService getEventCalendarService(final EventDataModel eventDataModel) {
        return new EventCalendarServiceImpl(eventDataModel);
    }

    @Provides
    EventsCalendarViewModelParser getEventsCalendarViewModelParser() {
        return new EventsCalendarViewModelParserImpl();
    }
}
