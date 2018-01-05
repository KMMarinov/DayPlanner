package com.kalinmarinov.dayplanner.models.builders;

import com.kalinmarinov.dayplanner.models.Event;

import java.util.Date;

/**
 * Created by Kalin.Marinov on 29.12.2017.
 */
public class EventBuilder {

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String description;

    public EventBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Event build() {
        final Event event = new Event();
        event.setId(id);
        event.setName(name);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        return event;
    }
}
