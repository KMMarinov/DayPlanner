package com.kalinmarinov.dayplanner.views.containers;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class EventModelViewContainer {

    private int id;
    private String name;
    private DateContainer startDate;
    private DateContainer endDate;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateContainer getStartDate() {
        return startDate;
    }

    public void setStartDate(DateContainer startDate) {
        this.startDate = startDate;
    }

    public DateContainer getEndDate() {
        return endDate;
    }

    public void setEndDate(DateContainer endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class DateContainer {

        private String date;

        public DateContainer(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
