package me.recipes.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRecipes {
    private String name;
    private int cookingTime;
    private String ingredients;
}
