package com.pa.devbox.domain.delegate;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 15/5/16 19:59.
 * Email: whailong2010@gmail.com
 */
public interface LastCommitInfoCallback {

    void onCompleted();

    void onError();

    void onSuccess(String name, String msg);
}
