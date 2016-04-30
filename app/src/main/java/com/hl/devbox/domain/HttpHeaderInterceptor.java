package com.hl.devbox.domain;

import com.hl.devbox.utils.CipherUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 30/4/16 10:42.
 * @Email: whailong2010@gmail.com
 */
public class HttpHeaderInterceptor implements Interceptor {

    public static final String APPKey = "OkaU2qxpo1fbHPtc9o7yQVgM";
    public static final String APPId = "OOhkF87ffBYBHmvph465ApmV-gzGzoHsz";
    public static final String MasterKey = "b1PRoxgF967vn4cXalY3DK0Y";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //If it isn't leancloud request, like github/clouddn, I just return it without any handling.
        if (!request.url().toString().contains("leancloud"))
            return  chain.proceed(request);

        Request newRequest = request
                .newBuilder()
                .header("X-LC-Id", APPId)
                .header("Content-Type", "application/json")
                .header("X-LC-Sign", generateLCSign())
                .build();

        return chain.proceed(newRequest);
    }

    private String generateLCSign() {
        String timeStamp = new Timestamp(new Date().getTime()).toString();
        return CipherUtils.md5(timeStamp + APPKey) + "," + timeStamp;
    }
}
