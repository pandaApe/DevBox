package com.pa.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.ItemTypeBinding;
import com.pa.devbox.domain.entity.Type;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 15/1/16 12:39.
 * Email: whailong2010@gmail.com
 */
public class TypeListAdapter extends BaseAdapter<Type, TypeListAdapter.TypeItemHolder> {

    public TypeListAdapter(Context context, List<Type> types) {
        super(context, types);
    }

    @Override
    public TypeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeItemHolder(this.layoutInflater.inflate(R.layout.item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(TypeItemHolder holder, int position) {
        holder.bindData(this.data.get(position));
        super.onBindViewHolder(holder,position);
    }

    public static class TypeItemHolder extends RecyclerView.ViewHolder {
        private ItemTypeBinding typeBinding;

        public TypeItemHolder(View itemView) {
            super(itemView);
            typeBinding = ItemTypeBinding.bind(itemView);
        }

        public void bindData(Type type) {
            typeBinding.setType(type);
        }
    }

}
