package com.semartinez.choppit.service;


import com.semartinez.choppit.model.entity.AssemblyRecipe;
import java.util.Optional;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

  public ResponseEntity<AssemblyRecipe> processData(AssemblyRecipe input) {
    return ResponseEntity.of(machine.process(input));
  }

  public ResponseEntity<AssemblyRecipe> simmer(AssemblyRecipe input) {
    AssemblyRecipe output = machine.process(input).get();
    return new ResponseEntity<>(output, HttpStatus.OK);
  }


  private static class InstanceHolder {

    private static final Kitchen INSTANCE = new Kitchen();
  }

}
