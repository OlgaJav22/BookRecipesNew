package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private static long countId = 0;
    private static Map<Long, Ingredients> ingredientsMap = new LinkedHashMap<>();

    @Override
    public Ingredients addNewIngredients(Ingredients ingredients) {
        ingredientsMap.put(countId++, ingredients);
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(long id) {
        return ingredientsMap.get(id);
    }


}