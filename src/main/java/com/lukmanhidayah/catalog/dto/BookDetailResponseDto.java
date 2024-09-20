package com.lukmanhidayah.catalog.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lukmanhidayah.catalog.dto.author.AuthorResponseDto;
import com.lukmanhidayah.catalog.dto.category.CategoryListResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherResponseDto;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class BookDetailResponseDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -7889938648939242355L;

  private String bookId;

  private List<AuthorResponseDto> authors;

  private List<CategoryListResponseDto> categories;

  private PublisherResponseDto publisher;

  private String bookTitle;

  private String bookDescription;

}
