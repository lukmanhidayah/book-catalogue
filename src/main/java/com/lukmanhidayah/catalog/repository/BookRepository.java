package com.lukmanhidayah.catalog.repository;

import com.lukmanhidayah.catalog.domain.Book;

import java.util.List;

public interface BookRepository {

  // Add method findBookById
  public Book findBookById(Long id);

  // Add method findAllBook
  public List<Book> findAllBook();

}
