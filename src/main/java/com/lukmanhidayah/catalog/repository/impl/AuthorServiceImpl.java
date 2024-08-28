package com.lukmanhidayah.catalog.repository.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.dto.author.AuthorCreateDto;
import com.lukmanhidayah.catalog.dto.author.AuthorResponseDto;
import com.lukmanhidayah.catalog.dto.author.AuthorUpdateRequestDto;
import com.lukmanhidayah.catalog.exception.BadRequestException;
import com.lukmanhidayah.catalog.repository.AuthorRepository;
import com.lukmanhidayah.catalog.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public AuthorResponseDto findAuthorById(Long id) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Invalid author id"));

    AuthorResponseDto authorResponseDto = new AuthorResponseDto();
    authorResponseDto.setAuthorName(author.getName());
    authorResponseDto.setBirthDate(author.getBirthDate().toEpochDay());

    return authorResponseDto;
  }

  @Override
  public void createAuthor(List<AuthorCreateDto> authorCreateDto) {
    List<Author> authors = authorCreateDto.stream().map((dto) -> {
      Author author = new Author();
      log.info("AuthorCreateDto: {}", dto);
      author.setName(dto.getAuthorName());
      author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
      return author;
    }).collect(Collectors.toList());

    authorRepository.saveAll(authors);
  }

  @Override
  public void updateAuthor(Long id, AuthorUpdateRequestDto authorUpdateRequestDto) {
    // Find author by id
    // If author id not found then throw BadRequestException
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Invalid author id"));

    // If author name is null then use the old author name
    String authorName = authorUpdateRequestDto.getAuthorName() == null ? author.getName()
        : authorUpdateRequestDto.getAuthorName();

    // If birth date is null then use the old birth date
    LocalDate birthDate = authorUpdateRequestDto.getBirthDate() == null ? author.getBirthDate()
        : LocalDate.ofEpochDay(authorUpdateRequestDto.getBirthDate());

    // Update author name and birth date
    author.setName(authorName);
    author.setBirthDate(birthDate);

    // Save author
    authorRepository.save(author);
  }

  @Override
  public void deleteAuthor(Long id) {
    // Delete author
    authorRepository.deleteById(id);

    // Find author by id
    // If author id not found then throw BadRequestException
    // Author author = authorRepository.findById(id)
    //     .orElseThrow(() -> new BadRequestException("Invalid author id"));

    // Set deleted to true
    // author.setDeleted(true);

    // Save author
    // authorRepository.save(author);
  }

}
