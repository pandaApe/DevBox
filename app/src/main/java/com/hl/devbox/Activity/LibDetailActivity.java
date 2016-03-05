package com.hl.devbox.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.dd.CircularProgressButton;
import com.hl.devbox.Engine.AppException;
import com.hl.devbox.Engine.GetLastCommitInfoCallback;
import com.hl.devbox.Engine.WebActionImpl;
import com.hl.devbox.Entity.ApkItem;
import com.hl.devbox.Entity.CodeLib;
import com.hl.devbox.Entity.LocalAVObject;
import com.hl.devbox.Entity.User;
import com.hl.devbox.R;
import com.hl.devbox.utils.OSPluginManager;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends BaseActivity {

    @Bind(R.id.iv_header)
    ImageView ivHeader;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tv_lib_discription)
    TextView tvLibDiscription;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.btn_download)
    CircularProgressButton btnDownload;
    @Bind(R.id.tv_githubAddress)
    TextView tvGithubAddress;
    @Bind(R.id.cv_githubAddress)
    CardView cvGithubAddress;
    @Bind(R.id.tv_LastUpdateDate)
    TextView tvLastUpdateDate;
    @Bind(R.id.tv_LastUpdateMsg)
    TextView tvLastUpdateMsg;
    @Bind(R.id.tv_author)
    TextView tvAuthor;
    @Bind(R.id.tv_license)
    TextView tvLicense;

    public static final String SELECTEDITEM = "selectedItem";
    private CodeLib codeLib;
    private ApkItem apkItem;
    private OSPluginManager operator;
    private LocalAVObject localLibCode;
    private KJDB kjdb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        ButterKnife.bind(this);

        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LibDetailActivity.this.finish();
            }
        });

        codeLib = getIntent().getParcelableExtra(SELECTEDITEM);

        kjdb = KJDB.create(this);
        localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);

        apkItem = new ApkItem(this, codeLib);
        if (apkItem.exists()) {
            btnDownload.setProgress(100);
            btnDownload.setCompleteText("打开");
        } else {
            btnDownload.setProgress(0);
            btnDownload.setText(codeLib.getApkFileSizeStr());
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(codeLib.getLibName());

        tvLibDiscription.setText(codeLib.getDescriptionCN());
        tvGithubAddress.setText(codeLib.getGithubAddress());
        tvVersion.setText("API " + codeLib.getMinSDKVersion());
        tvAuthor.setText(codeLib.getAuthor());
        tvLicense.setText(codeLib.getLicense());

        codeLib.increaseViewCount();
        codeLib.saveInBackground();
        operator = new OSPluginManager(this);
        new WebActionImpl(this).getLastCommitInfo(codeLib.getGithubAddress(), new GetLastCommitInfoCallback() {

            @Override
            public void done(String committerName, String commitDate, String msgStr, AppException e) {

                if (e == null) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    Date date = null;
                    try {
                        date = dateFormat.parse(commitDate);
                    } catch (ParseException ep) {
                        ep.printStackTrace();
                    }

                    LibDetailActivity.this.tvLastUpdateMsg.setText(committerName + ":" + msgStr);
                    LibDetailActivity.this.tvLastUpdateDate.setText(DateUtils.getRelativeDateTimeString(LibDetailActivity.this, date.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));

                } else {

                    LibDetailActivity.this.tvLastUpdateMsg.setText("加载失败");
                    LibDetailActivity.this.tvLastUpdateDate.setText("加载失败");

                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    @OnClick({R.id.btn_download, R.id.cv_githubAddress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

                if (apkItem.exists()) {
                    operator.openApk(apkItem);
                    return;
                }

                codeLib.increaseDownloadCount();
                codeLib.saveInBackground();

                codeLib.getLibApkFile().getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        final String apkName = codeLib.getLibName().replace(" ", "") + ".apk";
                        final String folderPath = FileUtils.getSDCardPath() + File.separator + "OSCode" + File.separator;

                        FileUtils.saveFileCache(bytes, folderPath, apkName);
                        operator.installApk(apkItem);
                        btnDownload.setProgress(100);

                    }
                }, new ProgressCallback() {
                    @Override
                    public void done(Integer integer) {
                        btnDownload.setProgress(integer);
                    }
                });
                codeLib.increaseDownloadCount();
                codeLib.saveInBackground();
                break;
            case R.id.cv_githubAddress:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(codeLib.getGithubAddress()));
                startActivity(browserIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.action_collect:

//                localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);
//                if (localLibCode == null) {
//                    kjdb.save(new LocalAVObject(codeLib.getObjectId(), codeLib.toString()));
//                    codeLib.increaseCollectionCount();
//                } else {
//                    kjdb.delete(localLibCode);
//                    codeLib.decreaseCollectionCount();
//                }
//                codeLib.saveInBackground();
//                int resourceId = localLibCode == null ? R.drawable.ic_heart_outline : R.drawable.ic_heart;
//                item.setIcon(resourceId);


                if (User.getCurrentUser() == null) {

                    startActivity(new Intent(this, LoginActivity.class));

                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
        int resourceId = localLibCode == null ? R.drawable.ic_heart_outline : R.drawable.ic_heart;
        menu.findItem(R.id.action_collect).setIcon(resourceId);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}