package com.oscode.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oscode.R;


/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCollect;
    private Button btnDownload;
    private Button btnShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        btnCollect = (Button) findViewById(R.id.btn_collect);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnShare = (Button) findViewById(R.id.btn_share);

        btnCollect.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        btnShare.setOnClickListener(this);

//
//        OSCodeLib codeLib = new OSCodeLib();
//        codeLib.setLibName("libName");
//        codeLib.setAuthor("author");
//        codeLib.setThumbAddress("thumbAddress");
//        codeLib.setType("type");
//        codeLib.setDescriptionEN("descriptionEN");
//        codeLib.setDescriptionCN("descriptionCN");
//        codeLib.setGithubAddress("githubAddress");
//        codeLib.setLicense("license");
//        codeLib.setSize("size");
//        codeLib.setMinSDKVersion("minSDKVersion");
//        codeLib.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                Log.d("", "------>Done");
//            }
//        });

    }


//    private String libName;
//    private String author;
//    private String type;
//    private String[] tags;
//    private String descriptionEN;
//    private String descriptionCN;
//    private String githubAddress;
//    private String license;
//    private String size;
//    private String minSDKVersion;
//    private int collectionCount;
//    private int downloadCount;
//    private int viewCount;
//    private AVFile libApkFile;
//    private AVFile libPreImage;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_collect:
                break;

            case R.id.btn_download:
                break;

            case R.id.btn_share:
                break;
        }
    }
}
