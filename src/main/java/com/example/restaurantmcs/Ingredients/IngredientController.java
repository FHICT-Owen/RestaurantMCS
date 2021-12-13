package com.example.restaurantmcs.Ingredients;

import com.example.restaurantmcs.Restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @PostMapping
    public void addNewIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.createIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        var success = ingredientService.updateIngredient(id, ingredient);
        if (success)
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Integer id) {
        ingredientService.removeIngredient(id);
    }
}
