package com.devbox;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.devbox.model.CodeLib;
import com.devbox.model.CodeType;
import com.morgoo.droidplugin.PluginHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by whailong on 13/1/16.
 */
public class DBApplication extends Application {

//    private

    @Override
    public void onCreate() {
        super.onCreate();

        AVObject.registerSubclass(CodeLib.class);
        AVObject.registerSubclass(CodeType.class);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "FCVowRhDwwwHkNKPxAz0rb7L-gzGzoHsz", "3g2B0rtN2ADbU5lpErTASgMq");
        AVAnalytics.enableCrashReport(this, true);

        //这里必须在super.onCreate方法之后，顺序不能变
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());
        initImageLoader(getBaseContext());
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

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(80 * 1024 * 1024); // 80 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
