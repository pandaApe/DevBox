package com.hl.devbox.domain.service;

import com.hl.devbox.domain.entity.Type;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 27/4/16 23:13.
 * @Email: whailong2010@gmail.com
 */
public interface TypeService {

    @GET("classes/Type")
    Observable<Type> getType();

}
