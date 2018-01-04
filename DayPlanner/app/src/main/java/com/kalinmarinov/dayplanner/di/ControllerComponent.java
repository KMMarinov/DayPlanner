package com.kalinmarinov.dayplanner.di;

import com.kalinmarinov.dayplanner.di.controller.ControllerModule;
import com.kalinmarinov.dayplanner.di.controller.ServiceModule;
import com.kalinmarinov.dayplanner.views.CreateEditEventActivity;
import com.kalinmarinov.dayplanner.views.EventActivity;
import com.kalinmarinov.dayplanner.views.ShowEventActivity;
import dagger.Component;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
@Component(dependencies = ApplicationComponent.class,
        modules = {ControllerModule.class, ServiceModule.class})
public interface ControllerComponent {

    void inject(final CreateEditEventActivity createEditEventActivity);

    void inject(final ShowEventActivity showEventActivity);

    void inject(final EventActivity createEditEventActivity);
}
