package com.oscode.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.dd.CircularProgressButton;
import com.oscode.R;
import com.oscode.model.ApkItem;
import com.oscode.model.ApkOperator;
import com.oscode.model.OSCodeLib;
import com.oscode.service.GetLastCommitInfoService;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCollect;
    private CircularProgressButton btnDownload;
    private Button btnShare;

    private TextView tvDescription;
    private TextView tvGithubAddress;
    private TextView tvVersion;
    private TextView tvLibName;
    private TextView tvLastUpdateDate;
    private TextView tvLastUpdateMsg;
    private TextView tvAuthor;
    private TextView tvLicense;
    private ImageView ivHeader;

    private OSCodeLib codeLib;
    private ApkItem apkItem;
    private ApkOperator operator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        codeLib = getIntent().getParcelableExtra("selectedItem");

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
        tvVersion.setText(codeLib.getMinSDKVersion());
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
                LibDetailActivity.this.tvLastUpdateDate.setText(DateUtils.getRelativeTimeSpanString(date.getTime()));
                Log.v("*******", committerName + "------" + commitDate + "------" + msgStr);
            }

            @Override
            public void onFailure() {

            }
        });
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
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:


                break;
            case R.id.action_collect:


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
        return true;
    }
}