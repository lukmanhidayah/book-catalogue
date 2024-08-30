package com.lukmanhidayah.catalog.dto.publisher;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class PublisherUpdateRequestDto implements Serializable {

  /**
   * Generated serial version UID
   */
  private static final long serialVersionUID = 1L;

  private String publisherName;

  private String address;

  private String companyName;
}
