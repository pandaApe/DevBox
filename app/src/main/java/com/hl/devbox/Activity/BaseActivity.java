package com.hl.devbox.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.hl.devbox.App.DBApplication;
import com.hl.devbox.R;
import com.hl.devbox.utils.LogUtil;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected DBApplication application;
    protected Toolbar toolbar;
    private android.support.v7.app.ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.log(this, "---------------------OnCreate");

        this.initVariables();
        this.initViews(savedInstanceState);
        this.loadData();
        this.registerBordcast();
    }

    protected void initVariables() {
        this.context = getApplicationContext();
        this.application = (DBApplication) this.getApplication();
    }

    //The method setContentView: should be called in this method.
    protected void initViews(Bundle savedInstanceState) {
    }

    protected void loadData() {
    }

    protected void registerBordcast() {
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

    @Override
    protected void onResume() {
        LogUtil.log(this, "---------------------onResume");
        super.onResume();
//        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        LogUtil.log(this, "---------------------onPause");
        super.onPause();

//        Bugtags.onPause(this);
    }

    @Override
    protected void onStop() {
        LogUtil.log(this, "---------------------onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtil.log(this, "---------------------onDestroy");
        super.onDestroy();
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
        intent.setClass(this, cls);
        this.startActivity(intent);
    }

}
