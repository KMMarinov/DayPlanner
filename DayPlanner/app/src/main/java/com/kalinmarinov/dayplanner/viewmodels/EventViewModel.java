package com.kalinmarinov.dayplanner.viewmodels;

import com.kalinmarinov.dayplanner.models.Event;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public interface EventViewModel {

    Flowable<List<String>> getEventNames();

    void save(final Event event);
    
    void deleteEvent(final Event event);
}
