package com.semartinez.choppit.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semartinez.choppit.model.entity.Unit;
import java.net.URI;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"ingredient_id", "recipe_id", "quantity", "unit", "unit_text", "unit_alt", "name"})
public interface FlatIngredient {

  @NonNull
  Long getIngredientId();

  @NonNull
  Long getRecipeId();

  @NonNull
  String getQuantity();

  Unit getUnit();

  String getUnitText();

  String getUnitAlt();

  @NonNull
  String getName();

  URI getHref();
}
