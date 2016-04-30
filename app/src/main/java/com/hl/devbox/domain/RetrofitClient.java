package com.hl.devbox.domain;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 28/4/16 21:32.
 * @Email: whailong2010@gmail.com
 */
public class RetrofitClient {

    private String BASE_URL = "https://api.leancloud.cn/1.1/";


    private static RetrofitClient singleton = new RetrofitClient();

    public RetrofitClient shareInstance() {
        return singleton;
    }

    private Retrofit retrofit;

    public RetrofitClient() {
        createRetrofit();
    }

    private void createRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(getHttpHeaderInterceptor())
                .addInterceptor(getLoggingInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<?> clazz) {
        return (T) retrofit.create(clazz);
    }

    private Interceptor getLoggingInterceptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return httpLoggingInterceptor;
    }

    private Interceptor getHttpHeaderInterceptor() {

        return new HttpHeaderInterceptor();
    }


}
