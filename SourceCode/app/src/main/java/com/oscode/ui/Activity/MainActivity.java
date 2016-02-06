package com.oscode.ui.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.oscode.R;
import com.oscode.ui.Adapter.TabViewPageAdapter;
import com.oscode.ui.Fragment.AccountFragment;
import com.oscode.ui.Fragment.LibDisplayFragment;
import com.oscode.ui.Fragment.TypeDisplayFrament;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TabViewPageAdapter adapter;
    private SmartTabLayout viewPagerTab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TypeDisplayFrament.newInstance(0));
        fragments.add(LibDisplayFragment.newInstance(1));
        fragments.add(LibDisplayFragment.newInstance(2));
        fragments.add(AccountFragment.newInstance(3));

        ArrayList<String> titles = new ArrayList<>();
        titles.add("分类");
        titles.add("最新");
        titles.add("最热");
        titles.add("帐户");

        adapter = new TabViewPageAdapter(getSupportFragmentManager(), fragments);
        adapter.titles = titles;

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(4);

        new FeedbackAgent(this).sync();
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
