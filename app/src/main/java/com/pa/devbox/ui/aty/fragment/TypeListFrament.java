package com.pa.devbox.ui.aty.fragment;

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
import com.hl.devbox.domain.entity.Type;
import com.hl.devbox.ui.activity.SpecificTypeActivity;
import com.hl.devbox.ui.adapter.TypeListAdapter;

import java.util.ArrayList;

/**
 * Created by whailong on 22/1/16.
 */
public class TypeListFrament extends Fragment {

    ContentLoadingProgressBar progressBar;

    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Type> codeTypes;
    private TypeListAdapter adapter;

    public static TypeListFrament newInstance(int num) {
        TypeListFrament f = new TypeListFrament();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lib, container, false);
//        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        codeTypes = new ArrayList<>();

        setupView(view);

        setupServerData();
    }

    private void setupServerData() {

//        new WebActionImpl(getActivity()).getTypeList(new HttpCallback<List<Type>>() {
//            @Override
//            public void onSucess(List<Type> list) {
//
//                codeTypes.clear();
//                codeTypes.addAll(list);
//                adapter.notifyDataSetChanged();
//
//                progressBar.hide();
//            }
//
//            @Override
//            public void onFailure(final AppException e) {
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
//                progressBar.hide();
//            }
//        });
    }

    private void setupView(View view) {
        swipeRefreshLayout.setEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TypeListAdapter(getActivity(), codeTypes);

        adapter.setItemOnClickListenner(new TypeListAdapter.AdapterItemOnClickListenner() {
            @Override
            public void onClick(View v, int index) {

                Intent intent = new Intent(getActivity(), SpecificTypeActivity.class);
                intent.putExtra(SpecificTypeActivity.SELECTEDITEM, codeTypes.get(index));
                getActivity().startActivity(intent);
            }
        });


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性显示 类似于list view
        recyclerView.setAdapter(adapter);

        progressBar = (ContentLoadingProgressBar) view.findViewById(R.id.clprogressBar);
        progressBar.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }
}
