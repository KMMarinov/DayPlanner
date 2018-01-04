package com.kalinmarinov.dayplanner.di;

import android.content.Context;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.di.application.ApplicationModule;
import com.kalinmarinov.dayplanner.di.application.DatabaseModule;
import com.kalinmarinov.dayplanner.providers.SchedulerProvider;
import dagger.Component;

/**
 * Created by Kalin.Marinov on 03.01.2018.
 */
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    Context getApplication();

    EventDataModel getEventDataModel();

    SchedulerProvider getSchedulerProvider();
}
