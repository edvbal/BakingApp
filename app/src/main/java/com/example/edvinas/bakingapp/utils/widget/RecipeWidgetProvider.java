package com.example.edvinas.bakingapp.utils.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.edvinas.bakingapp.R;
import com.example.edvinas.bakingapp.base.BaseApplication;
import com.example.edvinas.bakingapp.utils.network.pojo.Recipe;

public class RecipeWidgetProvider extends AppWidgetProvider {

    public static void updateRecipeWidget(
            Context context,
            AppWidgetManager appWidgetManager,
            int appWidgetId
    ) {
        Recipe recipe = BaseApplication.getRecipeHolder(context).getRecipe();
        if (recipe != null) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
            views.setTextViewText(R.id.widgetTitleTextView, recipe.getName());
            Intent intent = new Intent(context, RecipeWidgetService.class);
            views.setRemoteAdapter(R.id.ingredientsListView, intent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RecipeWidgetHandler.startActionChangeIngredientList(context);
    }

    public static void updateRecipeWidgets(
            Context context,
            AppWidgetManager appWidgetManager,
            int[] recipeWidgetIds
    ) {
        for (int appWidgetId : recipeWidgetIds) {
            updateRecipeWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Perform any action when an AppWidget for this provider is instantiated
    }

    @Override
    public void onDisabled(Context context) {
        // Perform any action when the last AppWidget instance for this provider is deleted
    }
}

