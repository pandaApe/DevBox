package com.pa.devbox.di.component;

import com.pa.devbox.di.module.TypeFragModule;
import com.pa.devbox.ui.fragment.TypeListFragment;

import dagger.Component;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:09.
 * Email: whailong2010@gmail.com
 */

@Component(modules = TypeFragModule.class)
public interface TypeFragComponent {

    void inject(TypeListFragment context);
}
