package com.kalinmarinov.dayplanner.di.application;

import android.app.Application;
import android.content.Context;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import com.kalinmarinov.dayplanner.providers.SchedulerProviderImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 03.01.2018.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }

    @Provides
    Context getContext() {
        return application;
    }

    @Provides
    SchedulerProvider getSchedulerProvider() {
        return new SchedulerProviderImpl();
    }
}
