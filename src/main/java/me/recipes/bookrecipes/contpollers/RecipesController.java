package me.recipes.bookrecipes.contpollers;

import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.services.RecipesService;
import me.recipes.bookrecipes.services.impl.RecipesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private RecipesService recipesService;

    @GetMapping
    public String BookRecipes () {
        return "Книга рецептов";
    }

    @GetMapping ("/id")
    public ResponseEntity<BookRecipes> getRecipes (@PathVariable long lastId) {
        BookRecipes bookRecipes = recipesService.getRecipes(lastId);
        if (bookRecipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes);
    }

    @PostMapping("/add")
    public ResponseEntity<BookRecipes> addNewRecipes (@RequestBody BookRecipes bookRecipes) throws Exception {
        BookRecipes bookRecipes1 = recipesService.addNewRecipes(bookRecipes);
        if (bookRecipes1 == null) {
            throw new Exception();
        } else {
            return new ResponseEntity<>(bookRecipes1, HttpStatus.CREATED);
        }
    }
}
