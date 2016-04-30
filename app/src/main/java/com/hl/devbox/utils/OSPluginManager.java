package com.hl.devbox.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;

import com.hl.devbox.domain.entity.ApkItem;

import java.util.List;

public class OSPluginManager {

    private Activity mActivity;

    public OSPluginManager(Activity activity) {
        mActivity = activity;
    }

    /**
     * 安装Apk, 耗时较长, 需要使用异步线程
     *
     * @param item Apk项
     * @return [0:成功, 1:已安装, -1:连接失败, -2:权限不足, -3:安装失败]
     */
    public String installApk(final ApkItem item) {
        if (!com.morgoo.droidplugin.pm.PluginManager.getInstance().isConnected()) {
            return "连接失败"; // 连接失败
        }

        if (isApkInstall(item)) {
            return "已安装"; // 已安装
        }

        try {
            int result = com.morgoo.droidplugin.pm.PluginManager.getInstance().installPackage(item.apkFilePath, 0);
            boolean isRequestPermission = (result == com.morgoo.droidplugin.pm.PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION);
            if (isRequestPermission) {
                return "权限不足";
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            return "安装失败";
        }

        return "成功";
    }

    // Apk是否安装
    private boolean isApkInstall(ApkItem apkItem) {
        PackageInfo info = null;
        try {
            info = com.morgoo.droidplugin.pm.PluginManager.getInstance().getPackageInfo(apkItem.getPackageInfo().packageName, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return info != null;
    }

    // 打开Apk
    public void openApk(final ApkItem item) {

//        if (!isApkInstall(item))
//            LogUtils.log(installApk(item));

        PackageManager pm = mActivity.getPackageManager();

        String pn = item.getPackageInfo().packageName;
        Intent intent = pm.getLaunchIntentForPackage(item.getPackageInfo().packageName);

        //如果返回的intent为空
        if (intent == null) {
//findActivitiesForPackage是备选方案,当getLaunchIntentForPackage没有正确得到intent时
            ResolveInfo r = findActivitiesForPackage(pm, item.getPackageInfo().packageName);
            if (r != null) {
                intent = new Intent();
                intent.setComponent(new ComponentName(item.getPackageInfo().packageName, r.activityInfo.name));
            }
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        item.context.startActivity(intent);
    }

    private static ResolveInfo findActivitiesForPackage(PackageManager packageManager,
                                                        String packageName) {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        boolean isfinded = false;
        //通过CATEGORY_DEFAULT参数查找应用程序默认的Activity
        //为什么不是CATEGORY_MAIN参数，原因上面已经说了
        mainIntent.addCategory(Intent.CATEGORY_DEFAULT);
        final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
        ResolveInfo info = null;
        if (apps != null) {
            // Find all activities that match the packageName
            int count = apps.size();
            for (int i = 0; i < count; i++) {
                info = apps.get(i);
                final ActivityInfo activityInfo = info.activityInfo;
                if (packageName.equals(activityInfo.packageName)) {
                    isfinded = true;
                    break;//只要一个
                }
            }
        }
        if (!isfinded) {
            return null;
        }
        return info;
    }
}
