package com.pa.devbox.di.module;

import android.os.Bundle;

import com.pa.devbox.ui.aty.BaseActivity;
import com.pa.devbox.ui.viewModel.AccountFragModel;
import com.pa.devbox.ui.viewModel.LibListFragModel;
import com.pa.devbox.ui.viewModel.TypeListFragModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:09.
 * Email: whailong2010@gmail.com
 */
@Module
public class FragmentModule {

    private BaseActivity context;
    private Bundle savedInstanceState;

    public FragmentModule(BaseActivity context, Bundle savedInstanceState) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
    }

    @Provides
    TypeListFragModel provideModel() {
        return new TypeListFragModel(context);
    }

    @Provides
    AccountFragModel provideAccountModel() {
        return new AccountFragModel(this.context);
    }

    @Provides
    LibListFragModel provideLibModel() {
        return new LibListFragModel(context,this.savedInstanceState);
    }

    @Provides
    BaseActivity provideContext() {
        return this.context;
    }
}
