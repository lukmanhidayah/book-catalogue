package com.lukmanhidayah.catalog.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.domain.Book;
import com.lukmanhidayah.catalog.repository.BookRepository;
import com.lukmanhidayah.catalog.repository.impl.BookRepositoryImpl;


@Configuration
public class AppConfig {
	
	@Bean
	public Author author() {
		return new Author(1L, "Pramoedya Ananta Toer", null);
	}
	
	@Bean
	public Book book1(Author author) {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Bumi Manusia");
		book.setDescription("Bumi Manusia adalah salah satu Novel karya Pramoedya Ananta Toer");
		book.setAuthor(author);
		return book;
	}
	
	@Bean
	public Book book2(Author author) {
		Book book = new Book();
		book.setId(2L);
		book.setTitle("Arok Dedes");
		book.setDescription("Arok Dedes adalah salah satu Novel karya Pramoedya Ananta Toer");
		book.setAuthor(author);
		return book;
	}

	@Bean
	public Book book3(Author author) {
		Book book = new Book();
		book.setId(3L);
		book.setTitle("Arok Dedes");
		book.setDescription("Arok Dedes adalah salah satu Novel karya Pramoedya Ananta Toer");
		book.setAuthor(author);
		return book;
	}
	
	@Bean
	public BookRepository bookRepository(Book book1, Book book2, Book book3) {
		Map<Long, Book> bookMap = new HashMap<Long, Book>();
		bookMap.put(1L, book1);
		bookMap.put(2L, book2);
		bookMap.put(3L, book3);
		
		BookRepositoryImpl bookRepository = new BookRepositoryImpl();
		bookRepository.setBookMap(bookMap);
		
		return bookRepository;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
