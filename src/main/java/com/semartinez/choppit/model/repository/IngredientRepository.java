package com.semartinez.choppit.model.repository;

import com.semartinez.choppit.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
