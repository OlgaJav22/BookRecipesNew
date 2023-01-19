package me.recipes.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredients {
    private String nameIngredients;
    private int amount;
    private String measureUnit;
}