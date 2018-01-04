package com.kalinmarinov.dayplanner.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.kalinmarinov.dayplanner.DayPlannerApplication;
import com.kalinmarinov.dayplanner.di.ApplicationComponent;
import com.kalinmarinov.dayplanner.di.ControllerComponent;
import com.kalinmarinov.dayplanner.di.DaggerControllerComponent;
import com.kalinmarinov.dayplanner.di.controller.ControllerModule;

/**
 * Created by Kalin.Marinov on 04.01.2018.
 */
public class InjectableAppCompatActivity extends AppCompatActivity {

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
                .build();
    }

    protected ControllerComponent getControllerComponent() {
        return controllerComponent;
    }
}
