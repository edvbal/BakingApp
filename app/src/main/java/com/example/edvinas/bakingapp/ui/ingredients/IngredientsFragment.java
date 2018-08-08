package com.example.edvinas.bakingapp.ui.ingredients;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseFragment;
import com.example.edvinas.bakingapp.utils.network.pojo.Ingredients;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IngredientsFragment extends BaseFragment {
    private static final String KEY_INGREDIENTS_INFO = "key.quantityInfo";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static IngredientsFragment newInstance(List<Ingredients> ingredients) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_INGREDIENTS_INFO, ingredients.toArray());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) {
            return;
        }
        Object[] ingredients = (Object[]) getArguments().getSerializable(KEY_INGREDIENTS_INFO);
        if (ingredients == null) {
            return;
        }
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (Object ingredient : ingredients) {
            ingredientsList.add(((Ingredients) ingredient));
        }
        IngredientsAdapter adapter = new IngredientsAdapter();
        adapter.setItems(ingredientsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ingredients;
    }
}
