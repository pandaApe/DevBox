package com.pa.devbox.app;

import android.app.Application;
import android.content.Context;

import com.morgoo.droidplugin.PluginHelper;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 1/5/16 11:39.
 * Email: whailong2010@gmail.com
 */
public class DevBoxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }

}
