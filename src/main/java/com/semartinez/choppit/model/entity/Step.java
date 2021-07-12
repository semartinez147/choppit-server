package com.semartinez.choppit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semartinez.choppit.view.FlatRecipe;
import com.semartinez.choppit.view.FlatStep;
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(
    indexes = {
        @Index(columnList = "step_id")
    }
)
public class Step implements FlatStep {
  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue
  @Column(name = "step_id", nullable = false, updatable = false)
  private Long stepId;

  @NonNull
  @Column(name = "recipe_id", nullable = false)
  private Long recipeId;

  @NonNull
  @Column
  private String instructions;

  @NonNull
  private Integer recipeOrder;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "recipe", nullable = false)
  @JsonSerialize(as = FlatRecipe.class)
  private Recipe recipe;

  public Step() {
  }

  public Step(long recipeId, @NonNull String instructions, int recipeOrder) {
    super();
    this.recipeId = recipeId;
    this.instructions = instructions;
    this.recipeOrder = recipeOrder;
  }

  @NonNull
  public Long getStepId() {
    return stepId;
  }

  public void setStepId(long stepId) {
    this.stepId = stepId;
  }

  @NonNull
  public Long getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(long recipeId) {
    this.recipeId = recipeId;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public @NonNull String getInstructions() {
    return instructions;
  }

  public void setInstructions(@NonNull String instructions) {
    this.instructions = instructions;
  }

  @NonNull
  public Integer getRecipeOrder() {
    return recipeOrder;
  }

  public void setRecipeOrder(int recipeOrder) {
    this.recipeOrder = recipeOrder;
  }

  public URI getHref() {
    return entityLinks.linkForItemResource(Step.class, stepId).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Step.entityLinks = entityLinks;
  }

  @NonNull
  @Override
  public String toString() {
    return ("Step " + getRecipeOrder() + ": " + getInstructions().substring(0, 20) + " ...");
  }
}
