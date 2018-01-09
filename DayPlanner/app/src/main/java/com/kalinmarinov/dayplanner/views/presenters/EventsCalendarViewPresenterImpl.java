package com.kalinmarinov.dayplanner.views.presenters;

import android.app.Activity;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.kalinmarinov.dayplanner.exceptions.MismatchClassException;
import com.kalinmarinov.dayplanner.models.Event;
import com.kalinmarinov.dayplanner.utils.Constants;
import com.kalinmarinov.dayplanner.types.CalendarPeriodType;
import com.kalinmarinov.dayplanner.views.EventsCalendarActivity;
import com.kalinmarinov.dayplanner.views.adapters.EventDayItemGridAdapter;
import com.kalinmarinov.dayplanner.views.adapters.EventMonthItemGridAdapter;
import com.kalinmarinov.dayplanner.views.adapters.EventWeekdayItemGridAdapter;
import com.kalinmarinov.dayplanner.views.containers.EventCalendarContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public class EventsCalendarViewPresenterImpl implements EventsCalendarViewPresenter {

    private static final int NUM_COLUMNS_WEEK = 2;
    private static final int NUM_COLUMNS_DAY = 1;

    private final EventsCalendarActivity eventsCalendarActivity;
    private final Map<CalendarPeriodType, Consumer<List<Event>>> eventHandlers;

    public EventsCalendarViewPresenterImpl(final Activity activity) {
        if (!EventsCalendarActivity.class.isAssignableFrom(activity.getClass())) {
            throw new MismatchClassException(activity.getClass(), EventsCalendarActivity.class);
        }
        eventsCalendarActivity = (EventsCalendarActivity) activity;

        // Init handlers
        eventHandlers = new HashMap<>();
        eventHandlers.put(CalendarPeriodType.MONTH, this::monthEventHandler);
        eventHandlers.put(CalendarPeriodType.WEEK, this::weekEventHandler);
        eventHandlers.put(CalendarPeriodType.DAY, this::dayEventHandler);
    }

    @Override
    public void handleCalendar(final EventCalendarContainer eventCalendarContainer) {
        final CalendarPeriodType calendarPeriodType = eventCalendarContainer.getCalendarPeriodType();
        final List<Event> events = eventCalendarContainer.getEvents();
        final Consumer<List<Event>> listConsumer = eventHandlers.get(calendarPeriodType);
        listConsumer.accept(events);
    }

    private void monthEventHandler(final List<Event> events) {
        getEventsCalendarGridView().setNumColumns(Constants.DAYS_IN_WEEK);
        final ListAdapter customListAdapter = new EventMonthItemGridAdapter(eventsCalendarActivity, events);
        getEventsCalendarGridView().setAdapter(customListAdapter);
    }

    private void weekEventHandler(final List<Event> events) {
        getEventsCalendarGridView().setNumColumns(NUM_COLUMNS_WEEK);
        final ListAdapter customListAdapter = new EventWeekdayItemGridAdapter(eventsCalendarActivity, events);
        getEventsCalendarGridView().setAdapter(customListAdapter);
    }

    private void dayEventHandler(final List<Event> events) {
        getEventsCalendarGridView().setNumColumns(NUM_COLUMNS_DAY);
        final ListAdapter customListAdapter = new EventDayItemGridAdapter(eventsCalendarActivity, events);
        getEventsCalendarGridView().setAdapter(customListAdapter);
    }

    private GridView getEventsCalendarGridView() {
        return eventsCalendarActivity.getEventsCalendarGridView();
    }
}
