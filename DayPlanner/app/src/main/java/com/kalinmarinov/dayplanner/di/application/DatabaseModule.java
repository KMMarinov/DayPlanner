package com.kalinmarinov.dayplanner.di.application;

import android.content.Context;
import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.database.datasources.EventDataSource;
import com.kalinmarinov.dayplanner.database.datasources.EventDataSourceImpl;
import com.kalinmarinov.dayplanner.database.roomdatabases.ApplicationDatabase;
import com.kalinmarinov.dayplanner.datamodels.EventDataModel;
import com.kalinmarinov.dayplanner.datamodels.EventDataModelImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kalin.Marinov on 03.01.2018.
 */
@Module
public class DatabaseModule {

    @Provides
    ApplicationDatabase getApplicationDatabase(final Context context) {
        return ApplicationDatabase.getInstance(context);
    }

    @Provides
    EventDao getEventDao(final ApplicationDatabase applicationDatabase) {
        return applicationDatabase.getEventDao();
    }

    @Provides
    EventDataSource getEventDataSource(final EventDao eventDao) {
        return new EventDataSourceImpl(eventDao);
    }

    @Provides
    EventDataModel getEventDataModel(final EventDataSource eventDataSource) {
        return new EventDataModelImpl(eventDataSource);
    }
}
