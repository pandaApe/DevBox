package com.hl.devbox.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.devbox.Activity.SpecificTypeActivity;
import com.hl.devbox.Adapter.TypeListAdapter;
import com.hl.devbox.Engine.AppException;
import com.hl.devbox.Engine.HttpCallback;
import com.hl.devbox.Engine.WebActionImpl;
import com.hl.devbox.Entity.Type;
import com.hl.devbox.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by whailong on 22/1/16.
 */
public class TypeListFrament extends Fragment {
    private ArrayList<Type> codeTypes;
    private RecyclerView recyclerView;
    private TypeListAdapter adapter;
    private ContentLoadingProgressBar progressBarContainer;
    private Type currentType;

    public static TypeListFrament newInstance(int num) {
        TypeListFrament f = new TypeListFrament();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lib, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        codeTypes = new ArrayList<>();

        setupView(view);

        setupServerData();
    }

    private void setupServerData() {

        new WebActionImpl(getActivity()).getTypeList(new HttpCallback<List<Type>>() {
            @Override
            public void onSucess(List<Type> list) {

                codeTypes.clear();
                codeTypes.addAll(list);
                adapter.notifyDataSetChanged();

                progressBarContainer.hide();
            }

            @Override
            public void onFailure(final AppException e) {
                if (e.getCode() == AppException.NETWORK_ERROR) {

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }, 500);
                }

                progressBarContainer.hide();
            }
        });
    }

    private void setupView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclyView);
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


        progressBarContainer = (ContentLoadingProgressBar) view.findViewById(R.id.clprogressBar);
        progressBarContainer.show();
    }
}
