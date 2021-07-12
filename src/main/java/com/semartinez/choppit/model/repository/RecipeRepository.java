package com.semartinez.choppit.model.repository;

import com.semartinez.choppit.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {

}
