package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.modle.LibListModel;
import com.pa.devbox.ui.viewModel.LibListFragModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 14:59.
 * Email: whailong2010@gmail.com
 */

@Module
public class LibFragModule {

    private MainActivity context;

    public LibFragModule(MainActivity context) {
        this.context = context;
    }

    @Provides
    LibListFragModel provideLibModel(MainActivity context) {
        return new LibListFragModel(context, new LibListModel());
    }

    @Provides
    MainActivity provideContext() {
        return this.context;
    }
}
