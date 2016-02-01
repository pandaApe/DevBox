package com.oscode.ui.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
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
import com.oscode.R;
import com.oscode.model.ApkItem;
import com.oscode.model.ApkOperator;
import com.oscode.model.CodeLib;
import com.oscode.model.LocalAVObject;
import com.oscode.service.GetLastCommitInfoService;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends BaseActivity implements View.OnClickListener {
    private CircularProgressButton btnDownload;
    private TextView tvDescription;
    private TextView tvGithubAddress;
    private TextView tvVersion;
    private TextView tvLastUpdateDate;
    private TextView tvLastUpdateMsg;
    private TextView tvAuthor;
    private TextView tvLicense;
    private ImageView ivHeader;

    private CodeLib codeLib;
    private ApkItem apkItem;
    private ApkOperator operator;
    private CardView cvGithubAddress;
    private LocalAVObject localLibCode;
    private KJDB kjdb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibDetailActivity.this.finish();
            }
        });

        cvGithubAddress = (CardView) findViewById(R.id.cv_githubAddress);
        btnDownload = (CircularProgressButton) findViewById(R.id.btn_download);
        ivHeader = (ImageView) findViewById(R.id.iv_header);
        tvDescription = (TextView) findViewById(R.id.tv_lib_discription);
        tvGithubAddress = (TextView) findViewById(R.id.tv_githubAddress);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvLicense = (TextView) findViewById(R.id.tv_license);
        tvLastUpdateDate = (TextView) findViewById(R.id.tv_LastUpdateDate);
        tvLastUpdateMsg = (TextView) findViewById(R.id.tv_LastUpdateMsg);

        btnDownload.setOnClickListener(this);
        cvGithubAddress.setOnClickListener(this);

        codeLib = getIntent().getParcelableExtra("selectedItem");

        kjdb = KJDB.create(this);
        localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);

        apkItem = new ApkItem(this, codeLib);
        if (apkItem.exists()) {
            btnDownload.setProgress(100);
            btnDownload.setCompleteText("打开");
        } else {
            btnDownload.setProgress(0);
            double size = codeLib.getLibApkFile().getSize() / 1000.0 / 1000.0;
            double sizeFinal = Math.round(size * 100) / 100.0;
            btnDownload.setText("下载(" + sizeFinal + "MB)");
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(codeLib.getLibName());

        tvDescription.setText(codeLib.getDescriptionCN());
        tvGithubAddress.setText(codeLib.getGithubAddress());
        tvVersion.setText("API " + codeLib.getMinSDKVersion());
        tvAuthor.setText(codeLib.getAuthor());
        tvLicense.setText(codeLib.getLicense());

        codeLib.increaseViewCount();
        codeLib.saveInBackground();
        operator = new ApkOperator(this);

        GetLastCommitInfoService service = new GetLastCommitInfoService();
        service.setCodeLib(codeLib);
        service.getGetLastCommitInfoWithCompletgion(new GetLastCommitInfoService.GetLastCommitCallBack() {
            @Override
            public void onSuccess(String committerName, String commitDate, String msgStr) {


                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    date = dateFormat.parse(commitDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                LibDetailActivity.this.tvLastUpdateMsg.setText(committerName + ":" + msgStr);
                LibDetailActivity.this.tvLastUpdateDate.setText(DateUtils.getRelativeDateTimeString(LibDetailActivity.this, date.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));

            }

            @Override
            public void onFailure() {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:

                if (apkItem.exists()) {
                    operator.openApk(apkItem);
                    return;
                }

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

                break;
            case R.id.action_collect:

                localLibCode = kjdb.findById(codeLib.getObjectId(), LocalAVObject.class);
                if (localLibCode == null)
                    kjdb.save(new LocalAVObject(codeLib.getObjectId(), codeLib.toString()));
                else
                    kjdb.delete(localLibCode);

                int resourceId = localLibCode == null ? R.drawable.ic_heart_outline : R.drawable.ic_heart;
                item.setIcon(resourceId);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
        int resourceId = localLibCode == null ? R.drawable.ic_heart_outline : R.drawable.ic_heart;
        menu.findItem(R.id.action_collect).setIcon(resourceId);
//        createNewLib();
        return true;
    }


    private void createNewLib() {

//        CodeLib newLib = new CodeLib();
//        newLib.setLibName("MaterialViewPager");
//        newLib.setAuthor("florent37");
//        newLib.setDescriptionCN("A Material Design ViewPager easy to use library");
//
//        newLib.setGithubAddress("https://github.com/florent37/MaterialViewPager");
//        newLib.setLicense("Apache 2.0");
//        newLib.setMinSDKVersion("14");
//
//
//        newLib.saveInBackground();

//        CodeType codeType = new CodeType();
//        codeType.setTypeCNDescription("ProgressBar");
//        codeType.setParentType("控件");
//        codeType.addCodeLib(codeLib);
//        codeType.saveInBackground();

//        new AVQuery<CodeType>().getInBackground();

    }


}