package com.semartinez.choppit.model.entity;

import java.util.List;
import java.util.UUID;

public class User {

  private UUID id;
  private List<Recipe> recipes;

  public User() {

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }
}
