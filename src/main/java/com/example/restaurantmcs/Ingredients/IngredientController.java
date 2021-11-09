package com.example.restaurantmcs.Ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getDishes() {
        return ingredientService.getIngredients();
    }

    @PostMapping
    public void addNewIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.createIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Integer id) {
        ingredientService.removeIngredient(id);
    }
}
