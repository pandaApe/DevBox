package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.pa.devbox.BR;
import com.pa.devbox.R;
import com.pa.devbox.ui.adapter.TabViewPageAdapter;
import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.fragment.AccountFragment;
import com.pa.devbox.ui.fragment.LibListFragment;
import com.pa.devbox.ui.fragment.TypeListFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 26/4/16 23:23.
 * Email: whailong2010@gmail.com
 */
public class MainAtyModel extends BaseObservable {

    @Bindable
    public TabViewPageAdapter viewPagerAdapter;

    @Bindable
    public int currentItemIndex;

    @Bindable
    public int offscreenPageLimit;

    @Bindable
    public ViewPager contentViewPager;


    public MainAtyModel(MainActivity context) {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TypeListFragment());
        fragments.add(new LibListFragment());
        fragments.add(new AccountFragment());

        ArrayList<String> tabTitles = new ArrayList<>();
        String[] titles = context.getResources().getStringArray(R.array.tabTitles);
        tabTitles.addAll(Arrays.asList(titles));

        viewPagerAdapter = new TabViewPageAdapter(context.getSupportFragmentManager(), fragments, tabTitles);

        currentItemIndex = 1;

        offscreenPageLimit = 3;

    }


    public TabViewPageAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    public void setViewPagerAdapter(TabViewPageAdapter viewPagerAdapter) {
        this.viewPagerAdapter = viewPagerAdapter;
        notifyPropertyChanged(BR.contentViewPager);
    }

    public int getCurrentItemIndex() {
        return currentItemIndex;
    }

    public void setCurrentItemIndex(int currentItemIndex) {
        this.currentItemIndex = currentItemIndex;
        notifyPropertyChanged(BR.currentItemIndex);
    }

    public int getOffscreenPageLimit() {
        return offscreenPageLimit;
    }

    public void setOffscreenPageLimit(int offscreenPageLimit) {
        this.offscreenPageLimit = offscreenPageLimit;
        notifyPropertyChanged(BR.offscreenPageLimit);
    }

    public ViewPager getContentViewPager() {
        return contentViewPager;
    }

    public void setContentViewPager(ViewPager contentViewPager) {
        this.contentViewPager = contentViewPager;
        notifyPropertyChanged(BR.contentViewPager);
    }
}
