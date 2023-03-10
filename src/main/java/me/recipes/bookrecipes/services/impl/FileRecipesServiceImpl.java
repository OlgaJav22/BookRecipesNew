package me.recipes.bookrecipes.services.impl;

import me.recipes.bookrecipes.services.FileRecipesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileRecipesServiceImpl implements FileRecipesService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readerFromFile() {
        try {
           return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException("Файл не найден");
        }
    }


    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);

            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
