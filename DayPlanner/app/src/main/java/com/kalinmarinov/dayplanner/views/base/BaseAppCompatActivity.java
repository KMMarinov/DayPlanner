package com.kalinmarinov.dayplanner.views.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kalin.Marinov on 08.01.2018.
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    protected void showError(final Throwable throwable) {
        Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    protected void addDisposable(final Disposable... disposables) {
        getDisposable().addAll(disposables);
    }

    private CompositeDisposable getDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }
}
