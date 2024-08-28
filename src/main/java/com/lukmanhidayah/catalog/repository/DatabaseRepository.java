package com.lukmanhidayah.catalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukmanhidayah.catalog.domain.Database;

public interface DatabaseRepository extends JpaRepository<Database, Long> {

  /*
   * Find author by id
   * 
   * @param id
   */
  public Optional<Database> findById(Long id);
}
