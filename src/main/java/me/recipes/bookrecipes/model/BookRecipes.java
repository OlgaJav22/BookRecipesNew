package me.recipes.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRecipes {
    private String name;
    private int cookingTime;
    private Ingredients ingredients[];


}
