package com.pa.devbox.di.component;

import com.pa.devbox.di.module.FragmentModule;
import com.pa.devbox.ui.fragment.AccountFragment;
import com.pa.devbox.ui.fragment.LibListFragment;
import com.pa.devbox.ui.fragment.TypeListFragment;

import dagger.Component;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:09.
 * Email: whailong2010@gmail.com
 */

@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(LibListFragment libListFragment);
    void inject(AccountFragment accountFragment);
    void inject(TypeListFragment context);
}
