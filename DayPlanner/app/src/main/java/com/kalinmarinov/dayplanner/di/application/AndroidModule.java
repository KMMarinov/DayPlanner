package com.kalinmarinov.dayplanner.di.application;

import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.providers.SchedulerProviderImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Module
public class AndroidModule {

    @Provides
    SchedulerProvider getSchedulerProvider() {
        return new SchedulerProviderImpl();
    }
}
