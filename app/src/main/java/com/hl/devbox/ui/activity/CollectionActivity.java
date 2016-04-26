package com.hl.devbox.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hl.devbox.R;
import com.hl.devbox.domain.entity.Library;
import com.hl.devbox.ui.adapter.LibListAdapter;

import java.util.ArrayList;

public class CollectionActivity extends BaseActivity {

    RecyclerView recyclerView;

    private LibListAdapter adapter;
    private ArrayList<Library> codeLibs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        this.setToolbarTitle(getString(R.string.liked));
        this.setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.this.finish();
            }
        });

//        KJDB kjdb = KJDB.create(this);
//        List<LocalAVObject> list = kjdb.findAll(LocalAVObject.class);
//        codeLibs = new ArrayList<>();
//        for (LocalAVObject la : list) {
//            try {
//                codeLibs.add((CodeLib) CodeLib.parseAVObject(la.getObjectStr()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new LibListAdapter(this, codeLibs);
        recyclerView.setAdapter(adapter);
    }

}
