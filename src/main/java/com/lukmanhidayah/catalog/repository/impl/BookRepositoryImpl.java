package com.lukmanhidayah.catalog.repository.impl;

import java.util.Map;
import java.lang.Long;

import com.lukmanhidayah.catalog.domain.Book;
import com.lukmanhidayah.catalog.repository.BookRepository;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class BookRepositoryImpl implements BookRepository {

  // Add Map<Long, Book> bookMap
  private Map<Long, Book> bookMap;

  @Override
  public Book findBookById(Long id) {
    Book book = bookMap.get(id);
    return book;
  }

  // show all book
  @Override
  public List<Book> findAllBook() {
    List<Book> books = new ArrayList<>(bookMap.values());
    return books;
  }

  // save book
  @Override
  public void save(Book book) {
    int size = bookMap.size();
    book.setId((long) (size + 1));
    bookMap.put(book.getId(), book);
  }

}