package com.pa.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 6/3/16 15:56.
 * @Email: whailong2010@gmail.com
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<T> arrayList;
    private int mLayoutId;

    public BaseAdapter(Context context, ArrayList<T> arrayList, int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.arrayList = arrayList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
