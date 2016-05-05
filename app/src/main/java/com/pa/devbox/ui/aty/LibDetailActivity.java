package com.pa.devbox.ui.aty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.pa.devbox.R;
import com.pa.devbox.domain.entity.ApkItem;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.util.OSPluginManager;


/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends BaseActivity {


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

    public static final String SELECTEDITEM = "selectedItem";
    private Library codeLib;
    private ApkItem apkItem;
    private OSPluginManager operator;

    private final int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 123;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);


        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LibDetailActivity.this.finish();
            }
        });

        codeLib = (Library) getIntent().getSerializableExtra(SELECTEDITEM);

//        kjdb = KJDB.create(this);
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
//        new WebActionImpl(this).getLastCommitInfo(codeLib.getGithubAddress(), new GetLastCommitInfoCallback() {
//
//            @Override
//            public void done(String committerName, String commitDate, String msgStr, AppException e) {
//
//                if (e == null) {
//                    Calendar calendar = Calendar.getInstance();
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//                    Date date = null;
//                    try {
//                        date = dateFormat.parse(commitDate);
//                    } catch (ParseException ep) {
//                        ep.printStackTrace();
//                    }
//
//                    LibDetailActivity.this.tvLastUpdateMsg.setText(committerName + ":" + msgStr);
//                    LibDetailActivity.this.tvLastUpdateDate.setText(DateUtils.getRelativeDateTimeString(LibDetailActivity.this, date.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));
//
//                } else {
//
//                    LibDetailActivity.this.tvLastUpdateMsg.setText(R.string.loadFailed);
//                    LibDetailActivity.this.tvLastUpdateDate.setText(R.string.loadFailed);
//
//                }
//
//            }
//        });
//
//        new WebActionImpl(this).increaseViewCount(codeLib.getObjectId(), new HttpCallback() {
//            @Override
//            public void onSucess() {
//
//            }
//
//            @Override
//            public void onFailure(AppException e) {
//
//            }
//        });
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

//    @OnClick({R.id.btn_download, R.id.cv_githubAddress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

                if (apkItem.exists()) {
//                    operator.openApk(apkItem);
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
//        new WebActionImpl(this).downloadApkFile(this.codeLib, new HttpCallback<String>() {
//            @Override
//            public void onSucess(String filePath) {
//                LogUtils.log("--->" + operator.installApk(apkItem));
//                btnDownload.setProgress(100);
//
//            }
//
//            @Override
//            public void onProgress(int percentage) {
//                btnDownload.setProgress(percentage);
//                LogUtils.log("******->" + percentage);
//            }
//
//            @Override
//            public void onFailure(AppException e) {
//                btnDownload.setProgress(-1);
//                btnDownload.setText(R.string.downloadFailed);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
//                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.action_collect:

//                KJDB kjdb = KJDB.create(this);
//                List<Library> list = kjdb.findAllByWhere(Library.class, "isCollected=true AND name ='" + this.codeLib.getName() + "'");
//
//                if (list == null || list.size() == 0) {
//                    this.codeLib.setIsCollected(true);
//                    kjdb.save(this.codeLib);
//                } else {
//                    this.codeLib.setIsCollected(false);
//                    kjdb.update(this.codeLib);
//                }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);

//        KJDB kjdb = KJDB.create(this);
//        List<Library> list = kjdb.findAllByWhere(Library.class, "name ='" + this.codeLib.getName() + "'");
//
//        int resourceId;
//        if (list == null || list.size() == 0 || !list.get(0).isCollected()) {
//            resourceId = R.drawable.ic_heart_outline;
//
//        } else {
//            resourceId = R.drawable.ic_heart;
//        }
//
//        menu.findItem(R.id.action_collect).setIcon(resourceId);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}