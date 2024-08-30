package com.lukmanhidayah.catalog.util;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;

public class PaginationUtil {

  public static <T> ResultPageResponseDto<T> createResultPageDto(List<T> result, Long totalElements, Integer pages) {
    ResultPageResponseDto<T> resultPageResponseDto = new ResultPageResponseDto<>();
    resultPageResponseDto.setElements(totalElements);
    resultPageResponseDto.setPages(pages);
    resultPageResponseDto.setResult(result);
    return resultPageResponseDto;
  }

  public static Sort.Direction getSortBy(String sortBy) {
    if (sortBy.equalsIgnoreCase("asc")) {
      return Sort.Direction.ASC;
    } else {
      return Sort.Direction.DESC;
    }
  }

}
