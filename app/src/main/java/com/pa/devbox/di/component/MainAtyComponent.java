package com.pa.devbox.di.component;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.pa.devbox.di.module.MainAtyModule;

import dagger.Component;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 30/4/16 21:42.
 * @Email: whailong2010@gmail.com
 */
@Component(modules = MainAtyModule.class)
public interface MainAtyComponent {
    void inject(AppCompatActivity aty);
}
