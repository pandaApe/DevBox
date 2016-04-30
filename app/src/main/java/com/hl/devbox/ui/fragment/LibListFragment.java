package com.hl.devbox.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.devbox.R;
import com.hl.devbox.domain.entity.Library;
import com.hl.devbox.domain.entity.Type;
import com.hl.devbox.ui.activity.LibDetailActivity;
import com.hl.devbox.ui.activity.SpecificTypeActivity;
import com.hl.devbox.ui.adapter.LibListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by whailong on 14/1/16.
 */
public class LibListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ContentLoadingProgressBar progressBarContainer;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Library> codeLibs = new ArrayList<>();

    private LibListAdapter adapter;

    private HashMap<String, String> parmMap;

    public static LibListFragment newInstance(int num) {
        LibListFragment f = new LibListFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib, container, false);
//        ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        parmMap = new HashMap<>();
        if (bundle != null) {
            Type currentType = (Type) bundle.getSerializable(SpecificTypeActivity.SELECTEDITEM);

            if (currentType != null)
                parmMap.put("objId", currentType.getObjectId());
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initeView();
        requestServerData();
    }

    private void initeView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LibListAdapter(getActivity(), codeLibs);
        adapter.setItemOnClickListenner(new LibListAdapter.AdapterItemOnClickListenner() {

            @Override
            public void onClick(View v, int index) {
                Intent intent = new Intent(getActivity(), LibDetailActivity.class);
                intent.putExtra(LibDetailActivity.SELECTEDITEM, codeLibs.get(index));
                getActivity().startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void requestServerData() {
//        new WebActionImpl(getActivity()).getLibList(parmMap, 1, new HttpCallback<List<Library>>() {
//
//            @Override
//            public void onSucess(List<Library> list) {
//
//                KJDB kjdb = KJDB.create(getActivity());
//                for (Library lib : kjdb.findAll(Library.class)) {
//                    kjdb.delete(lib);
//                }
//
//                kjdb.save(list);
//
//                LibListFragment.this.codeLibs.clear();
//                LibListFragment.this.codeLibs.addAll(list);
//                LibListFragment.this.adapter.notifyDataSetChanged();
//
//                LibListFragment.this.progressBarContainer.hide();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(final AppException e) {
//
//                if (e.getCode() == AppException.NETWORK_ERROR) {
//
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG).show();
//                        }
//                    }, 500);
//                }
//
//                LibListFragment.this.progressBarContainer.hide();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        requestServerData();
    }
}
