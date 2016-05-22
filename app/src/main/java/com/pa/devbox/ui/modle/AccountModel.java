package com.pa.devbox.ui.modle;

import android.util.Log;

import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.User;
import com.pa.devbox.domain.entity.rest.Auth;
import com.pa.devbox.domain.service.LoginService;
import com.pa.devbox.util.PersistenceUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 21/5/16 16:41.
 * Email: whailong2010@gmail.com
 */
public class AccountModel {

    private LoginService loginService;

    private HttpRequestCallback httpRequestCallback;

//    private String nickName;

    public AccountModel() {
        loginService = RetrofitClient.shareInstance().create(LoginService.class);
    }


    public User getCurrentUser() {
        User user = new User();
        user.setNickName(PersistenceUtils.shareInstance().readString("DevBox", "nickName"));
        user.setSessionToken(PersistenceUtils.shareInstance().readString("DevBox", "sessionToken"));

        return user;
    }

    public void login(Auth auth) {

        Observable<User> observable = loginService.login(auth);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                               @Override
                               public void onCompleted() {
                                   Log.e("onCompleted", ": ");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("onError", ": " + e);
                               }

                               @Override
                               public void onNext(User user) {

                                   PersistenceUtils.shareInstance().write("DevBox", "sessionToken", user.getSessionToken());

                                   httpRequestCallback.onCompleted();
                                 /*  user.setNickName(nickName);
                                   Observable<ResponseBody> observable = loginService.updateUserNickName(user.getObjectId(), user);

                                   observable.subscribeOn(Schedulers.io())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .subscribe(new Observer<ResponseBody>() {
                                               @Override
                                               public void onCompleted() {

                                               }

                                               @Override
                                               public void onError(Throwable e) {

                                               }

                                               @Override
                                               public void onNext(ResponseBody user) {

                                               }
                                           });*/
                               }
                           }
                );
    }

    public void setHttpRequestCallback(HttpRequestCallback httpRequestCallback) {
        this.httpRequestCallback = httpRequestCallback;
    }
}
