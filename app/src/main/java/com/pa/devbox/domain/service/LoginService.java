package com.pa.devbox.domain.service;

import com.pa.devbox.domain.entity.User;
import com.pa.devbox.domain.entity.rest.Auth;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 21/5/16 16:29.
 * Email: whailong2010@gmail.com
 */
public interface LoginService {

    @POST("users")
    Observable<User> login(@Body Auth auth);

    @PUT("users/{objId}")
    Observable<ResponseBody> updateUserNickName(@Path("objId") String objId, @Body User user);
}
