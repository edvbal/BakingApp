package com.example.edvinas.bakingapp.utils.network.pojo;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private final String name;
    private final List<Ingredients> ingredients;
    private final List<Steps> steps;
    private final int servings;

    public Recipe(String name, List<Ingredients> ingredients, List<Steps> steps, int servings) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }
}
