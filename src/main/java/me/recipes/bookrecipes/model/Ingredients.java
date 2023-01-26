package me.recipes.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    private String nameIngredients;
    private int amount;
    private String measureUnit;
}