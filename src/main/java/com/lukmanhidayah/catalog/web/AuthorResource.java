package com.lukmanhidayah.catalog.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.author.AuthorCreateDto;
import com.lukmanhidayah.catalog.dto.author.AuthorResponseDto;
import com.lukmanhidayah.catalog.dto.author.AuthorUpdateRequestDto;
import com.lukmanhidayah.catalog.service.AuthorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/author")
public class AuthorResource {

  /*
   * Tambahkan dependency AuthorService
   */
  private final AuthorService authorService;

  /*
   * Implementasikan method findAuthorById yang berfungsi untuk mencari data
   * author berdasarkan id yang diberikan.
   */
  @GetMapping("/{id}/detail")
  public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable String id) {
    return ResponseEntity.ok().body(authorService.findAuthorById(id));
  }

  @PostMapping
  public ResponseEntity<Void> createAuthor(@RequestBody @Valid List<AuthorCreateDto> authorCreateDto) {
    authorService.createAuthor(authorCreateDto);
    return ResponseEntity.created(URI.create("/author")).build();
  }

  @PutMapping("{authorId}")
  public ResponseEntity<Void> updateAuthor(@PathVariable("authorId") String id,
      @RequestBody AuthorUpdateRequestDto authorUpdateRequestDto) {

    authorService.updateAuthor(id, authorUpdateRequestDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{authorId}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") String authorId) {
    authorService.deleteAuthor(authorId);
    return ResponseEntity.ok().build();
  }

}
