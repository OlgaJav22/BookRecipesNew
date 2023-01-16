package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.RecipesService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {

    private static long lastId = 0;
    private static Map<Long, BookRecipes> bookRecipesMap = new LinkedHashMap<>();

    @Override
    public BookRecipes addNewRecipes(BookRecipes bookRecipes) {
        bookRecipesMap.getOrDefault(lastId++, bookRecipes);
        return bookRecipes;
    }

    @Override
    public BookRecipes getRecipes(long id) {
        return bookRecipesMap.get(id);
    }

    @Override
    public Ingredients addNewIngredients(Ingredients ingredients) {
        return null;
    }

    @Override
    public Ingredients getIngredients(long id) {
        return null;
    }
}