package com.devbox.action;

/**
 * @Author: PandaApe.
 * @CreatedAt: 7/2/16 14:50.
 * @Email: whailong2010@gmail.com
 */
public interface HttpCallback<T> {

    void onSuccess(T data);

    void onFailure(String errorEvent, String errorMsg);

}
