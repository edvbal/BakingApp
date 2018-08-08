package com.example.edvinas.bakingapp.utils.network;

import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RecipesService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Single<List<Recipe>> getRecipes();
}
