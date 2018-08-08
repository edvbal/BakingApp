package com.example.edvinas.bakingapp.ui.recipes;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseActivity;
import com.example.edvinas.bakingapp.ui.details.DetailsActivity;
import com.example.edvinas.bakingapp.utils.DefaultDeviceTypeChecker;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

import java.util.List;

import butterknife.BindView;

public class RecipesActivity extends BaseActivity implements RecipesContract.MainView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecipesContract.RecipePresenter presenter;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjections();
        presenter.onViewCreated();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recipe;
    }

    private void setupInjections() {
        adapter = new RecipesAdapter(recipe -> DetailsActivity.starter(recipe, this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (DefaultDeviceTypeChecker.isTablet(this.getResources().getConfiguration())) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        presenter = new RecipesPresenterFactory(this).create();
        presenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.dropView();
        super.onDestroy();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        adapter.setItems(recipes);
    }
}
