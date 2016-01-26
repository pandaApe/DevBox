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
import com.oscode.model.OSCodeType;
import com.oscode.service.GetTypeListService;
import com.oscode.ui.Adapter.TypeFragRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whailong on 22/1/16.
 */
public class TypeDisplayFrament extends Fragment {
    private ArrayList<OSCodeType> codeTypes;
    private RecyclerView recyclerView;
    private TypeFragRVAdapter adapter;

    public static TypeDisplayFrament newInstance(int num) {
        TypeDisplayFrament f = new TypeDisplayFrament();
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

        for (int i = 0; i <= 50; i++) {
            OSCodeType OSCodeType = new OSCodeType();
            OSCodeType.setNameCN("列表_" + i);
            OSCodeType.setNameEn("Listview_" + i);
            codeTypes.add(OSCodeType);
        }


        setupView(view);

//        setupServerData();
    }

    private void setupServerData() {
        new GetTypeListService().doGetTypeListQueryWithCompletion(new FindCallback<OSCodeType>() {
            @Override
            public void done(List<OSCodeType> list, AVException e) {
                codeTypes.clear();
                codeTypes.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TypeFragRVAdapter(getActivity(), codeTypes);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性显示 类似于list view
        recyclerView.setAdapter(adapter);
    }
}
