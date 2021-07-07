package com.semartinez.choppit.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semartinez.choppit.view.FlatAssemblyRecipe;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AssemblyRecipe implements FlatAssemblyRecipe {

  private static EntityLinks entityLinks;
  private Long recipeId = 0L;
  private String url = "";
  private String title = "";
  private Boolean favorite = false;
  private List<Step> steps = new ArrayList<>();
  private List<Ingredient> ingredients = new ArrayList<>();
  // fields for server interactions
  @JsonIgnore
  private Document document = new Document("");
  private List<String> reduction = new ArrayList<>();
  private Map<String, String> samples = new HashMap<>();

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

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
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
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
    this.url = document.location();
    this.title = document.title();
  }

  public List<String> getReduction() {
    return reduction;
  }

  public void setReduction(List<String> reduction) {
    this.reduction = reduction;
  }

  public Map<String, String> getSamples() {
    return samples;
  }

  public void setSamples(Map<String, String> samples) {
    if (samples == null) {
      samples = new HashMap<>();
    }
    this.samples.put("ingredient", samples.get("ingredient"));
    this.samples.put("instruction", samples.get("instruction"));
  }

/*
  public URI getHref() {
    return entityLinks.linkForItemResource(Ingredient.class, recipeId).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    AssemblyRecipe.entityLinks = entityLinks;
  }
*/
}