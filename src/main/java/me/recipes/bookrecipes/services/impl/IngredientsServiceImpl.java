package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private static long countId = 0;
    private static Map<Long, Ingredients> ingredientsMap = new LinkedHashMap<>();

    @Override
    public Ingredients addNewIngredients(Ingredients ingredients) {
        if (!ingredients.getNameIngredients().isEmpty()) {
            ingredientsMap.put(countId++, ingredients);
        }
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(long id) {
        return ingredientsMap.get(id);
    }

    @Override
    public Ingredients editIngredients(long id, Ingredients ingredients) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredients);
            return ingredients;
        }
        return null;
    }

    @Override
    public boolean deleteIngredients(long id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Ingredients> getAllIngredients() {
        return Collections.unmodifiableCollection(ingredientsMap.values());
    }

}