package com.devbox.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;

import com.devbox.model.ApkItem;

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
        PackageManager pm = mActivity.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(item.getPackageInfo().packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        item.context.startActivity(intent);
    }
}
