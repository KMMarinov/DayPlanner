package com.kalinmarinov.dayplanner.di.qualifiers;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ViewModelProvided {
}
