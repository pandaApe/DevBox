package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.MainAtyModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 30/4/16 21:43.
 * Email: whailong2010@gmail.com
 */

@Module
public class MainAtyModule {
    private MainActivity context;

    public MainAtyModule(MainActivity context) {
        this.context = context;
    }

    @Provides
    MainAtyModel provideMainAtyModel(MainActivity context) {
        return new MainAtyModel(context);
    }

    @Provides
    MainActivity provideMainActivity() {
        return this.context;
    }

}
