package com.pa.devbox.domain;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.pa.devbox.domain.FileDownloadHelper.FileConverterFactory;
import com.pa.devbox.domain.FileDownloadHelper.body.HttpClientHelper;
import com.pa.devbox.domain.FileDownloadHelper.body.ProgressResponseListener;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 28/4/16 21:32.
 * Email: whailong2010@gmail.com
 */
public class RetrofitClient {

    private String BASE_URL = "https://api.leancloud.cn/1.1/";


    private static RetrofitClient singleton = new RetrofitClient();

    public static RetrofitClient shareInstance() {
        return singleton;
    }

    public static RetrofitClient downloadClient() {
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

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public <T> T createDownloadService(Class<T> clazz, ProgressResponseListener listener) {
        OkHttpClient client = HttpClientHelper.addProgressResponseListener(new OkHttpClient.Builder(), listener).build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(this.BASE_URL)
                .addConverterFactory(FileConverterFactory.create())
                .build().create(clazz);
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
