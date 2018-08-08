package com.example.edvinas.bakingapp.ui.recipes;

import android.content.Context;

import com.example.edvinas.bakingapp.base.BaseApplication;
import com.example.edvinas.bakingapp.utils.network.RecipesService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class RecipesPresenterFactory {
    private final Context context;

    public RecipesPresenterFactory(Context context) {
        this.context = context;
    }

    public RecipesContract.RecipePresenter create() {
        RecipesService service = BaseApplication.getRetrofit(context).create(RecipesService.class);
        return new RecipesPresenter(new RecipesModel(service), AndroidSchedulers.mainThread());
    }
}
