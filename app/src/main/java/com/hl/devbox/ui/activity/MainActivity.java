package com.hl.devbox.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hl.devbox.R;
import com.hl.devbox.ui.adapter.TabViewPageAdapter;
import com.hl.devbox.ui.fragment.AccountFragment;
import com.hl.devbox.ui.fragment.LibListFragment;
import com.hl.devbox.ui.fragment.TypeListFrament;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagerTab;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private TabViewPageAdapter adapter;
    private ArrayList<String> tabTitles = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TypeListFrament.newInstance(0));
        fragments.add(LibListFragment.newInstance(1));
        fragments.add(AccountFragment.newInstance(3));

        String[] titles = getResources().getStringArray(R.array.tabTitles);
        tabTitles.addAll(Arrays.asList(titles));

        adapter = new TabViewPageAdapter(getSupportFragmentManager(), fragments);
        adapter.titles = tabTitles;
        viewPager.setAdapter(adapter);

        viewpagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewpagerTab.setViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);
    }

}
