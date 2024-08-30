package com.lukmanhidayah.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherCreateRequestDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherListResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherUpdateRequestDto;
import com.lukmanhidayah.catalog.service.PublisherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
public class PublisherResource {

  private final PublisherService publisherService;

  @PostMapping("/v1/publisher")
  public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDto publisherCreateDto) {
    publisherService.createPublisher(publisherCreateDto);
    return ResponseEntity.created(URI.create("/v1/publisher")).build();
  }

  @PutMapping("/v1/publisher/{publisherId}")
  public ResponseEntity<Void> updatePublisher(@PathVariable("publisherId") String publisherId,
      @RequestBody PublisherUpdateRequestDto updatePublisherDto) {

    publisherService.updatePublisher(publisherId, updatePublisherDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/v1/publisher")
  public ResponseEntity<ResultPageResponseDto<PublisherListResponseDto>> findPublisherList(
      @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
      @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
      @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
      @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
      @RequestParam(name = "publisherName", required = false) String publisherName) {

    return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
  }
}
