package com.example.edvinas.bakingapp.ui.ingredients;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseActivity;
import com.example.edvinas.bakingapp.utils.network.pojo.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends BaseActivity {
    private static final String KEY_INGREDIENTS_INFO = "key.quantityInfo";

    public static void starter(List<Ingredients> ingredients, Context context) {
        Intent intent = new Intent(context, IngredientsActivity.class);
        intent.putExtra(KEY_INGREDIENTS_INFO, ingredients.toArray());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHomeAsUp();
        setToolbarTitle(getString(R.string.details_label_ingredients));
        Object[] ingredients = (Object[]) getIntent().getSerializableExtra(KEY_INGREDIENTS_INFO);
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (Object ingredient : ingredients) {
            ingredientsList.add(((Ingredients) ingredient));
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ingredientsContainer, IngredientsFragment.newInstance(ingredientsList))
                    .commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ingredients;
    }
}
