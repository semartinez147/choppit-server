package com.semartinez.choppit.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semartinez.choppit.view.FlatIngredient;
import com.semartinez.choppit.view.FlatRecipe;
import java.net.URI;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
@Table(
    indexes = {
        @Index(columnList = "ingredient_name")
    }
)
public class Ingredient implements FlatIngredient {
  private static EntityLinks entityLinks;

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "ingredient_id", nullable = false, updatable = false)
  private Long ingredientId;

  @NonNull
  @Column(name = "recipe_id", nullable = false, updatable = false)
  private Long recipeId;

  @NonNull
  @Column(name = "ingredient_quantity", nullable = false)
  private String quantity;

  @NonNull
  @Column(name = "ingredient_name", nullable = false)
  private String name;

  @Column(name = "ingredient_unit")
  private Unit unit;

  @Column(name = "ingredient_unit_alt")
  private String unitAlt;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "recipe", nullable = false)
  @JsonSerialize(as = FlatRecipe.class)
  private Recipe recipe;

  public Ingredient() {

  }

  public Ingredient(long recipeId, String quantity, Unit unit, String unitAlt,
      String name) {
    super();
    this.recipeId = recipeId;
    this.quantity = quantity;
    this.unit = unit;
    this.unitAlt = (unitAlt != null && !unitAlt.isEmpty()) ? unitAlt : unit.toString();
    this.name = name;
  }

  @NonNull
  public Long getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(Long ingredientId) {
    this.ingredientId = ingredientId;
  }

  @NonNull
  public Long getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(Long recipeId) {
    this.recipeId = recipeId;
  }

  @NonNull
  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    // TODO: Remove anything that isn't a number, space or "/"

    this.quantity = quantity;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
    unitAlt = this.unit.toString();
  }

  public String getUnitText() {
    return unitAlt.equalsIgnoreCase("other")? " " : unitAlt;
  }

  public void setUnitText(String unit) {
    unitAlt = unit;
    this.unit = Unit.toUnit(unit != null? unit : "other");
  }

  public String getUnitAlt() {
    return unitAlt;
  }

  public void setUnitAlt(String unitAlt) {
    this.unitAlt = unitAlt;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public URI getHref() {
    return entityLinks.linkForItemResource(Ingredient.class, ingredientId).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Ingredient.entityLinks = entityLinks;
  }

  @NonNull
  @Override
  public String toString() {
    return (getQuantity() + " " + ((getUnit() != Unit.OTHER) ? getUnit().toString() : getUnitAlt())
        + " " + getName());
  }

  private int hashCode;

  @Override
  public int hashCode() {
    if (hashCode == 0) {
      hashCode = Objects.hash(quantity, unit, name);
    }
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj == this) {
      result = true;
    } else if (obj instanceof Ingredient && obj.hashCode() == hashCode()) {
      Ingredient other = (Ingredient) obj;
      result = quantity.equals(other.quantity)
          && name.equals(other.name)
          && unit.equals(other.unit)
          && unitAlt == null ? other.unitAlt == null : (unitAlt.equalsIgnoreCase(other.unitAlt));
    }
    return result;
  }

}