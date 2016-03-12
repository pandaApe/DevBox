package com.hl.devbox.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.devbox.Adapter.LibListAdapter;
import com.hl.devbox.Engine.AppException;
import com.hl.devbox.Engine.HttpCallback;
import com.hl.devbox.Engine.WebActionImpl;
import com.hl.devbox.Entity.Library;
import com.hl.devbox.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by whailong on 14/1/16.
 */
public class LibListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.clprogressBar)
    ContentLoadingProgressBar progressBarContainer;
    @Bind(R.id.recyclyView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Library> codeLibs = new ArrayList<>();

    private LibListAdapter adapter;

    public static LibListFragment newInstance(int num) {
        LibListFragment f = new LibListFragment();
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
        swipeRefreshLayout.setOnRefreshListener(this);

        requestServerData();

    }

    private void requestServerData() {
//        new WebActionImpl(getActivity()).getLibList("", 0, new HttpCallback<ArrayList<CodeLib>>() {
//
//            @Override
//            public void onSucess(ArrayList<CodeLib> list, final AppException e) {
//                if (e == null) {
//                    LibListFragment.this.codeLibs.clear();
//                    LibListFragment.this.codeLibs.addAll(list);
//                    LibListFragment.this.adapter.notifyDataSetChanged();
//                } else if (e.getCode() == AppException.NETWORK_ERROR) {
//
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG).show();
//                        }
//                    }, 500);
//
//                }
//
//                LibListFragment.this.progressBarContainer.hide();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        requestServerData();
    }
}
