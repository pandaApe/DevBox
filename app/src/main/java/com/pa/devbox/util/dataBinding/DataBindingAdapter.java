package com.pa.devbox.util.dataBinding;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.pa.devbox.ui.adapter.BaseAdapter;
import com.pa.devbox.ui.adapter.TabViewPageAdapter;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 26/4/16 23:29.
 * Email: whailong2010@gmail.com
 */
public class DataBindingAdapter {
    /*
    * SwipeRefreshLayout
    * */
    @BindingAdapter({"refreshEnable"})
    public static void bindStatus(SwipeRefreshLayout swipeRefreshLayout, boolean status) {
        swipeRefreshLayout.setEnabled(status);
    }

    @BindingAdapter({"onRefreshListener"})
    public static void bindStatus(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"refreshing"})
    public static void bindRefreshingStatus(SwipeRefreshLayout swipeRefreshLayout, boolean status) {
        swipeRefreshLayout.setRefreshing(status);
    }

    /*
    * ContentLoadingProgressBar
    * */
    @BindingAdapter({"progressBarVisible"})
    public static void bindStatus(ContentLoadingProgressBar progressBar, boolean status) {
        if (status)
            progressBar.show();
        else
            progressBar.hide();
    }

    /*
    * ImageView
    * */
    @BindingAdapter({"imgUrl"})
    public static void bindImgUrl(ImageView imageView, String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl))
            Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }


    /*
    * For Recycler View
    * */
    @BindingAdapter({"recyclerViewAdapter"})
    public static void bindAdapter(RecyclerView recyclerView, BaseAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"recyclerViewLayout"})
    public static void bindLayout(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"recyclerViewData"})
    public static void bindData(RecyclerView recyclerView, List data) {
        BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.data = data;
            adapter.notifyDataSetChanged();
        }
    }

    /*
    normal state [0]
    progress state [1-99]
    success state [100]
    error state [-1]
    */
    @BindingAdapter({"circularProgress"})
    public static void bindProgress(CircularProgressButton circularBtn, int progress) {
        circularBtn.setProgress(progress);
    }

    @BindingAdapter({"indeterminateProgressMode"})
    public static void bindProgressMode(CircularProgressButton circularBtn, boolean status) {
        if (status) {
            circularBtn.setIndeterminateProgressMode(true);
            circularBtn.setProgress(50);
        }else {
            circularBtn.setIndeterminateProgressMode(false);
        }
    }

    /*
    *
    **/
    @BindingAdapter({"aViewPagerAdapter"})
    public static void bindViewPagerAdapter(ViewPager viewPager, TabViewPageAdapter adapter) {
        viewPager.setAdapter(adapter);
        asmartTabLayout.setViewPager(viewPager);
    }

    // TODO: 7/5/16 This is not a good idea
    private static SmartTabLayout asmartTabLayout;

    @BindingAdapter({"viewPager"})
    public static void bindViewPager(SmartTabLayout smartTabLayout, ViewPager viewPager) {
        smartTabLayout.setViewPager(viewPager);
        asmartTabLayout = smartTabLayout;
    }

    @BindingAdapter({"offscreenPageLimit"})
    public static void bindOffscreenPageLimit(ViewPager viewPager, int offscreenPageLimit) {
        viewPager.setOffscreenPageLimit(offscreenPageLimit);
    }

    @BindingAdapter({"currentItemIndex"})
    public static void bindCurrentItemIndex(ViewPager viewPager, int currentItemIndex) {
        viewPager.setCurrentItem(currentItemIndex);
    }


}
