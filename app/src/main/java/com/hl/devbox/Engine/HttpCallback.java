package com.hl.devbox.Engine;

/**
 * @Author: PandaApe.
 * @CreatedAt: 7/2/16 14:50.
 * @Email: whailong2010@gmail.com
 */
public abstract class HttpCallback<T> {

    public void onSucess(T data) {
    }

    public void onFailure(AppException e) {
    }

    public void onSucess() {
    }

    public void onProgress(int percentage){}

}
