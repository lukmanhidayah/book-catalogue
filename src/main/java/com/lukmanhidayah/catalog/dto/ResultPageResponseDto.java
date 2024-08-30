package com.lukmanhidayah.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class ResultPageResponseDto<T> implements Serializable {

  /**
   * Generated random Long stream of serial version UID
   */
  private static final long serialVersionUID = -7889938648939242355L;

  private List<T> result;

  private Integer pages;

  private Long elements;

}
