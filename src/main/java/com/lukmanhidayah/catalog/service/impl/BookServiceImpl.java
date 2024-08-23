package com.lukmanhidayah.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.domain.Book;
import com.lukmanhidayah.catalog.dto.BookCreateDto;
import com.lukmanhidayah.catalog.dto.BookDetailDto;
import com.lukmanhidayah.catalog.repository.BookRepository;
import com.lukmanhidayah.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public BookDetailDto findBookDetailById(Long bookId) {
		log.info("findBookDetailById with bookId: {}", bookId);
		Book book = bookRepository.findBookById(bookId);
		BookDetailDto dto = new BookDetailDto();
		dto.setBookId(book.getId());
		dto.setAuthorName(book.getAuthor().getName());
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailDto> findAllBookDetail() {
		List<Book> books = bookRepository.findAllBook();
		return books.stream().map((b) -> {
			BookDetailDto dto = new BookDetailDto();
			dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateDto bookCreateDto) {
		Author author = new Author();
		author.setName(bookCreateDto.getAuthorName());

		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(bookCreateDto.getBookTitle());
		book.setDescription(bookCreateDto.getDescription());

		bookRepository.save(book);
	}

}
