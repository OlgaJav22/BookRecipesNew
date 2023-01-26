package me.recipes.bookrecipes.services;

public interface FileRecipesService {
    boolean saveToFile(String json);

    String readerFromFile();
}
