package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.dto.BookCreateDto;
import com.lukmanhidayah.catalog.dto.BookDetailDto;
import java.util.List;
import java.lang.Long;

public interface BookService {

	public BookDetailDto findBookDetailById(Long bookId);

	public List<BookDetailDto> findAllBookDetail();

	public void createNewBook(BookCreateDto bookCreateDto);

}
