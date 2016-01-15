package com.oscode.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscode.R;

/**
 * Created by whailong on 15/1/16.
 */
public class PageFragmentRecycleViewAdapter extends RecyclerView.Adapter<PageFragmentRecycleViewAdapter.NormalTextViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mTitles;

    public PageFragmentRecycleViewAdapter(Context context) {
        mTitles = new String[]{"0", "1", "2", "3",};
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_types_recycleview, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.tvType.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView tvType;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);

        }
    }
}
