package com.example.restaurantmcs.Ingredients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Optional<Ingredient> findIngredientByName(String name);
    void deleteIngredientById(Integer id);
}
