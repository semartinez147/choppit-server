package com.semartinez.choppit.model.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semartinez.choppit.view.FlatIngredient;
import com.semartinez.choppit.view.FlatRecipe;
import com.semartinez.choppit.view.FlatStep;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(
    indexes = {
        @Index(columnList = "recipe_title")
    }
)
public class Recipe implements FlatRecipe {
  private static EntityLinks entityLinks;

  public Recipe() {
  }

  public Recipe(String url, @NonNull String title, boolean favorite, List<Step> steps,
      List<Ingredient> ingredients) {
    super();
    this.url = url;
    this.title = title;
    this.favorite = favorite;
    this.ingredients = ingredients;
    this.steps = steps;
  }
  public Recipe(AssemblyRecipe data) {
    this.recipeId = data.getRecipeId();
    this.url = data.getUrl();
    this.title = data.getTitle();
    this.favorite = data.isFavorite();
    this.ingredients = data.getIngredients();
    this.steps = data.getSteps();
  }

  @Id
  @GeneratedValue
  @Column(name = "recipe_id", nullable = false, updatable = false)
  private Long recipeId;

  @NonNull
  @Column(name = "recipe_title", length = 255, nullable = false, unique = true)
  private String title;

  @Column(name = "recipe_url", updatable = false)
  private String url;

  @NonNull
  @Column(name = "recipe_favorite")
  private Boolean favorite;

  @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
  @JsonSerialize(contentAs = FlatStep.class)
  private List<Step> steps;

  @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
  @JsonSerialize(contentAs = FlatIngredient.class)
  private List<Ingredient> ingredients;


  @NonNull
  public Long getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(long recipeId) {
    this.recipeId = recipeId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public @NonNull
  String getTitle() {
    return title;
  }

  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  @NonNull
  public Boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public void setSteps(List<Step> steps) {
    this.steps = steps;
    for (Step step : steps) {
      step.setRecipe(this);
    }
  }

  public void setStep(Step step) {
    if (this.steps == null) {
      this.steps = new ArrayList<>();
    }
    this.steps.add(step);
    step.setRecipe(this);
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
    for (Ingredient ingredient : ingredients) {
      ingredient.setRecipe(this);
    }
  }

  public void setIngredient(Ingredient ingredient) {
    if (this.ingredients == null) {
      this.ingredients = new ArrayList<>();
    }
    this.ingredients.add(ingredient);
    ingredient.setRecipe(this);
  }

  public URI getHref() {
    return entityLinks.linkForItemResource(Recipe.class, recipeId).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Recipe.entityLinks = entityLinks;
  }

  @NonNull
  @Override
  public String toString() {
    return getTitle();
  }

}
