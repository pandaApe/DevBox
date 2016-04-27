package com.hl.devbox.utils.dataBing;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.hl.devbox.domain.entity.Library;
import com.hl.devbox.ui.adapter.LibListAdapter;

import java.util.List;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 26/4/16 23:29.
 * @Email: whailong2010@gmail.com
 */
public class DataBindingAdapter {

    @BindingAdapter({"adapter"})
    public static void bindAdapter(RecyclerView recyclerView, LibListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"data"})
    public static void bindData(RecyclerView recyclerView, List<Library> data) {
        LibListAdapter adapter = (LibListAdapter) recyclerView.getAdapter();
        adapter.setCodeLibs(data);
        adapter.notifyDataSetChanged();
    }


    @BindingAdapter({""})


}
