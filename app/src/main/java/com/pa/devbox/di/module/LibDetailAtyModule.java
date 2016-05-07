package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.LibDetailActivity;
import com.pa.devbox.ui.viewModel.LibDetailAtyModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:20.
 * Email: whailong2010@gmail.com
 */

@Module
public class LibDetailAtyModule {

    private LibDetailActivity context;

    public LibDetailAtyModule(LibDetailActivity context) {
        this.context = context;
    }

    @Provides
    LibDetailAtyModel provideLibDetailModel(LibDetailActivity context) {
        return new LibDetailAtyModel(context);
    }

    @Provides
    LibDetailActivity provideContext() {
        return this.context;
    }
}
