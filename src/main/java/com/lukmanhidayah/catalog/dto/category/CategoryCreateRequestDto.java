package com.lukmanhidayah.catalog.dto.category;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CategoryCreateRequestDto implements Serializable {

  /**
   * Generated random Long serial version UID by timestamp
   */
  private static final long serialVersionUID = 1L;

  @NotBlank
  private String code;

  @NotBlank
  private String categoryName;
  
  private String description;
  
}
