package com.pa.devbox.ui.viewModel;

import android.databinding.Bindable;

import com.pa.devbox.ui.adapter.TypeListAdapter;
import com.pa.devbox.ui.aty.MainActivity;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 7/5/16 00:01.
 * @Email: whailong2010@gmail.com
 */
public class TypeListFragModel<T> extends ListBaseModel {

    @Bindable
    TypeListAdapter<T> adapter;

    public TypeListFragModel(MainActivity context) {
        super(context);
        swipeRefreshLayoutStatus = false;
        adapter = new TypeListAdapter<T>(context, data);
    }

}
