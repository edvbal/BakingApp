package com.example.edvinas.bakingapp.ui.recipes;

import com.example.edvinas.bakingapp.base.BasePresenter;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface RecipesContract {
    interface MainView {

        void showRecipes(List<Recipe> recipes);
    }

    interface RecipePresenter extends BasePresenter<MainView> {
        void onViewCreated();
    }

    interface RecipeModel {
        Single<List<Recipe>> fetchRecipes();
    }
}
