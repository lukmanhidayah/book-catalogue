package com.lukmanhidayah.catalog.dto.author;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class AuthorUpdateRequestDto {

  private String authorName;

  private Long birthDate;

}
