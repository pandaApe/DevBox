package com.hl.devbox.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.dd.CircularProgressButton;
import com.hl.devbox.Engine.AppException;
import com.hl.devbox.Engine.GetLastCommitInfoCallback;
import com.hl.devbox.Engine.HttpCallback;
import com.hl.devbox.Engine.WebActionImpl;
import com.hl.devbox.Entity.ApkItem;
import com.hl.devbox.Entity.Library;
import com.hl.devbox.Entity.User;
import com.hl.devbox.R;
import com.hl.devbox.utils.LogUtil;
import com.hl.devbox.utils.OSPluginManager;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.database.OneToManyLazyLoader;
import org.kymjs.kjframe.utils.SystemTool;

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
    private Library codeLib;
    private ApkItem apkItem;
    private OSPluginManager operator;

    private final int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 123;
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

        codeLib = (Library) getIntent().getSerializableExtra(SELECTEDITEM);

        kjdb = KJDB.create(this);
//        localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);

        apkItem = new ApkItem(this, codeLib);
        if (apkItem.exists()) {
            btnDownload.setProgress(100);
            btnDownload.setCompleteText(getString(R.string.launch));
        } else {
            btnDownload.setProgress(0);
            btnDownload.setText(codeLib.getApk().getApkSizeStr(this));
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(codeLib.getName());

        tvLibDiscription.setText(codeLib.getEnDescription());
        tvGithubAddress.setText(codeLib.getGithubAddress());
        tvVersion.setText("API " + codeLib.getMinSdkVersion());
        tvAuthor.setText(codeLib.getAuthor());
        tvLicense.setText(codeLib.getLicense());

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

                    LibDetailActivity.this.tvLastUpdateMsg.setText(R.string.loadFailed);
                    LibDetailActivity.this.tvLastUpdateDate.setText(R.string.loadFailed);

                }

            }
        });

        new WebActionImpl(this).increaseViewCount(codeLib.getObjectId(), new HttpCallback() {
            @Override
            public void onSucess() {

            }

            @Override
            public void onFailure(AppException e) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_STORAGE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    downloadApk();
                else
                    showSnackbar(getString(R.string.refusePermission));
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @OnClick({R.id.btn_download, R.id.cv_githubAddress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

                if (apkItem.exists()) {
                    operator.openApk(apkItem);
                    return;
                }

                int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_STORAGE_PERMISSIONS);
                    return;
                }

//                codeLib.increaseDownloadCount();
//                codeLib.saveInBackground();

                downloadApk();

                break;
            case R.id.cv_githubAddress:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(codeLib.getGithubAddress()));
                startActivity(browserIntent);
        }
    }

    private void downloadApk() {
        btnDownload.setProgress(1);
        new WebActionImpl(this).downloadApkFile(this.codeLib, new HttpCallback<String>() {
            @Override
            public void onSucess(String filePath) {
                LogUtil.log("--->" + operator.installApk(apkItem));
                btnDownload.setProgress(100);

            }

            @Override
            public void onProgress(int percentage) {
                btnDownload.setProgress(percentage);
                LogUtil.log("******->" + percentage);
            }

            @Override
            public void onFailure(AppException e) {
                btnDownload.setProgress(-1);
                btnDownload.setText(R.string.downloadFailed);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
//                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.action_collect:
                User user = new User(SystemTool.getPhoneIMEI(this));
                user.setLikedLibs(new OneToManyLazyLoader<User, Library>(user, User.class, Library.class, KJDB.create(this)));


                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
//        int resourceId = localLibCode == null ? R.drawable.ic_heart_outline : R.drawable.ic_heart;
//        menu.findItem(R.id.action_collect).setIcon(resourceId);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}