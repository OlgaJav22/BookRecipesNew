package me.recipes.bookrecipes.contpollers;

import me.recipes.bookrecipes.model.Ingredients;
import me.recipes.bookrecipes.services.impl.IngredientsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientsServiceImpl ingredientsService;

    public IngredientsController(IngredientsServiceImpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/countId")
    public ResponseEntity getIngredients(@PathVariable long countId) {
        Ingredients ingredients = ingredientsService.getIngredients(countId);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/app")

    public ResponseEntity appNewRecipes(@RequestBody Ingredients ingredients) {
        Ingredients ingredients1 = ingredientsService.appNewIngredients(ingredients);
        return ResponseEntity.ok(ingredients1);
    }
}
