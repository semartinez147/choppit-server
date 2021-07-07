package com.semartinez.choppit.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"recipe_id", "title", "url", "favorite"})
public interface FlatRecipe {

  @NonNull
  Long getRecipeId();

  @NonNull
  String getTitle();

  String getUrl();

  @NonNull
  Boolean isFavorite();

  URI getHref();
}
