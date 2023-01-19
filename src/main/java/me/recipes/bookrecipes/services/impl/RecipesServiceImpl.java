package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.model.BookRecipes;
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
        bookRecipesMap.put(lastId++, bookRecipes);
        return bookRecipes;
    }

    @Override
    public BookRecipes getRecipes(long id) {
        return bookRecipesMap.get(id);
    }

    @Override
    public BookRecipes editRecipes(long id, BookRecipes bookRecipes) {
        if (bookRecipesMap.containsKey(id)) {
            bookRecipesMap.put(id, bookRecipes);
            return bookRecipes;
        }
        return null;
    }

    @Override
    public boolean deleteRecipes(long id) {
        if (bookRecipesMap.containsKey(id)) {
            bookRecipesMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void getAllRecipes() {
        for (Map.Entry current : bookRecipesMap.entrySet()) {
            System.out.println(current.getKey() + ":" + current.getValue());
        }
    }

}