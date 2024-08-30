package com.lukmanhidayah.catalog.repository;

import com.lukmanhidayah.catalog.domain.Book;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.Long;

public interface BookRepository extends JpaRepository<Book, Long> {

  // Add method findBookById
  public Optional<Book> findById(Long id);

  // // Add method findAllBook
  // public List<Book> findAllBook();

  // // Add method save
  // public void save(Book book);

  // // Delete method delete
  // public void delete(Long bookId);

}
