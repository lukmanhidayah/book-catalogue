package com.lukmanhidayah.catalog.dto.publisher;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class PublisherListResponseDto implements Serializable {

  /**
   * Generated random Long stream of serial version UID by current timestamp
   */
  private static final long serialVersionUID = 1626880731L;

  private String publisherId;

  private String publisherName;

  private String companyName;
}
