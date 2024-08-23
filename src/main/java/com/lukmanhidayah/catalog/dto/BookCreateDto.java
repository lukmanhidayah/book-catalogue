package com.lukmanhidayah.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class BookCreateDto {
  
  @NotBlank
  private String bookTitle;

  @NotBlank
  private String authorName;

  private String description;
}
