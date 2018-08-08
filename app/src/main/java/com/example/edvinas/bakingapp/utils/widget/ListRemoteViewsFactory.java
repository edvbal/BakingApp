package com.example.edvinas.bakingapp.utils.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseApplication;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private Recipe recipe;

    public ListRemoteViewsFactory(Context applicationContext) {
        context = applicationContext;
    }

    @Override
    public void onCreate() {
        // empty
    }

    @Override
    public void onDataSetChanged() {
        recipe = BaseApplication.getRecipeHolder(context).getRecipe();
    }

    @Override
    public void onDestroy() {
        // empty
    }


    @Override
    public int getCount() {
        return recipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
        String ingredient = recipe.getIngredients().get(position).getIngredient();
        row.setTextViewText(R.id.ingredientTextView, ingredient);
        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}