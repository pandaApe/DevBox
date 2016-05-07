package com.pa.devbox.ui.delegate;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 11:23.
 * Email: whailong2010@gmail.com
 */
public interface HttpRequestCallback<T> {

    void onCompleted();
    void onError();
    void onSuccess(List<T> data);
}
