package com.devbox.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devbox.R;
import com.devbox.model.CodeType;

import java.util.ArrayList;

/**
 * Created by whailong on 15/1/16.
 */
public class TypeFragRVAdapter extends RecyclerView.Adapter<TypeFragRVAdapter.NormalTextViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<CodeType> CodeTypes;

    public TypeFragRVAdapter(Context context, ArrayList<CodeType> CodeTypes) {
        this.CodeTypes = CodeTypes;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        String str = CodeTypes.get(position).getFullTitle();
        holder.tvType.setText(str);
    }

    @Override
    public int getItemCount() {
        return CodeTypes.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView tvType;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);

        }
    }
}
