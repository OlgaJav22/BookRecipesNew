package me.recipes.bookrecipes.contpollers;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.services.impl.RecipesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipesServiceImpl recipesService;

    public RecipesController(RecipesServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public String BookRecipes() {
        return "Книга рецептов";
    }

    @GetMapping("/id")
    public ResponseEntity getRecipes(@PathVariable long lastId) {
        BookRecipes bookRecipes = recipesService.getRecipes(lastId);
        if (bookRecipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes);
    }

    @PutMapping("/app")

    public ResponseEntity appNewRecipes(@RequestBody BookRecipes bookRecipes) {
        BookRecipes bookRecipes1 = recipesService.appNewRecipes(bookRecipes);
        return ResponseEntity.ok(bookRecipes1);
    }

}
