package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.view.View;

import com.pa.devbox.ui.aty.BaseActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:08.
 * Email: whailong2010@gmail.com
 */
public class AccountFragModel extends BaseObservable {
    private BaseActivity context;


    public AccountFragModel(BaseActivity context) {
        this.context = context;


    }

    public void accountViewOnClick(View view) {

        Platform qqLogin = ShareSDK.getPlatform(QQ.NAME);

        qqLogin.authorize();


    }


}
