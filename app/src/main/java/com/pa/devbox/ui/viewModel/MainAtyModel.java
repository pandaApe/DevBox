package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.pa.devbox.R;
import com.pa.devbox.ui.adapter.TabViewPageAdapter;
import com.pa.devbox.ui.fragment.AccountFragment;
import com.pa.devbox.ui.fragment.LibListFragment;
import com.pa.devbox.ui.fragment.TypeListFrament;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 26/4/16 23:23.
 * @Email: whailong2010@gmail.com
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

    @Inject
    public MainAtyModel(FragmentActivity context) {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TypeListFrament.newInstance(0));
        fragments.add(LibListFragment.newInstance(1));
        fragments.add(AccountFragment.newInstance(3));

        ArrayList<String> tabTitles = new ArrayList<>();
        String[] titles = context.getResources().getStringArray(R.array.tabTitles);
        tabTitles.addAll(Arrays.asList(titles));

        viewPagerAdapter = new TabViewPageAdapter(context.getSupportFragmentManager(), fragments, tabTitles);

        currentItemIndex = 1;

        offscreenPageLimit = 3;

    }
}
