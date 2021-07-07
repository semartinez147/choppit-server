package com.semartinez.choppit.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"step_id", "recipe_id", "recipe_order", "instructions"})
public interface FlatStep {

  @NonNull
  Long getStepId();

  @NonNull
  Long getRecipeId();

  @NonNull
  Integer getRecipeOrder();

  @NonNull
  String getInstructions();

  URI getHref();
}
