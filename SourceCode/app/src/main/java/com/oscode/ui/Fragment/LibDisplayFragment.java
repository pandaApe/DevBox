package com.oscode.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.FindCallback;
import com.oscode.R;
import com.oscode.model.OSCodeLib;
import com.oscode.service.GetLibListService;
import com.oscode.ui.Adapter.LibFragRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whailong on 14/1/16.
 */
public class LibDisplayFragment extends Fragment {
    private ArrayList<OSCodeLib> codeLibs;
    private RecyclerView recyclerView;
    private LibFragRVAdapter adapter;


    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static LibDisplayFragment newInstance(int num) {
        LibDisplayFragment f = new LibDisplayFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lib, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        codeLibs = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LibFragRVAdapter(getActivity(), codeLibs);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        new GetLibListService().doGetLibListQueryWithCompletion(new FindCallback<OSCodeLib>() {
            @Override
            public void done(List<OSCodeLib> list, AVException e) {
                LibDisplayFragment.this.codeLibs.clear();
                LibDisplayFragment.this.codeLibs.addAll(list);
                LibDisplayFragment.this.adapter.notifyDataSetChanged();
            }
        });
    }
}
