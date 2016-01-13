package com.oscode;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by whailong on 13/1/16.
 */
public class OSCodeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "FCVowRhDwwwHkNKPxAz0rb7L-gzGzoHsz", "3g2B0rtN2ADbU5lpErTASgMq");
    }
}
