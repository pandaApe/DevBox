package com.hl.devbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hl.devbox.R;
import com.hl.devbox.domain.entity.Library;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by whailong on 23/1/16.
 */
public class LibListAdapter extends RecyclerView.Adapter<LibListAdapter.CodeLibViewHolder> {
    private final DisplayImageOptions options;
    private Context context;
    private ArrayList<Library> codeLibs;
    private LayoutInflater layoutInflater;


    public LibListAdapter(Context context, ArrayList<Library> codeLibs) {
        this.context = context;
        this.codeLibs = codeLibs;
        layoutInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.temp)
                .showImageForEmptyUri(R.mipmap.temp)
                .showImageOnFail(R.mipmap.temp)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public LibListAdapter.CodeLibViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CodeLibViewHolder viewHolder = new CodeLibViewHolder(layoutInflater.inflate(R.layout.item_lib, parent, false));
        viewHolder.setOnClickListenner(new CodeLibViewHolder.ItemOnClickListenner() {
            @Override
            public void onClick(View v, int position) {
                if (itemOnClickListenner != null)
                    itemOnClickListenner.onClick(v, position);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LibListAdapter.CodeLibViewHolder holder, int position) {
        Library codeLib = codeLibs.get(position);
        holder.tvLibName.setText(codeLib.getName());
        ImageLoader.getInstance().displayImage(codeLib.getImage().getUrl(), holder.ivLibPreFace, this.options);
    }

    @Override
    public int getItemCount() {
        return this.codeLibs.size();
    }

    public static class CodeLibViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivLibPreFace;
        TextView tvLibName;
        private ItemOnClickListenner onClickListenner;

        public CodeLibViewHolder(View itemView) {
            super(itemView);
            ivLibPreFace = (ImageView) itemView.findViewById(R.id.iv_libPreface);
            tvLibName = (TextView) itemView.findViewById(R.id.tv_libName);
            ivLibPreFace.setOnClickListener(this);
            tvLibName.setOnClickListener(this);
        }

        public void setOnClickListenner(ItemOnClickListenner onClickListenner) {
            this.onClickListenner = onClickListenner;
        }

        @Override
        public void onClick(View v) {
            if (onClickListenner != null)
                onClickListenner.onClick(v, getAdapterPosition());
        }

        public interface ItemOnClickListenner {
            void onClick(View v, int index);
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