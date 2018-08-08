package com.example.edvinas.bakingapp.utils.network.pojo;

import java.io.Serializable;

public class Ingredients implements Serializable {
    private final double quantity;
    private final String measure;
    private final String ingredient;

    Ingredients(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
