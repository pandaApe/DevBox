package com.pa.devbox.ui.viewModel;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.pa.devbox.BR;
import com.pa.devbox.R;
import com.pa.devbox.domain.delegate.FileDownloadCallback;
import com.pa.devbox.domain.delegate.LastCommitInfoCallback;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.aty.LibDetailActivity;
import com.pa.devbox.ui.modle.LibDetailModel;
import com.pa.devbox.util.PersistenceUtils;
import com.pa.devbox.util.PluginManager;

import java.io.File;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:22.
 * Email: whailong2010@gmail.com
 */
public class LibDetailAtyModel extends BaseObservable implements FileDownloadCallback, LibDetailActivity.PermissionRequestCallback, LibDetailActivity.OptionsItemSelectedCallback, LastCommitInfoCallback {

    public static final String SELECTEDITEM = "selectedItem";

    private LibDetailActivity context;

    @Bindable
    Library library;
    @Bindable
    int circularProgress;
    @Bindable
    String lastCommitDate;
    @Bindable
    String lastCommitMsg;
    @Bindable
    boolean indeterminateProgressMode;
    @Bindable
    String btnText;

    private String savePath;
    private String appFolder;

    private final int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 123;

    private LibDetailModel libDetailModel;

    public LibDetailAtyModel(LibDetailActivity context) {
        this.context = context;
        this.context.setPermissionRequestCallback(this);
        this.context.setOptionsItemSelectedCallback(this);
        this.setCircularProgress(0);
    }

    public void parseArguments(Intent intent) {

        if (intent != null)
            library = (Library) intent.getSerializableExtra(SELECTEDITEM);

        this.setBtnText(getApkSizeStr());

        appFolder = PersistenceUtils.getSdCardPath()
                + "DevBox";


        savePath = appFolder + File.separator
                + library.getName() + ".apk";

        if (new File(savePath).exists())
            this.setCircularProgress(100);

        this.libDetailModel = new LibDetailModel(library);
        this.libDetailModel.setFileDownloadCallback(this);
        this.libDetailModel.setLastCommitInfoCallback(this);

    }

    public void githubAddressOnClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.library.getGithubAddress()));
        context.startActivity(browserIntent);
    }

    @TargetApi(23)
    public void circularBtnOnClick(View view) {

        if (android.os.Build.VERSION.SDK_INT >= 23) {     // Do something for M and above versions } else{     // do something for phones running an SDK before froyo }

            int hasWriteContactsPermission = context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_STORAGE_PERMISSIONS);
                return;
            }
        }
        new File(appFolder).mkdirs();
        File apkFile = new File(savePath);
        if (!apkFile.exists()) {
            download();
        } else {
            new PluginManager(context).openApk(apkFile);
        }
    }

    private void download() {

        libDetailModel.download(library.getApk().getUrl(), savePath);
        this.setIndeterminateProgressMode(true);
    }

    @Override
    public void onSuccess(File file) {

        PluginManager pm = new PluginManager(context);
        pm.installApk(file);

    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("-->", "onFailure");
        this.setCircularProgress(-1);
    }

    @Override
    public void onProgress(long bytesRead, long contentLength, boolean done) {

        int percentage = (int) (1.0f * bytesRead / contentLength * 100);
        Log.e("onProgress", ": " + percentage);
        if (done)
            this.setCircularProgress(100);
        else if (percentage != 0) {
            this.setCircularProgress(percentage);
            this.setIndeterminateProgressMode(false);
        }
    }


    public String getApkSizeStr() {

        double size = this.library.getApk().getMetaData().getSize() / 1000.0 / 1000.0;
        double sizeFinal = Math.round(size * 100) / 100.0;
        return context.getString(R.string.download) + sizeFinal + "MB)";

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_STORAGE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    download();
//                else
//                    showSnackbar(getString(R.string.refusePermission));
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(String name, String msg) {
        this.setLastCommitDate(name);
        this.setLastCommitMsg(msg);
    }


    public boolean isIndeterminateProgressMode() {
        return indeterminateProgressMode;
    }

    public void setIndeterminateProgressMode(boolean indeterminateProgressMode) {
        this.indeterminateProgressMode = indeterminateProgressMode;
        notifyPropertyChanged(BR.indeterminateProgressMode);
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
        notifyPropertyChanged(BR.btnText);
    }

    public String getBtnText() {
        return btnText;
    }

    public void setLibrary(Library library) {
        this.library = library;
        notifyPropertyChanged(BR.library);
    }

    public void setLastCommitDate(String lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
        notifyPropertyChanged(BR.lastCommitDate);
    }

    public void setLastCommitMsg(String lastCommitMsg) {
        this.lastCommitMsg = lastCommitMsg;
        notifyPropertyChanged(BR.lastCommitMsg);
    }

    public int getCircularProgress() {
        return circularProgress;
    }

    public void setCircularProgress(int circularProgress) {
        this.circularProgress = circularProgress;
        notifyPropertyChanged(BR.circularProgress);
    }

    public String getLastCommitMsg() {
        return lastCommitMsg;
    }

    public String getDescription() {
        return library.getEnDescription();
    }

    public String getTitle() {
        return library.getName();
    }

    public String getMinSdkVersion() {
        return library.getMinSdkVersion();
    }

    public String getGithubAddress() {
        return library.getGithubAddress();
    }

    public String getAuthor() {
        return library.getAuthor();
    }

    public String getLicense() {
        return library.getLicense();
    }

    public String getLastCommitDate() {
        return lastCommitDate;
    }

    @Override
    public void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:

                ShareSDK.initSDK(this.context);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("发现一个很不错的控件：" + this.library.getGithubAddress());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
//                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
//                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(this.context);

        }
    }
}
