package com.pa.devbox.di.component;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.modle.TypeListModel;

import dagger.Component;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:09.
 * Email: whailong2010@gmail.com
 */

@Component(modules = TypeListModel.class)
public interface TypeFragComponent {

    void inject(MainActivity context);
}
