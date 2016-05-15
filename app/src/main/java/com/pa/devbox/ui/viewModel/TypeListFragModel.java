package com.pa.devbox.ui.viewModel;

import android.content.Intent;
import android.view.View;

import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.adapter.BaseAdapter;
import com.pa.devbox.ui.adapter.TypeListAdapter;
import com.pa.devbox.ui.aty.BaseActivity;
import com.pa.devbox.ui.aty.SpecificTypeActivity;
import com.pa.devbox.ui.modle.TypeListModel;

import java.util.List;


/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:01.
 * Email: whailong2010@gmail.com
 */
public class TypeListFragModel extends ListBaseModel<Type> implements HttpRequestCallback<Type>, BaseAdapter.OnItemClickListener {

    private TypeListModel listModel;

    public TypeListFragModel(BaseActivity context) {
        super(context);

        this.listModel = new TypeListModel();
        this.listModel.setCallback(this);
        swipeRefreshLayoutStatus = false;
        adapter = new TypeListAdapter(context, data);
        adapter.setItemClickListener(this);

        this.listModel.getTypes();
    }

    @Override
    public void onCompleted() {
        this.setProgressBarVisible(false);
    }

    @Override
    public void onError() {
        this.setProgressBarVisible(false);
    }

    @Override
    public void onSuccess(List<Type> data) {
        this.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this.context, SpecificTypeActivity.class);
        intent.putExtra(SpecificTypeActivity.SELECTEDITEM, data.get(position));
        this.context.startActivity(intent);
    }
}
