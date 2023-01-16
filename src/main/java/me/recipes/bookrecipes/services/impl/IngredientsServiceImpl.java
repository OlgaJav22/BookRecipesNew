package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class IngredientsServiceImpl {

    private static long countId = 0;
    private static Map<Long, Ingredients> ingredientsMap = new LinkedHashMap<>();

    public Ingredients appNewIngredients (Ingredients ingredients) {
        ingredientsMap.getOrDefault(countId++, ingredients);
        return ingredients;
    }

    public Ingredients getIngredients(long id) {
        return ingredientsMap.get(id);
    }

}
