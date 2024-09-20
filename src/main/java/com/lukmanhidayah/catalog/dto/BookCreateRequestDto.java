package com.lukmanhidayah.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class BookCreateRequestDto implements Serializable {

  /**
   * Generated random Long stream of serial version UID by current timestamp
   */
  private static final long serialVersionUID = 1L;

  @NotBlank
  private String bookTitle;

  @NotEmpty
  private List<String> authorIds;

  @NotEmpty
  private List<String> categoryList;

  @NotEmpty
  private String publisherId;

  @JsonProperty("synopsis")
  private String description;
}
