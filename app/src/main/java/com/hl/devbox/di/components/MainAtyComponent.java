package com.hl.devbox.di.components;

import android.support.v4.app.FragmentActivity;

import com.hl.devbox.di.modules.MainAtyModule;

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
    void inject(FragmentActivity aty);
}
