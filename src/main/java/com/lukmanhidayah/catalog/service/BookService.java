package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.dto.BookCreateDto;
import com.lukmanhidayah.catalog.dto.BookDetailDto;
import com.lukmanhidayah.catalog.dto.BookUpdateDto;

import java.util.List;
import java.lang.Long;

public interface BookService {

	/**
	 * Find book detail by id
	 * @param bookId
	 * @return BookDetailDto
	*/
	public BookDetailDto findBookDetailById(Long bookId);

	/**
	 * Find all book detail
	 * @return List<BookDetailDto>
	*/
	public List<BookDetailDto> findAllBookDetail();

	/**
	 * Create new book
	 * @param bookCreateDto
	*/
	public void createNewBook(BookCreateDto bookCreateDto);

	/**
	 * Update book
	 * @param bookId
	 * @param bookCreateDto
	*/
	public void updateBook(Long bookId, BookUpdateDto bookCreateDto);

	/**
	 * Delete book
	 * @param bookId
	*/
	public void deleteBook(Long bookId);

}
