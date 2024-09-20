package com.lukmanhidayah.catalog.service;

import java.util.List;

import com.lukmanhidayah.catalog.domain.Category;
import com.lukmanhidayah.catalog.dto.ResultPageResponseDto;
import com.lukmanhidayah.catalog.dto.category.CategoryCreateRequestDto;
import com.lukmanhidayah.catalog.dto.category.CategoryListResponseDto;

public interface CategoryService {

  public void createAndUpdateCategory(CategoryCreateRequestDto createCategoryDto);

  public ResultPageResponseDto<CategoryListResponseDto> findCategoryList(Integer pages, Integer limit, String sortBy,
      String direction, String categoryName);

  /**
   * Find categories by category code list
   */
  public List<Category> findCategories(List<String> categoryCodeList);

  /**
   * Get list
   */
  public List<CategoryListResponseDto> constructDTO(List<Category> categories);

}
