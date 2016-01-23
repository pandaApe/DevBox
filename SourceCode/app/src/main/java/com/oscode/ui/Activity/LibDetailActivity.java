package com.oscode.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oscode.R;
import com.oscode.model.OSCodeLib;


/**
 * Created by whailong on 23/1/16.
 */
public class LibDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCollect;
    private Button btnDownload;
    private Button btnShare;

    private TextView tvDescription;
    private TextView tvGithubAddress;
    private TextView tvVersion;
    private TextView tvLibName;
    private TextView tvLastUpdate;
    private TextView tvAuthor;
    private TextView tvLicense;

    private OSCodeLib codeLib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libdetail);
        btnCollect = (Button) findViewById(R.id.btn_collect);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnShare = (Button) findViewById(R.id.btn_share);

        tvDescription = (TextView) findViewById(R.id.tv_lib_discription);
        tvGithubAddress = (TextView) findViewById(R.id.tv_githubAddress);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvLibName = (TextView) findViewById(R.id.tv_libName);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvLicense = (TextView) findViewById(R.id.tv_license);
        tvLastUpdate = (TextView) findViewById(R.id.tv_LastUpdate);

        btnCollect.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        btnShare.setOnClickListener(this);

        codeLib = getIntent().getParcelableExtra("selectedItem");

        tvLibName.setText(codeLib.getLibName());
        tvDescription.setText(codeLib.getDescriptionCN());
        tvGithubAddress.setText(codeLib.getGithubAddress());
        tvVersion.setText(codeLib.getMinSDKVersion());
        tvAuthor.setText(codeLib.getAuthor());
        tvLicense.setText(codeLib.getLicense());
        btnDownload.setText("下载(" + codeLib.getSize() + "MB)");

        codeLib.increaseViewCount();
        codeLib.saveInBackground();
    }

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
