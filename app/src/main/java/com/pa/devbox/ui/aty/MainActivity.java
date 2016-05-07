package com.pa.devbox.ui.aty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.pa.devbox.R;
import com.pa.devbox.databinding.ActivityMainBinding;
import com.pa.devbox.di.component.DaggerMainAtyComponent;
import com.pa.devbox.di.module.MainAtyModule;
import com.pa.devbox.ui.viewModel.MainAtyModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainAtyModel mainAtyModel;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        DaggerMainAtyComponent.builder()
                .mainAtyModule(new MainAtyModule(this))
                .build().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainAtyModel.contentViewPager = binding.viewpager;
        binding.setViewModel(mainAtyModel);
    }

}