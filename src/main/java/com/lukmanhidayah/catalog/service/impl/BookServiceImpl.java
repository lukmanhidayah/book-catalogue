package com.lukmanhidayah.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.domain.Book;
import com.lukmanhidayah.catalog.domain.Category;
import com.lukmanhidayah.catalog.domain.Publisher;
import com.lukmanhidayah.catalog.dto.BookCreateRequestDto;
import com.lukmanhidayah.catalog.dto.BookDetailResponseDto;
import com.lukmanhidayah.catalog.dto.BookUpdateDto;
import com.lukmanhidayah.catalog.repository.BookRepository;
import com.lukmanhidayah.catalog.service.AuthorService;
import com.lukmanhidayah.catalog.service.BookService;
import com.lukmanhidayah.catalog.service.CategoryService;
import com.lukmanhidayah.catalog.service.PublisherService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	private final AuthorService authorService;

	private final CategoryService categoryService;

	private final PublisherService publisherService;

	@Override
	public BookDetailResponseDto findBookDetailById(String bookId) {
		log.info("findBookDetailById with bookId: {}", bookId);
		Book book = bookRepository.findBySecureId(bookId)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

		BookDetailResponseDto dto = new BookDetailResponseDto();
		dto.setBookId(book.getSecureId());
		dto.setCategories(categoryService.constructDTO(book.getCategories()));
		dto.setAuthors(authorService.constructDTO(book.getAuthors()));
		dto.setPublisher(publisherService.constructDTO(book.getPublisher()));
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailResponseDto> findAllBookDetail() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map((b) -> {
			BookDetailResponseDto dto = new BookDetailResponseDto();
			// dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getSecureId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateRequestDto dtos) {
		log.info("createNewBook with bookCreateDto: {}", dtos.toString());
		List<Author> authors = authorService.findAuthors(dtos.getAuthorIds());

		List<Category> categories = categoryService.findCategories(dtos.getCategoryList());

		Publisher publisher = publisherService.findPublisher(dtos.getPublisherId());

		Book book = new Book();
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublisher(publisher);
		book.setTitle(dtos.getBookTitle());
		book.setDescription(dtos.getDescription());

		bookRepository.save(book);
	}

	@Override
	public void updateBook(Long bookId, BookUpdateDto bookUpdateDto) {

		// get book
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

		log.info(book.toString(), bookUpdateDto.toString());
		// update book
		book.setTitle(bookUpdateDto.getBookTitle());
		book.setDescription(bookUpdateDto.getDescription());

		// save book
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
	}

}
