package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pa.devbox.BR;
import com.pa.devbox.ui.adapter.BaseAdapter;
import com.pa.devbox.ui.aty.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
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
    boolean swipeRefreshLayoutRefreshing;

    @Bindable
    RecyclerView.LayoutManager layoutManager;

    @Bindable
    BaseAdapter adapter;

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

    public boolean isProgressBarVisible() {
        return progressBarVisible;
    }

    public void setProgressBarVisible(boolean progressBarVisible) {
        this.progressBarVisible = progressBarVisible;
        notifyPropertyChanged(BR.progressBarVisible);
    }

    public boolean isSwipeRefreshLayoutStatus() {
        return swipeRefreshLayoutStatus;
    }

    public void setSwipeRefreshLayoutStatus(boolean swipeRefreshLayoutStatus) {
        this.swipeRefreshLayoutStatus = swipeRefreshLayoutStatus;
        notifyPropertyChanged(BR.swipeRefreshLayoutStatus);
    }

    public boolean isSwipeRefreshLayoutRefreshing() {
        return swipeRefreshLayoutRefreshing;
    }

    public void setSwipeRefreshLayoutRefreshing(boolean swipeRefreshLayoutRefreshing) {
        this.swipeRefreshLayoutRefreshing = swipeRefreshLayoutRefreshing;
        notifyPropertyChanged(BR.swipeRefreshLayoutRefreshing);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }
}
