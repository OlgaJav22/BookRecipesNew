package me.recipes.bookrecipes.services;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;

public interface RecipesService {

    BookRecipes addNewRecipes(BookRecipes bookRecipes);

    BookRecipes getRecipes(long id);
}
