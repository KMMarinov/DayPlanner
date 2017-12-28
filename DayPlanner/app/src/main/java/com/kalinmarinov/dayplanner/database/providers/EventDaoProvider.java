package com.kalinmarinov.dayplanner.database.providers;

import com.kalinmarinov.dayplanner.database.dao.EventDao;

/**
 * Created by Kalin.Marinov on 28.12.2017.
 */
public interface EventDaoProvider {

    EventDao getEventDao();
}
