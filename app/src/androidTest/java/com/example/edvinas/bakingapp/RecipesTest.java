package com.example.edvinas.bakingapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.edvinas.bakingapp.base.BaseApplication;
import com.example.edvinas.bakingapp.base.RecipeHolder;
import com.example.edvinas.bakingapp.ui.details.DetailsActivity;
import com.example.edvinas.bakingapp.ui.instructions.InstructionsActivity;
import com.example.edvinas.bakingapp.ui.recipes.RecipesActivity;
import com.example.edvinas.bakingapp.utils.DefaultDeviceTypeChecker;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipesTest {
    private static final String KEY_INSTRUCTIONS = "key.instructions";

    @Rule
    public ActivityTestRule<RecipesActivity> activityTestRule =
            new ActivityTestRule<>(RecipesActivity.class);

    @Test
    public void onRecipesRecyclerItemClick_opensDetailsActivityWithExtras() {
        Intents.init();

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasExtraWithKey(DetailsActivity.KEY_RECIPE));

        Intents.release();
    }

    @Test
    public void onRecipeDetailsRecyclerViewClick_opensInstructions() {
        Resources resources = activityTestRule.getActivity().getApplicationContext().getResources();
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        boolean isTablet = DefaultDeviceTypeChecker.isTablet(resources.getConfiguration());
        if (!isTablet) {
            Intents.init();
            onView(withId(R.id.detailsRecyclerView))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            intended(hasComponent(InstructionsActivity.class.getName()));
            intended(hasExtraWithKey(KEY_INSTRUCTIONS));
            Intents.release();

            onView(withId(R.id.simpleExoPlayerView))
                    .check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.detailsRecyclerView))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            onView(withId(R.id.simpleExoPlayerView))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void onAddToWidgetClick_setsRecipeToRecipeHolder() {
        Context context = activityTestRule.getActivity().getApplicationContext();
        RecipeHolder holder = BaseApplication.getRecipeHolder(context);
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.action_add))
                .check(matches(isDisplayed()))
                .perform(click());

        Assert.assertNotNull(holder.getRecipe());
    }
}
