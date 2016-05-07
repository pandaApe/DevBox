package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.AccountFragModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:06.
 * Email: whailong2010@gmail.com
 */

@Module
public class AccountFragModule {

    private MainActivity context;

    public AccountFragModule(MainActivity context) {
        this.context = context;
    }

    @Provides
    AccountFragModel provideAccountModel(MainActivity context) {
        return new AccountFragModel(context);
    }

    @Provides
    MainActivity provideContext() {
        return this.context;
    }
}
