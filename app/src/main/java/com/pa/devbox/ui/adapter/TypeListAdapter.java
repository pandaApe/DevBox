package com.pa.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pa.devbox.R;
import com.pa.devbox.domain.entity.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whailong on 15/1/16.
 */
public class TypeListAdapter<T> extends RecyclerView.Adapter<TypeListAdapter.NormalTextViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<T> CodeTypes;

    public TypeListAdapter(Context context, List<T> CodeTypes) {
        this.CodeTypes = CodeTypes;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NormalTextViewHolder viewHolder = new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_type, parent, false));

        viewHolder.setOnClickListenner(new NormalTextViewHolder.ItemOnClickListenner() {
            @Override
            public void onClick(View v, int index) {
                itemOnClickListenner.onClick(v, index);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        String str = CodeTypes.get(position).getEnDescription();
        holder.tvType.setText(str);
    }

    @Override
    public int getItemCount() {
        return CodeTypes.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvType;
        private ItemOnClickListenner onClickListenner;

        public interface ItemOnClickListenner {
            void onClick(View v, int index);
        }

        public void setOnClickListenner(ItemOnClickListenner onClickListenner) {
            this.onClickListenner = onClickListenner;
        }

        @Override
        public void onClick(View v) {
            if (onClickListenner != null)
                onClickListenner.onClick(v, getAdapterPosition());
        }

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvType.setOnClickListener(this);
        }
    }

    private AdapterItemOnClickListenner itemOnClickListenner;

    public void setItemOnClickListenner(AdapterItemOnClickListenner itemOnClickListenner) {
        this.itemOnClickListenner = itemOnClickListenner;
    }

    public interface AdapterItemOnClickListenner {
        void onClick(View v, int index);
    }

}
