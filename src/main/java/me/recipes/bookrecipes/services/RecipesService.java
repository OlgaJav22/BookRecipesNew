package me.recipes.bookrecipes.services;

import me.recipes.bookrecipes.model.BookRecipes;

import java.util.Collection;

public interface RecipesService {

    BookRecipes addNewRecipes(BookRecipes bookRecipes);

    BookRecipes getRecipes(long id);

    BookRecipes editRecipes(long id, BookRecipes bookRecipes);

    boolean deleteRecipes(long id);

    Collection<BookRecipes> getAllRecipes();
}
