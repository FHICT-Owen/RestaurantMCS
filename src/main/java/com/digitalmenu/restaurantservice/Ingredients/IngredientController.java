package com.digitalmenu.restaurantservice.Ingredients;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:inventory')")
    public ResponseEntity<Ingredient> createCategory(@RequestBody @Valid Ingredient ingredient) {
        return new ResponseEntity<>(ingredientService.createIngredient(ingredient), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('access:inventory')")
    public ResponseEntity<Ingredient> updateCategory(@RequestBody @Valid Ingredient ingredient) {
        ingredientService.updateIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access:inventory')")
    public void deleteIngredient(@PathVariable Integer id) {
        ingredientService.removeIngredient(id);
    }
}
