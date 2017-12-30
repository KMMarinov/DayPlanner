package com.kalinmarinov.dayplanner.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@Dao
public interface EventDao {

    @Query("SELECT * FROM events")
    Flowable<List<Event>> findAll();

    @Query("SELECT * FROM events where id=:eventId")
    Flowable<Event> findById(final int eventId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(final Event event);

    @Delete
    void delete(final Event event);
}