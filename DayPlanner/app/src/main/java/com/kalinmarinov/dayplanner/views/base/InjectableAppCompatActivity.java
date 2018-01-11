package com.kalinmarinov.dayplanner.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.kalinmarinov.dayplanner.DayPlannerApplication;
import com.kalinmarinov.dayplanner.di.ApplicationComponent;
import com.kalinmarinov.dayplanner.di.ControllerComponent;
import com.kalinmarinov.dayplanner.di.DaggerControllerComponent;
import com.kalinmarinov.dayplanner.di.controller.ControllerModule;
import com.kalinmarinov.dayplanner.di.controller.ServiceModule;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class InjectableAppCompatActivity extends BaseAppCompatActivity {

    private ControllerComponent controllerComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ApplicationComponent applicationComponent = DayPlannerApplication.getDayPlannerApplication()
                .getApplicationComponent();

        controllerComponent = DaggerControllerComponent
                .builder()
                .applicationComponent(applicationComponent)
                .controllerModule(new ControllerModule(this))
                .serviceModule(new ServiceModule(this))
                .build();
    }

    protected ControllerComponent getControllerComponent() {
        return controllerComponent;
    }
}
