package com.kalinmarinov.dayplanner.database.roomdatabases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.database.roomdatabases.typeconverters.DateConverters;
import com.kalinmarinov.dayplanner.models.Event;

import static com.kalinmarinov.dayplanner.database.roomdatabases.EventsDatabase.EVENTS_DATABASE_VERSION;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@Database(entities = Event.class,
        version = EVENTS_DATABASE_VERSION,
        exportSchema = false)
@TypeConverters(DateConverters.class)
public abstract class EventsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "event-database";

    static final int EVENTS_DATABASE_VERSION = 1;

    private static EventsDatabase INSTANCE;

    public abstract EventDao getEventDao();

    public static synchronized EventsDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), EventsDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }
}
