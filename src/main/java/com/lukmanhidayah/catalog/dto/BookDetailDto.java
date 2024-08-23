package com.lukmanhidayah.catalog.dto;

import java.io.Serializable;

import lombok.Data;
import java.lang.Long;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class BookDetailDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -7889938648939242355L;

  private Long bookId;

  private String authorName;

  private String bookTitle;

  private String bookDescription;

}
