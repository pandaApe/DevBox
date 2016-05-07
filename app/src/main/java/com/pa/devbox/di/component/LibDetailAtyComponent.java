package com.pa.devbox.di.component;

import com.pa.devbox.di.module.LibDetailAtyModule;
import com.pa.devbox.ui.aty.LibDetailActivity;

import dagger.Component;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:20.
 * Email: whailong2010@gmail.com
 */
@Component(modules = LibDetailAtyModule.class)
public interface LibDetailAtyComponent {

    void inject(LibDetailActivity libDetailActivity);

}
