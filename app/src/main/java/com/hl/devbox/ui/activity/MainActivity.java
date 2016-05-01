package com.hl.devbox.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hl.devbox.R;
import com.hl.devbox.databinding.ActivityMainBinding;
import com.hl.devbox.di.components.DaggerMainAtyComponent;
import com.hl.devbox.di.modules.MainAtyModule;
import com.hl.devbox.ui.viewModel.MainAtyModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainAtyModel mainViewModel;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        DaggerMainAtyComponent.builder()
                .mainAtyModule(new MainAtyModule(this))
                .build().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel.contentViewPager = binding.viewpager;
        binding.setViewModel(mainViewModel);
    }

}
