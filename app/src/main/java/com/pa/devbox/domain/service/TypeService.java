package com.pa.devbox.domain.service;

import com.pa.devbox.domain.entity.BaseResponse;
import com.pa.devbox.domain.entity.Type;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 27/4/16 23:13.
 * Email: whailong2010@gmail.com
 */
public interface TypeService {

    @GET("classes/Type")
    Observable<BaseResponse<Type>> getType();

}
