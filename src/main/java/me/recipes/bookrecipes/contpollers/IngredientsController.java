package me.recipes.bookrecipes.contpollers;

import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.IngredientsService;
import me.recipes.bookrecipes.services.RecipesService;
import me.recipes.bookrecipes.services.impl.IngredientsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/{countId}")
    public ResponseEntity<Ingredients> getIngredients(@PathVariable long countId) {
        Ingredients ingredients = ingredientsService.getIngredients(countId);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @PostMapping("/add")
    public ResponseEntity<Ingredients> addNewRecipes(@RequestBody Ingredients ingredients) throws Exception {
        Ingredients ingredients1 = ingredientsService.addNewIngredients(ingredients);
        if (ingredients1 == null) {
            throw new ServerException("Некорректные данные");
        } else {
            return new ResponseEntity<>(ingredients1, HttpStatus.CREATED);
        }
    }
}
