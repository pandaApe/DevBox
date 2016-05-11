package com.pa.devbox.domain;

import com.cm.retrofit2.converter.file.FileConverterFactory;
import com.cm.retrofit2.converter.file.body.HttpClientHelper;
import com.cm.retrofit2.converter.file.body.ProgressResponseListener;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;

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

    public static <T> T createDownloadService(Class<T> clazz, ProgressResponseListener listener) {
        OkHttpClient client = HttpClientHelper.addProgressResponseListener(new OkHttpClient.Builder(), listener).build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.leancloud.cn/1.1/")
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
