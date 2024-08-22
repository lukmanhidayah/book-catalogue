package com.lukmanhidayah.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("user", "John Doe");
    model.addAttribute("message", "Welcome to the Home Page!");
    return "home";
  }
}
