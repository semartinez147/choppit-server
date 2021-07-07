package com.semartinez.choppit.controller;

import com.semartinez.choppit.model.entity.AssemblyRecipe;
import com.semartinez.choppit.service.Kitchen;
import com.semartinez.choppit.service.Log;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen")
@ExposesResourceFor(AssemblyRecipe.class)
public class KitchenController {

  private final Kitchen kitchen;

  @Autowired
  public KitchenController() {
    this.kitchen = Kitchen.getInstance();
  }

  @ResponseBody
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public AssemblyRecipe siteReduction(@RequestBody Request request) {
    Log.info("kitchenCon", "got url: " + request.url + "wantHtml: " + request.wantHtml);
    return kitchen.reduce(request.url, request.wantHtml);
  }

  private static class Request {
    private String url;
    private boolean wantHtml;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isWantHtml() {
      return wantHtml;
    }

    public void setWantHtml(boolean wantHtml) {
      this.wantHtml = wantHtml;
    }
  }
}
