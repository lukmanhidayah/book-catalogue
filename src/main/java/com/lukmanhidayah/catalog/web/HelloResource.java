package com.lukmanhidayah.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.HelloMessageResponseDto;
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
  public ResponseEntity<HelloMessageResponseDto> helloWord() {
    HelloMessageResponseDto response = new HelloMessageResponseDto();
    response.setMessage(greetingService.sayGreeting());
    return ResponseEntity.ok().body(response);
  }

}
