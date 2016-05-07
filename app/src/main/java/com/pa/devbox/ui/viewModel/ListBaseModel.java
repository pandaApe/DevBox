package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pa.devbox.ui.aty.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 7/5/16 10:27.
 * Email: whailong2010@gmail.com
 */
public class ListBaseModel<T> extends BaseObservable implements SwipeRefreshLayout.OnRefreshListener {
    @Bindable
    boolean progressBarVisible;

    @Bindable
    boolean swipeRefreshLayoutStatus;


    @Bindable
    RecyclerView.LayoutManager layoutManager;

    @Bindable
    List<T> data;

    public ListBaseModel(MainActivity context) {

        progressBarVisible = true;

        data = new ArrayList<>();
        layoutManager = new GridLayoutManager(context, 2);
    }

    @Override
    public void onRefresh() {

    }
}
