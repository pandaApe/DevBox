package com.hl.devbox.app;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.BugtagsOptions;
import com.morgoo.droidplugin.PluginHelper;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by whailong on 13/1/16.
 */
public class DevBoxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        

        setupShareSDK();

        setupDroidPlugin();

        setupBugTags();

    }

    private void setupDroidPlugin() {
        //这里必须在super.onCreate方法之后，顺序不能变
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
    }

    private void setupBugTags() {
        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).//是否获取位置
                trackingCrashLog(true).//是否收集crash
                trackingConsoleLog(true).//是否收集console log
                trackingUserSteps(true).//是否收集用户操作步骤
                versionName("1.0.1").//自定义版本名称
                versionCode(10).//自定义版本号
                build();

//        Bugtags.start("d57e9f0603a064754c5875c1a7a1fbd7", this, Bugtags.BTGInvocationEventBubble, options);
    }

    private void setupShareSDK() {
        ShareSDK.initSDK(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }

    @Override
    public void onTerminate() {
        ShareSDK.stopSDK(this);
        super.onTerminate();
    }
}
