package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.RecipesService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements RecipesService {

    private static long countId = 0;
    private static Map<Long, Ingredients> ingredientsMap = new LinkedHashMap<>();

    @Override
    public Ingredients addNewIngredients(Ingredients ingredients) {
        ingredientsMap.getOrDefault(countId++, ingredients);
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(long id) {
        return ingredientsMap.get(id);
    }

    @Override
    public BookRecipes addNewRecipes(BookRecipes bookRecipes) {
        return null;
    }

    @Override
    public BookRecipes getRecipes(long id) {
        return null;
    }
}