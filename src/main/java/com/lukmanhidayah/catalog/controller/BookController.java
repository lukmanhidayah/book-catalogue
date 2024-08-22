package com.lukmanhidayah.catalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lukmanhidayah.catalog.dto.BookDetailDto;
import com.lukmanhidayah.catalog.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BookController {

  private final BookService bookService;
  
  @GetMapping("/book")
  public String findBookList(Model model){
    List<BookDetailDto> books = bookService.findAllBookDetail();
    model.addAttribute("books", books);
    return "book/list";
  }
}
