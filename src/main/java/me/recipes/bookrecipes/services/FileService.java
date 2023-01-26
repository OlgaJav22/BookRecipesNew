package me.recipes.bookrecipes.services;

public interface FileService {
    boolean saveToFile(String json);

    String readerFromFile();
}
