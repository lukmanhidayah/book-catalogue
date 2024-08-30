package com.lukmanhidayah.catalog.dto.publisher;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class PublisherCreateRequestDto implements Serializable {

  /**
   * Generated serial version UID
   */
  private static final long serialVersionUID = 1L;

  @NotBlank
  private String publisherName;

  @NotBlank
  private String companyName;
  
  private String address;
}
