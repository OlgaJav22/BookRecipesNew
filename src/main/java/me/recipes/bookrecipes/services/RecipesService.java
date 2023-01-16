package me.recipes.bookrecipes.services;

import me.recipes.bookrecipes.model.BookRecipes;

public interface RecipesService {

    BookRecipes appNewRecipes(BookRecipes bookRecipes);

    BookRecipes getRecipes(long id);
}
