package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.domain.Database;
import com.lukmanhidayah.catalog.dto.database.DatabaseConResponseDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseRequestDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseResponseDto;

public interface DatabaseService {

  DatabaseConResponseDto connectToDatabase(DatabaseRequestDto request);

  DatabaseConResponseDto getTableDDL(String tableName, String dbType);

  Database saveDatabaseDetails(DatabaseRequestDto request);

  // find by id
  public DatabaseResponseDto findById(Long id);

}