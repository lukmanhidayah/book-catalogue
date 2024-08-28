package com.lukmanhidayah.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukmanhidayah.catalog.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  /**
   * Find author by id
   * @param id
   */
  public Optional<Author> findById(Long id);

  /**
   * Find author by id and soft delete
   * @param id
   */
  public Optional<Author> findByIdAndDeletedFalse(Long id);

  public List<Author> findByNameLike(String authorName);

}
