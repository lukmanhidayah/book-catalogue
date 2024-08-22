package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.dto.BookDetailDto;
import java.util.List;

public interface BookService {
	
	public BookDetailDto findBookDetailById(Long bookId);

	public List<BookDetailDto> findAllBookDetail();

}
