package org.vokhlebalov.tacocloud.repositories;

import org.vokhlebalov.tacocloud.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    List<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
