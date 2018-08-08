package com.example.edvinas.bakingapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseActivity;
import com.example.edvinas.bakingapp.base.BaseApplication;
import com.example.edvinas.bakingapp.ui.ingredients.IngredientsActivity;
import com.example.edvinas.bakingapp.ui.ingredients.IngredientsFragment;
import com.example.edvinas.bakingapp.ui.instructions.InstructionsActivity;
import com.example.edvinas.bakingapp.ui.instructions.InstructionsFragment;
import com.example.edvinas.bakingapp.utils.DefaultDeviceTypeChecker;
import com.example.edvinas.bakingapp.utils.network.pojo.Ingredients;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;
import com.example.edvinas.bakingapp.utils.widget.RecipeWidgetHandler;

import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity {
    public static final String KEY_RECIPE = "key.recipe";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nameCardView)
    CardView nameCardView;

    public static void starter(Recipe recipe, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_RECIPE, recipe);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int masterLayoutContainerId = R.id.masterLayoutContainer;
        setHomeAsUp();
        boolean isTablet = DefaultDeviceTypeChecker.isTablet(getResources().getConfiguration());
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(KEY_RECIPE);
        setToolbarTitle(recipe.getName());
        List<Ingredients> ingredients = recipe.getIngredients();
        if (savedInstanceState == null && isTablet) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(masterLayoutContainerId, IngredientsFragment.newInstance(ingredients))
                    .commit();
        }
        nameCardView.setOnClickListener(view -> {
            if (isTablet) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(masterLayoutContainerId, IngredientsFragment.newInstance(ingredients))
                        .commit();
            } else {
                IngredientsActivity.starter(ingredients, this);
            }
        });
        InstructionsAdapter adapter = new InstructionsAdapter(steps -> {
            if (isTablet) {
                InstructionsFragment instructionsFragment = InstructionsFragment.newInstance(steps);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(masterLayoutContainerId, instructionsFragment)
                        .commit();
            } else {
                InstructionsActivity.starter(steps, this);
            }
        });
        adapter.setItems(recipe.getSteps());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            RecipeWidgetHandler.startActionChangeIngredientList(this);
            Recipe recipe = (Recipe) getIntent().getSerializableExtra(KEY_RECIPE);
            BaseApplication.getRecipeHolder(this).setRecipe(recipe);
            String toastText = getString(R.string.details_recipe_added_to_widget);
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }
}
