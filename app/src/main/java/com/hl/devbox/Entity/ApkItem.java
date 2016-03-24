package com.hl.devbox.Entity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.hl.devbox.utils.Config;
import com.hl.devbox.utils.LogUtil;
import java.io.File;

/**
 * Apk项的条目
 *
 * @author wangchenlong
 */
public class ApkItem {

    public Drawable icon; // 图标
    public CharSequence title; // 标题
    public String versionName; // 版本名称
    public int versionCode; // 版本号
    public String apkFilePath; // Apk路径

    public PackageInfo getPackageInfo() {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_SIGNATURES);
        pi.applicationInfo.sourceDir = apkFilePath;
        pi.applicationInfo.publicSourceDir = apkFilePath;

        try {
            icon = pm.getApplicationIcon(pi.applicationInfo);
        } catch (Exception e) {
            icon = pm.getDefaultActivityIcon();
        }
        try {
            title = pm.getApplicationLabel(pi.applicationInfo);
        } catch (Exception e) {
            title = pi.packageName;
        }
        versionName = pi.versionName;
        versionCode = pi.versionCode;
        packageInfo = pi;
        return packageInfo;
    }

    private PackageInfo packageInfo; // 包信息
    public Context context;

    public ApkItem(Context context, Library lib) {
        this.context = context;

        String apkName = lib.getName().replace(" ", "") + ".apk";
        String path = Config.AppFolder + apkName;
        apkFilePath = path;

    }

    public boolean exists() {
        return new File(apkFilePath).exists();
    }
}