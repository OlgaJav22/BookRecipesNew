package me.recipes.bookrecipes.services;

import java.io.File;
import java.nio.file.Path;

public interface FileRecipesService {
    boolean saveToFile(String json);

    String readerFromFile();

    File getDataFile();

    boolean cleanDataFile();

    Path createTempFile(String syffix);
}
