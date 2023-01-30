package me.recipes.bookrecipes.services;

import java.io.File;

public interface FileRecipesService {
    boolean saveToFile(String json);

    String readerFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
