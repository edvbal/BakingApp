package com.example.edvinas.bakingapp.ui.recipes;

import com.example.edvinas.bakingapp.utils.network.RecipesService;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

import java.util.List;

import io.reactivex.Single;

class RecipesModel implements RecipesContract.RecipeModel {
    private final RecipesService service;

    RecipesModel(RecipesService service) {
        this.service = service;
    }

    @Override
    public Single<List<Recipe>> fetchRecipes() {
        return service.getRecipes();
    }
}
