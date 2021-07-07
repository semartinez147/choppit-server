package com.semartinez.choppit.service;


import com.semartinez.choppit.model.entity.AssemblyRecipe;
import java.util.Optional;
import org.apache.commons.validator.routines.UrlValidator;

public class Kitchen {

  private final JsoupMachine machine;

  public static Kitchen getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private Kitchen() {
    machine = JsoupMachine.getInstance();
  }

  public AssemblyRecipe reduce(String url, boolean wantHtml) {
    String validUrl;
    UrlValidator validator = new UrlValidator();
    if (validator.isValid(url.trim())) {
      validUrl = url;
      Log.info("valid url: ", validUrl);
    } else {
      return null;
    }
    return machine.generateStrings(validUrl, wantHtml);
  }

  public Optional<AssemblyRecipe> processData(String ingredient, String instruction) {
    return machine.process(ingredient, instruction);
  }


  private static class InstanceHolder {

    private static final Kitchen INSTANCE = new Kitchen();
  }

}
