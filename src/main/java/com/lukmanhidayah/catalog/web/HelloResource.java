package com.lukmanhidayah.catalog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.service.GreetingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class HelloResource {

  // Add a private GreetingService field
  private GreetingService greetingService;

  @GetMapping("hello")
  public String helloWord() {
    log.trace("This is log trace");
    log.debug("This is log debug");
    log.info("This is log info");
    log.warn("This is log warn");
    log.error("This is log error");
    return greetingService.sayGreeting();
  }

}
