package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.dto.BookCreateRequestDto;
import com.lukmanhidayah.catalog.dto.BookDetailResponseDto;
import com.lukmanhidayah.catalog.dto.BookUpdateDto;

import java.util.List;
import java.lang.Long;

public interface BookService {

	/**
	 * Find book detail by id
	 * @param bookId
	 * @return BookDetailDto
	*/
	public BookDetailResponseDto findBookDetailById(String secureId);

	/**
	 * Find all book detail
	 * @return List<BookDetailDto>
	*/
	public List<BookDetailResponseDto> findAllBookDetail();

	/**
	 * Create new book
	 * @param bookCreateDto
	*/
	public void createNewBook(BookCreateRequestDto bookCreateDto);

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
