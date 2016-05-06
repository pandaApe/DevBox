package com.pa.devbox.util.dataBinding;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.adapter.LibListAdapter;
import com.pa.devbox.ui.adapter.TabViewPageAdapter;

import java.util.List;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 26/4/16 23:29.
 * @Email: whailong2010@gmail.com
 */
public class DataBindingAdapter {

    /*
    * For Recycler View
    * */
    @BindingAdapter({"recyclerViewAdapter"})
    public static void bindAdapter(RecyclerView recyclerView, LibListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"recyclerViewLayout"})
    public static void bindLayout(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"recyclerViewData"})
    public static void bindData(RecyclerView recyclerView, List<Library> data) {
        LibListAdapter adapter = (LibListAdapter) recyclerView.getAdapter();
        adapter.setCodeLibs(data);
        adapter.notifyDataSetChanged();
    }

    /*
    *
    **/
    @BindingAdapter({"viewPagerAdapter"})
    public static void bindViewPagerAdapter(ViewPager viewPager, TabViewPageAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"viewPager"})
    public static void bindViewPager(SmartTabLayout smartTabLayout, ViewPager viewPager) {
        smartTabLayout.setViewPager(viewPager);
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
