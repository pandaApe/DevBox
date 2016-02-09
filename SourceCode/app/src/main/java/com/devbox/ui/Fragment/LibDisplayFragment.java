package com.devbox.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.FindCallback;
import com.devbox.R;
import com.devbox.action.GetLibListService;
import com.devbox.model.CodeLib;
import com.devbox.ui.Adapter.LibListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by whailong on 14/1/16.
 */
public class LibDisplayFragment extends Fragment {
    @Bind(R.id.clprogressBar)
    ContentLoadingProgressBar progressBarContainer;
    @Bind(R.id.recyclyView)
    RecyclerView recyclerView;
    private ArrayList<CodeLib> codeLibs = new ArrayList<>();

    private LibListAdapter adapter;

    public static LibDisplayFragment newInstance(int num) {
        LibDisplayFragment f = new LibDisplayFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LibListAdapter(getActivity(), codeLibs);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        new GetLibListService().doGetLibListQueryWithCompletion(new FindCallback<CodeLib>() {
            @Override
            public void done(List<CodeLib> list, AVException e) {
                LibDisplayFragment.this.codeLibs.clear();
                LibDisplayFragment.this.codeLibs.addAll(list);
                LibDisplayFragment.this.adapter.notifyDataSetChanged();
                LibDisplayFragment.this.progressBarContainer.hide();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
