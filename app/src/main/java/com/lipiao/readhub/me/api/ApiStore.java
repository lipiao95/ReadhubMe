package com.lipiao.readhub.me.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lipiao
 * @data 2018/5/24.
 */
public class ApiStore {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.API_HOST)
            .client(new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor(
                            message -> Log.e("retrofit", message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static HotTopicStore getHotTopicStore() {
        return retrofit.create(HotTopicStore.class);
    }
}
