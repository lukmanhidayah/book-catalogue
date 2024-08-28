package com.lukmanhidayah.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.database.DatabaseConResponseDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseRequestDto;
import com.lukmanhidayah.catalog.dto.database.DatabaseResponseDto;
import com.lukmanhidayah.catalog.service.DatabaseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/database")
public class DatabaseResource {

  private final DatabaseService databaseService;

  @GetMapping("/{id}/detail")
  public ResponseEntity<DatabaseResponseDto> findDatabaseById(@PathVariable Long id) {
    return ResponseEntity.ok().body(databaseService.findById(id));
  }

  @PostMapping("/connect")
  public ResponseEntity<DatabaseConResponseDto> connectToDatabase(@RequestBody DatabaseRequestDto request) {
    return ResponseEntity.ok().body(databaseService.connectToDatabase(request));
  }

  @PostMapping
  public ResponseEntity<Void> saveDatabase(@RequestBody DatabaseRequestDto request) {
    databaseService.saveDatabaseDetails(request);
    return ResponseEntity.ok().build();
  }
}
