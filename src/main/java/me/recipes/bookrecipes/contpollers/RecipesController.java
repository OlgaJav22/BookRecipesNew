package me.recipes.bookrecipes.contpollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.RecipesService;
import me.recipes.bookrecipes.services.impl.RecipesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.rmi.ServerException;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD - операции для работы с рецептами")
public class RecipesController {
    private final RecipesService recipesService;

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public String BookRecipes() {
        return "Книга рецептов";
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRecipes> getRecipes(@PathVariable long id) {
        BookRecipes bookRecipes = recipesService.getRecipes(id);
        if (bookRecipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes);
    }

    @PostMapping("/add")
    public ResponseEntity<BookRecipes> addNewRecipes(@RequestBody BookRecipes bookRecipes) throws Exception {
        BookRecipes bookRecipes1 = recipesService.addNewRecipes(bookRecipes);
        if (bookRecipes1 == null) {
            throw new ServerException("Некорректные данные");
        } else {
            return new ResponseEntity<>(bookRecipes1, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRecipes> editRecipes(@PathVariable long id, @RequestBody BookRecipes bookRecipes) {
        BookRecipes bookRecipes1 = recipesService.editRecipes(id, bookRecipes);
        if (bookRecipes1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipes(@PathVariable long id) {
        if (recipesService.deleteRecipes(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/All")
    @Operation ( summary = "Поиск всех рецептов",
            description = "выводит сразу все наименования")
    public ResponseEntity<Void> getAllRecipes() {
        recipesService.getAllRecipes();
        return ResponseEntity.ok().build();
   }
}
