package com.kalinmarinov.dayplanner.viewmodels;

import com.kalinmarinov.dayplanner.views.containers.EventModelViewContainer;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Kalin.Marinov on 27.12.2017.
 */
public interface SingleEventViewModel {

    Single<EventModelViewContainer> getEventSingle(final int eventId);

    Flowable<EventModelViewContainer> getEvent(final int eventId);

    Completable saveEvent(final EventModelViewContainer eventModelViewContainer);

    Completable deleteEvent();
}
