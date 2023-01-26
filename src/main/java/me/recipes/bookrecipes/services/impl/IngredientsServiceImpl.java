package me.recipes.bookrecipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.FileIngredientsService;
import me.recipes.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    final private FileIngredientsService fileService;
    private static long countId = 0;
    private static Map<Long, Ingredients> ingredientsMap = new LinkedHashMap<>();

    public IngredientsServiceImpl(FileIngredientsService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Ingredients addNewIngredients(Ingredients ingredients) {
        if (!ingredients.getNameIngredients().isEmpty()) {
            ingredientsMap.put(countId++, ingredients);
            saveToFile();
        }
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(long id) {
        return ingredientsMap.get(id);
    }

    @Override
    public Ingredients editIngredients(long id, Ingredients ingredients) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredients);
            saveToFile();
            return ingredients;
        }
        return null;
    }

    @Override
    public boolean deleteIngredients(long id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Ingredients> getAllIngredients() {
        return Collections.unmodifiableCollection(ingredientsMap.values());
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при записи файла");
        }
    }

    private void readFromFile() {
        String json = fileService.readerFromFile();
        try {
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при чтении файла");
        }
    }

}