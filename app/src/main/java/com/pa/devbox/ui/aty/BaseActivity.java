package com.pa.devbox.ui.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.pa.devbox.R;
import com.pa.devbox.app.DevBoxApplication;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected DevBoxApplication application;
    protected Toolbar toolbar;
    private android.support.v7.app.ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initVariables();
        this.initViews(savedInstanceState);

    }

    protected void initVariables() {
        this.context = getApplicationContext();
        this.application = (DevBoxApplication) this.getApplication();
    }

    //The method setContentView: should be called in this method.
    void initViews(Bundle savedInstanceState) {
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mActionBar = getSupportActionBar();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    protected void setToolbarDisplayHomeAsUpEnabledAndClickListenner(boolean enabled, View.OnClickListener listener) {
        this.mActionBar.setDisplayHomeAsUpEnabled(enabled);
        toolbar.setNavigationOnClickListener(listener);
    }

    protected void setToolbarTitle(CharSequence text) {
        this.mActionBar.setTitle(text);
    }

    public void showSnackbar(String msg) {
        View rootView = findViewById(android.R.id.content);
        if (rootView != null)
            Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    }

    protected void showActivity(Intent intent) {
        this.startActivity(intent);
    }

    protected void showActivity(Class<?> cls) {
        Intent intent = new Intent();
        this.showActivity(intent);
    }

}
