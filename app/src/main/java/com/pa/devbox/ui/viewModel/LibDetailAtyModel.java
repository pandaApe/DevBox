package com.pa.devbox.ui.viewModel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.pa.devbox.BR;
import com.pa.devbox.R;
import com.pa.devbox.domain.delegate.FileDownloadCallback;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.aty.LibDetailActivity;
import com.pa.devbox.ui.modle.LibDetailModel;
import com.pa.devbox.util.FileUtils;

import java.io.File;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:22.
 * Email: whailong2010@gmail.com
 */
public class LibDetailAtyModel extends BaseObservable implements FileDownloadCallback, LibDetailActivity.PermissionRequestCallback {

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
    String btnText;

    private final int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 123;

    private LibDetailModel libDetailModel;

    public void parseArguments(Intent intent) {

        if (intent != null)
            library = (Library) intent.getSerializableExtra(SELECTEDITEM);

        if (library != null) {
            this.setBtnText(getApkSizeStr());
        }

    }

    public void circularBtnOnClick(View view) {

        int hasWriteContactsPermission = context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_STORAGE_PERMISSIONS);
            return;
        }
        download();
    }

    private void download() {

        String savePath = FileUtils.getSdCardPath()
                + "DevBox" + File.separator
                + library.getName() + ".apk";

        if (new File(savePath).exists()) {

        } else {
            libDetailModel.download(library.getApk().getUrl(), savePath);
        }
    }

    public LibDetailAtyModel(LibDetailActivity context) {
        this.context = context;
        this.context.setPermissionRequestCallback(this);
        this.libDetailModel = new LibDetailModel();
        this.libDetailModel.setFileDownloadCallback(this);
        this.setCircularProgress(0);

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
    public void onSuccess(File file) {
// TODO: 10/5/16 Need to install apk to plugin system in sub thread

        Log.e("-->", "Seccess" + file.getPath());
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("-->", "onFailure");
    }

    @Override
    public void onProgress(long bytesRead, long contentLength, boolean done) {

        int percentage = (int) (1.0f * bytesRead / contentLength * 100);
        Log.e("-->", "onProgress-" +percentage);
        if (done)
            this.setCircularProgress(100);
        else
            this.setCircularProgress(percentage);
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
}
