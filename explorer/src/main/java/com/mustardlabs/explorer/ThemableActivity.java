package com.dnielfe.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.dnielfe.manager.settings.Settings;

public abstract class ThemableActivity extends ActionBarActivity {

    private int mCurrentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // get default preferences at start - we need this for setting the theme
        Settings.updatePreferences(this);

        mCurrentTheme = Settings.mTheme;
        setTheme(mCurrentTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCurrentTheme != Settings.mTheme) {
            restart();
        }
    }

    protected void restart() {
        final Bundle outState = new Bundle();
        onSaveInstanceState(outState);
        final Intent intent = new Intent(this, getClass());
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
