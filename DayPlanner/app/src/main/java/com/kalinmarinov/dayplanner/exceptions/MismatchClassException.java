package com.kalinmarinov.dayplanner.exceptions;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public class MismatchClassException extends RuntimeException {

    private static final long serialVersionUID = 6359286149338399211L;

    public MismatchClassException(final Class<?> received, final Class<?> expected) {
        super(String.format("Expected class %s, but received %s", expected.getSimpleName(), received.getSimpleName()));
    }
}
