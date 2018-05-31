package com.sqisland.tutorial.recipes.ui.recipe;

/**
 * Created by Diogo Bittencourt on 31/05/18.
 */
public interface RecipeContract {

    interface View {
        void showRecipeNotFoundError();

        void setTitle(String title);

        void setDescription(String description);

        void setFavorite(boolean favorite);
    }

    interface Listener {

    }
}