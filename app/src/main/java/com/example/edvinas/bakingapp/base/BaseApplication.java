package com.example.edvinas.bakingapp.base;

import android.app.Application;
import android.content.Context;

import com.example.edvinas.bakingapp.utils.network.RetrofitFactory;

import retrofit2.Retrofit;

public class BaseApplication extends Application {
    private Retrofit retrofit;
    private RecipeHolder recipeHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new RetrofitFactory().create();
        recipeHolder = new RecipeHolder();
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }

    public static Retrofit getRetrofit(Context context) {
        return getBaseApplication(context).getRetrofit();
    }

    private RecipeHolder getRecipeHolder() {
        return recipeHolder;
    }

    public static RecipeHolder getRecipeHolder(Context context) {
        return getBaseApplication(context).getRecipeHolder();
    }

    private static BaseApplication getBaseApplication(Context context) {
        return ((BaseApplication) context.getApplicationContext());
    }
}
