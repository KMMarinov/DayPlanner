package com.kalinmarinov.dayplanner.di.controller;

import com.kalinmarinov.dayplanner.services.EventViewModelService;
import com.kalinmarinov.dayplanner.services.EventViewModelServiceImpl;
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
}
