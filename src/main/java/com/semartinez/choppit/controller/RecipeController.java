package com.semartinez.choppit.controller;

import com.semartinez.choppit.model.entity.AssemblyRecipe;
import com.semartinez.choppit.model.entity.Recipe;
import com.semartinez.choppit.service.Kitchen;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@ExposesResourceFor(Recipe.class)
public class RecipeController {

  private final Kitchen kitchen;

  @Autowired
  public RecipeController() {
    this.kitchen = Kitchen.getInstance();
  }

/*  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AssemblyRecipe> siteProcess(@RequestBody Map<String, String> stringMap) {
    String ingredient = stringMap.get("ingredient");
    String instruction = stringMap.get("instruction");
    return ResponseEntity.of(kitchen.processData(ingredient, instruction));
  }*/
}
