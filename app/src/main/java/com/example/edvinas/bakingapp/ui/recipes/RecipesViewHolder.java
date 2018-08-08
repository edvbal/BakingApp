package com.example.edvinas.bakingapp.ui.recipes;

import android.view.View;
import android.widget.TextView;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseViewHolder;
import com.example.edvinas.bakingapp.utils.ItemClickListener;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecipesViewHolder extends BaseViewHolder<Recipe> {
    @BindView(R.id.nameTextView)
    TextView nameTextView;
    private View itemView;
    private final ItemClickListener<Recipe> itemClickListener;

    public RecipesViewHolder(View itemView, ItemClickListener<Recipe> itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Recipe recipe) {
        nameTextView.setText(recipe.getName());
        itemView.setOnClickListener(view -> itemClickListener.onItemClicked(getItem()));
    }
}
