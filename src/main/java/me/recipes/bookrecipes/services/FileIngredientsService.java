package me.recipes.bookrecipes.services;

import java.io.File;

public interface FileIngredientsService {
    boolean saveToFile(String json);

    File getDataFile();

    String readerFromFile();

    boolean cleanDataFile();
}
