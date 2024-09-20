package com.lukmanhidayah.catalog.web;

import java.lang.Long;
import java.util.List;
import java.net.URI; // Add this import statement

import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.BookCreateRequestDto;
import com.lukmanhidayah.catalog.dto.BookDetailResponseDto;
import com.lukmanhidayah.catalog.dto.BookUpdateDto;
import com.lukmanhidayah.catalog.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class BookResource {

  private final BookService bookService;

  @GetMapping("/{bookId}")
  public ResponseEntity<BookDetailResponseDto> findBookDetail(@PathVariable("bookId") String id) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    log.info("start findBookDetail with bookId: {}", id);
    BookDetailResponseDto bookDetailDto = bookService.findBookDetailById(id);
    log.info("end findBookDetail with bookId: {}", id, " in: ", stopWatch.getTotalTimeMillis());
    return ResponseEntity.ok().body(bookDetailDto);
  }

  @GetMapping
  public ResponseEntity<List<BookDetailResponseDto>> findAllBookDetail() {
    List<BookDetailResponseDto> bookDetailDtos = bookService.findAllBookDetail();
    return ResponseEntity.ok().body(bookDetailDtos);
  }

  @PostMapping
  public ResponseEntity<Void> createNewBook(@RequestBody @Valid BookCreateRequestDto bookCreateDto) {
    bookService.createNewBook(bookCreateDto);
    return ResponseEntity.created(URI.create("/book")).build();
  }

  // Add updateBook method
  @PutMapping("{bookId}")
  public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long id, @RequestBody BookUpdateDto bookUpdateDto) {

    bookService.updateBook(id, bookUpdateDto);

    return ResponseEntity.ok().build();
  }

  // Add deleteBook method
  @DeleteMapping("{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long id) {

    bookService.deleteBook(id);

    return ResponseEntity.ok().build();
  }

}