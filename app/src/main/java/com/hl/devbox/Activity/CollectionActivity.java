package com.hl.devbox.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hl.devbox.Adapter.LibListAdapter;
import com.hl.devbox.Entity.Library;
import com.hl.devbox.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CollectionActivity extends BaseActivity {

    @Bind(R.id.recyclyView)
    RecyclerView recyclerView;

    private LibListAdapter adapter;
    private ArrayList<Library> codeLibs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);

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
