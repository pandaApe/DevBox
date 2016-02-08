package com.devbox.ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bugtags.library.Bugtags;
import com.devbox.DBApplication;
import com.devbox.R;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected DBApplication application;
    protected Toolbar toolbar;
    private android.support.v7.app.ActionBar mActionBar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();
        this.application = (DBApplication) this.getApplication();

//        initContentView(R.layout.activity_base);
    }


    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();

        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_corrdinatorlayout);
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

//        LayoutInflater.from(this).inflate(layoutResID, coordinatorLayout, true);

        initToolbar();
    }

    private void initToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mActionBar = getSupportActionBar();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
//        coordinatorLayout.addView(view);

        initToolbar();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initToolbar();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    protected void setToolbarDisplayHomeAsUpEnabledAndClickListenner(boolean enabled, View.OnClickListener listener) {
        this.mActionBar.setDisplayHomeAsUpEnabled(enabled);
        toolbar.setNavigationOnClickListener(listener);
    }


    protected void setToolbarTitle(CharSequence text) {

        this.mActionBar.setTitle(text);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

}
