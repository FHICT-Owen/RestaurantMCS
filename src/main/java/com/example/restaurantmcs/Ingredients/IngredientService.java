package com.example.restaurantmcs.Ingredients;

import com.example.restaurantmcs.Restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public void createIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientByName = ingredientRepository.findIngredientByName(ingredient.getName());
        if (ingredientByName.isPresent()) {
            throw new EntityExistsException("Ingredient already exist!");
        }
        ingredientRepository.save(ingredient);
    }

    public boolean updateIngredient(Integer id, Ingredient ingredient) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if(optionalIngredient.isPresent()) {
            Ingredient actualIngredient = optionalIngredient.get();
            actualIngredient.setName(ingredient.getName());
            actualIngredient.setName_NL(ingredient.getName_NL());
            actualIngredient.setIsAllergen(ingredient.getIsAllergen());
            actualIngredient.setIsInStock(ingredient.getIsInStock());
            ingredientRepository.save(actualIngredient);
            return true;
        }
        return false;
    }

    public void removeIngredient(Integer id) {
        boolean exists = ingredientRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Ingredient with id " + id + " does not exists!");
        }
        ingredientRepository.deleteIngredientById(id);
    }
}
