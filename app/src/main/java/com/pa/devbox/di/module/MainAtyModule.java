package com.pa.devbox.di.module;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.pa.devbox.ui.viewModel.MainAtyModel;

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
    private AppCompatActivity context;

    public MainAtyModule(AppCompatActivity context) {
        this.context = context;
    }

    @Provides
    MainAtyModel provideMainAtyModel(AppCompatActivity context) {
        return new MainAtyModel(context);
    }

    @Provides
    AppCompatActivity provideContext() {
        return this.context;
    }

}
