package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.modle.LibListModel;
import com.pa.devbox.ui.modle.TypeListModel;
import com.pa.devbox.ui.viewModel.AccountFragModel;
import com.pa.devbox.ui.viewModel.LibListFragModel;
import com.pa.devbox.ui.viewModel.TypeListFragModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:09.
 * Email: whailong2010@gmail.com
 */
@Module
public class FragmentModule {

    private MainActivity context;

    public FragmentModule(MainActivity context) {
        this.context = context;
    }

    @Provides
    TypeListFragModel provideModel(MainActivity context){
        return new TypeListFragModel(context,new TypeListModel());
    }

    @Provides
    AccountFragModel provideAccountModel(MainActivity context) {
        return new AccountFragModel(context);
    }

    @Provides
    LibListFragModel provideLibModel(MainActivity context) {
        return new LibListFragModel(context, new LibListModel());
    }

    @Provides
    MainActivity provideContext(){
        return this.context;
    }
}
