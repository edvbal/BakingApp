package com.example.edvinas.bakingapp.utils.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.edvinas.bakingapp.R;

public class RecipeWidgetHandler extends IntentService {
    public static final String ACTION_UPDATE_RECIPE_WIDGET =
            "com.example.edvinas.bakingapp.utils.widget.action.update_recipe_widget";

    public RecipeWidgetHandler() {
        super("RecipeWidgetHandler");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_RECIPE_WIDGET.equals(action)) {
                handleActionUpdateRecipeWidget();
            }
        }
    }

    public static void startActionChangeIngredientList(Context context) {
        Intent intent = new Intent(context, RecipeWidgetHandler.class);
        intent.setAction(ACTION_UPDATE_RECIPE_WIDGET);
        context.startService(intent);
    }

    private void handleActionUpdateRecipeWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName name = new ComponentName(this, RecipeWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(name);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ingredientsListView);
        RecipeWidgetProvider.updateRecipeWidgets(this, appWidgetManager, appWidgetIds);
    }
}
