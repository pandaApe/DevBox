package com.pa.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.ItemLibBinding;
import com.pa.devbox.domain.entity.Library;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 23/1/16 12:39.
 * Email: whailong2010@gmail.com
 */
public class LibListAdapter extends BaseAdapter<Library, LibListAdapter.LibItemHolder> {

    public LibListAdapter(Context context, List<Library> codeLibs) {
        super(context, codeLibs);
    }

    @Override
    public LibItemHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        return new LibItemHolder(this.layoutInflater.inflate(R.layout.item_lib, parent, false));
    }

    @Override
    public void onBindViewHolder(LibItemHolder holder, int position) {
        holder.bind(data.get(position));
        super.onBindViewHolder(holder,position);
    }

    public static class LibItemHolder extends RecyclerView.ViewHolder {

        private ItemLibBinding itemBinding;

        public LibItemHolder(View itemView) {
            super(itemView);
            itemBinding = ItemLibBinding.bind(itemView);
        }

        public void bind(Library library) {
            itemBinding.setLib(library);
        }
    }


}
