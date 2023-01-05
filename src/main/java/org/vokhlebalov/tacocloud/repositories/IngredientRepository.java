package org.vokhlebalov.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.vokhlebalov.tacocloud.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
