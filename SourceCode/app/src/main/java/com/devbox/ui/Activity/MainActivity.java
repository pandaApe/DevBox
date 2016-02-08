package com.devbox.ui.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.devbox.R;
import com.devbox.ui.Adapter.TabViewPageAdapter;
import com.devbox.ui.Fragment.AccountFragment;
import com.devbox.ui.Fragment.LibDisplayFragment;
import com.devbox.ui.Fragment.TypeDisplayFrament;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        new FeedbackAgent(this).sync();
    }


    private void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TypeDisplayFrament.newInstance(0));
        fragments.add(LibDisplayFragment.newInstance(1));
        fragments.add(LibDisplayFragment.newInstance(2));
        fragments.add(AccountFragment.newInstance(3));

        String[] titles = getResources().getStringArray(R.array.tabTitles);
        tabTitles.addAll(Arrays.asList(titles));

        adapter = new TabViewPageAdapter(getSupportFragmentManager(), fragments);
        adapter.titles = tabTitles;


        viewPager.setAdapter(adapter);

        viewpagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewpagerTab.setViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }
}
