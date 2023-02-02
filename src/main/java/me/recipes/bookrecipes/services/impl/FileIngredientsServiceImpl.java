package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.services.FileIngredientsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileIngredientsServiceImpl implements FileIngredientsService {
    @Value("${path.to.ingredients.file}")
    private String ingredientFilePath;
    @Value("${name.to.ingredients.file}")
    private String ingredientFileName;


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }

    @Override
    public String readerFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException("Файл не найден");
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);

            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
