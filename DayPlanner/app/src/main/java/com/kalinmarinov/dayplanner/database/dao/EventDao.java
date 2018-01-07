package com.kalinmarinov.dayplanner.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.kalinmarinov.dayplanner.database.models.EventEntity;
import io.reactivex.Flowable;

import java.util.Date;
import java.util.List;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
@Dao
public interface EventDao {

    @Query("SELECT * FROM events")
    Flowable<List<EventEntity>> findAll();

    @Query("SELECT * FROM events where id=:eventId")
    Flowable<EventEntity> findById(final int eventId);

    @Query("SELECT * FROM events where start_date >= :start AND start_date < :end")
    Flowable<List<EventEntity>> findByStartDateBetween(final Date start, final Date end);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(final EventEntity event);

    @Delete
    int delete(final EventEntity event);
}