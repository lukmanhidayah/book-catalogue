package com.lukmanhidayah.catalog.repository;

import com.lukmanhidayah.catalog.domain.Book;

import java.util.List;
import java.lang.Long;

public interface BookRepository {

  // Add method findBookById
  public Book findBookById(Long id);

  // Add method findAllBook
  public List<Book> findAllBook();

  // Add method save
  public void save(Book book);

  // Delete method delete
  public void delete(Long bookId);

}
