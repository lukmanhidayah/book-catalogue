package com.lukmanhidayah.catalog.service;

import java.util.List;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.dto.author.AuthorCreateDto;
import com.lukmanhidayah.catalog.dto.author.AuthorResponseDto;
import com.lukmanhidayah.catalog.dto.author.AuthorUpdateRequestDto;

public interface AuthorService {

  /**
   * Find author by id
   * 
   * @param id
   * @return AuthorResponseDto
   */
  public AuthorResponseDto findAuthorById(String id);

  /**
   * Create new author
   * 
   * @param authorCreateDto
   */
  public void createAuthor(List<AuthorCreateDto> authorCreateDto);

  /**
   * Update author
   * 
   * @param id
   * @param authorUpdateRequestDto
   */
  public void updateAuthor(String authorId, AuthorUpdateRequestDto authorUpdateRequestDto);

  /**
   * Delete author
   * 
   * @param id
   */
  public void deleteAuthor(String authorId);

  /**
   * Find authors
   * 
   */
  public List<Author> findAuthors(List<String> authorIdsList);

  /**
   * Get list
   */
  public List<AuthorResponseDto> constructDTO(List<Author> authors);
}
