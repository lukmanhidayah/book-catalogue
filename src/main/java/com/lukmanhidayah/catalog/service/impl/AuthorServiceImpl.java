package com.lukmanhidayah.catalog.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Author;
import com.lukmanhidayah.catalog.dto.author.AuthorCreateDto;
import com.lukmanhidayah.catalog.dto.author.AuthorResponseDto;
import com.lukmanhidayah.catalog.dto.author.AuthorUpdateRequestDto;
import com.lukmanhidayah.catalog.exception.BadRequestException;
import com.lukmanhidayah.catalog.exception.ResourceNotFoundException;
import com.lukmanhidayah.catalog.repository.AuthorRepository;
import com.lukmanhidayah.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public AuthorResponseDto findAuthorById(String id) {
    Author author = authorRepository.findBySecureId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid author id"));

    AuthorResponseDto authorResponseDto = new AuthorResponseDto();
    authorResponseDto.setAuthorName(author.getName());
    authorResponseDto.setBirthDate(author.getBirthDate().toEpochDay());

    return authorResponseDto;
  }

  @Override
  public void createAuthor(List<AuthorCreateDto> authorCreateDto) {
    List<Author> authors = authorCreateDto.stream().map((dto) -> {
      Author author = new Author();
      author.setName(dto.getAuthorName());
      author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
      author.setDeleted(false);
      return author;
    }).collect(Collectors.toList());

    authorRepository.saveAll(authors);
  }

  @Override
  public void updateAuthor(String authorId, AuthorUpdateRequestDto authorUpdateRequestDto) {
    // Find author by id
    // If author id not found then throw BadRequestException
    Author author = authorRepository.findBySecureId(authorId)
        .orElseThrow(() -> new ResourceNotFoundException("Invalid author id"));

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
  public void deleteAuthor(String id) {
    // Delete author
    // authorRepository.deleteById(id);

    // Find author by id
    // If author id not found then throw BadRequestException
    // Author author = authorRepository.findById(id)
    // .orElseThrow(() -> new BadRequestException("Invalid author id"));

    Author author = authorRepository.findBySecureId(id)
        .orElseThrow(() -> new BadRequestException("Invalid author id"));

    authorRepository.delete(author);

    // Set deleted to true
    // author.setDeleted(true);

    // Save author
    // authorRepository.save(author);
  }

  @Override
  public List<Author> findAuthors(List<String> authorIdsList) {
    List<Author> authors = authorRepository.findBySecureIdIn(authorIdsList);

    if (authors.isEmpty()) {
      throw new BadRequestException("Author cannot be found");
    }

    return authors;
  }

  @Override
  public List<AuthorResponseDto> constructDTO(List<Author> authors) {
    return authors.stream().map((author) -> {
      AuthorResponseDto dto = new AuthorResponseDto();
      dto.setAuthorName(author.getName());
      dto.setBirthDate(author.getBirthDate().toEpochDay());
      return dto;
    }).collect(Collectors.toList());
  }

}
