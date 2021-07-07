package com.semartinez.choppit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @GetMapping("/hello")
  public String hello() {
    return "Welcome to Choppit Server. There isn't much here yet.";
  }

}
