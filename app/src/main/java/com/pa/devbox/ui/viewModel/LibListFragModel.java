package com.pa.devbox.ui.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.adapter.BaseAdapter;
import com.pa.devbox.ui.adapter.LibListAdapter;
import com.pa.devbox.ui.aty.BaseActivity;
import com.pa.devbox.ui.aty.LibDetailActivity;
import com.pa.devbox.ui.aty.SpecificTypeActivity;
import com.pa.devbox.ui.modle.LibListModel;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListFragModel extends ListBaseModel<Library> implements BaseAdapter.OnItemClickListener, HttpRequestCallback<Library> {

    private Type currentType;
    //Start from 1
    private int currentPageIndex = 1;


    private LibListModel libListModel;

    public LibListFragModel(BaseActivity context, Bundle savedInstanceState) {
        super(context);

        swipeRefreshLayoutStatus = true;

        adapter = new LibListAdapter(context, data);
        adapter.setItemClickListener(this);

        if (savedInstanceState != null)
            currentType = (Type) savedInstanceState.getSerializable(SpecificTypeActivity.SELECTEDITEM);

        this.libListModel = new LibListModel();
        this.libListModel.setCallback(this);

        onRefresh();

    }

    @Override
    public void onRefresh() {
        if (currentType == null)
            libListModel.getLibs(1);
        else
            libListModel.getLibsByType(this.currentType.getObjectId(), 1);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this.context, LibDetailActivity.class);
        intent.putExtra(LibDetailAtyModel.SELECTEDITEM, data.get(position));
        this.context.startActivity(intent);
    }

    @Override
    public void onCompleted() {
        this.setSwipeRefreshLayoutRefreshing(false);
        this.setProgressBarVisible(false);
    }

    @Override
    public void onError() {
        this.setProgressBarVisible(false);
        this.setSwipeRefreshLayoutRefreshing(false);
    }

    @Override
    public void onSuccess(List<Library> data) {
        this.setData(data);
    }
}
