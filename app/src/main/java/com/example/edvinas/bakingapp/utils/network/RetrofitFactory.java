package com.example.edvinas.bakingapp.utils.network;

import android.support.annotation.NonNull;

import com.example.edvinas.bakingapp.base.BaseFactory;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory implements BaseFactory<Retrofit> {
    @Override
    public Retrofit create() {
        RxJava2CallAdapterFactory scheduler
                = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addCallAdapterFactory(scheduler)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideHttpClient())
                .build();
    }

    private OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .build();
    }

    @NonNull
    private HttpLoggingInterceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
