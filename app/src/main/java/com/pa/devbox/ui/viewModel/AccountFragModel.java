package com.pa.devbox.ui.viewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.pa.devbox.BR;
import com.pa.devbox.R;
import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.User;
import com.pa.devbox.domain.entity.rest.Auth;
import com.pa.devbox.domain.entity.rest.QQAuth;
import com.pa.devbox.ui.aty.AboutActivity;
import com.pa.devbox.ui.aty.BaseActivity;
import com.pa.devbox.ui.modle.AccountModel;
import com.pa.devbox.util.PersistenceUtils;

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
    private User user;
    private BaseActivity context;
    private AccountModel accountModel;

    @Bindable
    private String nickName;
    @Bindable
    private String subTitle;

    public AccountFragModel(BaseActivity context) {
        this.context = context;

        accountModel = new AccountModel();
        accountModel.setHttpRequestCallback(this);
        user = accountModel.getCurrentUser();
        if (user.getNickName() != null) {
            this.setNickName(user.getNickName());
            this.setSubTitle(context.getString(R.string.fromQQ));
        }
    }

    public void accountViewOnClick(View view) {
        if (user == null) {

            Platform qqLogin = ShareSDK.getPlatform(QQ.NAME);
            qqLogin.setPlatformActionListener(this);
            qqLogin.authorize();
            context.showProgressDialog(context.getString(R.string.loggingIn));
        }
    }

    public void aboutViewOnClick(View v) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        //This method is called in sub thread.
        Log.e("currentThread: ", "" + (Looper.myLooper() == Looper.getMainLooper()));

        this.nickName = platform.getDb().get("nickname");

        String accessToken = platform.getDb().getToken(); // 获取授权token
        String openId = platform.getDb().getUserId(); // 获取用户在此平台的ID
        long expiresIn = platform.getDb().getExpiresIn();

        QQAuth qqAuth = new QQAuth();
        qqAuth.setAccess_token(accessToken);
        qqAuth.setOpenid(openId);
        qqAuth.setExpires_in(expiresIn);

        Auth auth = new Auth();
        auth.setAuthData(new Auth.AuthData().setQq(qqAuth));

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
        context.hideProgressDialog();

        PersistenceUtils.shareInstance().write("DevBox", "nickName", nickName);

        this.setNickName(nickName);
        this.setSubTitle(context.getString(R.string.fromQQ));
    }

    @Override
    public void onError() {
        context.showSnackBar("登录出现问题了");
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        notifyPropertyChanged(BR.subTitle);
    }
}
