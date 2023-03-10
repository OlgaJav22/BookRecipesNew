package me.recipes.bookrecipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.services.FileRecipesService;
import me.recipes.bookrecipes.services.RecipesService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {

    final private FileRecipesService fileService;

    private static long lastId = 0;
    private static Map<Long, BookRecipes> bookRecipesMap = new LinkedHashMap<>();

    public RecipesServiceImpl(FileRecipesService fileService) {
        this.fileService = fileService;
    }


    @Override
    public BookRecipes addNewRecipes(BookRecipes bookRecipes) {
        if (!bookRecipes.getName().isEmpty()) {
            bookRecipesMap.put(lastId++, bookRecipes);
            saveToFile();
        }
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
            saveToFile();
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
    public Collection<BookRecipes> getAllRecipes() {
        return Collections.unmodifiableCollection(bookRecipesMap.values());
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(bookRecipesMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("???????????? ?????? ???????????? ??????????");
        }
    }

    private void readFromFile() {
        String json = fileService.readerFromFile();
        try {
            bookRecipesMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, BookRecipes>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("???????????? ?????? ???????????? ??????????");
        }
    }

}