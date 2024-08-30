package com.lukmanhidayah.catalog.service;

import com.lukmanhidayah.catalog.dto.category.CategoryCreateRequestDto;

public interface CategoryService {

  public void createAndUpdateCategory(CategoryCreateRequestDto createCategoryDto);
}
