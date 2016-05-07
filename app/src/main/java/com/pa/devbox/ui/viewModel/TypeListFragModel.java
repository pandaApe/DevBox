package com.pa.devbox.ui.viewModel;

import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.adapter.TypeListAdapter;
import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.delegate.HttpRequestCallback;
import com.pa.devbox.ui.modle.TypeListModel;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:01.
 * Email: whailong2010@gmail.com
 */
public class TypeListFragModel extends ListBaseModel<Type> implements HttpRequestCallback<Type> {

    private TypeListModel listModel;

    public TypeListFragModel(MainActivity context, TypeListModel listModel) {
        super(context);

        this.listModel = listModel;
        this.listModel.setCallback(this);
        swipeRefreshLayoutStatus = false;
        adapter = new TypeListAdapter(context, data);
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
}
