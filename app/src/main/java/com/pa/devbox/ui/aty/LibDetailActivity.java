package com.pa.devbox.ui.aty;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pa.devbox.R;
import com.pa.devbox.databinding.ActivityLibdetailBinding;
import com.pa.devbox.di.component.DaggerLibDetailAtyComponent;
import com.pa.devbox.di.module.LibDetailAtyModule;
import com.pa.devbox.ui.viewModel.LibDetailAtyModel;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 23/1/16 12:39.
 * Email: whailong2010@gmail.com
 */
public class LibDetailActivity extends BaseActivity {

    @Inject
    LibDetailAtyModel libDetailAtyModel;

    private PermissionRequestCallback permissionRequestCallback;


    @Override
    void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        DaggerLibDetailAtyComponent
                .builder()
                .libDetailAtyModule(new LibDetailAtyModule(this))
                .build()
                .inject(this);

        ActivityLibdetailBinding libDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_libdetail);
        libDetailAtyModel.parseArguments(getIntent());
        libDetailBinding.setViewModel(libDetailAtyModel);

        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, (v) -> {
            LibDetailActivity.this.finish();
        });


    }

    public void setPermissionRequestCallback(PermissionRequestCallback permissionRequestCallback) {
        this.permissionRequestCallback = permissionRequestCallback;
    }

    public void onCdreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        kjdb = KJDB.create(this);
//        localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);

//        apkItem = new ApkItem(this, codeLib);
//        if (apkItem.exists()) {
//            btnDownload.setProgress(100);
//            btnDownload.setCompleteText(getString(R.string.launch));
//        } else {
//            btnDownload.setProgress(0);
//            btnDownload.setText(codeLib.getApk().getApkSizeStr(this));
//        }

//        operator = new OSPluginManager(this);
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
        permissionRequestCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //    @OnClick({R.id.btn_download, R.id.cv_githubAddress})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

//                if (apkItem.exists()) {
//                    operator.openApk(apkItem);
//                    return;
//                }
//
//                int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            REQUEST_CODE_ASK_STORAGE_PERMISSIONS);
//                    return;
//                }

//                codeLib.increaseDownloadCount();
//                codeLib.saveInBackground();

//                downloadApk();

                break;
            case R.id.cv_githubAddress:
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(codeLib.getGithubAddress()));
//                startActivity(browserIntent);
        }
    }

    public interface PermissionRequestCallback {
        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }

    private void downloadApk() {
//        btnDownload.setProgress(1);
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