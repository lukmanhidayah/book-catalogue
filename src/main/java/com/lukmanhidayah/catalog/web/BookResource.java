package com.lukmanhidayah.catalog.web;

import java.lang.Long;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.BookDetailDto;
import com.lukmanhidayah.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {

  private final BookService bookService;

  @GetMapping("book/{bookId}")
  public BookDetailDto findBookDetail(@PathVariable("bookId") Long id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    log.info("start findBookDetail with bookId: {}", id);
    BookDetailDto bookDetailDto = bookService.findBookDetailById(id);
    log.info("end findBookDetail with bookId: {}", id, " in: ", stopWatch.getTotalTimeMillis());
    return bookDetailDto;
  }
}