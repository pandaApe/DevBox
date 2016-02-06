package com.oscode.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.avos.avoscloud.AVAnalytics;
import com.oscode.R;
import com.oscode.model.CodeLib;
import com.oscode.model.LocalAVObject;
import com.oscode.ui.Adapter.LibFragRVAdapter;

import org.kymjs.kjframe.KJDB;

import java.util.ArrayList;
import java.util.List;


public class CDActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LibFragRVAdapter adapter;
    private ArrayList<CodeLib> codeLibs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("收藏");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CDActivity.this.finish();
            }
        });

        KJDB kjdb = KJDB.create(this);
        List<LocalAVObject> list = kjdb.findAll(LocalAVObject.class);
        codeLibs = new ArrayList<>();
        for (LocalAVObject la : list) {
            try {
                codeLibs.add((CodeLib) CodeLib.parseAVObject(la.getObjectStr()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LibFragRVAdapter(this, codeLibs);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

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

}
