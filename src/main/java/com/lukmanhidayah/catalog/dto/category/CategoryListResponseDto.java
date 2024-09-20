package com.lukmanhidayah.catalog.dto.category;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CategoryListResponseDto implements Serializable {

  /**
   * Generated random Long serial version UID by timestamp
   */
  private static final long serialVersionUID = 1L;

  private String code;

  private String name;
  
  private String description;
  
}
