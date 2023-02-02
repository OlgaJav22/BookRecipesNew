package me.recipes.bookrecipes.contpollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.recipes.bookrecipes.model.BookRecipes;
import me.recipes.bookrecipes.services.RecipesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.ServerException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD - операции для работы с рецептами")
public class RecipesController {
    private final RecipesService recipesService;

    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    @Operation(summary = "Название главной страницы")
    public String BookRecipes() {
        return "Книга рецептов";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск нужного рецепта", description = "вводим необходимый id")
    public ResponseEntity<BookRecipes> getRecipes(@PathVariable long id) {
        BookRecipes bookRecipes = recipesService.getRecipes(id);
        if (bookRecipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes);
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление нового рецепта", description = "каждому рецепту присваивается : id")
    public ResponseEntity<BookRecipes> addNewRecipes(@RequestBody BookRecipes bookRecipes) throws Exception {
        BookRecipes bookRecipes1 = recipesService.addNewRecipes(bookRecipes);
        if (bookRecipes1 == null) {
            throw new ServerException("Некорректные данные");
        } else {
            return new ResponseEntity<>(bookRecipes1, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Добавление изменений в существующий рецепт", description = "поиск по номеру id")
    public ResponseEntity<BookRecipes> editRecipes(@PathVariable long id, @RequestBody BookRecipes bookRecipes) {
        BookRecipes bookRecipes1 = recipesService.editRecipes(id, bookRecipes);
        if (bookRecipes1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRecipes1);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "по номеру - id")
    public ResponseEntity<Void> deleteRecipes(@PathVariable long id) {
        if (recipesService.deleteRecipes(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/All")
    @Operation(summary = "Поиск всех рецептов",
            description = "выводит сразу все наименования")
    public Collection<BookRecipes> getAllRecipes() {
        return recipesService.getAllRecipes();
    }

    @GetMapping("/Book")
    @Operation(summary = "получение книги рецептов", description = "без параметров запроса")
    public ResponseEntity <Object> getRecipesBook () {
        try {
            Path path = recipesService.createRecipesBook();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"-book-recipes.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
