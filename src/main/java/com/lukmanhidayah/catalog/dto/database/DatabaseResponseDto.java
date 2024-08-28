package com.lukmanhidayah.catalog.dto.database;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class DatabaseResponseDto {
  private Long id;
  
  private String username;

  private String ip;

  private String port;

  private String databaseName;

  private String password;

  private String dbType;

}
