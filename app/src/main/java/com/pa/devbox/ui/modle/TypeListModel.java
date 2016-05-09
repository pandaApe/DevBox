package com.pa.devbox.ui.modle;

import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.BaseResponse;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.domain.service.TypeService;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:03.
 * Email: whailong2010@gmail.com
 */
public class TypeListModel {

    private TypeService typeService;
    private HttpRequestCallback<Type> callback;

    public void setCallback(HttpRequestCallback<Type> callback) {
        this.callback = callback;
    }

    @Inject
    public TypeListModel() {
        typeService = RetrofitClient
                .shareInstance()
                .create(TypeService.class);
    }

    public void getTypes() {
        Observable<BaseResponse<Type>> observable = typeService.getType();

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Type>>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(BaseResponse<Type> response) {
                        callback.onSuccess(response.getResults());
                    }
                });
    }
}
