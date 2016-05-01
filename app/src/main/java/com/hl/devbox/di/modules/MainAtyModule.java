package com.hl.devbox.di.modules;

import android.support.v4.app.FragmentActivity;

import com.hl.devbox.ui.viewModel.MainAtyModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 30/4/16 21:43.
 * @Email: whailong2010@gmail.com
 */

@Module
public class MainAtyModule {
    private FragmentActivity context;

    public MainAtyModule(FragmentActivity context) {
        this.context = context;
    }

    @Provides
    MainAtyModel provideMainAtyModel(FragmentActivity context) {
        return new MainAtyModel(context);
    }

    @Provides
    FragmentActivity provideContext() {
        return this.context;
    }

}
