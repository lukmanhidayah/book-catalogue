package com.lukmanhidayah.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lukmanhidayah.catalog.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

  public Optional<Publisher> findBySecureId(String secureId);

  public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);

  // make find by name when name container publisherName and ignore case and pagination
  public Page<Publisher> findByNameContainingIgnoreCase(String publisherName, Pageable pageable);

}
