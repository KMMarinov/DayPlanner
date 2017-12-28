package com.kalinmarinov.dayplanner.database.providers;

import android.arch.persistence.room.Room;
import com.kalinmarinov.dayplanner.DayPlannerApplication;
import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.database.roomdatabases.EventsDatabase;

/**
 * Created by Kalin.Marinov on 28.12.2017.
 */
public class EventDaoProviderImpl implements EventDaoProvider {

    @Override
    public EventDao getEventDao() {
        return Room
                .databaseBuilder(DayPlannerApplication.getContext(), EventsDatabase.class, EventsDatabase.DATABASE_NAME)
                .allowMainThreadQueries().build()
                .getEventDao();
    }
}
