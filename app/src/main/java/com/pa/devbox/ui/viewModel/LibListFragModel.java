package com.pa.devbox.ui.viewModel;

import android.databinding.Bindable;

import com.pa.devbox.ui.adapter.LibListAdapter;
import com.pa.devbox.ui.adapter.TypeListAdapter;
import com.pa.devbox.ui.aty.MainActivity;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 7/5/16 10:01.
 * @Email: whailong2010@gmail.com
 */
public class LibListFragModel<T> extends ListBaseModel {

    @Bindable
    LibListAdapter adapter;

    public LibListFragModel(MainActivity context) {
        super(context);
        swipeRefreshLayoutStatus = true;
        adapter = new LibListAdapter<T>(context, data);
    }
}
