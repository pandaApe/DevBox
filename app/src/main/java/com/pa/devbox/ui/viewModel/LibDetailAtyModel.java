package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pa.devbox.BR;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.aty.LibDetailActivity;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:22.
 * Email: whailong2010@gmail.com
 */
public class LibDetailAtyModel extends BaseObservable {

    private LibDetailActivity context;

    public LibDetailAtyModel(LibDetailActivity context) {
        this.context = context;
    }

    @Bindable
    Library library;

    @Bindable
    String lastCommitDate;

    @Bindable
    String lastCommitMsg;

    public String getDescription() {
        return library.getEnDescription();
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

    public void setLastCommitDate(String lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
        notifyPropertyChanged(BR.lastCommitDate);
    }

    public String getLastCommitMsg() {
        return lastCommitMsg;
    }

    public void setLastCommitMsg(String lastCommitMsg) {
        this.lastCommitMsg = lastCommitMsg;
        notifyPropertyChanged(BR.lastCommitMsg);
    }
/*
     ImageView ivHeader;

    CollapsingToolbarLayout collapsingToolbar;

    TextView tvLibDiscription;

    TextView tvVersion;

    CircularProgressButton btnDownload;

    TextView tvGithubAddress;

    CardView cvGithubAddress;

    TextView tvLastUpdateDate;

    TextView tvLastUpdateMsg;

    TextView tvAuthor;

    TextView tvLicense;



    */


}
