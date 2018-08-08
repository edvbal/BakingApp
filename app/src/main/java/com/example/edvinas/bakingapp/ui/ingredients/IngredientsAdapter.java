package com.example.edvinas.bakingapp.ui.ingredients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseAdapter;
import com.example.edvinas.bakingapp.base.BaseViewHolder;
import com.example.edvinas.bakingapp.utils.network.pojo.Ingredients;

public class IngredientsAdapter extends BaseAdapter<Ingredients> {
    @Override
    public BaseViewHolder<Ingredients> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_ingredients, parent, false);
        return new IngredientsViewHolder(itemView);
    }
}
