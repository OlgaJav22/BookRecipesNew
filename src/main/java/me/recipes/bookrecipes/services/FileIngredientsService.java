package me.recipes.bookrecipes.services;

public interface FileIngredientsService {
    boolean saveToFile(String json);

    String readerFromFile();
}
