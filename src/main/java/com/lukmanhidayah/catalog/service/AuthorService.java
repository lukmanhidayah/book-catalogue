package com.lukmanhidayah.catalog.service;

import java.util.List;

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
  public AuthorResponseDto findAuthorById(Long id);

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
  public void updateAuthor(Long id, AuthorUpdateRequestDto authorUpdateRequestDto);

  /**
   * Delete author
   * 
   * @param id
   */
  public void deleteAuthor(Long id);
}
