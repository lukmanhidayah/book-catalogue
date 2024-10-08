package com.lukmanhidayah.catalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lukmanhidayah.catalog.dto.BookCreateRequestDto;
import com.lukmanhidayah.catalog.dto.BookDetailResponseDto;
import com.lukmanhidayah.catalog.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  @GetMapping("/list")
  public String findBookList(Model model) {
    List<BookDetailResponseDto> books = bookService.findAllBookDetail();
    model.addAttribute("books", books);
    return "book/list";
  }

  @GetMapping("/new")
  public String loadBookForm(Model model) {
    BookCreateRequestDto bookCreateDto = new BookCreateRequestDto();
    model.addAttribute("bookCreateDto", bookCreateDto);
    return "book/book-new";
  }

  @PostMapping("/new")
  public String addNewBook(@ModelAttribute("bookCreateDto") @Valid BookCreateRequestDto bookCreateDto,
      BindingResult bindingResult,
      Errors errors,
      Model model) {

    if (errors.hasErrors()) {
      model.addAttribute("bookCreateDto", bookCreateDto);
      return "/book/book-new";
    }
    bookService.createNewBook(bookCreateDto);
    return "redirect:/book/list";
  }
}
