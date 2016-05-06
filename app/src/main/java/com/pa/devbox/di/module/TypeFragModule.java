package com.pa.devbox.di.module;

import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.TypeListFragModle;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 7/5/16 00:09.
 * @Email: whailong2010@gmail.com
 */
@Module
public class TypeFragModule {

    private MainActivity context;

    public TypeFragModule(MainActivity context) {
        this.context = context;
    }

    @Provides
    TypeListFragModle provideModle(MainActivity context){
        return new TypeListFragModle();
    }

    @Provides
    MainActivity provideContext(){
        return this.context;
    }
}
