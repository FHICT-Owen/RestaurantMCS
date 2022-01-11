package com.digitalmenu.restaurantservice.Ingredients;

import com.digitalmenu.restaurantservice.exception.common.ElementAlreadyExistsException;
import com.digitalmenu.restaurantservice.exception.common.NoSuchElementFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createIngredient(Ingredient ingredient) {

        if (ingredientRepository.existsCategoryByName(ingredient.getName()))
            throw new ElementAlreadyExistsException("Ingredient with name: " + ingredient.getName() + " already exists");

        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        if (!ingredientRepository.existsById(ingredient.getId()))
            throw new NoSuchElementFoundException("Restaurant not found");
        return ingredientRepository.save(ingredient);
    }

    public void removeIngredient(Integer id) {
        if (!ingredientRepository.existsById(id))
            throw new NoSuchElementFoundException("Ingredient not found");

        ingredientRepository.deleteById(id);
    }
}
