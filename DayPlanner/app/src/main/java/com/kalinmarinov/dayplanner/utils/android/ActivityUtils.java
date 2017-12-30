package com.kalinmarinov.dayplanner.utils.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Kalin.Marinov on 30.12.2017.
 */
public final class ActivityUtils {

    private ActivityUtils() {
    }

    public static void startActivityWithExtra(final Context context, final Class<?> clazz, final String key,
                                              final int value) {
        final Intent intent = new Intent(context, clazz);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    public static int getExtra(final Intent intent, final String key) {
        final Bundle extras = intent.getExtras();
        return extras != null ? extras.getInt(key) : 0;
    }
}
