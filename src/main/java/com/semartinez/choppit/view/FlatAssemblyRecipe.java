package com.semartinez.choppit.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semartinez.choppit.model.entity.Ingredient;
import com.semartinez.choppit.model.entity.Step;
import java.net.URI;
import java.util.List;
import org.jsoup.nodes.Document;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"recipe_id", "title", "url", "favorite", "document", "reduction"})
public interface FlatAssemblyRecipe {

  @NonNull
  Long getRecipeId();

  @NonNull
  String getTitle();

  String getUrl();

  @NonNull
  Boolean isFavorite();

  Document getDocument();

  List<String> getReduction();

  List<Step> getSteps();

  List<Ingredient> getIngredients();

  String getSampleStep();

  String getSampleIngredient();

}
