package com.pa.devbox.di.component;

import com.pa.devbox.di.module.MainAtyModule;
import com.pa.devbox.ui.aty.MainActivity;

import dagger.Component;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 30/4/16 21:42.
 * Email: whailong2010@gmail.com
 */
@Component(modules = MainAtyModule.class)
public interface MainAtyComponent {
    void inject(MainActivity aty);
}
