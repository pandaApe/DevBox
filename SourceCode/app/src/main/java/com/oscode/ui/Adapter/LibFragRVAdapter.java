package com.oscode.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.oscode.R;
import com.oscode.model.OSCodeLib;
import com.oscode.ui.Activity.LibDetailActivity;

import java.util.ArrayList;

/**
 * Created by whailong on 23/1/16.
 */
public class LibFragRVAdapter extends RecyclerView.Adapter<LibFragRVAdapter.CodeLibViewHolder> {
    private final DisplayImageOptions options;
    private Context context;
    private ArrayList<OSCodeLib> codeLibs;
    private LayoutInflater layoutInflater;

    public LibFragRVAdapter(Context context, ArrayList<OSCodeLib> codeLibs) {
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
    public LibFragRVAdapter.CodeLibViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CodeLibViewHolder viewHolder = new CodeLibViewHolder(layoutInflater.inflate(R.layout.item_oslib, parent, false));
        viewHolder.setOnClickListenner(new CodeLibViewHolder.ItemOnClickListenner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(context, LibDetailActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LibFragRVAdapter.CodeLibViewHolder holder, int position) {
        OSCodeLib codeLib = codeLibs.get(position);
        holder.tvLibName.setText(codeLib.getLibName());
        ImageLoader.getInstance().displayImage(codeLib.getLicense(), holder.ivLibPreFace, this.options);
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
            void onClick(View v, int position);
        }
    }
}
