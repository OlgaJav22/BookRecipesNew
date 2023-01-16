package me.recipes.bookrecipes.services;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;

public interface RecipesService {
    Ingredients addNewIngredients(Ingredients ingredients);

    Ingredients getIngredients(long id);

    BookRecipes addNewRecipes(BookRecipes bookRecipes);

    BookRecipes getRecipes(long id);
}
