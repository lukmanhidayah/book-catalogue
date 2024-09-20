package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.domain.Publisher;
import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherCreateRequestDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherListResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherUpdateRequestDto;

public interface PublisherService {

  /**
   * Create a new publisher
   * 
   * @param PublisherCreateRequestDto
   */
  public void createPublisher(PublisherCreateRequestDto createPublisherDto);

  /**
   * Find publisher by publisher id
   */
  public Publisher findPublisher(String secureId);

  /**
   * Update a publisher
   * 
   * @param id
   * @param updatePublisherDto
   */
  public void updatePublisher(String secureId, PublisherUpdateRequestDto updatePublisherDto);

  /**
   * Show list response publisher
   */
  public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages, Integer limit, String sortBy,
      String direction, String publisherName);

  /**
   * Get construct DTO
   * 
   * @param publishers
   */
  public PublisherResponseDto constructDTO(Publisher publishers);
}
