package com.lukmanhidayah.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookCreateDto {
  
  @NotBlank
  private String bookTitle;

  @NotBlank
  private String authorName;

  private String description;
}
