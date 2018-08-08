package com.example.edvinas.bakingapp.ui.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseAdapter;
import com.example.edvinas.bakingapp.utils.ItemClickListener;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

public class RecipesAdapter extends BaseAdapter<Recipe> {
    private final ItemClickListener<Recipe> itemClickListener;

    public RecipesAdapter(ItemClickListener<Recipe> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipesViewHolder(itemView, itemClickListener);
    }
}
