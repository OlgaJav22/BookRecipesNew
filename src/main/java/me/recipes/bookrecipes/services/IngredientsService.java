package me.recipes.bookrecipes.services;

import me.recipes.bookrecipes.model.Ingredients;

public interface IngredientsService {

    Ingredients addNewIngredients(Ingredients ingredients);

    Ingredients getIngredients(long id);

    Ingredients editIngredients(long id, Ingredients ingredients);

    boolean deleteIngredients(long id);

    void getAllIngredients();
}
