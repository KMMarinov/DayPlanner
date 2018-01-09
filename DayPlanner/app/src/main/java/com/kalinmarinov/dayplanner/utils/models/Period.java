package com.kalinmarinov.dayplanner.utils.models;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public class Period {

    private final int start;
    private final int end;

    public Period(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
