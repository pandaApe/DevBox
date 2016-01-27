package com.oscode.ui.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.dd.CircularProgressButton;
import com.oscode.R;
import com.oscode.model.OSCodeLib;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;


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
    private TextView tvLastUpdate;
    private TextView tvAuthor;
    private TextView tvLicense;
    private ImageView ivHeader;

    private OSCodeLib codeLib;
    private String filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        btnCollect = (Button) findViewById(R.id.btn_collect);
        btnDownload = (CircularProgressButton) findViewById(R.id.btn_download);
//        btnShare = (Button) findViewById(R.id.btn_share);
        ivHeader = (ImageView) findViewById(R.id.iv_header);
        tvDescription = (TextView) findViewById(R.id.tv_lib_discription);
        tvGithubAddress = (TextView) findViewById(R.id.tv_githubAddress);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvLicense = (TextView) findViewById(R.id.tv_license);
        tvLastUpdate = (TextView) findViewById(R.id.tv_LastUpdate);

//        btnCollect.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
//        btnShare.setOnClickListener(this);
        String fileFolder = FileUtils.getSDCardPath() + File.separator + "OSCode" + File.separator;
        String fileName = "lib.apk";
        filePath = fileFolder + File.separator + fileName;
        if (new File(filePath).exists()) {
            btnDownload.setProgress(100);
            btnDownload.setCompleteText("已下载");
        } else {
            btnDownload.setProgress(0);

        }

        codeLib = getIntent().getParcelableExtra("selectedItem");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(codeLib.getLibName());

        tvDescription.setText(codeLib.getDescriptionCN());
        tvGithubAddress.setText(codeLib.getGithubAddress());
        tvVersion.setText(codeLib.getMinSDKVersion());
        tvAuthor.setText(codeLib.getAuthor());
        tvLicense.setText(codeLib.getLicense());
//        btnDownload.setText("下载(" + codeLib.getSize() + "MB)");

//        codeLib.getLibPreImage().getDataInBackground(new GetDataCallback() {
//            @Override
//            public void done(byte[] bytes, AVException e) {
//                Bitmap bm = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                LibDetailActivity.this.ivHeader.setImageBitmap(bm);
//            }
//        });

        codeLib.increaseViewCount();
        codeLib.saveInBackground();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_collect:
//
//                break;

            case R.id.btn_download:

                if (new File(filePath).exists()) {
                    return;
                }
                codeLib.getLibApkFile().getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        Log.v("Done--->", "____>");

                        FileUtils.saveFileCache(bytes, FileUtils.getSDCardPath() + File.separator + "OSCode" + File.separator, "lib.apk");
//                        int PluginManager.getInstance().installPackage(String filepath, int flags);
//                        try {
//                            PluginManager.getInstance().installPackage(fileFolder + fileName, 0);
//                        } catch (RemoteException e1) {
//                            e1.printStackTrace();
//                        }
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

//            case R.id.btn_share:

//                String fileFolder = FileUtils.getSDCardPath() + File.separator + "OSCode" + File.separator;
//                String fileName = "lib.apk";

//                try {
//                    int ints = PluginManager.getInstance().installPackage(fileFolder + fileName, 0);
//                    Log.v("tag--->", "-->" + ints);
//                } catch (RemoteException e1) {
//                    e1.printStackTrace();
//                }
//                Intent intent = new Intent().setClassName(this, "com.daimajia.slider.demo.MainActivity");
//                this.startActivity(intent);
//                break;


        }


    }


    private class FalseProgress extends AsyncTask<Integer, Integer, Integer> {

        private CircularProgressButton cpb;

        public FalseProgress(CircularProgressButton cpb) {
            this.cpb = cpb;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            for (int progress = 0; progress < 100; progress += 5) {
                publishProgress(progress);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            cpb.setProgress(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            cpb.setProgress(progress);
        }
    }

}
