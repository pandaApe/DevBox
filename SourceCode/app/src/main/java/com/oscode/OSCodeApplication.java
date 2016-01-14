package com.oscode;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.morgoo.droidplugin.PluginHelper;
import com.oscode.model.OSCodeLib;

/**
 * Created by whailong on 13/1/16.
 */
public class OSCodeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(OSCodeLib.class);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "FCVowRhDwwwHkNKPxAz0rb7L-gzGzoHsz", "3g2B0rtN2ADbU5lpErTASgMq");
        //这里必须在super.onCreate方法之后，顺序不能变
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }
}
