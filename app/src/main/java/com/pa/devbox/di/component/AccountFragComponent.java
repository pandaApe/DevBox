package com.pa.devbox.di.component;

import com.pa.devbox.di.module.AccountFragModule;
import com.pa.devbox.ui.fragment.AccountFragment;

import dagger.Component;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:06.
 * Email: whailong2010@gmail.com
 */
@Component(modules = AccountFragModule.class)
public interface AccountFragComponent {

    void inject(AccountFragment accountFragment);

}
