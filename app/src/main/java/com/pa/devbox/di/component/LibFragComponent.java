package com.pa.devbox.di.component;

import com.pa.devbox.di.module.LibFragModule;
import com.pa.devbox.ui.fragment.LibListFragment;

import dagger.Component;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 14:59.
 * Email: whailong2010@gmail.com
 */
@Component(modules = LibFragModule.class)
public interface LibFragComponent {

    void inject(LibListFragment libListFragment);

}
