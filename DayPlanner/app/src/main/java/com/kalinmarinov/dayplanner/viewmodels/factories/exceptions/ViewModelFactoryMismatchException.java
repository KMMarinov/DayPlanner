package com.kalinmarinov.dayplanner.viewmodels.factories.exceptions;

/**
 * Created by Kalin.Marinov on 02.01.2018.
 */
public class ViewModelFactoryMismatchException extends RuntimeException {

    private static final long serialVersionUID = 6229209010676083297L;

    public ViewModelFactoryMismatchException(final Class<?> castClass, final Class<?> toClass) {
        super(String.format("Cannot convert class of type %s to %s", castClass, toClass));
    }
}
