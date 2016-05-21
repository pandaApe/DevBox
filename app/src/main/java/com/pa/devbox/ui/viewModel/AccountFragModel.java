package com.pa.devbox.ui.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.pa.devbox.BR;
import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.rest.Auth;
import com.pa.devbox.domain.entity.rest.QQAuth;
import com.pa.devbox.ui.aty.BaseActivity;
import com.pa.devbox.ui.modle.AccountModel;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;


/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 15:08.
 * Email: whailong2010@gmail.com
 */
public class AccountFragModel extends BaseObservable implements PlatformActionListener, HttpRequestCallback {
    private BaseActivity context;
    private AccountModel accountModel;


    @Bindable
    private String nickName;

    public AccountFragModel(BaseActivity context) {
        this.context = context;

        accountModel = new AccountModel();
        accountModel.setHttpRequestCallback(this);
    }

    public void accountViewOnClick(View view) {

        Platform qqLogin = ShareSDK.getPlatform(QQ.NAME);
        qqLogin.setPlatformActionListener(this);
        qqLogin.authorize();

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.d("onComplete", ": " + platform.getDb().get("nickname"));

        String accessToken = platform.getDb().getToken(); // 获取授权token
        String openId = platform.getDb().getUserId(); // 获取用户在此平台的ID
        long expiresIn = platform.getDb().getExpiresIn(); // 获取用户在此平台的ID

        QQAuth qqAuth = new QQAuth();
        qqAuth.setAccess_token(accessToken);
        qqAuth.setOpenid(openId);
        qqAuth.setExpires_in(expiresIn);

        Auth auth = new Auth();
        auth.setAuthData(new Auth.AuthData().setQq(qqAuth));
//        context.showProgressDialog("登录中...");
        accountModel.login(auth);

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.e("onError", ": " + throwable);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.d("onCancel", ": ");
    }

    @Override
    public void onCompleted() {
//        context.hideProgressDialog();
        this.setNickName("haha");
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(List data) {

    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        notifyPropertyChanged(BR.nickName);
    }
}
