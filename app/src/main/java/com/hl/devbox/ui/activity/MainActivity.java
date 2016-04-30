package com.hl.devbox.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hl.devbox.R;
import com.hl.devbox.databinding.ActivityMainBinding;
import com.hl.devbox.ui.viewModel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

//    private TabViewPageAdapter adapter;
//    private ArrayList<String> tabTitles = new ArrayList<>();
//

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel.contentViewPager = binding.viewpager;
        binding.setViewModel(mainViewModel);

//
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(TypeListFrament.newInstance(0));
//        fragments.add(LibListFragment.newInstance(1));
//        fragments.add(AccountFragment.newInstance(3));
//
//        String[] titles = getResources().getStringArray(R.array.tabTitles);
//        tabTitles.addAll(Arrays.asList(titles));
//
//        adapter = new TabViewPageAdapter(getSupportFragmentManager(), fragments,tabTitles);
//
//        viewPager.setAdapter(adapter);
//
//        viewpagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
//        viewpagerTab.setViewPager(viewPager);
//        viewPager.setCurrentItem(1);
//        viewPager.setOffscreenPageLimit(3);
    }

}
