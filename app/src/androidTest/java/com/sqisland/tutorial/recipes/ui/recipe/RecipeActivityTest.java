package com.sqisland.tutorial.recipes.ui.recipe;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.test.RecipeRobot;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public class RecipeActivityTest {
    private static final String CARROTS_ID = "creamed_carrots";
    @Rule
    public ActivityTestRule<RecipeActivity> activityRule
            = new ActivityTestRule<>(
            RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound() {
        new RecipeRobot()
                .launch(activityRule)
                .noTitle()
                .description(R.string.recipe_not_found);
    }

    @Test
    public void clickToFavorite() {
        launchRecipe(CARROTS_ID);

        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }

    @Test
    public void alreadyFavorite() {
        new RecipeRobot()
                .setFavorite(CARROTS_ID)
                .launch(activityRule, CARROTS_ID)
                .isFavorite();
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityRule.launchActivity(intent);
    }
}