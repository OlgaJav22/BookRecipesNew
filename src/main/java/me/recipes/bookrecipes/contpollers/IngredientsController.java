package me.recipes.bookrecipes.contpollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Crud - операции для работы с ингредиентами")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/{countId}")
    @Operation(summary = "Получение необходимого ингредиента", description = "вводим нужный id")
    public ResponseEntity<Ingredients> getIngredients(@PathVariable long countId) {
        Ingredients ingredients = ingredientsService.getIngredients(countId);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление нового ингредиента", description = "каждому ингредиенту присваивается уникальный номер - id")
    public ResponseEntity<Ingredients> addNewRecipes(@RequestBody Ingredients ingredients) throws Exception {
        Ingredients ingredients1 = ingredientsService.addNewIngredients(ingredients);
        if (ingredients1 == null) {
            throw new ServerException("Некорректные данные");
        } else {
            return new ResponseEntity<>(ingredients1, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Внесение изменений в существующий ингредиент", description = "посик по id")
    public ResponseEntity<Ingredients> editIngredients(@PathVariable long id, @RequestBody Ingredients ingredients) {
        Ingredients ingredients1 = ingredientsService.editIngredients(id, ingredients);
        if (ingredients1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients1);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление необходимого ингредиента", description = "поиск нужного ингредиента по id")
    public ResponseEntity<Void> deleteIngredients(@PathVariable long id) {
        if (ingredientsService.deleteIngredients(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получение всего списка ингредиентов", description = "никакие параметры вводить не надо")
    public Collection<Ingredients> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }

}
