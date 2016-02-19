package com.devbox.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avos.avoscloud.AVAnalytics;
import com.devbox.R;
import com.devbox.Entity.CodeLib;
import com.devbox.Entity.LocalAVObject;
import com.devbox.Adapter.LibListAdapter;

import org.kymjs.kjframe.KJDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CollectionActivity extends BaseActivity {

    @Bind(R.id.recyclyView)
    RecyclerView recyclerView;

    private LibListAdapter adapter;
    private ArrayList<CodeLib> codeLibs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);

        this.setToolbarTitle("收藏");
        this.setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.this.finish();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new LibListAdapter(this, codeLibs);
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
