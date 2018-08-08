package com.example.edvinas.bakingapp.base;

import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

public class RecipeHolder {
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
