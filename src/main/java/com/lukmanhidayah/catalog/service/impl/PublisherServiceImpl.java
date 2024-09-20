package com.lukmanhidayah.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lukmanhidayah.catalog.domain.Publisher;
import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherCreateRequestDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherListResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherResponseDto;
import com.lukmanhidayah.catalog.dto.publisher.PublisherUpdateRequestDto;
import com.lukmanhidayah.catalog.exception.BadRequestException;
import com.lukmanhidayah.catalog.repository.PublisherRepository;
import com.lukmanhidayah.catalog.service.PublisherService;
import com.lukmanhidayah.catalog.util.PaginationUtil;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PublisherServiceImpl implements PublisherService {

  /**
   * Add publisher repository
   */
  private PublisherRepository publisherRepository;

  @Override
  public void createPublisher(PublisherCreateRequestDto createPublisherDto) {
    Publisher publisher = new Publisher();
    publisher.setName(createPublisherDto.getPublisherName());
    publisher.setCompanyName(createPublisherDto.getCompanyName());
    publisher.setAddress(createPublisherDto.getAddress());

    publisherRepository.save(publisher);
  }

  @Override
  public void updatePublisher(String publisherId, PublisherUpdateRequestDto updatePublisherDto) {
    Publisher publisher = publisherRepository.findBySecureId(publisherId)
        .orElseThrow(() -> new BadRequestException("Publisher not found"));

    /*
     * Update publisher name
     * if updatePublisherDto.getPublisherName() is not null
     * otherwise, keep the old name
     */
    publisher.setName(
        updatePublisherDto.getPublisherName() == null || updatePublisherDto.getPublisherName().isBlank()
            ? publisher.getName()
            : updatePublisherDto.getPublisherName());

    /**
     * Update publisher company name
     */
    publisher.setCompanyName(
        updatePublisherDto.getCompanyName() == null || updatePublisherDto.getPublisherName().isBlank()
            ? publisher.getCompanyName()
            : updatePublisherDto.getCompanyName());
    publisher.setAddress(updatePublisherDto.getAddress());

    publisherRepository.save(publisher);
  }

  @Override
  public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages, Integer limit, String sortBy,
      String direction, String publisherName) {

    // Publisher name
    // adalah nama penerbit yang akan dicari
    // jika publisherName kosong, maka akan dicari semua penerbit
    publisherName = StringUtils.isBlank(publisherName) ? "%" : "%" + publisherName + "%";

    // Sort by direction and sort by
    // adalah class yang merepresentasikan informasi pengurutan
    Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));

    // Pageable
    // adalah interface yang merepresentasikan informasi halaman permintaan dan
    Pageable pageable = PageRequest.of(pages, limit, sort);

    Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
    List<PublisherListResponseDto> publisherListResponseDtos = pageResult.stream().map(publisher -> {
      PublisherListResponseDto publisherListResponseDto = new PublisherListResponseDto();
      publisherListResponseDto.setPublisherId(publisher.getSecureId());
      publisherListResponseDto.setPublisherName(publisher.getName());
      publisherListResponseDto.setCompanyName(publisher.getCompanyName());

      return publisherListResponseDto;
    }).collect(Collectors.toList());

    return PaginationUtil.createResultPageDto(publisherListResponseDtos, pageResult.getTotalElements(),
        pageResult.getTotalPages());
  }

  @Override
  public Publisher findPublisher(String secureId) {
    return publisherRepository.findBySecureId(secureId)
        .orElseThrow(() -> new BadRequestException("Publisher not found"));
  }

  @Override
  public PublisherResponseDto constructDTO(Publisher publishers) {
    PublisherResponseDto dto = new PublisherResponseDto();
    dto.setPublisherName(publishers.getName());
    dto.setCompanyName(publishers.getCompanyName());
    return dto;
  }

}
