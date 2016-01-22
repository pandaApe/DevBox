package com.oscode.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscode.R;
import com.oscode.model.OSCodeType;

import java.util.ArrayList;

/**
 * Created by whailong on 15/1/16.
 */
public class PageFragmentRecycleViewAdapter extends RecyclerView.Adapter<PageFragmentRecycleViewAdapter.NormalTextViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<OSCodeType> OSCodeTypes;

    public PageFragmentRecycleViewAdapter(Context context, ArrayList<OSCodeType> OSCodeTypes) {
        this.OSCodeTypes = OSCodeTypes;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_types, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        String str = OSCodeTypes.get(position).getNameCN() + "(" + OSCodeTypes.get(position).getNameEn() + ")";
        holder.tvType.setText(str);
    }

    @Override
    public int getItemCount() {
        return OSCodeTypes.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView tvType;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);

        }
    }
}
