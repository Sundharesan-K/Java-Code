package com.secure.spring_secure_6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  //These also tested endpoints
  @GetMapping("/")
  public String hello() {
    return "Welcome to JAVA";
  }

  @GetMapping("/say-hi")
  public String sayHi() {
    return "Hi Java You Welcome";
  }
}
