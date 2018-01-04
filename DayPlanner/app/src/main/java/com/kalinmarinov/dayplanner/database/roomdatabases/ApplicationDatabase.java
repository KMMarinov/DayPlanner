package com.kalinmarinov.dayplanner.database.roomdatabases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.kalinmarinov.dayplanner.database.dao.EventDao;
import com.kalinmarinov.dayplanner.database.roomdatabases.typeconverters.DateConverters;
import com.kalinmarinov.dayplanner.models.Event;

import static com.kalinmarinov.dayplanner.database.roomdatabases.ApplicationDatabase.DATABASE_VERSION;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@Database(entities = Event.class,
        version = DATABASE_VERSION,
        exportSchema = false)
@TypeConverters(DateConverters.class)
public abstract class ApplicationDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "application-database";

    static final int DATABASE_VERSION = 1;

    private static ApplicationDatabase INSTANCE;

    public abstract EventDao getEventDao();

    public static synchronized ApplicationDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }
}
