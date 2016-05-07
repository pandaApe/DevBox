package com.pa.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 6/3/16 15:56.
 * Email: whailong2010@gmail.com
 */
public class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    LayoutInflater layoutInflater;
    Context context;
    public List<T> data;

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        if (holder.itemView != null && this.itemClickListener != null)

            //Lambda expression
            holder.itemView.setOnClickListener((v) -> {
                        itemClickListener.onItemClick(v, position);
                    }
            );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

}
